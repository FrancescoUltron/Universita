/*Membership*/

/*Vero se X è un elemento di L*/
member(X, [X|_]).
member(X, [_ | _]) :-
    member(X, _).

/*Concatenazione*/
conc([],L,L).
conc([X|L1] , L2, [X|L3]) :-
    conc(L1,L2,L3).

/*Elimina gli ultimi tre elementi*/
concDeleteLast3(L1, L2) :-
    conc(L2, [_,_,_], L1).

/*Elimita primi tre e ultimi tre elementi*/
concDeleteLastFirst3(_, L2) :-
    conc(L2, [_,_,_], L1),
	conc([_,_,_], L2, L1).

/*Ultimo oggetto*/
last1(Item, List) :-
    conc(_, [Item], List).

/*Aggiungi in testa*/
add(X, L, [X|L]).

/*Elimina elemento*/
del(X, [X | Tail], Tail).
del(X, [H | T] , [H | T1]) :-
    del(X, T, T1).

/*Inserimento in posizione qualsiasi*/
insert(X, L1, L2) :-
    del(X, L2, L1).

/*Sublist*/
sublist(S, L) :-
    conc(_, L2, L),
    conc(S, _, L2).

/* Permutazioni */
permutazioni([], []).
permutazioni([X|L], P) :-
    permutazioni(L, L1),
    insert(X, L1, P).

/*Lunghezza Pari e Dispari*/
evenlenght([]).
evenlenght(L) :-
   conc([_,_], L1, L),
   evenlenght(L1).

oddlenght([_]).
oddlenght(L) :-
    conc([_,_], L1, L),
    oddlenght(L1).

/*Reverse*/
reverse([],[]).
reverse([H|T], ReversedList) :-
    reverse(T, RT),
    conc(RT, [H], ReversedList).
