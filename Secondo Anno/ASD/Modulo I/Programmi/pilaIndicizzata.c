//Progettare una struttura dati indicizzata che implementi
//il tipo di dato Pila. Le operazioni devo
//avere complessità temporale costante.

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

int pila[256]; //256 dimensione della pila
int count = 0; 

void push(int x){   
    if(count == 256){
        fprintf(stderr, "Non ci sta più spazio nella pila\n");
        return;
    }
    pila[count] = x;
    count++;
}

int pop(){
    if(count == 0){
        fprintf(stderr, "Non ci sta nulla da prendere dalla pila\n");
        return -1;
    }
    int result = pila[count-1];
    count--;
    return result;
}

int top(){
    return pila[count-1]; 
}

bool isEmpty(){
    if(count == 0){
        return true;
    } else {
        return false;
    }
}


int main(int argc, char* argv[]){ //Prove

    push(1);
    push(2);
    push(3);
    push(5);

    for(int i = 0; i< 4;i++){
        printf("%d\n", pop());
    }

    bool y = isEmpty();
    if(y == true){
        printf("La pila e' vuota");
    }

    return 0;

}