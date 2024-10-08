.global _start
_start:
	
	//Dati X e Y fai la moltiplicazione senza usare l'istruzione di moltiplicazione
	
	MOV R0, #0X02
	MOV R1, #0X04	
	MOV R2, #0X0
	MOV R3, #0X0
	
	while: CMP R1, R2
	BLS end                 //Branch if lower or same
	
	ADDGT R3,R3,R0 //ADDGT è un'istruzione che effettua un'addizione solo se il flag di condizione GT (Greater Than) è impostato. 
	ADDGT R2,R2,#1
	BGT while      //BGT (Branch if Greater Than)
	
	end:NOP
   