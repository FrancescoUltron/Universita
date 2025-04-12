% Definizione della transizione di stato s
% s(Stacks, [Stack1, [Top1 | Stack2] | OtherStacks]) :-
% Questa regola tenta di spostare un blocco dalla cima di una pila (Stack1) a un'altra pila (Stack2).

s(Stacks, [Stack1, [Top1 | Stack2] | OtherStacks]) :-
    del([Top1|Stack1], Stacks, Stack1), % Rimuove il blocco superiore (Top1) dalla pila Stack1.
    del(Stack2, Stack1, OtherStacks).  % Aggiunge il blocco Top1 alla pila Stack2.

% Definizione del predicato del
% del(X, [X|L], L).
% Questa regola rimuove un elemento X dalla lista se è il primo elemento.
del(X, [X|L], L).

% del(X, [Y|L], [Y|L1]) :-
% Questa regola cerca di rimuovere l'elemento X da una lista, mantenendo intatti gli altri elementi.
del(X, [Y|L], [Y|L1]) :-
    del(X, L, L1).

% Definizione dello stato obiettivo goal
% goal(Situation) :-
% Verifica se la configurazione desiderata ([a, b, c]) è presente in una delle pile nella situazione corrente.
goal(Situation) :-
    member([a, b, c], Situation).

% Predicato solve
% solve(Start, Solution).
% Questo predicato è attualmente incompleto e non implementa alcuna logica per trovare una soluzione.
solve(Start, Solution).
