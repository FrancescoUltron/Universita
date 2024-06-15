//Si scriva un programma in linguaggio C che riceva in ingresso due parole inserite da tastiera. 
//Si consideri che ciascuna parola può contenere al massimo 30 caratteri. Il programma
//deve sostituire ogni occorrenza della seconda parola nella prima parola con una sequenzadi caratteri ’*’.
//Ad esempio, inserite le parole abchdfffchdtlchd e chd, il programma deve visualizare la parola ab***fff***tl***.

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    char parola1[30], parola2[30];
    int i, j, k, trovato;

    printf("Inserisci la prima parola: ");
    scanf("%s", parola1);
    printf("Inserisci la seconda parola: ");
    scanf("%s", parola2);

    for(i = 0; i < strlen(parola1); i++) {
        trovato = 1;
        for(j = 0, k = i; j < strlen(parola2); j++, k++) {
            if(parola1[k] != parola2[j]) {
                trovato = 0;
                break;
            }
        }
        if(trovato) {
            for(j = 0; j < strlen(parola2); j++) {
                parola1[i+j] = '*';
            }
        }
    }

    printf("La parola modificata è: %s\n", parola1);

    return 0;
}