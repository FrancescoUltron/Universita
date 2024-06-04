#include<stdio.h>

//lu - unsigned long - stampa la grandezza in byte e si usa solo su interi 
//sizeof - dimensione in byte di una grandezza
//I puntatori hanno tutti la stessa dimensione indipendentemente dal tipo
//Un puntatore punta all'indirizzo di memoria di una variabile

main(){

    int i = 9;
    char c = 'a';

    printf("Char: %lu byte - int: %lu byte", sizeof(char), sizeof(int));
    printf("\n");
    printf("Puntatore a char: %lu - Puntatore a int: %lu", sizeof(char*), sizeof(int*));

    int* pi;  //Dichiarazione di un puntatore
    char* pc;

    pi = &i; //Prendere l'indirizzo di memoria di i, prenderà la prima cella in cui è contenuto 9
    pc = &c;

    printf("\n");
    printf("Indirizzo di memoria di i: %p - Indirizzo di memoria di c: %p\n", pi, pc); //Segnaposto p è per i puntatori, cosi' stampiamo gli indirizzi di memoria in hex

    printf("Il valore di i: %d = %d\n",i, *pi); //*pi referenzia il valore di i, cioè 9, possiamo usare il segnaposto %d perchè è un puntatore int
    printf("Il valore di c: %c = %c\n",c, *pc);

    //MODIFICA TRAMITE PUNTATORE

    i = 12;
    printf("Il valore di i: %d, il valore della cella puntata da pi %d\n", i, *pi); //Il puntatore segue i

    *pi = 18; //Vai alla cella di memoria di i e modifica il contenuto della cella di memoria

    printf("Il valore di i: %d, il valore della cella puntata da pi %d\n", i, *pi); //i segue il puntatore

    //i = *pi, cioè entrambi si riferiscono alla stessa cella di memoria


    //p2 e p1, se metto p2 = p1, allora il puntatore di p2 puntreà alla cella di memoria di p1

    int a = 7;
    int b = 5;

    int *p1 = &a;  //& fa riferimento all'indirizzo di memoria
    int *p2 = &b;

    printf("Indirizzo a: %d - Indirizzo b: %d \n", p1,p2); //p = indirizzo della cella, *p = contenuto della cella

    p2 = p1; //p2 punta a p1, cambio l'indirizzo della cella
    printf("Indirizzo a: %d - Indirizzo b: %d \n", p1,p2); 

    *p2 = *p1; //p2 ha il valore di p1, cambio il contenuto della cella
    printf("Indirizzo a: %d - Indirizzo b: %d \n", *p1,*p2); 

}