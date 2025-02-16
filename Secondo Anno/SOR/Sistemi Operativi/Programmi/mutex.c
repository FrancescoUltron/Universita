/*Scrivere un programma C che segue le seguenti specifiche. 
Il processo eseguito, inizialmente crea un buffer come array di 11 numeri interi, inizializzati a zero. 
In seguito genera tre thread utilizzando le librerie POSIX secondo le seguenti specifiche:

• Il primo thread sceglie casualmente una cella del buffer e vi scrive il numero +1, qualsiasi sia il valore
presente nella cella.

• Il secondo thread sceglie casualmente una cella del buffer e vi scrive il numero -1, qualsiasi sia il valore
presente nella cella.

• Il terzo thread controlla se tutte le celle del buffer sono state inizializzate. In caso positivo, determina
se il numero di celle contententi un valore pari a +1 è maggiore di quelle con -1 e termina tutti e tre i
thread. 

Mentre un thread ha accesso al buffer, nessun altro thread deve accedervi. 
Una volta che un thread ha acceduto in lettura o scrittura al buffer, deve attendere un numero di secondi random tra 0 e
3*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h> //Per mutex e thread
#include <time.h>

#define N 11
int BUFFER[N] = {0};
pthread_mutex_t mutex;
int fatto = 0;

void *primo_thread(){

    srand(time(NULL));

    while(fatto == 0){
        pthread_mutex_lock(&mutex);

        int indice = rand() % N;

        BUFFER[indice] = 1;
        printf("Il primo thread ha scritto 1 nella posizione: %d\n", indice);

        pthread_mutex_unlock(&mutex);
        sleep(rand() % 3);
    }

    return NULL;
    
}


void *secondo_thread(){
    
    srand(time(NULL));
    
    while(fatto == 0){
        pthread_mutex_lock(&mutex);

        int indice = rand() % N;

        BUFFER[indice] = -1;
        printf("Il secondo thread ha scritto -1 nella posizione: %d\n", indice);

        pthread_mutex_unlock(&mutex);
        sleep(rand() % 3);
    }

    return NULL;
}

void *terzo_thread(){
    while(1){

        printf("Sono entrato nel terzo thread\n");

        pthread_mutex_lock(&mutex);

        int cont_pos = 0;
        int cont_neg = 0;
        int cont_ini = 0;

        for(int i = 0; i < N; i++){

            if(BUFFER[i] != 0){

                cont_ini++;

                if(BUFFER[i] == 1){
                    cont_pos++;
                } else {
                    cont_neg++;
                }

            }
        }

        if(cont_ini == N){
            printf("+1: %d, -1: %d\n", cont_pos, cont_neg);
            fatto = 1;
            pthread_mutex_unlock(&mutex);
            return NULL;
        }


        pthread_mutex_unlock(&mutex);
        sleep(rand() % 3);
    }
}

int main(){

    pthread_t t1, t2, t3;
    pthread_mutex_init(&mutex, NULL);

    pthread_create(&t1, NULL, primo_thread, NULL);
    pthread_create(&t2, NULL, secondo_thread, NULL);
    pthread_create(&t3, NULL, terzo_thread, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    pthread_join(t3, NULL);

    pthread_mutex_destroy(&mutex);

    return 0; 
}