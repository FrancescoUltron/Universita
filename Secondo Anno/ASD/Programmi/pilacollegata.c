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

nodo* creaNodo(int nuovo_elemento){
    nodo* nuovoNodo = (nodo*)malloc(sizeof(nodo));
    nuovoNodo->elemento = nuovo_elemento;
    nuovoNodo->prossimo = NULL;
    return nuovoNodo;
}

typedef struct pila{
    nodo* testa;
} pila;

void creaPila(pila* pila){
    pila->testa = NULL;
}

void push(pila* pila, int nuovo_elemento){
    nodo* nuovo_nodo = creaNodo(nuovo_elemento);
    if(!nuovo_nodo){
        printf("Errore nell'allocazione della memoria\n");
        return;
    }
    nuovo_nodo->prossimo = pila->testa;
    pila->testa = nuovo_nodo;
}

int isEmpty(pila* pila){
    return pila->testa == NULL;
}

void pop(pila *pila){
    if(isEmpty(pila)){
        printf("La pila è vuota\n");
        return;
    } else {
        nodo* temp = pila->testa;
        pila->testa = pila->testa->prossimo;
        free(temp);
    }
}

int top(pila* pila){
    if(!isEmpty(pila)){
        return pila->testa->elemento;
    } else {
        printf("La pila e' vouta\n");
        return -1;
    }
}


int main(int argc, char* argv[]){

    // Creating a pila
    pila pila;
    creaPila(&pila);

    // Push elements onto the pila
    push(&pila, 11);
    push(&pila, 22);
    push(&pila, 33);
    push(&pila, 44);

    // Print top element of the pila
    printf("Top element is %d\n", top(&pila));

  
      // removing two elemements from the top
    printf("Removing two elements...\n");
    pop(&pila);
    pop(&pila);

    // Print top element of the pila
    printf("Top element is %d\n", top(&pila));

    return 0;


}
