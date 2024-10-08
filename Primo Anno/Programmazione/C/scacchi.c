/*Su una scacchiera 8x8 sono posizionati due
pezzi: il Re bianco e la Regina nera.
• Si scriva un programma in linguaggio C che,
acquisite le posizioni del Re e della Regina,
determini se la Regina è in posizione tale da
poter mangiare il Re. Le posizioni dei due pezzi
sono identificate da mediante la riga e la
colonna su cui si trovano, espresse come numeri
interi tra 1 e 8.*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
    int re_x, re_y, regina_x, regina_y;
    printf("Inserisci la posizione del Re (x y): ");
    scanf("%d %d", &re_x, &re_y);
    printf("Inserisci la posizione della Regina (x y): ");
    scanf("%d %d", &regina_x, &regina_y);

    if (re_x == regina_x || re_y == regina_y || abs(re_x - regina_x) == abs(re_y - regina_y))
    {
        printf("La Regina può mangiare il Re\n");
    }
    else
    {
        printf("La Regina non può mangiare il Re\n");
    }

    return 0;
}