V: .word 4,4,2,2,1; //Definizione dell'array

LDR R0, =V;    // Carica l'indirizzo base dell'array V nel registro R0
MOV R1, #2;    //Carica il valore 2 nel registro R1

LDR R2, [R0];    // Carica il primo elemento dell'array V (4) nel registro R2
LSL R3, R2, #2;  // Moltiplica il valore nel registro R2 per 4 (shift logico a sinistra di 2) e memorizza il risultato in R3

MOV R4, #4; // Imposta R4 a 4, contatore per scorrere l'array

while:                     
	CMP R4, R3; // Confronta il valore in R4 con il limite superiore dell'array moltiplicato per 4
	BGT end;    // Salta alla fine del ciclo se R4 è maggiore di R3

	LDR R2, [R0, R4]; // Carica l'elemento successivo dell'array nel registro R2
	CMP R2, R1;      // Confronta il valore in R2 con il valore 2 in R1
	BEQ aumenta;     // Se sono uguali, esegui aumenta

	ADD R4, #4;      // Passa all'elemento successivo dell'array
	B while;         // Torna all'inizio del ciclo

aumenta:                // Aumenta il contatore
	ADD R6, #1;      // Incrementa il contatore nel registro R6
	ADD R4, #4;       // Passa all'elemento successivo dell'array
	B while;          // Torna all'inizio del ciclo

end:                     // Fine del ciclo
	NOP;                 
