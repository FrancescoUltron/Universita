// SOMMA DEGLI ELEMENTI DI UN ARRAY V = [2,5,6] DI DIMENSIONE 3

V: .word 3,2,5,6;

LDR R0, =V;         // Carica l'indirizzo di V in R0
LDR R1, [R0];       // Carica la dimensione dell'array in R1
CMP R1, #0;         // Confronta R1 con 0
BLE end;            // Se R1 è minore o uguale a 0, salta a 'end'
LSL R2, R1, #2;     // R2 = R1 * 4 (dimensione in byte dell'array)
MOV R3, #4;         // R3 = 4 (indice per il primo elemento dell'array)
MOV R4, #0;         // Inizializza il registro della somma a 0

do: 
    LDR R1, [R0, R3];  // Carica l'elemento dell'array in R1
    ADD R4, R4, R1;    // Aggiunge l'elemento di V a R4 (somma cumulativa)
    ADD R3, #4;        // Incrementa l'indice per il prossimo elemento
    CMP R3, R2;        // Confronta l'indice con la dimensione totale
while: BLE do;         // Se l'indice è minore o uguale, ripete il ciclo

end: 
    NOP;               // No Operation
