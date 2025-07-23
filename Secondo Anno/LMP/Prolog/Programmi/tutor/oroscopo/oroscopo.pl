pezzo_msg(situazioneAttualeAmore,[frase1,frase2]).
pezzo_msg(situazioneAttualeLavoro,[frase3,frase4]).
pezzo_msg(situazioneAttualeSalute,[frase5,frase6]).
pezzo_msg(situazioneAttualeVincite,[frase7,frase8]).
pezzo_msg(eventoFuturoAmore,[frase9,frase10]).
pezzo_msg(eventoFuturoLavoro,[frase11,frase12]).
pezzo_msg(eventoFuturoSalute,[frase13,frase14]).
pezzo_msg(eventoFuturoVincite,[frase15,frase16]).
pezzo_msg(fraseGenericaFelicita,[frase17,frase18]).
pezzo_msg(fraseGenericaEventoPositivo,[frase19,frase20]).

persona(davide,[20,03,2000]).

eventiFuturi(P):-
    pezzo_msg(eventoFuturoAmore,L1),
    pezzo_msg(eventoFuturoLavoro,L2),
    pezzo_msg(eventoFuturoSalute,L3),
    pezzo_msg(eventoFuturoVincite,L4),
    member(H1,L1),
    member(H2,L2),
    member(H3,L3),
    member(H4,L4),
    append([H1],[H2],P1),
    append(P1,[H3],P2),
    append(P2,[H4],P).

situazioneAmore(P):-
    pezzo_msg(situazioneAttualeAmore,L1),
    pezzo_msg(eventoFuturoAmore,L2),
	member(H1,L1),
    member(H2,L2),
    append([H1],[H2],P).
              
situazioneSalute(P):-
    pezzo_msg(situazioneAttualeSalute,L1),
    pezzo_msg(eventoFuturoSalute,L2),
    member(H1,L1),
    member(H2,L2),
    append([H1],[H2],P).
              
situazioneLavoro(P):-
    pezzo_msg(situazioneAttualeLavoro,L1),
    pezzo_msg(eventoFuturoLavoro,L2),
    member(H1,L1),
    member(H2,L2),
    append([H1],[H2],P).
              
messaggio(M):-
    eventiFuturi(M).

messaggio(M):-
    situazioneAmore(M).

messaggio(M):-
    situazioneLavoro(M1),
    situazioneSalute(M2),
    append(M1,M2,M).
    
messaggio(M):-
    pezzo_msg(fraseGenericaEventoPositivo,[M1|_]),
    situazioneSalute(M2),
    append([M1],M2,M).

messaggi(L):-
    setof(M,messaggio(M),L).

messaggio(_,_,_,M):-
	messaggi(L),
    length(L,N),
    random(1,N,X),
	element_at(M,L,X).

element_at(X,[X|_],1).
element_at(X,[_|L],K) :- K > 1, K1 is K - 1, element_at(X,L,K1).


