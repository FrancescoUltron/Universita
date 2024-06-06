// Codice 1

V: .word 3,4,5,6;  // Array V con valori 3, 4, 5, 6
W: .word 0;         // Array W inizializzato a 0

// Caricamento dell'indirizzo di V nel registro R0
LDR R0, =V;

// Caricamento dell'indirizzo di W nel registro R1
LDR R1, =W;

// Caricamento del primo elemento di V nel registro R2
LDR R2, [R0];

// Memorizzazione del primo elemento di V in W
STR R2, [R1];

// Moltiplicazione per 4 dell'elemento di V
LSL R2, R2, #2;

// Inizializzazione dell'indice per il ciclo a 4 (offset di un elemento in byte)
MOV R3, #4;


while:
	// Controlla se l'elemento di V è minore o uguale a 0
	CMP R2, #0;
	BLE end;   // Se sì, salta alla fine

	// Carica l'elemento corrente di V
	LDR R4, [R0, R2];

	// Memorizza l'elemento corrente di V in W
	STR R4, [R1, R3];

	// Incrementa l'indice per W
	ADD R3, #4;

	// Decrementa l'elemento di V
	SUB R2, #4;

	B while;   // Torna all'inizio del ciclo

end:
	NOP;       // Fine del programma

// Codice 2

// Definizione degli array V e X
V: .word 3,1,2,3;  // Array V con valori 3, 1, 2, 3
X: .word 0;         // Array X inizializzato a 0

// Caricamento dell'indirizzo di V nel registro R0
LDR R0, =V;

// Caricamento dell'indirizzo di X nel registro R1
LDR R1, =X;

// Caricamento del primo elemento di V nel registro R2
LDR R2, [R0];

// Controlla se il primo elemento di V è uguale a 0
CMP R2, #0;
BEQ end;   // Se sì, salta alla fine

// Memorizza il primo elemento di V in X
STR R2, [R1];

// Moltiplicazione per 4 dell'elemento di V
LSL R3, R2, #2;

// Inizializzazione dell'indice per il ciclo a 4 (offset di un elemento in byte)
MOV R4, #4;

// Etichetta del ciclo
while:
	// Controlla se l'elemento di V è uguale a 0
	CMP R3, #0;
	BEQ end;   // Se sì, salta alla fine

	// Carica l'elemento corrente di V
	LDR R2, [R0, R3];

	// Memorizza l'elemento corrente di V in X
	STR R2, [R1, R4];

	// Incrementa l'indice per X
	ADD R4, #4;

	// Decrementa l'elemento di V
	SUB R3, #4;

	B while;   // Torna all'inizio del ciclo

end:
	
