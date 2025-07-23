% Star Wars
sith(sidious).
sith(maul).
sith(exarkun).
sith(vader).
jedi(yoda).
jedi(kenobi).
jedi(macewindu).
jedi(ayalasecura).
spada(sidious).
spada(yoda).
spada(macewindu).
spada(kenobi).
spada(vader).
haduespade(ayalasecura).
haduespade(exarkun).


doppiaSpada(X) :-
    \+ spada(X),           
    jedi(X),             
    haduespade(X).    

doppiaSpada(X) :-
    \+ spada(X),           
    sith(X),             
    haduespade(X).  

doppiaLama(X) :-
    \+ spada(X),           
    sith(X),     
    \+ doppiaSpada(X).     

doppiaLama(X) :-
    \+ spada(X),           
    jedi(X),     
    \+ doppiaSpada(X). 


%%%%%%%%%%%%%%%%%%%%

/*Stati possibili*/
stato_valido(1).
stato_valido(2).
stato_valido(3).
stato_valido(4).
stato_valido(5).

mossa(Stato, destra, Stato_successivo) :-
    Stato_successivo is Stato + 1,
    stato_valido(Stato_succesivo).

mossa(Stato, sinistra, Stato_successivo) :-
    Stato_successivo is Stato + 1,
	stato_valido(Stato_succesivo).

soluzione(PosizioneFinale, PosizioneFinale, []).
soluzione(PosizioneInizale, PosizioneFinale, [Mossa | AltreMosse]) :-
    mossa(PosizioneInizale, Mossa, PosizioneIntermedia),
    soluzione(PosizioneIntermedia, PosizioneFinale, AltreMosse).
    
%%%%%%%%%%%%%%%%%%%%
/*Problema del ponte*/

stato_valido(0).
stato_valido(1).
stato_valido(2).
stato_valido(3).
stato_valido(4).
stato_valido(5).

sicuro(0).
sicuro(1).
sicuro(3).
sicuro(5).

mossa(Posizione, 1, NuovaPosizione) :-
    NuovaPosizione is Posizione + 1,
    stato_valido(NuovaPosizione),
    sicuro(NuovaPosizione).
    
mossa(Posizione, 2, NuovaPosizione) :-
    NuovaPosizione is Posizione + 2,
	stato_valido(NuovaPosizione),
	sicuro(NuovaPosizione).

soluzione(PosizioneFinale, PosizioneFinale, []).
soluzione(PosizioneIniziale, PosizioneFinale, [Mossa | AltreMosse]) :-
    mossa(PosizioneIniziale, Mossa, PosizioneIntermedia),
    soluzione(PosizioneIntermedia, PosizioneFinale, AltreMosse).