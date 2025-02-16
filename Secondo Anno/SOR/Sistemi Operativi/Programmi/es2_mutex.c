/*Si scriva un programma con tre thread che risolvono il seguente problema: Un buffer di n elementi inizializzato
con a -1 viene riempito nel seguente modo:
• Il primo thread aggiunge nelle posizioni pari del buffer un numero casuale da 0 a 100.
• Il secondo thread aggiunge nelle posizioni dispari del buffer un casuale da 100 a 200.
• Il terzo thread somma gli elementi e modifica il buffer nel seguente modo:
buff[0] = buff[0]; buff[1] = buff[1] + buff[0]; buff[2] = buff[1] + buff[2]. Si proponga
una soluzione di mutua esclusione.*/

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <pthread.h>
#include <time.h>

#define N 10
int buffer[N];

pthread_mutex_t mutex;

void* primo_thread(void *arg){

    srand(time(NULL));
    int numero;

    for(int i = 0; i < N; i = i + 2){
        pthread_mutex_lock(&mutex);
        numero = rand() % 100;
        buffer[i] = numero;
        printf("Il primo thread ha scritto %d nella cella %d\n", numero, i);
        pthread_mutex_unlock(&mutex);
        sleep(3);
    }
}



void* secondo_thread(void *args){

    srand(time(NULL) - 100);
    int numero2;

    for(int i = 1; i < N; i = i + 2){
        pthread_mutex_lock(&mutex);
        numero2 = rand() % 100 + 100;
        buffer[i] = numero2;
        printf("Il secondo thread ha scritto %d nella cella %d\n", numero2, i);
        pthread_mutex_unlock(&mutex);
        sleep(3);
    }

}

void* terzo_thread(void *args){
    for(int i = 1;i < N; i++){
        pthread_mutex_unlock(&mutex);

        buffer[i] = buffer[i] + buffer[i-1];

        pthread_mutex_unlock(&mutex);
        sleep(3);
    }
    pthread_exit(NULL);
    return 0;
}

int main(int argc, char* argv[]){

    for(int i = 0; i < N;i++){
        buffer[i] = -1;
    }

    pthread_mutex_init(&mutex, NULL);

    pthread_create(&primo_thread, NULL, primo_thread, NULL);
    pthread_create(&secondo_thread, NULL, secondo_thread, NULL);

    pthread_join(primo_thread, NULL);
    pthread_join(secondo_thread, NULL);

    pthread_create(&terzo_thread, NULL, terzo_thread, NULL);

    printf("Il buffer modificato è il seguente: \n");
    for(int i = 0; i < N; i++){
        printf("Posizione %d: %d\n", i, buffer[i]);
    }
    
    pthread_join(terzo_thread, NULL);
    pthread_mutex_destroy(&mutex);

}