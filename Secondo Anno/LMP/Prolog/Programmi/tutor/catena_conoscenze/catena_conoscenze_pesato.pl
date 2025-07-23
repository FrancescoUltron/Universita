%1: parente di primo grado
%2: amico
%3: collega
%
rel(marco,luca,1).
rel(sandro,francesca,1).
rel(marco,sandro,2).
rel(marco,pietro,3).
rel(francesca,pietro,3).
rel(luca,giovanni,2).
rel(giovanni,ettore,2).
rel(ettore,francesca,3).
rel(X,Y,N):- rel(Y,X,N),!.

catena_conoscenza(A1,A2,[A1,A2],N):- rel(A1,A2,N).

catena_conoscenza(X,Y,[X|T],N):-
    rel(X,Z,N1),
    catena_conoscenza(Z,Y,T,M),
    N is N1+M.

catena_conoscenza_da_percorrere(X,Y,CMigliore):-
    setof((L,C),X^Y^catena_conoscenza(X,Y,L,C),List),
    minof(List,CMigliore).

minof([(L,C)],(L,C)).
minof([(L,C)|T],(L,C)):-
    minof(T,(_,C1)),
    C < C1.

minof([_|T],(L,C)):-
	minof(T,(L,C)).
