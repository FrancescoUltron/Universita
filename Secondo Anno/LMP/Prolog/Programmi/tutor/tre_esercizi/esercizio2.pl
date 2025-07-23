quadratoInUnPianoFattoDi(A,LunghezzaLato,Piano):-
    length(Piano,10),
    lunghezzaSublists(Piano),
    trovaQuadratoDiA(A,LunghezzaLato,Piano).

trovaQuadratoDiA(A,1,[L|_]):-
    trovata(A,1,L).

trovaQuadratoDiA(A,2,[L1,L2|_]):-
    trovaQuadrato(A,2,L1,L2).

trovaQuadratoDiA(A,3,[L1,L2,L3|_]):-
    trovaQuadrato(A,3,L1,L2,L3),!.

trovaQuadratoDiA(A,N,[_|L]):-
    trovaQuadratoDiA(A,N,L).

trovaQuadrato(A,2,[A|L1],[A|L2]):-
    LT is 2-1,
    occorrenzeConsecutive(A,LT,L1),
    occorrenzeConsecutive(A,LT,L2).

trovaQuadrato(A,2,[_|L1],[_|L2]):-
    trovaQuadrato(A,2,L1,L2).

trovaQuadrato(A,3,[A|L1],[A|L2],[A|L3]):-
    LT is 3-1,
    occorrenzeConsecutive(A,LT,L1),
    occorrenzeConsecutive(A,LT,L2),
    occorrenzeConsecutive(A,LT,L3).

trovaQuadrato(A,3,[_|L1],[_|L2],[_|L3]):-
    trovaQuadrato(A,3,L1,L2,L3).

lunghezzaSublists([]).

lunghezzaSublists([H|L]):-
    length(H,5),
    lunghezzaSublists(L).

trovata(A,Lunghezza,[A|LL]):-
    Lunghezza1 is Lunghezza-1,
    occorrenzeConsecutive(A,Lunghezza1,LL).

trovata(A,Lunghezza,[_|L]):-
    trovata(A,Lunghezza,L).

occorrenzeConsecutive(_,0,_).
occorrenzeConsecutive(A,L,[A|T]):-
    L1 is L - 1,
    occorrenzeConsecutive(A,L1,T).
