insegnamento(fiu, 1).
insegnamento(fit, 1).
insegnamento(cs, 1).
insegnamento(bb, 1).
insegnamento(bu, 2).
insegnamento(ce, 2).
insegnamento(si, 2).
insegnamento(op, 2).

orario_lezioni(Aula1, Aula2):-
    length(Tot, 16),
    setof((X,Y), insegnamento(X,Y), Lezioni),
    riempiOrario(Tot,Lezioni),
    permutation(Lezioni,LezioniPossibili),
    split(LezioniPossibili,Aula1,Aula2),
    vincoli_rispettati(Aula1,Aula2).


riempiOrario([],[]).
riempiOrario([A|L],[A|T]):-
    riempiOrario(L,T).

riempiOrario([x|L],T):-
    riempiOrario(L,T).

vincoli_rispettati([],[]).
vincoli_rispettati([x|L1],[_|L2]):-
    vincoli_rispettati(L1,L2).
vincoli_rispettati([_|L1],[x|L2]):-
    vincoli_rispettati(L1,L2).

vincoli_rispettati([(_,Y)|L1],[(_,Z)|L2]):-
    Z \== Y,
    vincoli_rispettati(L1,L2).

split(L,L1,L2):-
    length(L,N), %N unificata con la lunghezza di L
    N1 is N/2,
    length(L1,N1),
    length(L2,N1),
    append(L1,L2,L).

%Usa il predicato TRACE per visualizzare le soluzioni


