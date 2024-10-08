// Codice 1

// Definizione degli array V0, V1 e V2
V0: .word 3,1,5,10;   // Array V0 con valori 3, 1, 5, 10
V1: .word 2,2,9;       // Array V1 con valori 2, 2, 9
V2: .word 0;           // Array V2 inizializzato a 0

// Caricamento degli indirizzi degli array nei registri
LDR R0, =V0;          // Carica l'indirizzo di V0 nel registro R0
LDR R1, =V1;          // Carica l'indirizzo di V1 nel registro R1
LDR R2, =V2;          // Carica l'indirizzo di V2 nel registro R2

// Caricamento del primo elemento di ciascun array nei registri
LDR R3, [R0];         // Carica il primo elemento di V0 nel registro R3
LDR R4, [R1];         // Carica il primo elemento di V1 nel registro R4

// Somma dei primi elementi di V0 e V1
ADD R5, R3, R4;       // Somma il contenuto di R3 (V0[0]) e R4 (V1[0]) e memorizza il risultato in R5

// Memorizzazione del risultato nella prima posizione di V2
STR R5, [R2];         // Memorizza il contenuto di R5 (somma) nella prima posizione di V2

// Moltiplicazione per 4 di ciascun indice degli array
LSL R3, R3, #2;       // Moltiplica ogni elemento di V0 per 4
LSL R4, R4, #2;       // Moltiplica ogni elemento di V1 per 4
LSL R5, R5, #2;       // Moltiplica la somma per 4
ADD R5, R5, #4;       // Incrementa la somma di 4 per puntare alla prossima posizione di V2

// Inizializzazione degli indici per il ciclo
MOV R6, #4;           // Inizializza l'indice R6 a 4 
MOV R7, #4;           // Inizializza l'indice R7 a 4 
MOV R8, #4;           // Inizializza l'indice R8 a 4 

// Etichetta del ciclo
for:
	CMP R8, R5;        // Confronta R8 con il limite superiore
	BGE end;           // Se R8 è maggiore o uguale a R5, salta alla fine
	CMP R7, R4;        // Confronta R7 con il limite superiore di V1
	BGT then;          // Se R7 è maggiore di R4, salta a then

	// Caricamento dell'elemento corrente di V1 in R10
	LDR R10, [R1, R7];
	CMP R6, R3;        // Confronta R6 con il limite superiore di V0
	BGT else;          // Se R6 è maggiore di R3, salta a else

	// Caricamento dell'elemento corrente di V0 in R9
	LDR R9, [R0, R6];
	CMP R9, R10;       // Confronta l'elemento di V0 con l'elemento di V1
	BGT else;          // Se R9 è maggiore di R10, salta a else

then:
	// Memorizza l'elemento corrente di V0 in V2 e aggiorna gli indici
	STR R9, [R2, R8];
	ADD R6, #4;
	ADD R8, #4;
	B for;             // Torna all'inizio del ciclo

else:
	// Memorizza l'elemento corrente di V1 in V2 e aggiorna gli indici
	STR R10, [R2, R8];
	ADD R7, #4;
	ADD R8, #4;
	B for;             // Torna all'inizio del ciclo

end:
	NOP;               // Fine del programma

// Codice 2

// Definizione degli array V0, V1 e V2
V0: .word 3,1,3,5;   // Array V0 con valori 3, 1, 3, 5
V1: .word 2,2,4;     // Array V1 con valori 2, 2, 4
V2: .word 0;         // Array V2 inizializzato a 0

// Caricamento degli indirizzi degli array nei registri
LDR R0, =V0;        // Carica l'indirizzo di V0 nel registro R0
LDR R1, =V1;        // Carica l'indirizzo di V1 nel registro R1
LDR R2, =V2;        // Carica l'indirizzo di V2 nel registro R2

// Caricamento del primo elemento di ciascun array nei registri
LDR R3, [R0];       // Carica il primo elemento di V0 nel registro R3
LDR R4, [R1];       // Carica il primo elemento di V1 nel registro R4

// Somma dei primi elementi di V0 e V1
ADD R5, R3, R4;     // Somma il contenuto di R3 (V0[0]) e R4 (V1[0]) e memorizza il risultato in R5

// Memorizzazione del risultato nella prima posizione di V2
STR R5, [R2];       // Memorizza il contenuto di R5 (somma) nella prima posizione di V2

// Moltiplicazione per 4 di ciascun indice degli array
LSL R3, R3, #2;     // Moltiplica ogni elemento di V0 per 4
LSL R4, R4, #2;     // Moltiplica ogni elemento di V1 per 4

// Inizializzazione degli indici per il ciclo
MOV R6, #4;         // Inizializza l'indice R6 a 4 (offset di un elemento in byte)
MOV R7, #4;         // Inizializza l'indice R7 a 4 (offset di un elemento in byte)
MOV R8, #4;         // Inizializza l'indice R8 a 4 (offset di un elemento in byte)

// Etichetta del ciclo
while:
	LDR R9, [R0, R6];  // Carica l'elemento corrente di V0 in R9
	LDR R10, [R1, R7]; // Carica l'elemento corrente di V1 in R10
	CMP R9, R10;       // Confronta l'elemento corrente di V0 con quello di V1
	BLE then1;         // Se R9 è minore o uguale a R10, salta a then1
	B then2;           // Altrimenti, salta a then2
	
then1:
	// Memorizza l'elemento corrente di V0 in V2 e aggiorna gli indici
	STR R9, [R2, R8];
	ADD R8, #4;
	ADD R6, #4;
	CMP R6, R3;        // Controlla se l'indice di V0 supera il limite superiore
	BGT end1;          // Se sì, salta alla fine
	B while;           // Altrimenti, torna all'inizio del ciclo
	
then2:
	// Memorizza l'elemento corrente di V1 in V2 e aggiorna gli indici
	STR R10, [R2, R8];
	ADD R8, #4;
	ADD R7, #4;
	CMP R7, R4;        // Controlla se l'indice di V1 supera il limite superiore
	BGT end2;          // Se sì, salta alla fine
	B while;           // Altrimenti, torna all'inizio del ciclo
	
end1:
	// Se l'indice di V0 supera il limite, copia il resto degli elementi di V1 in V2
	LDR R10, [R1, R7];
	STR R10, [R2, R8];
	ADD R8, #4;
	ADD R7, #4;
	CMP R7, R4;        // Controlla se l'indice di V1 supera il limite superiore
	BGT end3;          // Se sì, salta alla fine
	B end1;            // Altrimenti, torna alla fine1
	
end2:
	// Se l'indice di V1 supera il limite, copia il resto degli elementi di V0 in V2
	LDR R9, [R0, R6];
	STR R9, [R2, R8];
	ADD R8, #4;
	ADD R6, #4;
	CMP R6, R3;        // Controlla se l'indice di V0 supera il limite superiore
	BGT end3;          // Se sì, salta alla fine
	B end2;            // Altrimenti, torna alla fine2
	
end3: 
	NOP;               // Fine del programma

