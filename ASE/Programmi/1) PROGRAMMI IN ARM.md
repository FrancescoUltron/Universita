**Indirizzamento immediato**: ==Modalità di indirizzamento della memoria in cui l'indirizzo della locazione di memoria è specificato direttamente nell'istruzione.==

**Indirizzamento immediato tra registri**: ==Modalità di indirizzamento della memoria in cui l'indirizzo della locazione di memoria è specificato direttamente nell'istruzione, e in cui scriviamo il valore di un registro in un altro registro.==


``` 

//PROVE CON ARITMETICA E CSPR

.global _start
_start:

	MOV R0, #5 //INSERISCE 5 NEL REGISTRO 0
	MOV R1, #7 //INSERISCE 7 NEL REGISTRO 1
	
	ADD R2,R0,R1 //R2 = R0 + R1
	SUBS R3,R0,R1 //R3 = R0 + R1, LA S SERVE AD ATTIVARE IL REGISTRO CPSR COSI' DA CAPIRE SE IL NUMERO CHE ABBIAMO E' NEGATIVO O MENO
	MUL R4, R0, R1 // R4 = R0 * R1
	
	MOV R8, #0XFFFFFFFF //LA SOMMA FRA R8 E R9 VA IN OVERFLOW (SUPERA GLI 8 BIT DI GRANDEZZA)
	MOV R9, #3
	ADDS R10,R8,R9 //LA S SERVE AD ATTIVARE IL REGISTRO CPSR COSI' DA CAPIRE SE STIAMO ANDANDO IN OVERFLOW
	
	//RICORDA CHE USARE LA S RALLENTA IL PROGRAMMA, QUINDI VA USATA SOLO NELLE SITUAZIONI IN CUI NON SIAMO
	//SICURI DEI DATI CHE STIAMO USANDO, NEL CSPR SI ATTIVERA' LA C (CARRY)
	
	ADC R11,R9,R8 //R11 = R9 + R8 + CARRY GENERATO DALL'ULTIMA `ISTRUZIONE`
```

