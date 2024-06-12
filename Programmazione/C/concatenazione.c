#include<stdlib.h>
#include<string.h>
#include<stdio.h>

char *cat(char *a, char *b)
{
    //Calcoliamo la lunghezza delle stringhe

    int len_a = strlen(a);
    int len_b = strlen(b);

    char *risultato = (char *)malloc((len_a + len_b + 1) * sizeof(char));

    if(risultato == NULL){
        return 0; //Problema di allocazione
    }

    // Copia la prima stringa nella nuova stringa
    for (int i = 0; i < len_a; i++) {
        risultato[i] = a[i];
    }
    
    // Aggiungi la seconda stringa alla nuova stringa dopo la prima
    for (int i = 0; i < len_b; i++) {
        risultato[len_a + i] = b[i];
    }
    
    // Aggiungi il terminatore nullo alla fine
    risultato[len_a + len_b] = '\0';

    return risultato;
}


int main()
{
    char* x = cat("Hello ", "World");
    printf("%s", x);
}