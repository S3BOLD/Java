#include <stdio.h>
#include <stdlib.h>

#define MAX 100000

// Função de comparação para ordenar em ordem crescente
int crescente(const void *a, const void *b) {
    return (*(int *)a - *(int *)b);
}

// Função de comparação para ordenar em ordem decrescente
int decrescente(const void *a, const void *b) {
    return (*(int *)b - *(int *)a);
}

int main() {
    int N;
    int pares[MAX], impares[MAX];
    int i, num, count_par = 0, count_impar = 0;

    // Lê a quantidade de números
    scanf("%d", &N);

    // Lê os N números e separa em pares e ímpares
    for (i = 0; i < N; i++) {
        scanf("%d", &num);
        if (num % 2 == 0) {
            pares[count_par++] = num;
        } else {
            impares[count_impar++] = num;
        }
    }

    // Ordena os pares em ordem crescente
    qsort(pares, count_par, sizeof(int), crescente);

    // Ordena os ímpares em ordem decrescente
    qsort(impares, count_impar, sizeof(int), decrescente);

    // Imprime os pares
    for (i = 0; i < count_par; i++) {
        printf("%d\n", pares[i]);
    }

    // Imprime os ímpares
    for (i = 0; i < count_impar; i++) {
        printf("%d\n", impares[i]);
    }

    return 0;
}
