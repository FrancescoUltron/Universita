% prova antenne
antenna(1,q).
antenna(2,c).

posizione((AntennaID,TipoAntenna),Mappa9x9,Posizione):-
    antenna(AntennaID,TipoAntenna),
    possibile_mappa_con_antenna((AntennaID,TipoAntenna),Mappa9x9,Posizione).

possibile_mappa_con_antenna((Id,Tipo),Mappa9x9,(X,Y)):-
    length(Mappa9x9,9),
    lunghezzaSublists(Mappa9x9),
    posizioneAntenna((Id,Tipo),X,Y,Mappa9x9).

posizioneAntenna((Id,q),1,Y,[H1,H2,H3|_]):-
    member(Id,H2),
    posizioneYAntenna((Id,q),H,Y).

posizioneAntenna((Id,Tipo),X,Y,[_|T]):-
    posizioneAntenna((Id,Tipo),X1,Y,T),
    X is X1+1.

posizioneYAntenna((Id,_),[Id|T],1).
posizioneYAntenna((Id,Tipo),[_|L],K):-
    posizioneYAntenna((Id,Tipo),L,K1),
    K is K1+1.

posizioni(ListaTotaliAntenne,ListaAntenneScelte,Mappa9x9,Posizioni):-
    sublist(ListaTotaliAntenne,ListaAntenneScelte),
    ListaAntenneScelte \== [],
    possibiliPosizioni(ListaAntenneScelte,Posizioni,Mappa9x9).

possibiliPosizioni([],[],_).
possibiliPosizioni([(Id,Tipo)|T1],[Posizione|T2],Mappa9x9):-
    posizione((Id,Tipo),Mappa9x9,Posizione),
    possibiliPosizioni(T1,T2,Mappa9x9).
    
lunghezzaSublists([]).

lunghezzaSublists([H|L]):-
    length(H,9),
    lunghezzaSublists(L).

sublist(L,LR):-
  append(_,L2,L),
  append(LR,_,L2).
