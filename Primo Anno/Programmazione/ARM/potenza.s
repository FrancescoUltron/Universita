.global _start
_start:
	
	//Elevazione a potenza dati X e Y calcolare X^Y
	
	MOV R0, #0x2
	MOV R1, #0x4
	CMP R0, #0x0 //Compariamo R0 con 0
	
	BEQ and   //L'istruzione BEQ in ARM assembly è un'istruzione di salto condizionale che 
	          //effettua un salto ad un'etichetta specificata se il flag di condizione EQ (Equal) è impostato.
	B else    //Branch sulla procedura else
	
    and: CMP R1, #0x0
	BEQ then               //Se R1 = 0, allora salto condizionale alla procedura then
	B else                 //Senno alla procedura else
	
	then: MVN R3, #0x0      //MVN inverte tutti i bit dell'operando e memorizza il risultato nel registro di destinazione.
	B end
	
	else: MOV R3,#0x1
	MOV R2, #0x0
	
	for: CMP R2, R1
	BGE end               //Branch se R2 e maggiore o uguale di R1
	MUL R3, R3, R0        
	ADD R2, R2, #0x1
	B for
	
	end:NOP
	

        