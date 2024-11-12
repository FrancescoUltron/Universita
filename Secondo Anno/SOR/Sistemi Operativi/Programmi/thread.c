#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <pthread.h> //Thread

void* routine(){
    printf("Test form threads\n");
    sleep(3);
    printf("Ending thread\n");
}


int main(int argc, char* argv[]){
    pthread_t t1, t2;
    if(pthread_create(&t1, NULL, &routine, NULL) != 0){
        return 1; //errore
    };
    
    if(pthread_create(&t2, NULL, &routine, NULL) != 0){
        return 2;
    }; //esecuzione parallela di due thread

    if(pthread_join(t1, NULL) != 0){
        return 3;
    }; //wait per thread
    
    if(pthread_join(t2, NULL) != 0){
        return 4;
    }; //wait per thread
    
    
    return 0;
}

//La funzione routine è come se venisse eseguita due volte
//in realtà sono due thread che la eseguono contemporaneamente