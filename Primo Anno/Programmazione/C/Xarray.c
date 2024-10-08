#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int *x_array(int *a, int na, int *b, int nb) {
    int *array = (int *)malloc((na * nb) * sizeof(int));
    if (array == NULL) {
        fprintf(stderr, "Errore di allocazione della memoria\n");
        exit(1);
    }

    int k = 0;

    for (int i = 0; i < na; i++) {
        for (int j = 0; j < nb; j++) {
            array[k] = a[i] * b[j];
            k++;
        }
    }

    return array;
}

int main() {
    int a[] = {1, 2, 3};
    int b[] = {10, 20, 30, 40};

    int len_a = sizeof(a) / sizeof(a[0]);
    int len_b = sizeof(b) / sizeof(b[0]);

    int *result = x_array(a, len_a, b, len_b);
    int len_result = len_a * len_b;

    for (int i = 0; i < len_result; i++) {
        printf("%d ", result[i]);
    }
    printf("\n");

    free(result);

    return 0;
}
