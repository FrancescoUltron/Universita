#include <signal.h>
#include <stdio.h>
#include <unistd.h>

void handler(int signum) {
    printf("Signal %d received\n", signum);
}

int main() {
    signal(SIGINT, handler);  // Gestore per il segnale SIGINT (Ctrl+C)
    while (1) {
        pause();  // Pausa in attesa di un segnale
    }
    return 0;
}