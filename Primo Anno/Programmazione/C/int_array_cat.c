#include<stdlib.h>
#include<stdio.h>

struct int_array{
    int *a;
    int size;
};
typedef struct int_array int_array;;

void int_array_cat( int_array *v0, int_array v1 );

void int_array_cat( int_array *v0, int_array v1 ){

    int new_size = v0 -> size + v1.size;                      //Calcoliamo la nuova lunghezza dell'array 
    v0 -> a = (int *)realloc(v0->a, new_size * sizeof(int));   //Riallochiamo memoria nell'array da modificare

    if (v0->a == NULL) {
        printf("Errore di allocazione della memoria\n");
        exit(1);
    }

    for(int i = 0; i < v1.size;i++){

     v0 -> a[v0 -> size + i] = v1.a[i];   //Inseriamo gli elementi di v1 in v0 
    }

    v0 -> size = new_size; //Aggiorniamo la lunghezza di v0
}

//La complessità temporale sarà di O(n+m) Dove n ed m sono rispettivamente la lunghezza dei due array

int main() {
    // Esempio di utilizzo della funzione int_array_cat
    int_array v0;
    v0.size = 3;
    v0.a = (int *)malloc(v0.size * sizeof(int));
    v0.a[0] = 1;
    v0.a[1] = 2;
    v0.a[2] = 3;

    int_array v1;
    v1.size = 2;
    v1.a = (int *)malloc(v1.size * sizeof(int));
    v1.a[0] = 4;
    v1.a[1] = 5;

    int_array_cat(&v0, v1);

    // Stampare gli elementi concatenati di v0
    for (int i = 0; i < v0.size; i++) {
        printf("%d ", v0.a[i]);
    }
    printf("\n");

    // Liberare la memoria allocata
    free(v0.a);
    free(v1.a);

    return 0;
}