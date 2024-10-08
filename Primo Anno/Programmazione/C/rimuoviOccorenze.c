//Si scriva una funzione C che rispetti la seguente specifica:
//Prototipo: void delete(char *src, char *ptr);
//dove src e ptr sono due stringhe.
//Comportamento: modifica src eliminando tutte le occorrenze di ptr in src

#include<stdio.h>
#include<stdlib.h>
#include<string.h>


char* delete(char *ptr, const char *src) {
    int len_ptr = strlen(ptr);
    int len_src = strlen(src);
    
    if (len_src == 0 || len_ptr < len_src) return ptr; 
    
    
    for (int i = 0; i <= len_ptr - len_src; i++) {
        int j;

        for (j = 0; j < len_src; j++) {
            if (ptr[i + j] != src[j]) 
            break;
        }

        if (j == len_src) 
        { 
            for (int k = i; k <= len_ptr - len_src; k++) {
                ptr[k] = ptr[k + len_src];
            }
            len_ptr -= len_src;
            ptr[len_ptr] = '\0'; 
            i--; 
        }
    }
    
    return ptr; // Restituisce la stringa modificata
}

int main() {
    char str[100] = "alberobello.";
    const char *sottostringa = "be";

    printf("Stringa originale: %s\n", str);
    
    rimuovi_stringa(str, sottostringa);
    
    printf("Stringa dopo l'eliminazione: %s\n", str);

    return 0;
}


