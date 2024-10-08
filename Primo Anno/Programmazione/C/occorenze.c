#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int occorrenze(char *s, char c) {
    int risultato = 0;

    for (int i = 0; i < strlen(s); i++) {
        if (s[i] == c) {
            risultato++;
        }
    }

    return risultato;
}

void stampa_frequenza(char *s) {
    int frequenza[26] = {0}; //Associamo come valore iniziale 0

    // Conta le occorrenze di ciascun carattere dalla 'a' alla 'z'
    for (int i = 0; i < strlen(s); i++) {

        char c = s[i];

        if (c >= 'a' && c <= 'z') {
            frequenza[c - 'a']++;      //Il carattere c è un carattere minuscolo compreso tra 'a' e 'z'. Sottraendo 'a' dal carattere c, ottieni un valore numerico che rappresenta la posizione di c nell'alfabeto.
        }
    }

    // Stampa la frequenza di ciascun carattere
    for (int i = 0; i < 26; i++) {
        if (frequenza[i] > 0) {
            printf("%c: %d\n", 'a' + i, frequenza[i]);
        }
    }
}

//La complessità delle funzioni è di O(n) perchè dovremmo vedere tutti gli elementi 
//della stringa per trovare le occorenze

int main() {
    char s[] = "ciao questa e una stringa";
    char c = 'a';

    int x = occorrenze(s, c);

    printf("%d\n", x);

    stampa_frequenza(s);

    return 0;
}