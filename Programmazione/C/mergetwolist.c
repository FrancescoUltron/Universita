//Merge di due liste ordinate si basa sulla ricorsione, chiamiamo la nostra
//funzione ogni volta che andiamo a confrontare i valori delle due liste
//finchè la una delle due liste ritorna NULL
//Allora la ricorsione tornerà indietro ed i puntatori punteranno ai nodi interessati

#include<stdlib.h>
#include<stdio.h>

typedef struct Node {
    int dato;                     //Valore del nodo della lista
    struct Node *prossimo;        //Puntatore di tipo nodo che punterà al prossimo nodo
}Node;

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
