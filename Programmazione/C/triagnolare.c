/*• Si definisce Triangolare un numero costituito dalla
somma dei primi N numeri interi positivi per un certo
N.
• Ad esempio: per Q = 10 si ha Q =1+2+3+4, da cui N = 4.
• Scrivere un programma C che stabilisca se un numero
intero positivo Q, letto dallo standard input, è un
numero triangolare o meno, utilizzando soltanto
operazioni tra numeri interi. In caso affermativo
stampare a video il numero inserito e il massimo degli
addendi che lo compongono.*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
    int Q, N = 0, somma = 0;
    printf("Inserisci un numero intero positivo: ");
    scanf("%d", &Q);

    for (int i = 1; somma < Q; i++)
    {
        somma += i;
        N++;
    }

    if (somma == Q)
    {
        printf("%d è un numero triangolare, N = %d\n", Q, N);
    }
    else
    {
        printf("%d non è un numero triangolare\n", Q);
    }
    
    return 0;
}