lineaOrizzontaleInUnPianoFattaDi(A,Lunghezza,Piano):-
    length(Piano,10),
    lunghezzaSublists(Piano),
    trovaRigaDiA(A,Lunghezza,Piano).

lunghezzaSublists([]).

lunghezzaSublists([H|L]):-
    length(H,5),
    lunghezzaSublists(L).

trovaRigaDiA(A,Lunghezza,[L|_]):-
    trovata(A,Lunghezza,L).

trovaRigaDiA(A,Lunghezza,[_|L]):-
    trovaRigaDiA(A,Lunghezza,L).

trovata(A,Lunghezza,[A|LL]):-
    Lunghezza1 is Lunghezza-1,
    occorrenzeConsecutive(A,Lunghezza1,LL).

trovata(A,Lunghezza,[_|L]):-
    trovata(A,Lunghezza,L).

occorrenzeConsecutive(_,0,_).
occorrenzeConsecutive(A,L,[A|T]):-
    L1 is L - 1,
    occorrenzeConsecutive(A,L1,T).
