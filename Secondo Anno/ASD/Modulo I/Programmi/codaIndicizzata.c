//Progettare una struttura dati indicizzata che implementi
//il tipo di dato Coda. Le operazioni devo
//avere complessità temporale costante.

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

int coda[256]; //Dimensione della coda
int count = 0;

bool isEmpty(){
    if(count == 0){
        return true;
    } else {
        return false;
    }
}

void enqueue(int x){
    if(count == 256){
        fprintf(stderr, "Non ci sta più spazio nella pila\n");
        return;
    }
    coda[count] = x;
    count++;
}

int dequeue(){
    int result = coda[0];
    for(int i = 0;i < count - 1;i++){
        coda[i] = coda[i+1];
    }
    count--;
    return result;
}

int first(){
    return coda[0];
}

int main(int arcg, char* argv){
    return 0;
}