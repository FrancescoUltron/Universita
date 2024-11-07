#include <stdio.h>      // Per printf
#include <sys/wait.h>   // Per wait()
#include <unistd.h>     // Per fork()

#define STDIN 0
#define STDOUT 1
#define PIPE_RD 0 
#define PIPE_WR 1

int main() {
    int pid, child_status;

    if ((pid = fork()) == 0) {
        // Questo è il codice eseguito dal processo figlio
        printf("I am the child and I see the PID %d\n", pid);
    } else { 
        // Questo è il codice eseguito dal processo padre
        wait(&child_status);  // Aspetta che il figlio termini
        printf("I am the parent, I see the child's PID (%d) and the status (%d)\n", pid, child_status);
    }

    return 0;
}
