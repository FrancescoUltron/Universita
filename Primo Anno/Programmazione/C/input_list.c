#include <stdio.h>
#include <stdlib.h>

// Definizione del nodo
typedef struct nodo {
    int valore;
    struct nodo *prec;
    struct nodo *succ;
} nodo;

// Funzione per inserire nodi in coda alla lista
nodo* input_list() {
    int value;
    int result;

    printf("Inserisci un numero intero da mettere nella lista (o un carattere non numerico per terminare): ");
    result = scanf("%d", &value);

    nodo *testa = NULL; // Inizializza la testa della lista

    while(result == 1) {
        nodo *nuovo_nodo = (nodo*)malloc(sizeof(nodo)); // Alloca dinamicamente un nuovo nodo
        if (nuovo_nodo == NULL) {
            printf("Errore di allocazione della memoria.\n");
            return NULL;
        }
        nuovo_nodo->valore = value; // Assegna il valore al nuovo nodo
        nuovo_nodo->prec = NULL;
        nuovo_nodo->succ = NULL;

        if (testa == NULL) {
            // Se la lista è vuota, il nuovo nodo è la testa
            testa = nuovo_nodo;
        } 
        else 
        {
            // Trova l'ultimo nodo
            nodo *ultimo = testa;
            while (ultimo->succ != NULL) {
                ultimo = ultimo->succ;
            }
            // Collega il nuovo nodo alla fine della lista
            ultimo->succ = nuovo_nodo;
            nuovo_nodo->prec = ultimo;
        }

        printf("Inserisci un altro numero intero da mettere nella lista (o un carattere non numerico per terminare): ");
        result = scanf("%d", &value); // Continua a leggere l'input
    }

    if (result != 1) {
        printf("Valore non intero inserito. Terminazione del programma.\n");
    }

    return testa; // Restituisce la testa della lista
}

void stampaLista(struct nodo* testa) {
    struct nodo* temp = testa;
    while (temp != NULL) {
        printf("%d -> ", temp->valore);
        temp = temp->succ;
    }
    printf("NULL\n");
}

int main() {
    nodo *lista = input_list();
    stampaLista(lista); // Stampa la lista per verificare l'inserimento dei nodi
    return 0;
}