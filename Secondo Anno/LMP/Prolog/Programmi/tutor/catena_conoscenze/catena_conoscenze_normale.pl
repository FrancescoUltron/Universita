rel(marco,luca,parentiPrimoGrado).
rel(sandro,francesca,parentiPrimoGrado).
rel(marco,sandro,amici).
rel(marco,pietro,colleghi).
rel(francesca,pietro,colleghi).
rel(luca,giovanni,amici).
rel(giovanni,ettore,amici).
rel(ettore,francesca,colleghi).


esiste_rete_conoscenza(X,Y):-
    rel(X,Y,_),!.

esiste_rete_conoscenza(X,Y):-
    rel(Y,X,_),!.
    
esiste_rete_conoscenza(X,Y):-
    rel(X,Z,_),
    esiste_rete_conoscenza(Z,Y).


catena_conoscenza(X,Y,[(X,Y)]):-
    rel(X,Y,_).

catena_conoscenza(X,Y,[(X,Z)|CATENA]):-
    rel(X,Z,_),
    esiste_rete_conoscenza(X,Y),
    catena_conoscenza(Z,Y,CATENA).
