// Codice 1
V: .word 3,5,10,2;

// Caricamento dell'indirizzo base dell'array V nel registro R0
LDR R0, =V;

// Caricamento del primo elemento dell'array V nel registro R1
LDR R1, [R0];

// Confronto del primo elemento con 0 per controllare se l'array è vuoto
CMP R1, #0;

// Se il primo elemento è minore o uguale a 0, salta alla fine
BLE end;


MOV R2, #4;         // Dimensione di una parola (in byte)
LSL R3, R1, #2;     // Calcola il limite superiore dell'array (numero di elementi * dimensione di una parola)

while:              
	CMP R2, R3;     // Confronta l'indice con il limite superiore dell'array
	BGT end;        // Se l'indice supera il limite, salta alla fine

	LDR R1, [R0, R2];  // Carica l'elemento corrente dell'array V nel registro R1
	CMP R1, R4;        // Confronta l'elemento corrente con R4
	BGE max;           // Se l'elemento corrente è maggiore o uguale a R4, salta a max

	ADD R2, #4;        // Incrementa l'indice per passare all'elemento successivo
	B while;           // Torna all'inizio del ciclo

max:               
	MOV R4, R1;      // Memorizza l'elemento corrente come nuovo massimo
	ADD R2, #4;      // Incrementa l'indice per passare all'elemento successivo
	B while;         // Torna all'inizio del ciclo

end:                // Fine del programma
	NOP;            // Nessuna operazione

// Codice 2

V: .word 3,4,5,2;

// Caricamento dell'indirizzo base dell'array V nel registro R0
LDR R0, =V;

// Caricamento del primo elemento dell'array V nel registro R1
LDR R1, [R0];

// Confronto del primo elemento con 0 per controllare se l'array è vuoto
CMP R1, #0;

// Se il primo elemento è uguale a 0, salta alla fine
BEQ end;

// Inizializzazione dell'indice per il ciclo e del limite superiore dell'array
MOV R2, #4;         // Dimensione di una parola (in byte)
LSL R3, R1, #2;     // Calcola il limite superiore dell'array (numero di elementi * dimensione di una parola)

while:              // Etichetta del ciclo while
	LDR R1, [R0, R2];  // Carica l'elemento corrente dell'array V nel registro R1
	CMP R1, R5;        // Confronta l'elemento corrente con R5
	BGE max;           // Se l'elemento corrente è maggiore o uguale a R5, salta a max

	ADD R2, #4;        // Incrementa l'indice per passare all'elemento successivo
	CMP R2, R3;        // Confronta l'indice con il limite superiore dell'array
	BGT end;           // Se l'indice supera il limite, salta alla fine

	B while;           // Torna all'inizio del ciclo

max:                // Etichetta per il massimo trovato
	MOV R5, R1;      // Memorizza l'elemento corrente come nuovo massimo
	ADD R2, #4;      // Incrementa l'indice per passare all'elemento successivo
	CMP R2, R3;      // Confronta l'indice con il limite superiore dell'array
	BGT end;         // Se l'indice supera il limite, salta alla fine
	B while;         // Torna all'inizio del ciclo

end:                // Fine del programma
	NOP;            // Nessuna operazione




