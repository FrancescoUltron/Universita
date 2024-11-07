#include<string.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/wait.h>

//i processID o PID sono degli identificatori associati ai processi
//Ogni processo ha un PID

int main(int argc, char* argv[]){
    int pid = fork();

    if(pid == 0){ //Processo figlio aspetta
        sleep(1);
    }

    //Con questa condizione si crea il problema dei processi orfani
    //Il processo figlio finisce dopo l'esecuzione del padre
    //E quindi gli viene assegnato un nuovo genitore
    //Per questo motivo se eseguiamo otteniamo un ID del processo padre diverso

    //Nella mia distribuzione di Linux è 1675

    //Dobbiamo sempre aspettare il processo figlio

    printf("Current ID: %d, Parent ID: %d\n", getpid(), getppid()); 
    //getpid() prende ID del processo corrente
    //getppid() prende ID del processo genitore

    int res = wait(NULL);

    if(res == -1){
        printf("No child to wait\n");
    } else{
        printf("%d finished execution\n", res);
    }; 

    return 0;
}