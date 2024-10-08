#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int rimuovi_stringa(char *a, char *b);


int rimuovi_stringa(char *a, char *b) {

    int len_a = strlen(a);
    int len_b = strlen(b);
    
    
    if (len_b == 0 || len_a < len_b) return 0; // b è vuota o più lunga di a
    
    // Cerca b dentro a
    for (int i = 0; i <= len_a - len_b; i++) {

        int j;

        for (j = 0; j < len_b; j++) {
            if (a[i + j] != b[j]) break;
        }

        if (j == len_b) 
        { // b è trovata in a
            // Rimuovi b da a
            for (int k = i; k <= len_a - len_b; k++) {
                a[k] = a[k + len_b];
            }
            return 1;
        }
    }
    
    return 0; // b non trovata in a
}


