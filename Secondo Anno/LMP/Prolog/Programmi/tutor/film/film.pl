% film(titolo,durata,costo,[comicita,sentimentalita,horritudine,fantasy,volgarita]).
% utente(nome_utente, tempo_visione, [comicita,sentimentalita,horritudine,fantasy,volgarita]).

% Il costo non è stato preso in considerazione in questa versione del codice.

film(titolo1,2,[2,3,0,4,1]).
film(titolo2,1,[4,0,2,1,3]).
film(titolo3,10,[0.5,4.5,3,0.2,1.4]).
film(titolo4,10,[0.5,4.5,3,0.2,1.4]).
film(titolo5,2,[2,3,1,4,1]).
film(titolo6,3,[2,3,3,1,2]).

utente(utente1,17,[3,2,3,4,1]).
utente(utente2,24,[2,4,5,1,1]).
utente(utente3,12,[1,4,3,1,4]).
utente(utente4,9,[1,5,3,0,1]).


% ""main"" una lista di film LF per l'utente U è vera se:
% - U è un utente,
% - LF è una possibile lista di film non vuota
% - sono rispettati i vincoli di durata e ranking
:-op(300,xfx,una_lista_di_film).
una_lista_di_film(U,LF):-
	utente(U,D,_),
    lista_possibile(LF),
    LF \= [],
    lista_con_durate(LF,L1),
    durata_films_per_utente(D,L1),
    vincolo_ranking_soddisfatto(U,LF).

% vero se LF è una lista di film possibile, cioè se è vero che LF è un qualsiasi sottoinsieme della lista dei film disponibili
subset([], []).
subset([E|Tail], [E|NTail]):-
  subset(Tail, NTail).
subset([_|Tail], NTail):-
  subset(Tail, NTail).

lista_possibile(L):-
    setof(X,Y^Z^film(X,Y,Z),Lall),
    subset(Lall,L).

% vero se L2 è la lista con le durate dei film nella lista L1
lista_con_durate([],[]).
lista_con_durate([H1|T1],[H2|T2]):-
    film(H1,H2,_),
    lista_con_durate(T1,T2).

% vero se la somma delle durate in L2 è minore o uguale al tempo di visione dell'utente
durata_films_per_utente(_,[]).
durata_films_per_utente(D,[H|T]):-
    D >= H,
    D1 is D-H,
    durata_films_per_utente(D1,T),!.

% vero se le caratteristiche dell'utente rientrano in un range +-0.5 rispetto alle caratteristiche del film
film_per_utente([],[]).
film_per_utente([H1|T1],[H2|T2]):-
    H3 is H2+0.5,
    H4 is H2-0.5,
    H1 =< H3,
    H1 >= H4,
    film_per_utente(T1,T2).

% vero se ogni film è un film adatto all'utente
vincolo_ranking_soddisfatto(_,[]).
vincolo_ranking_soddisfatto(U,[H|T]):-
	utente(U,_,L),
    film(H,_,L1),
    film_per_utente(L,L1),
    vincolo_ranking_soddisfatto(U,T).