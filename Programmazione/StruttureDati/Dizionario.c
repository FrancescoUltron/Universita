//Crezione di un dizionario in C

#include<stdlib.h>
#include<stdio.h>
#include <string.h>



typedef struct d_item {            // la coppia chiave-valore
	char *k;                       //la chiave (una stringa)
	float v;                       // il valore (un float)
}d_item;

typedef struct nodo {
    d_item info;                  //Coppia chiave-valore
    struct nodo *prossimo;        //Puntatore di tipo nodo che punterà al prossimo nodo
}nodo;

typedef struct dict {
	nodo **a;                      //lista principale, puntatore chiamato a di tipo nodo
	int m;                         // numero di liste (dimensione della lista principale)
	int n;                         // numero di elementi nel dizionario
}dict;

dict dict_init(int m){
	/*
	 * Ritorna un dizionario vuoto con m liste
	 * */
	dict d;
	int i;
	
	d.a = malloc(m*sizeof(nodo*)); //Andiamo ad allocare m volte la grandezza in byte di un nodo
	d.m = m;
	d.n = 0;
	for(i = 0; i < m; i++){
		d.a[i] = NULL;
	}
	
	return d;
}

int h(char *x, dict d){
	int out = 0;
	int i = 0;
	
	while( x[i] != '\0'){ //per ogni bit di x (la chiave)
		out = x[i]^out; //fai lo xor con out
		i++; // prossimo bit
	}

	return out % d.m; //ritorniamo il modulo tra out e la dimensione della lista
}

nodo *lista_cerca_k(nodo *x, char *k){
	/*
	 * Ritorna il puntatore al nodo contenente l'item con chiave k, 
	 * oppure NULL se k non e' una chiave del dizionario
	 * */
	nodo *p = x;
	
	while ( p != NULL ){
		if ( strcmp(p->info.k, k) == 0 ) {
			return p;
		}
		p = p->prossimo;
	}
	
	return NULL;
}


nodo *lista_in0(nodo* x, d_item e){
	nodo *n = malloc(sizeof(nodo));
	
	n->info = e; 
	n->prossimo = x;
	
	return n;
}

dict dict_update(dict d, d_item e){ 
	nodo *p; // nuovo nodo
	int lis = h(e.k, d); //chiedo alla funzione hash dove lo devo mettere, lei mi risponde "mettilo in lis"
	
	
	p = lista_cerca_k(d.a[lis], e.k);  // in lis ci potrebbero già essere una lista di nodi! Cerco se c'è già la mia chiave
	
	if ( p == NULL ) { // la chiave non è nel dizionario
	
		 d.a[lis] = lista_in0(d.a[lis], e); // inserisco il mio elemento
		 d.n++;
	} else { //la chiave è già nel dizionario !
		p->info.v = e.v; // aggiorno il valore
	}
	
	return d; //ritorno il dizionario aggiornato
}

void dict_mostra(dict d){
	int i;
	for (i = 0; i < d.m; i++){
		printf("%d - ", i);
		lista_mostra(d.a[i]);
	}
}

void lista_mostra(nodo *x){
	nodo *p = x;
	
	while( p != NULL ){
		printf("(%s, %.2f) ", p->info.k, p->info.v);
		p = p->prossimo;
	}
	printf("\n");
}

int main(int n, char *args[]){

	dict d = dict_init(5); //Inizializza dizionario con 5 posti

	d_item oggetto;       //Creazione oggetto dizionario (Gli do chiave e valore)
	oggetto.k = "Ciao";
	oggetto.v = 1;

	dict_update(d,oggetto);    //Aggiorno il dizionario
	dict_mostra(d);            //Mostro dizionario

	d_item prova;      //Creazione secondo oggetto dizionario (Gli do chiave e valore)
	prova.k = "key";
	prova.v = 2;

	dict_update(d, prova);  //aggiorno il dizionario
	dict_mostra(d);         //mostro dizionario aggiornato 

}
