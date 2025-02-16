/*
Sei thread, un scrittore e 5 lettori. 
Lo scrittore scrive su un buffer numeri dispari da 0 a 50 nelle posizioni pari e numeri pari da 50 a 100 nelle posizioni dispari. 
I lettori leggono coppie di numeri (paro, disparo), li somma e li stampa.
*/

#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>
#include<pthread.h>
#include<time.h>
#include<semaphore.h>

#define NUM_THREAD 6
#define N 10

int buffer[N];
int count = 0;

//semafori
sem_t semFull;
sem_t semEmpty;

//mutex
pthread_mutex_t mutexBuffer;

void* scrittore(void *args){
    while(1){
        int numero = rand() % 100 + 1;
        int pos_pari = rand() %5 + 5;
        int pos_dispari = rand() %5 + 1;

        sem_wait(&semEmpty);
        pthread_mutex_lock(&mutexBuffer);

        if(numero%2 == 0){
            printf("PRODUTTORE: Scritto il numero %d nella posizione %d\n", numero, pos_pari);
            buffer[pos_pari] = numero;
        } else {
            printf("PRODUTTORE: Scritto il numero %d nella posizione %d\n", numero, pos_dispari);
            buffer[pos_dispari] = numero;
        }

        count++;

        pthread_mutex_unlock(&mutexBuffer);
        sem_post(&semFull);

        sleep(1);
    }
    return NULL;
}

void *lettore(void *args){
    while(1){
        int pos_pari = rand() %5 + 5;
        int pos_dispari = rand() %5 + 1;
        int a;
        int b; 

        sem_wait(&semFull);
        pthread_mutex_lock(&mutexBuffer);

        a = buffer[pos_pari];
        b = buffer[pos_dispari];

        if(a != 0 && b != 0){
            int somma = a+b;
            printf("LETTORE: Preso i numeri %d (Pari) e %d (Dispari), la loro somma è: %d\n",a,b,somma);
        }

        pthread_mutex_unlock(&mutexBuffer);
        sem_post(&semEmpty);

        sleep(1);
    }
    return NULL;
}

int main(int argc, char* argv[]){
    srand(time(NULL));
    pthread_t th[NUM_THREAD];

    pthread_mutex_init(&mutexBuffer, NULL);
    sem_init(&semEmpty, 0, N);
    sem_init(&semFull, 0, 0);

    if(pthread_create(&th[0], NULL, &scrittore, NULL) != 0){
        perror("Errore nella creazione del thread scrittore\n");
    }

    for(int i = 0;i < NUM_THREAD; i++){
        if(pthread_create(&th[i], NULL, &lettore, NULL) != 0){
            perror("Errore nella lettura di uno dei processi lettori\n");
        }
    }


    //Serve per terminare i thread in questo caso non terminano perché ci sta un ciclo infinito
    for(int i = 0; i < NUM_THREAD; i++){
        if(pthread_join(th[i], NULL) != 0){
            perror("Errore nella terminazione dei thread\n");
        }
    }

    sem_destroy(&semEmpty);
    sem_destroy(&semFull);
    pthread_mutex_destroy(&mutexBuffer);

    return 0;

}

