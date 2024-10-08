#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *prefix(char *a, int n) {
    char *b; 
    int i; 
    int len_a = strlen(a); 

    if (n > len_a) {
        
        b = (char *)malloc(strlen(a) + 1);
        strcpy(b, a); 
    } 
    else 
    {
        
        b = (char *)malloc(n + 1);
       
        for (i = 0; i < n; i++) {
            b[i] = a[i];
        }
        b[i] = '\0'; 
    }
    return b; 
}

//La complessità temporale è O(n) perchè strlen, che è un operazione obbligatoria per il funzionamento del programma 
//ha complessità O(n) con n la lunghezza della stringa a, anche strcopy ha complessità O(n) con n la lunghezza della stringa a
//Il ciclo for ha complessità O(n) con n che sono i caratteri da copiare

//La complessità spaziale è O(n) in quanto viene allocato un array di n elementi per contenere la stringa b