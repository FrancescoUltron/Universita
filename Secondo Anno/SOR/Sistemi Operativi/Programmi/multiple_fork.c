#include<string.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/wait.h>
#include<errno.h>

int main(int argc, char* argv[]){
    int pid1 = fork(); //2 processi
    int pid2 = fork(); //4 processi 

    if(pid1 == 0){ //se vera allora siamo il figlio di un processo padre
        if(pid2 == 0){ //se vera allora siamo il figlio del processo figlio
            printf("Io sono il processo D\n");
        }
        else{
            printf("Io sono il processo C\n");
        }
    }
    else{
        if(pid2 == 0){ //Siamo l'altra foglia dell'albero
            printf("Io sono il processo B\n");
        }
        else{
            printf("Io sono il processo A\n");
        }
    }

    while(wait(NULL)!= -1 || errno != ECHILD){ //Ci permette di aspettare tutti i figli
        printf("Aspettando un figlio per finire il programma\n");
    }

    return 0; 
}