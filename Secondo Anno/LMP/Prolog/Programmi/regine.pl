% Problema delle otto regine
% 
% Il predicato per la soluzione è:
% solution(Pos)
%
% Che è vero se e solo se Pos rappresenta una posizione
% tali nessuna regina possa attacare altre regine.
% 
% Risolveremo il problema con diversi approcci
%

% Programma 1
%
%
% Rappresentiamo Pos come una lista di otto elementi 
% X/Y che rappresentano le celle dove si trovano le regine.
% Siccome tutte le regine si devono trovare su righe
% diverse possiamo scrivere la soluzione come:
% 
% [1/Y1, 2/Y2, ..... 8/Y8]
% 
% Generalizziamo il problema, ossia tenendo di conto di YN.
% La soluzione può essere formulata tenendo di conto di due casi:
% 
%  - La lista di regine è vuota --> E' soluzione.
%  - La lista non è vuota --> [X/Y | Others]
%  
% Nel secondo caso Pos è una soluzione se sono seguite
% le seguenti condizioni:
% 
%  1) Others deve essere una soluzione.
%  2) X e Y sono interi fra 1 e 8
%  3) Una regina in X/Y non deve attacare altre regine in Others
%

solution([]).
solution([X/Y | Others]) :-
    solution(Others),
    member(Y, [1,2,3,4,5,6,7,8]),
    no_attack(X/Y, Others). % Da implementare

% no_attack(Q, QList).
%
% - Se la lista delle regine è vuota allora la relazione è vera.
% - Qlist non è vuota -> [Q1 | Qlist1] dobbiamo soddisfare due condizioni:
% 
%   1) La regina in Q non deve attacare Q1 e
%   2) La regina in Q non deve attacare nessuna regina Qlist1
%
% Il template che abbiamo usato per la soluzione ha le regine
% in tutte righe diverse, allora ci dobbiamo preoccupare solo
% degli attacchi verticali e diagonali.
%

no_attack(_, []).
no_attack(X/Y, [X1/Y1 | Others]) :-
    Y =\= Y1, % colonna diversa
    Y1-Y =\= X1-X, % diagonale diversa
	Y1-Y =\= X-X1,
    no_attack(X/Y, Others).

member(X, [X|_]).
member(X, [_|T]) :-
    member(X, T).

%Un template per la soluzione
template([1/_, 2/_, 3/_, 4/_, 5/_, 6/_, 7/_, 8/_]).


% Programma 2

permutation([],[]).
permutation([H|T], PermList) :-
    permutation(T, PermTail),
    del(H, PermList, PermTail).

del(A, [A|List], List).
del(A, [B|List], [B|List1]) :-
    del(A, List, List1).

safe([]).
safe([Queen|Others]) :-
    safe(Others),
    noattack(Queen, Others, 1).

noattack(_, [], _).
noattack(Y, [Y1 | Ylist], Xdist):-
    Y1-Y =\= Xdist,
    Y-Y1 =\= Xdist,
    Dist1 is Xdist + 1,
    noattack(Y, Ylist, Dist1).

solution2(Queens) :-
    permutation([1,2,3,4,5,6,7,8], Queens),
    safe(Queens).







