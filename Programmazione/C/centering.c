#include <stdio.h>
#include <stdlib.h>

void centering(int a[], int r, int c);

void centering(int a[], int r, int c){
    int i, j, k, count;
    for(i = 0; i < r; i++){
        count = 0;
        for(j = 0; j < c; j++){
            if(a[i*c+j] == 1){
                count++;
            }
        }
        for(j = 0; j < c; j++){
            if(j < (c-count)/2 || j >= (c-count)/2+count){
                a[i*c+j] = 0;
            } else {
                a[i*c+j] = 1;
            }
        }
    }
}

int main(){
    int a[] = {
        1,1,0,0,0,
        1,0,0,0,0,
        1,1,1,0,0,
        1,1,0,0,0,
        1,1,1,1,0,
        1,1,1,1,1
    };
    int r = 6, c = 5;
    int i, j;
    centering(a, r, c);
    for(i = 0; i < r; i++){
        for(j = 0; j < c; j++){
            printf("%d ", a[i*c+j]);
        }
        printf("\n");
    }
    return 0;
}

//La complessità di questo algoritmo è O(n*m) dove n è il numero di righe e m è il numero di colonne.