#include <stdio.h>
#include <stdlib.h>

struct point {
float x, y;
};typedef struct point point;

struct segment {
 point *a;
 point *b;
}typedef segment;

segment *new_segment(float ax, float ay, float bx, float by);

//Si scriva una funzione che abbia il seguente prototipo
//e restituisca un puntatore ad un nuovo segment tale che i due point che lo
//compongono abbiano coordinate ax e ay per il primo e bx e by per il secondo

segment *new_segment(float ax, float ay, float bx, float by){
    segment *s = malloc(sizeof(segment));
    s->a = malloc(sizeof(point));
    s->b = malloc(sizeof(point));
    s->a->x = ax;
    s->a->y = ay;
    s->b->x = bx;
    s->b->y = by;
    return s;
}

int main(){
    segment *s = new_segment(1, 2, 3, 4);
    printf("Segmento: (%f, %f) - (%f, %f)\n", s->a->x, s->a->y, s->b->x, s->b->y);
    return 0;
}

//La compkìlessitò temporale di questa funzione è O(1) in quanto non ci sono cicli