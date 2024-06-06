.global _start
_start:

//Calcolo del fattoriale

	MOV R0, #5; // carica il valore 5 nel registro R0. Si imposta il numero per il quale calcolare il fattoriale.
	MOV R1, #1; // Inizializza R1 a 1. Questo registro servirà come contatore.
	MOV R2, #1; // Inizializza R2 a 1. Questo registro conterrà il risultato del fattoriale.
while:
	CMP R1, R0;// Confronta il valore di R1 con quello di R0.
	BGT end; // Se R1 è maggiore di R0, salta all'etichetta "end".
	MUL R2, R1; // Moltiplica il valore di R2 per R1 e memorizza il risultato in R2.
	ADD R1, #1; // Incrementa R1 di 1.
	B while; // Salta di nuovo all'etichetta "while".
end:
	NOP;
	
	