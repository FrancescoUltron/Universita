// Codice 1: Calcolo del massimo tra tre numeri

// Inizializzazione dei tre numeri
MOV R0, #7;    // Numero 7
MOV R1, #5;    // Numero 5
MOV R2, #6;    // Numero 6

// Confronto tra R0 e R1
CMP R0, R1;    // Confronta R0 con R1
BGE r0vr2;     // Se R0 è maggiore o uguale a R1, salta a r0vr2
B r1vr2;       // Altrimenti, salta a r1vr2

r0vr2:         // Etichetta per il caso in cui R0 è maggiore o uguale a R1
	CMP R0, R2;    // Confronta R0 con R2
	BGE load0;     // Se R0 è maggiore o uguale a R2, salta a load0
	B load2       // Altrimenti, salta a load2

r1vr2:         // Etichetta per il caso in cui R1 è maggiore o uguale a R2
	CMP R1, R2;    // Confronta R1 con R2
	BGE load1;     // Se R1 è maggiore o uguale a R2, salta a load1
	B load2;       // Altrimenti, salta a load2

load0:         // Etichetta per il caso in cui R0 è il massimo
	MOV R3, R0;    // Memorizza il valore di R0 in R3 (massimo)
	B end;         // Salta alla fine del programma

load1:         // Etichetta per il caso in cui R1 è il massimo
	MOV R3, R1;    // Memorizza il valore di R1 in R3 (massimo)
	B end;         // Salta alla fine del programma

load2:         // Etichetta per il caso in cui R2 è il massimo
	MOV R3, R2;    // Memorizza il valore di R2 in R3 (massimo)
	B end;         // Salta alla fine del programma

end:           // Fine del programma
	NOP;       // Nessuna operazione

// Codice 2: Calcolo del massimo tra tre numeri

// Inizializzazione dei tre numeri
MOV R0, #3;    // Numero 3
MOV R1, #4;    // Numero 4
MOV R2, #5;    // Numero 5

// Confronto tra R0 e R1
CMP R0, R1;    // Confronta R0 con R1
BGT r0vr2;     // Se R0 è maggiore di R1, salta a r0vr2
B r1vr2;       // Altrimenti, salta a r1vr2

r0vr2:         // Etichetta per il caso in cui R0 è maggiore di R1
	CMP R0, R2;    // Confronta R0 con R2
	BGT r0;        // Se R0 è maggiore di R2, salta a r0
	B r2;          // Altrimenti, salta a r2

r1vr2:         // Etichetta per il caso in cui R1 è maggiore o uguale a R2
	CMP R1, R2;    // Confronta R1 con R2
	BGT r1;        // Se R1 è maggiore di R2, salta a r1
	B r2;          // Altrimenti, salta a r2

r0:            // Etichetta per il caso in cui R0 è il massimo
	MOV R5, R0;    // Memorizza il valore di R0 in R5 (massimo)
r1:            // Etichetta per il caso in cui R1 è il massimo
	MOV R5, R1;    // Memorizza il valore di R1 in R5 (massimo)
r2:            // Etichetta per il caso in cui R2 è il massimo
	MOV R5, R2;    // Memorizza il valore di R2 in R5 (massimo)
