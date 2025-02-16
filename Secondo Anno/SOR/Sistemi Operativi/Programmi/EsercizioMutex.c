/*
Scrivere un programma in C con tre thread che operano su due array di dimensione N inizialmente a 0.
• Il primo thread scrive in un array A numeri casuali tra 1 e 150, scrivendo un numero per volta in posizioni
randomiche.

• Il secondo thread scrive in un array B numeri cassuali tra 150 e 300, scrivendo un numero per volta in
posizioni randomiche.

• Il terzo thread controlla se entrambi gli array sono stati inizializzati, in caso affermativo calcola il massimo
in A e in B, calcola il minimo in A e in B. Infine determina il max{max(A), max(B)} e il min{min(A), min(B)}.
*/

#include<stdlib.h>
#include<stdio.h>
#include<time.h>
#include<unistd.h>
#include<pthread.h>

#define N 10

int A[N] = {0};
int B[N] = {0};

int init_a = 0;
int init_b = 0;

pthread_mutex_t mutex;
pthread_cond_t cond;

void *primo_thread(void *arg){
    

    while(init_a < N){

        int indicea = rand() % N;
        int numero = (rand() % 150) + 1;

        pthread_mutex_lock(&mutex);
        
        if(A[indicea] == 0){
            A[indicea] = numero;
            init_a++;
            printf("Il primo THREAD ha scritto il numero: %d nella posizione: %d\n", A[indicea], indicea);
        }

        pthread_mutex_unlock(&mutex);

    }
    pthread_cond_signal(&cond);
    return NULL;
}

void *secondo_thread(void *arg){
    
    while(init_b < N){

        int indiceb = rand() % N;
        int numero = (rand() % 151) + 150;
    

        pthread_mutex_lock(&mutex);
        
        if(B[indiceb] == 0){
            B[indiceb] = numero;
            init_b++;
            printf("Il secondo THREAD ha scritto il numero: %d nella posizione: %d\n", B[indiceb], indiceb);
        }

        pthread_mutex_unlock(&mutex);

    }
    
    pthread_cond_signal(&cond);
    return NULL;
}

void *terzo_thread(void *arg){
 
    pthread_mutex_lock(&mutex);
    while(init_a < N || init_b < N){
        pthread_cond_wait(&cond, &mutex);
    }

    int min_A = A[0];
    int min_B = B[0];
    int max_A = A[0];
    int max_B = B[0];

    for(int i = 0; i < N; i++){
        if(A[i] > max_A){
            max_A = A[i];
        } 

        if(A[i] < min_A){
            min_A = A[i];
        }

        if(B[i] > max_B){
            max_B = B[i];
        } 

        if(A[i] < min_A){
            min_B = B[i];
        }

    }


    printf("Il massimo dell'array A e': %d, Il massimo dell'array B e': %d\n", max_A, max_B);
    printf("Il minimo dell'array A e': %d, Il minimo dell'array B e': %d\n", min_A, min_B);

    if(max_A >= max_B){
        printf("Il massimo tra i due array e': %d", max_A);
    } else {
        printf("Il massimo tra i due array e': %d", max_B);
    }

    if(min_A <= min_B){
        printf("Il minimo tra i due array e': %d", min_A);
    } else {
        printf("Il minimo tra i due array e': %d", min_B);
    }

    pthread_mutex_unlock(&mutex);
    return NULL;
}

int main(){
    
    srand(time(NULL));
    pthread_t t1,t2,t3;


    pthread_mutex_init(&mutex, NULL);
    pthread_cond_init(&cond, NULL);

    pthread_create(&t1, NULL, primo_thread, NULL);
    pthread_create(&t2, NULL, secondo_thread, NULL);
    pthread_create(&t3, NULL, terzo_thread, NULL);


    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    pthread_join(t3, NULL);
    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&cond);
}