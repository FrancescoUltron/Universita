//Progettare una struttura dati collegata che implementi
//il tipo di dato Pila. Le operazioni devo
//avere complessità temporale costante.

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

typedef struct nodo{
    int elemento;
    struct nodo* prossimo;
}nodo;

nodo* CreaNodo(int nuovo_dato){
    nodo* nuovo_nodo = (nodo*)malloc(sizeof(nodo));
    nuovo_nodo->elemento = nuovo_dato;
    nuovo_nodo->prossimo = NULL;
    return nuovo_nodo;
}

typedef struct coda{
    nodo *testa, *coda;
}coda;

coda* CreaCoda(){
    coda* q = (coda*)malloc(sizeof(coda));
    q->testa = q->coda = NULL;
    return q;
}

int isEmpty(coda* q){
    if(q->testa == NULL && q->coda == NULL){
        return 1;
    }
    return 0;
}

void enqueue(coda *q, int nuovo_dato){
    nodo *nuovo_nodo = CreaNodo(nuovo_dato);

    if(q->coda == NULL){
        q->testa = q->coda = nuovo_nodo;
        return;
    }

    q->coda->prossimo = nuovo_nodo;
    q->coda = nuovo_nodo;
}

int getFront(coda* q)
{

    // Checking if the queue is empty
    if (isEmpty(q)) {
        printf("La coda e' vuota\n");
        return -1;
    }
    return q->testa->elemento;
}

int main(int argc, char* argv[]){
    return 0;
}

