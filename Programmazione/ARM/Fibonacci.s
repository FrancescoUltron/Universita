// Versione 1: Calcolo della successione di Fibonacci


MOV R0, #1;     ; Primo numero della successione (Fibonacci(0))
MOV R1, #1;     ; Secondo numero della successione (Fibonacci(1))
MOV R2, #2;     ; Contatore per il numero massimo di iterazioni
MOV R3, #5;     ; Numero massimo di iterazioni desiderate

while:          ; 
	CMP R2, R3; ; Confronta il contatore con il numero massimo di iterazioni
	BGT end;    ; Se il contatore è maggiore o uguale al numero massimo di iterazioni, termina il ciclo
	ADD R4, R0, R1;    ; Calcola il successivo numero di Fibonacci sommando i due numeri precedenti
	MOV R0, R1;        ; Aggiorna il primo numero precedente con il secondo
	MOV R1, R4;        ; Aggiorna il secondo numero precedente con il successivo numero di Fibonacci
	ADD R2, #1;        ; Incrementa il contatore
	B while;           ; Torna all'inizio del ciclo principale

end:             ; 
	NOP;         ; 

// Versione 2: Calcolo della successione di Fibonacci


MOV R0, #6;     ; Numero massimo di iterazioni desiderate
MOV R1, #0;     ; Primo numero della successione (Fibonacci(0))
MOV R2, #1;     ; Secondo numero della successione (Fibonacci(1))
MOV R3, #1;     ; Contatore per il numero corrente di iterazioni

while:          ; 
	ADD R5, R1, R2;     ; Calcola il successivo numero di Fibonacci sommando i due numeri precedenti
	ADD R3, #1;         ; Incrementa il contatore delle iterazioni
	CMP R3, R0;         ; Confronta il contatore con il numero massimo di iterazioni
	BGE end;            ; Se il contatore è maggiore o uguale al numero massimo di iterazioni, termina il ciclo
	MOV R1, R2;         ; Aggiorna il primo numero precedente con il secondo
	MOV R2, R5;         ; Aggiorna il secondo numero precedente con il successivo numero di Fibonacci
	B while;            ; Torna all'inizio del ciclo principale

end:             ; 
	NOP;         ; 
