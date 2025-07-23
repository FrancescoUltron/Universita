tabella_oraria_settimanale(Settimana):-
    Nslots is 6,
    length(Settimana,Nslots),
    tempo_totale_attività(NslotsAttivo),
    NslotsAttivo < Nslots,
    tabella_possibile(Settimana),
    Settimana = [A,B,C|R],
    write(l(A,B,C)),nl,
    vincoli_soddisfatti(Settimana).


tempo_totale_attività(Tempo):-
    setof((A,T),X^oc_slot(A,X,T),L),
    tempo_totale(L,Tempo).

tempo_totale([],0).
tempo_totale([(tempo_libero,_)|L],T):-
    !,
    tempo_totale(L,T).
tempo_totale([(_,T2)|L],T):-
    !,
    tempo_totale(L,T1),
    T is T1 + T2/15.
    
    
vincoli_soddisfatti(Settimana):-
    setof((A,T),X^oc_slot(A,X,T),ListaAttività),
	inSettimanaAbbiamoTutteLeAttività(ListaAttività,Settimana).


inSettimanaAbbiamoTutteLeAttività([],Settimana).
inSettimanaAbbiamoTutteLeAttività([(tempo_libero,T)|Rest],Settimana):-
	!,	
    inSettimanaAbbiamoTutteLeAttività(Rest,Settimana).    

inSettimanaAbbiamoTutteLeAttività([(A,T)|Rest],Settimana):-
    slotInSettimana(A,Settimana,N),
    T is N*15,
	inSettimanaAbbiamoTutteLeAttività(Rest,Settimana).    
    

slotInSettimana(A,[],0).
slotInSettimana(A,[A|L],N):-
    !,
    slotInSettimana(A,L,N1),
    N is N1 + 1. 

slotInSettimana(A,[_|L],N):-
    slotInSettimana(A,L,N).

    
        
tabella_possibile([]).
tabella_possibile([_15Min|L]):-
    oc_slot(_15Min,_,_),
    tabella_possibile(L).
 


/*
oc_slot(katy,prj,240).
oc_slot(sfidanow,prj,240).
oc_slot(step,prj,240).
oc_slot(giustiziagiusta,prj,180).
oc_slot(revert,prj,180).

oc_slot(foi,lez,360).
oc_slot(lmp,lez,240).
*/
oc_slot(c1,coll,30).
oc_slot(c2,coll,30).

/*
oc_slot(c3,coll,60).
oc_slot(c4,coll,60).
oc_slot(c5,coll,60).
oc_slot(c6,coll,60).

oc_slot(studio,studio,240).
oc_slot(revisione,studio,120).
*/


oc_slot(tempo_libero,tempo_libero,1000).

