#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>
#include <time.h>
#include <unistd.h>
#include <pthread.h> 

#define THREAD_NUM 4

sem_t semaphore;


void* routine(void* args){
    sem_wait(&semaphore);
    sleep(1);
    printf("Thread %d\n", *(int*)args);
    sem_post(&semaphore);
    free(args); 
}

int main(int argc, char* argv[]){
    pthread_t th[THREAD_NUM];
    int i; 
    sem_init(&semaphore, 0, 1);


    for(i = 0; i < THREAD_NUM; i++){
        int* a = malloc(sizeof(int));
        *a = i;
        if(pthread_create(&th[i], NULL, &routine, a) != 0){
            perror("Errore nella creazione del thread\n");
        }
    }

    for(i = 0; i < THREAD_NUM; i++){
        if(pthread_join(th[i], NULL) != 0){
            perror("Errore nello join dei thread\n");
        }
    }

    sem_destroy(&semaphore);
    return 0;
}