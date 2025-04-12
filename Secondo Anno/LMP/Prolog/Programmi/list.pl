/*Esercizio MAX*/

/*La regola MAX ci ritorna il valore massimo della lista*/

maxOfTwo(X, Y, X) :-
    X >= Y. 

maxOfTwo(X, Y, Y) :-
    Y >= X.

max([], _).
max([X],X).
max([H|T], Max) :-
    max(T, MaxT),
    maxOfTwo(H, MaxT, Max).

%Somma di due numeri rappresentati tramite delle liste
%Per semplicità i valori di unità - decine - centinaia - ecc sono invertiti
%Esempio: L1 = [2, 9, 4] sarebbere 492

sum([], [], 0, []).
sum([H1|T1], [H2|T2], [S|TS]) :-
    sum(T1, T2, TS),
    S is H1 + H2.

%Appartiene
/*X appartiene ad L se:
	X è la testa di L oppure
	X appartiene alla coda di L
*/

appartiene(X, [X|T]).
appartiene(X, [H|T]) :-
    appartiene(X,T).


