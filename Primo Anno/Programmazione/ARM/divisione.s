.global _start
_start:
	
	//Dati X e Y calcolare il quoziente della divisione, senza usare l'operazione di divisione
	
	MOV R0, #0x0F  
	MOV R1, #0x05 
	MOV R2, #0
	
	
	while:CMP R0, R1
	      BLT end      //Salto condizionale alla procedura end BLT è branch
	
	SUB R0, R0, R1
	ADD R2, R2, #1
	B while            //Branch è usato per tornare all'etichetta while
	end: NOP           //NOP = no operation