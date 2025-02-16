/*
Un processo padre genera due processi figli.
• Il primo processo figlio invia al padre un numero casuale da 0 a 100.
• Il padre legge questo numero, lo moltiplica per un k causale e lo manda al secondo figlio.
• Il secondo figlio legge il numero inviato dal padre e lo stampa a video
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

int main(){

    int fd1[2], fd2[2]; //0 - Lettura    1 - Scrittura
    pid_t P1, P2; //Processi figli 
    int numero, numero2; 
    int k;

    if(pipe(fd1) < 0 || pipe(fd2) < 0){
        perror("Erroren nella creazione della pipe");
        return 1;
    }

    P1 = fork();
    if(P1 < 0){
        perror("Errore nella creazione del primo processo figlio\n");
        exit(1);
    }

    if(P1 == 0){
        close(fd1[0]);
        srand(time(NULL) ^ getpid()); //Genreratore randomico basato sul seed del getpid 

        numero = rand() % 101;

        if(write(fd1[1], &numero, sizeof(numero)) < 0){
            perror("Errore nella scrittura sulla pipe da parte del primo processo\n");
            exit(1);
        }

        usleep(100000);
        

        close(fd1[0]);
        exit(0);
    }

    P2 = fork();
    if(P2 < 0){
        perror("Errore nella creazione del secondo processo figlio\n");
        exit(1);
    }

    if(P2 == 0){
        close(fd2[1]);

        //usleep(100000);

        if(read(fd2[0], &numero2, sizeof(numero2)) < 0){
            perror("Errore lettura della pipe da parte del secondo processo\n");
            exit(1);
        }

        printf("Sono il secondo processo figlio e' ho ricevuto il seguente numero: %d\n", numero2);
        exit(0);
    }


    close(fd1[1]);
    close(fd2[0]);

    if(read(fd1[0], &numero, sizeof(numero)) < 0){
        perror("Errore nella lettura della pipe da parte del padre\n");
        return 1;
    }

    srand(time(NULL) ^ getpid());

    k = rand() % 5;

    numero2 = numero * k;
    printf("Padre ha ricevuto %d e lo ha moltiplicato per %d\n", numero, k);

    if(write(fd2[1], &numero2, sizeof(numero2)) < 0){
        perror("Errore nella scrittura sulla pipe da parte del processo padre\n");
        return 1;
    }

    close(fd1[0]);
    close(fd2[1]);


}