#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <fcntl.h>

#define BUFFER_SIZE 100

int main() {
    int pipe1[2];  // Pipe per inviare il numero dal padre al figlio
    int pipe2[2];  // Pipe per inviare il risultato dal figlio al padre
    pid_t pid;
    int num, square;

    // Creazione delle pipe
    if (pipe(pipe1) == -1) {
        perror("Pipe1 creation failed");
        exit(1);
    }
    if (pipe(pipe2) == -1) {
        perror("Pipe2 creation failed");
        exit(1);
    }

    // Creazione del processo figlio
    pid = fork();

    if (pid < 0) {
        // Se fork fallisce
        perror("Fork failed");
        exit(1);
    }

    if (pid == 0) {
        // Processo figlio
        close(pipe1[1]);  // Chiude il lato di scrittura della pipe1
        close(pipe2[0]);  // Chiude il lato di lettura della pipe2

        // Legge il numero inviato dal padre
        if (read(pipe1[0], &num, sizeof(num)) > 0) {
            printf("Figlio: Numero ricevuto dal padre: %d\n", num);

            // Calcola il quadrato del numero
            square = num * num;

            // Se il quadrato è pari, invia il risultato al padre
            if (square % 2 == 0) {
                write(pipe2[1], &square, sizeof(square));
                printf("Figlio: Quadrato pari inviato al padre: %d\n", square);
            }
        }

        close(pipe1[0]);
        close(pipe2[1]);
    } else {
        // Processo padre
        close(pipe1[0]);  // Chiude il lato di lettura della pipe1
        close(pipe2[1]);  // Chiude il lato di scrittura della pipe2

        // Inizializza il generatore di numeri casuali
        srand(time(NULL));
        num = rand() % 100 + 1;  // Numero casuale tra 1 e 100

        // Invia il numero al figlio
        write(pipe1[1], &num, sizeof(num));
        printf("Padre: Numero inviato al figlio: %d\n", num);

        // Legge il quadrato dal figlio se è pari
        if (read(pipe2[0], &square, sizeof(square)) > 0) {
            printf("Padre: Quadrato ricevuto dal figlio: %d\n", square);
        } else {
            printf("Padre: Nessun numero ricevuto (quadrato dispari).\n");
        }

        close(pipe1[1]);
        close(pipe2[0]);
    }

    return 0;
}
