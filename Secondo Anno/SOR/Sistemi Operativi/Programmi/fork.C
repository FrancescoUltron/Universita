#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h> //Libreria specifica per Linux
//Usata per tutte le funzioni che ci interessa usare (fork,execv,...)


int main(int argc, char* argv[]){
    int id = fork(); //Figlio processo nasce e comincia ad eseguire le altre funzioni
    //quindi abbiamo due processi contemporaneamente

    if(id != 0){
        fork();
        //se usiamo fork n volte avremo 2^n processi contemporantei
        //se vogliamo creare 3 processi possiamo fare un controllo sui processi per evitare il fork
    }


    printf("Hello World\n");


    
    //printf("Hello World from id: %d\n", pid);
    //Siccome abbiamo due processi che eseguono lo stesso programma
    //printf sarà eseguito due volte da due proessi diversi

    //if (pid == 0){
    //    printf("Hello from child process");
    //} else {
    //    printf("Hello from the main process"):
    //}


    return 0;
}

