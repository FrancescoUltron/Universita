/*  Generare due processi figli che comunicano con il padre.
• Uno dei processi genera numeri casuali [0-100] ed invia al padre solo i numeri pari.
• L’altro processo genera numeri casuali [0-100] ed invia al padre solo i numeri dispari.
• Il padre fa la loro somma e quando la somma > 190, termina l’esecuzione dei figli.
*/

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <pthread.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <string.h>
#include <sys/types.h>
#include <signal.h>


//Handlee per il segnale SIGINT: non termina il processo ma mostra un messaggio
void signal_handler(int signal){
    printf("Sono il processo %d: Interruzione disabilitata!!!\n", getpid());
}

int main(int argc, char* argv[]){

    int fd1[2], fd2[2]; //Pipe di comunicazione P1 --> padre e P2 --> padre
    pid_t P1, P2;
    int r1, r2; // Valori generati dai processi
    int sum;


    signal(SIGINT, signal_handler);

    if(pipe(fd1) < 0 || pipe(fd2) < 0){
        perror("Errore nella creazione delle pipe");
        return 1;
    }

    P1 = fork();
    if(P1 < 0){
        perror("Errore nella creazione del primo processo figlio\n");
        exit(1);
    }

    if(P1 == 0){
        close(fd1[0]); //Chiudo l'estremità di lettura
        srand(time(NULL) ^ getpid()); //Genreratore randomico basato sul seed del getpid

        while(1)
        {
            r1 = rand() % 101;

            if(r1%2 == 1){
                if(write(fd1[1], &r1, sizeof(r1)) < 0){

                    perror("Problema nella scrittura sulla pipe da parte del primo processo\n");
                    break;
                }
            }

            usleep(100000);

        }
        
        close(fd1[1]);
        exit(0);
    }


    P2 = fork();
    if(P2 < 0){
        perror("Errore nella creazine del secondo processo\n");
        exit(1);
    }

    if(P2 == 0){
        close(fd2[0]);
        srand(time(NULL) ^ getpid());

        while(1){

            r2 = rand() % 101;

            if(r2%2 == 0){
                if(write(fd2[1],&r2,sizeof(r2)) < 0){

                    perror("Errore nella scrittura sulla pipe da parte del secondo processo\n");
                    break;
                }
            }

            usleep(100000);

        }

        close(fd2[1]);
        exit(0);
    }

    close(fd1[1]);
    close(fd2[1]);

    while(1){

        if(read(fd1[0], &r1, sizeof(r1)) <= 0){
            perror("Errore nella lettura della pipe del primo processo\n");
            break;
        }

        if(read(fd2[0], &r2, sizeof(r2)) <= 0){
            perror("Errore nella lettura della pipe del secondo processo\n");
            break;
        }

        sum = r1+r2;
        printf("Padre riceve: P1:%d, P2:%d, somma:%d\n", r1, r2, sum);

        if(sum > 190){
            printf("Somma maggiore di 190. Terminazione dei processi figli\n");
            kill(P1, SIGTERM);
            kill(P2, SIGTERM);
            break;
        }
        usleep(100000);
    }

    close(fd1[0]);
    close(fd2[0]);

    //Attendo terminazione dei figli
    waitpid(P1,NULL, 0);
    waitpid(P2,NULL, 0);
    
    return 0;
}