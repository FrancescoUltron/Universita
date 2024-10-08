V1: .word 4,4,5,6,7;     // Definizione dell'array V1, il primo elemento (4) è la dimensione dell'array
MOV R0, #7;              // Carica il valore 7 nel registro R0

LDR R1, =V1;             // Carica l'indirizzo di V1 nel registro R1
LDR R2, [R1];            // Carica il primo valore di V1 (la dimensione dell'array) in R2
LSL R3, R2, #2;          // Moltiplica la dimensione dell'array per 4 (shifta a sinistra di 2 bit) e memorizza il risultato in R3 (dimensione totale dell'array in byte)
MOV R4, #4;              // Inizializza R4 a 4, l'offset del primo elemento dell'array (saltando la dimensione)

while:
    CMP R4, R3;          // Confronta l'indice corrente con la dimensione totale dell'array in byte
    BGT end;             // Se l'indice corrente è maggiore della dimensione totale, salta a 'end'
    LDR R5, [R1, R4];    // Carica l'elemento dell'array all'indice corrente (V1[R4/4]) in R5
    CMP R5, R0;          // Confronta l'elemento corrente con il valore in R0 (7)
    BEQ endWhile;        // Se l'elemento corrente è uguale a 7, salta a 'endWhile'
    ADD R4, #4;          // Incrementa l'indice (offset) di 4 byte per il prossimo elemento
    B while;             // Ripete il ciclo

endWhile:
    MOV R7, #1;          // Se un elemento uguale a 7 è trovato, carica 1 in R7
end:
    NOP;                 // No Operation (fine del programma)
