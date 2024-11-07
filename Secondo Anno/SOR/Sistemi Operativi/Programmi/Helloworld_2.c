#include<unistd.h>
#define STDOUT 1

int main(int argc, char **argv)
{
    char msg[] = "Hello World!\n";
    write (STDOUT, msg, sizeof(msg));
    return 0;
}