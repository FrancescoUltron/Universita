/*
Generare due processi figli che comunicano con il padre.
• Uno dei processi genera numeri casuali [0-50] ed invia al padre solo i numeri multipli di 3.
• L’altro processo genera numeri casuali [51-100] ed invia al padre solo i numeri multipli di 2.
• Il padre stampa i numeri ricevuti ed esegue la loro somma quando la somma > 130.
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

    pid_t P1, P2;
    int fd1[2], fd2[2]; //0 - Lettura  1 - Scrittura
    int r1, r2;
    int sum;


    signal(SIGINT, signal_handler);  

    if(pipe(fd1) < 0 || pipe(fd2) < 0){
        perror("Errore nella creazione delle pipe\n");
        return 1;
    }


    P1 = fork();
    if(P1 < 0){
        perror("Errore nella creazione del primo processo figlio\n");
        return 1;
    }

    if(P1 == 0){

        close(fd1[0]);
        srand(time(NULL)^getpid());

        while(1)
        {
            r1 = rand() % 50 + 51;

            if(r1%2 == 0){
                if(write(fd1[1], &r1, sizeof(r1)) < 0){
                    printf("Errore nella scrittura sulla pipe da parte del primo processo\n");
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
        perror("Errore nella creazione del secondo processo\n");
        return 1;
    }

    if(P2 == 0){
        close(fd2[0]);
        srand(time(NULL)^getpid());

        while(1){

            r2 = rand() % 51;

            if(r2%3 == 0){
                if(write(fd2[1], &r2, sizeof(r2)) < 0){
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

        if(read(fd1[0], &r1, sizeof(r1)) < 0){
            perror("Errore sulla lettura della pipe da parte del processo padre\n");
            break;
        }

        if(read(fd2[0], &r2, sizeof(r2)) < 0){
            perror("Errore sulla lettura della pipe da parte del processo padre\n");
            break;
        }

        sum = r1 + r2;
        printf("Il processo padre ha ricevuto i seguenti numeri: P1: %d P2: %d\n", r1, r2);

        if(sum > 130){
            printf("La somma dei numeri ricevuti è maggiore di 130, terminazione dei processi\n");
            kill(P1, SIGTERM);
            kill(P2, SIGTERM);
            break;
        }

        usleep(100000);
    }

    close(fd1[0]);
    close(fd2[0]);

    waitpid(P1, NULL, 0);
    waitpid(P2, NULL, 0);

    return 0;


}