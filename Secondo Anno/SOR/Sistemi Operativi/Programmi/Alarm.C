#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>
void alarm_handler(int signal){
	printf("In signal handler: caught signal %d!\n",signal);
	exit(0);
}

int main(int argc, char **argv){
	signal(SIGALRM, alarm_handler);
	alarm(1); // alarm will send signal after 1 sec
	
	while (1) {
		printf("I am running!\n");
	}
	return 0;
}