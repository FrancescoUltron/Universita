#include<stdio.h>
#include<stdlib.h>

struct ListNode
{
    int val;
    struct ListNode *next;
};

struct ListNode* removeNthFromEnd(struct ListNode* head, int n) 
{
    if(head == NULL || (head->next==NULL)) //Se la lista è vuota la ritorniamo subito
    {
        return NULL;
    }

    int len = 0; 
    int a;
    struct ListNode *temp,*nodoDaEliminare;

    temp = head; //puntatore temporaneo che punta alla testa della lista

    while(temp != NULL) //Prendiamo la lunghezza della lista
    {
        len++;
        temp=temp->next;
    }

    if(n==len) //Se il nodo da eliminare è il primo ritorniamo tutta la lista tranne il primo nodo
    {
        return head->next;
    }

    temp=head;//Rimettiamo temp all'inzio della lista
    a =len-n-1; //posizione nodo da eliminare

    while(a!=0)//Calcolo del nodo precedente a quello che dobbiamo eliminare
    {
        temp=temp->next;
        a--;
    }

    nodoDaEliminare = temp->next; //Troviamo il nodo da elimare aggiornando il puntatore 
    temp->next = temp->next->next;//Aggiorniamo il puntatore al nodo successivo rispetto a quello da eliminare

    free(nodoDaEliminare); //Eliminiamo il nodo
    return head;//Ritorniamo la lista

}