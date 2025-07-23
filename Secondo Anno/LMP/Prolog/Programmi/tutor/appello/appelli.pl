% appello(nome,annocorso,[data],aula,sessione)

appello(lmp,2,[13,07,2022],a2,estiva).
appello(asd,3,[13,07,2022],a2,estiva).

appello_con_informazioni_contraddittorie(Appello):-
    appello_stesso_giorno_e_aula(Appello,_),
    appello_stesso_giorno_stesso_anno(Appello,_),
    piu_di_due_appelli_stessa_sessione(Appello),
    distanza_minima_non_rispettata(Appello,_).


appello_stesso_giorno_e_aula(A1,A2):-
    appello(A1,_,[D,M,Y],A,_),
    appello(A2,_,[D,M,Y],A,_),
    A1 \== A2.

appello_stesso_giorno_stesso_anno(A1,A2):-
    appello(A1,N,[D,M,Y],_,_),
    appello(A2,N,[D,M,Y],_,_),
    A1 \== A2.

piu_di_due_appelli_stessa_sessione(A):-
    appello(A,N,D,Au,Sessione),
    setof(app(Nome,Session), Y^Aula^G^appello(Nome,Y,G,Aula,Session),L),
    length(L,N),
    N > 2.

quest(A1,A2):-
    appello(A1,_,[D1,M1,Y1],_,_),
    appello(A2,_,[D2,M2,Y2],_,_),
