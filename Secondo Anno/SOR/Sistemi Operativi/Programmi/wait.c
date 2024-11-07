#include<string.h>
#include<stdlib.h>
#include<unistd.h>
#include<time.h>
#include<stdio.h>
#include<sys/wait.h>


int main(int argc, char* argv[]){
    int id = fork();
    int n;

    if(id == 0){
        //child process
        n = 1;
    }else{
        //main process
        n = 6;
    }

    if(id != 0){
    //Se stiamo nel processo principale aspettiamo che finisca l'esecuzione il figlio
    wait(NULL);//Attende la terminazione del processo figlio
    }

    for(int i = n; i < n + 5;i++){
        printf("%d ",i);
        fflush(stdout);
    }

    if(id != 0){
        printf("\n");
    }

    return 0;
}