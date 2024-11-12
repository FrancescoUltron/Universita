#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <sys/stat.h>
#include <sys/types.h>
#include <errno.h>
#include <fcntl.h> //Open

int main(int argc, char* argv[]){

    //Possiamo creare dei file chiamati fifo
    //Che ci permettono di implementare delle pipes
    //E di trattarle come dei file

    
    //Creiamo un file fifo sulla directory corrente
    //Il file è accessibile a tutti (0777)

    if(mkfifo("myfifo1", 0777) == -1){
        if(errno != EEXIST){ //Fa la condizione solo se il file non esiste e si è verificato un problema
            printf("Errore nella creazione della fifo\n");
            return 1;
        }
    }

    //Possiamo scrivere, leggere e aprirle le fifo
    //Dobbiamo prima di tutto aprirle
    printf("Opening...\n");
    int fd = open("myfifo1",O_WRONLY); //Aprire in scrittura
    //Prendiamo il file descriptor
    if(fd == -1){
        return 3;
    }


    printf("Opened\n");
    int x = 97; //97 = 'a' in ASCII

    if(write(fd, &x, sizeof(x)) == -1){
        printf("Errore scrittura della pipe");
        return 2;
    }
    printf("Written\n");
    close(fd);
    printf("Closed\n");

    return 0;
}

//La funzione open aspetterà finchè un altro processo legge
//Non legge/scrive il processo pendente
//Questa cosa si può fare anche direttamente da terminale

//Per far si che le fifo funzionino dobbiamo
//aprire entrambi i 'lati'

//La lettura aspetta la scrittura e viceversa