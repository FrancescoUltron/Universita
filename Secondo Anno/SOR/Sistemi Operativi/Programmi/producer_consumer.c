/*
Abbiamo un buffer che viene riempito da un Produttore
Mentre invece viene svuotaato da un Consumatore

Dobbiamo creare un meccanismo che ci permetta di  fare l'accesso al buffer.

Le sfude principali sono quelle di:

1)Gestire uno spazio di memoria condiviso;
2)Evitare che i produttori riempiano il buffer oltre allo spazio disponibile;
3)Evitare che i consumatori prendano un oggetto da un buffer vuoto;
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>
#include <semaphore.h>

#define N 10 
#define THREAD_NUM 2 //Funziona pure con più di 2 thread

//Semaforo
sem_t semFull;
sem_t semEmpty;

//Mutex
pthread_mutex_t mutexBuffer;

int buffer[N];
int count = 0;

void* producer(void* args){
    while(1){ 
        //produci
        int x = rand() % 100;
        sleep(1);

        //Aggiungi al buffer
        
        sem_wait(&semEmpty); 
        pthread_mutex_lock(&mutexBuffer);

        
        buffer[count] = x;
        count++;


        pthread_mutex_unlock(&mutexBuffer);
        sem_post(&semFull);
    }
}


void* consumer(void* args){
    while(1){

        int y;
        //Rimuovi dal buffer
        sem_wait(&semFull);
        pthread_mutex_lock(&mutexBuffer);
        
        
        y = buffer[count-1];
        count--;
        
        //Consuma
        printf("Preso il numero %d\n", y);

        pthread_mutex_unlock(&mutexBuffer);
        sem_post(&semEmpty);

        sleep(1);
    }
}


int main(int argc, char* argv[]){

    srand(time(NULL));
    pthread_t th[2];
    pthread_mutex_init(&mutexBuffer, NULL);
    sem_init(&semEmpty, 0, 10);
    sem_init(&semFull, 0, 0);

    int i; 
    for(i = 0; i < THREAD_NUM; i++)
    {
        if(i%2 == 0){
            if(pthread_create(&th[i], NULL, &producer, NULL) != 0){
                perror("Errore nella creazione del thread\n");
            }
        } else {
            if(pthread_create(&th[i], NULL, &consumer, NULL) != 0){
                perror("Errore nella creazione del thread\n");
            }
        }
    }

    for(i = 0; i < THREAD_NUM; i++){
        if(pthread_join(th[i], NULL) != 0){
            perror("Errore nel fare lo join dei thread\n");
        }
    }
    
    sem_destroy(&semEmpty);
    sem_destroy(&semFull);
    pthread_mutex_destroy(&mutexBuffer);

    return 0;
}