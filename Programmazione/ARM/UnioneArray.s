V1: .word 3,4,5,6;
V2: .word 3,7,8,9;
V3: .word 0;

LDR R0, =V1;
LDR R1, =V2;
LDR R2, =V3;
LDR R3, [R0];
LDR R4, [R1];
ADD R5, R4, R3;
STR R5, [R2];

LSL R3, R3, #2;
LSL R4, R4, #2;
LSL R5, R5, #2;
ADD R5, #4;
MOV R6, #4;

while1:
	CMP R6, R3;
	BGT endWhile1;
	LDR R7, [R0, R6];
	STR R7, [R2, R6];
	ADD R6, #4;
	B while1;
endWhile1:
	MOV R8, R6;
	MOV R6, #4;
while2:
	CMP R6, R4;
	BGT end;
	LDR R7, [R1, R6];
	STR R7, [R2, R8];
	ADD R6, #4;
	ADD R8, #4;
	B while2;
end:
	NOP;




	
V0: .word 3,1,2,3;
V1: .word 2,4,5;
V2: .word 0;

LDR R0, =V0;
LDR R1, =V1;
LDR R2, =V2;

LDR R3, [R0];
LDR R4, [R1];
ADD R5, R4, R3;
STR R5, [R2];
LSL R6, R3, #2;
LSL R7, R4, #2;
MOV R8, #4;
MOV R9, #4;
MOV R10, #4;

while:
	CMP R8, R6;
	BGT while2;
	LDR R3, [R0, R8];
	STR R3, [R2, R10];
	ADD R8, #4;
	ADD R10, #4;
	B while;
while2: 
	CMP R9, R7;
	BGT end;
	LDR R4, [R1, R9];
	STR R4, [R2, R10];
	ADD R9, #4;
	ADD R10, #4;
	B while2;
end:
	NOP;
