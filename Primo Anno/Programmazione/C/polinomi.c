//Scrivere un programma in linguaggio C che
//legge un polinomio di grado n a coefficienti
//reali e lo valuta per un dato valore x

#include <stdio.h>
#include <stdlib.h>

int main(){

    int n;
    float x;
    float polinomio[100];
    float risultato = 0;
    int i;

    printf("Inserisci il grado del polinomio: ");
    scanf("%d", &n);
    printf("Inserisci il valore di x: ");
    scanf("%f", &x);

    for(i = 0; i <= n; i++){
        printf("Inserisci il coefficiente di grado %d: ", i);
        scanf("%f", &polinomio[i]);
    }

    for(i = 0; i <= n; i++){
        risultato += polinomio[i] * pow(x, i);
    }

    printf("Il risultato è: %f\n", risultato);
    return 0;
}