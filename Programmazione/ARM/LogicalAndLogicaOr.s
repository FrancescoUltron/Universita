// Definizione degli array V1 e V2
V1: .word 4,0,1,0,1;   // Array V1 con valori 4, 0, 1, 0, 1
V2: .word 4,1,0,1,0;   // Array V2 con valori 4, 1, 0, 1, 0

// Caricamento degli indirizzi degli array nei registri
LDR R0, =V1;          // Carica l'indirizzo di V1 nel registro R0
LDR R1, =V2;          // Carica l'indirizzo di V2 nel registro R1

// Caricamento dei primi elementi degli array nei registri
LDR R2, [R0];         // Carica il primo elemento di V1 nel registro R2
LDR R3, [R1];         // Carica il primo elemento di V2 nel registro R3

// Calcolo del limite superiore dell'array V1
LSL R4, R2, #2;       // Moltiplica il primo elemento di V1 per 4 e memorizza il risultato in R4

// Inizializzazione delle variabili
MOV R6, #4;           // Indice di scorrimento degli array
MOV R8, #1;           // Variabile per memorizzare il risultato finale, inizializzata a 1

// Controllo per il caso in cui il primo elemento di V1 sia zero
CMP R2, #0;           // Confronta il primo elemento di V1 con zero
BEQ end;              // Se è zero, salta alla fine

// Controllo per il caso in cui il primo elemento di V1 non sia uguale al primo elemento di V2
CMP R2, R3;           // Confronta il primo elemento di V1 con il primo elemento di V2
BNE end;              // Se non sono uguali, salta alla fine

while:                // Etichetta del ciclo while
	CMP R4, R6        // Confronta l'indice di scorrimento con il limite superiore
	BLT end;          // Se l'indice è minore del limite, salta alla fine

	LDR R2, [R0,R6];  // Carica l'elemento corrente di V1 nel registro R2
	LDR R3, [R1,R6];  // Carica l'elemento corrente di V2 nel registro R3

	// Calcola l'OR logico degli elementi correnti di V1 e V2
	ORR R7, R2, R3;   

	// Calcola l'AND logico del risultato attuale e il risultato parziale finora
	AND R8, R8, R7;

	CMP R8, #0;       // Confronta il risultato con zero
	BEQ end;          // Se è zero, salta alla fine

	ADD R6, #4;       // Incrementa l'indice di scorrimento
	B while;          // Torna all'inizio del ciclo

end:                  // Fine del programma
	NOP;              // Nessuna operazione
	
	


