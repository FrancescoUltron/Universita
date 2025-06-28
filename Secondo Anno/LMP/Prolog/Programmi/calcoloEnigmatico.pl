% Calcolo Enigmatico - Approccio Dichiarativo Migliorato
% Basato sulla tua idea iniziale ma completato

% Definizione dei domini per ogni simbolo (0-9)
cifra(X) :- member(X, [1,2,3,4,5,6,7,8,9]).

% Predicati per ogni simbolo distinto
quadrato(X) :- cifra(X).
doppia_striscia(X) :- cifra(X).
diagonale(X) :- cifra(X).
cerchio(X) :- cifra(X).
triangolo(X) :- cifra(X).
freccia(X) :- cifra(X).
croce(X) :- cifra(X).
rete(X) :- cifra(X).
griglia(X) :- cifra(X).

prima_equazione_orizzotale(Q, Ds, D, C, Cr, F, T, PrimoAddendo, SecondoAddendo, Risultato) :-
    quadrato(Q),
    doppia_striscia(Ds),
    diagonale(D),
    cerchio(C),
    croce(Cr),
    freccia(F),
    triangolo(T),
    PrimoAddendo is Q*100 + Ds*10 + D,
    SecondoAddendo is F*10 + Cr,
    Risultato is PrimoAddendo + SecondoAddendo,
    Risultato =:= F*100 + Cr*10 + T.  

prima_equazione_verticale(Q, Ds, D, C, R, F, PrimoAddendo, SecondoAddendo, Risultato) :-
    quadrato(Q),
    doppia_striscia(Ds),
    diagonale(D),
    cerchio(C),
    rete(R),
    freccia(F),
    PrimoAddendo is Q*100 + Ds*10 + D,
    SecondoAddendo is C*10 + R,
    Risultato is PrimoAddendo+SecondoAddendo,
	Risultato =:= F*100 + C*10 + F.

seconda_equazione_orizzontale(C, R, Cr, Q, D, F, PrimoFattore, SecondoFattore, Risultato) :-
    cerchio(C),
    rete(R),
    croce(Cr),
    quadrato(Q),
    diagonale(D),
    freccia(F),
    PrimoFattore is C*10 + R,
    SecondoFattore is Cr*10 + Q,
    Risultato is PrimoFattore * SecondoFattore,
    Risultato =:= Q*100 + D*10 + F.

seconda_equazione_verticale(C, Cr, Q, R, PrimoAddendo, SecondoAddendo, Risultato) :-
    cerchio(C),
    croce(Cr),
    quadrato(Q),
    rete(R),
    PrimoAddendo is C*10 + Cr,
    SecondoAddendo is Cr*10 + Q,
    Risultato is PrimoAddendo - SecondoAddendo,
    Risultato =:= R.

terza_equazione_orizzontale(F, C, R, G, Q, Dividendo, Divisore, Risultato) :-
    freccia(F),
    cerchio(C),
    rete(R),
    griglia(G),
    quadrato(Q),
    Dividendo is F*100 + C*10 + F,
    Divisore is R,
    Risultato is Dividendo/Divisore,
    Risultato =:= G*10 + Q.

terza_equazione_verticale(F, Cr, T, Q, D, G, PrimoAddendo, SecondoAddendo, Risultato) :-
    freccia(F),
    croce(Cr),
    triangolo(T),
    quadrato(Q),
    diagonale(D),
    griglia(G),
    PrimoAddendo is F*100 + Cr*10 + T,
    SecondoAddendo is Q*100 + D*10 + F,
    Risultato is PrimoAddendo - SecondoAddendo, 
    Risultato =:= G* 10 + Q.

risolvi_calcolo_enigmatico(Q, Ds, D, C, T, F, Cr, R, G) :-
    prima_equazione_verticale(Q, Ds, D, C, R, F, _, _, _),
    prima_equazione_verticale(Q, Ds, D, C, R, F, _, _, _),
    seconda_equazione_orizzontale(C, R, Cr, Q, D, F, _, _, _),
    seconda_equazione_verticale(C, Cr, Q, R, _, _, _),
    terza_equazione_orizzontale(F, C, R, G, Q, _, _, _),
    terza_equazione_verticale(F, Cr, T, Q, D, G, _, _, _).