/*Controllo dati*/

/*var(X)
  nonvar(X)
  atom(X)
  integer(X)
  float(X)
  number(X)
  atomic(X)
  compound(X)
*/

/*count(X,L,NUM_VOlTE)*/
% Caso base: lista vuota, nessuna occorrenza
count(_, [], 0).

% Caso ricorsivo 1: l'elemento in testa è uguale a X
count(X, [X | T], N) :-
    count(X, T, N1),
    N is N1 + 1.

% Caso ricorsivo 2: l'elemento in testa è diverso da X
count(X, [H | T], N) :-
    X \= H,
    count(X, T, N).


/*CUT - Una volta superato non torno indietro, quindi niente backtracking
 FAIL - Una volta trovato non posso andare in quella direzione, quindi fallisce sempre*/

max(X,Y,X) :- X >= Y, !.
max(_,Y,Y).
    