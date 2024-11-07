#include <signal.h>
#include <stdio.h>
#include <unistd.h>

int main() {
    pid_t pid = getpid();  // Otteniamo il PID del processo corrente
    kill(pid, SIGINT);      // Inviamo un SIGINT al processo corrente
    return 0;
}