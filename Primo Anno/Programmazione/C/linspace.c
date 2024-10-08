#include <stdio.h>
#include <stdlib.h>

float *linspace(float n0, float n1, int n) {
    float *array = (float *)malloc(n * sizeof(float));
    if (array == NULL) {
        printf("Errore di allocazione della memoria\n");
        exit(1);
    }
    
    float step = (n1 - n0) / (n - 1);
    for (int i = 0; i < n; i++) {
        array[i] = n0 + i * step;
    }
    
    return array;
}

int main() {
    float n0 = 3.0;
    float n1 = 7.0;
    int n = 6;
    
    float *result = linspace(n0, n1, n);
    
    for (int i = 0; i < n; i++) {
        printf("%.2f ", result[i]);
    }
    
    free(result);
    
    return 0;
}