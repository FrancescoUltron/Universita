//Struttura per un nodo di una lista concatenata, implementa le varie funzioni della lista

#include<stdlib.h>
#include<stdio.h>

typedef struct Node {
    int dato;                     //Valore del nodo della lista
    struct Node *prossimo;        //Puntatore di tipo nodo che punterà al prossimo nodo
}Node;

// Funzione per aggiungere un nodo in testa alla lista
void push(struct Node** testa, int valore) {  //doppio ** passo il puntatore, * passo il valore associato al puntatore

    // 1. Alloca il nuovo nodo
    struct Node* nuovoNodo = (struct Node*)malloc(sizeof(struct Node));

    // 2. Inserisci i dati nel nuovo nodo
    nuovoNodo->dato = valore;

    // 3. Imposta il puntatore del nuovo nodo al nodo attuale di testa
    nuovoNodo->prossimo = *testa;

    // 4. Cambia la testa della lista per puntare al nuovo nodo
    *testa = nuovoNodo;
}

void pushright(struct Node** testa, int valore)
{
    // 1. Alloca il nuovo nodo
    struct Node* nuovoNodo = (struct Node*)malloc(sizeof(struct Node));
    

    // 2. Inserisci i dati nel nuovo nodo
    nuovoNodo->dato = valore;
    nuovoNodo->prossimo = NULL;

        if (*testa == NULL) {
        // Se la lista è vuota, il nuovo nodo diventa la testa della lista
        *testa = nuovoNodo;
        return;
    }

    // 4. Altrimenti, scorri fino alla fine della lista
    struct Node* temp = *testa;
    while (temp->prossimo != NULL) {
        temp = temp->prossimo;
    }

    // 5. Collega il nuovo nodo all'ultimo nodo
    temp->prossimo = nuovoNodo;
}

// Funzione per stampare la lista
void stampaLista(struct Node* testa) {
    struct Node* temp = testa;
    while (temp != NULL) {
        printf("%d -> ", temp->dato);
        temp = temp->prossimo;
    }
    printf("NULL\n");
}

void pop(struct Node** testa, int posizione)
{
    //Raggiungere il nodo da eliminare
    struct Node* temp = *testa;

    if (posizione == 0) {
        *testa = temp->prossimo; // Cambia la testa
        free(temp); // Dealloca la vecchia testa
        return;
    }

    for(int i = 0;temp != NULL && i < posizione - 1; i++) //controlla minore uguale
    {
        temp = temp->prossimo;
    }

    // Nodo temp->prossimo è il nodo da eliminare
    struct Node* nodoDaEliminare = temp->prossimo;
    temp->prossimo = nodoDaEliminare->prossimo;

    // Dealloca il nodo
    free(nodoDaEliminare);

}

Node *lista_cerca(Node *x, int pos)
{
    struct Node *temp = x;
    
    int c = 0;
	
	while( temp != NULL && c != pos ) {
		temp = temp->prossimo;
		c++;
	}

    return temp;

}

struct Node* mergeTwoLists(Node* l1,Node* l2) //complessità temporale di O(n+m), spaziale di O(n+m)
{

    // if list1 happen to be NULL
		// we will simply return list2.
		if(l1 == NULL)
        {
			return l2;
		}
		
		// if list2 happen to be NULL
		// we will simply return list1.
		if(l2 == NULL)
        {
			return l1;
		} 
		
		// if value pointend by l1 pointer is less than equal to value pointed by l2 pointer
		// we wall call recursively l1 -> next and whole l2 list.
		if(l1 -> dato <= l2 -> dato)
        {
			l1 -> prossimo = mergeTwoLists(l1 -> prossimo, l2);
			return l1;
		}
		// we will call recursive l1 whole list and l2 -> next
		else
        {
			l2 -> prossimo = mergeTwoLists(l1, l2 -> prossimo);
			return l2;            
		}
}


// Funzione principale per testare l'aggiunta di nodi
int main() {
    struct Node* testa = NULL;

    // Aggiungi alcuni nodi alla lista
    pushright(&testa, 10);
    pushright(&testa, 20);
    pushright(&testa, 30);
    push(&testa,5);
    pushright(&testa, 40);
    push(&testa,23);
    pushright(&testa, 50);



    pop(&testa, 3);

    // Stampa la lista
    printf("Lista collegata: ");
    stampaLista(testa); // Output: 5 -> 10 -> 20 -> 30 -> 40 -> 50 -> NULL

    // Libera la memoria allocata
    struct Node* temp;
    while (testa != NULL) {
        temp = testa;
        testa = testa->prossimo;
        free(temp);
    }

    *lista_cerca(testa,3);

    return 0;
}
