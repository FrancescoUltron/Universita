homePage(PaginaRiempita, ListaArticoliConImportanza):-
    is_valid(ListaArticoliConImportanza), % controllo se la lista input è valida
    crea_pagina(ListaArticoliConImportanza, PaginaInit), % creo una pagina con i soli articoli che appaiono un numero di volte corrispondende al quadrato dell'importanza
    length(PaginaInit, LenPag), % ottengo la lunghezza della pagina attuale, cioè numero di celle occupate dagli articoli
    LenPag =< 50, % il numero di articoli deve essere <= 50
    Slack is 50 - LenPag, % Slack è il numero di "vuoto" da aggiungere per rendere la pagina lunga 50 celle
    riempi_con_vuoto(PaginaInit, Slack, PaginaRiempita). % uso la lista di articoli e aggiungo in coda Slack volte "vuoto" per rendere la pagina lunga 50 celle
   

riempi_con_vuoto([], 0, []).
riempi_con_vuoto([H|T], Slack, [H|RestPag]):-
    riempi_con_vuoto(T, Slack, RestPag).
riempi_con_vuoto([], Slack, [-|RestPag]):-
    Slack > 0,
    SlackMinus1 is Slack - 1,
    riempi_con_vuoto([], SlackMinus1, RestPag).
    

crea_pagina([], []).
crea_pagina([(A1, 1)|RestArticoli], Pag):-
    append([A1], RestPag, Pag),
    crea_pagina(RestArticoli, RestPag).

crea_pagina([(A1, 2)|RestArticoli], Pag):-
    append([A1, A1, A1, A1], RestPag, Pag),
    crea_pagina(RestArticoli, RestPag).

crea_pagina([(A1, 3)|RestArticoli], Pag):-
    append([A1, A1, A1, A1, A1, A1, A1, A1, A1], RestPag, Pag),
    crea_pagina(RestArticoli, RestPag).
    

is_valid([]).
is_valid([(_, Prio)|REST]):-
    Prio >= 1,
    Prio =< 3,
    is_valid(REST).

