family(
          person(tom, fox, date(7, may, 1950), works(bbc, 6000)), /*Padre*/
          person(ann, fox, date(9, may, 1951), unemployed), /*Madre*/
          [person(pat, fox, date(5, may, 1973), unemployed), /*Figli*/
           person(jim, fox, date(5, may, 1973), unemployed)]).

family(
    person(john, doe, date(1, jan, 1960), works(nasa, 20000)),
    person(jane, doe, date(3, feb, 1965), unemployed),
    [person(sue, doe, date(10, mar, 1990), works(google, 18000))]
).


% Un uomo è marito se è il primo elemento nella struttura family
husband(X) :-
    family(X, _, _).

% Una donna è moglie se è il secondo elemento nella struttura family
wife(X) :-
    family(_, X, _).

% Un bambino è uno dei membri della lista dei figli
child(X) :-
    family(_, _, Children),
    member(X, Children).

% Implementazione del predicato 'member' 
member(X, [X | _]).
member(X, [_ | T]) :-
    member(X, T).

% Una persona "esiste" se è marito, moglie o figlio
exists(Person) :-
    husband(Person);
    wife(Person);
    child(Person).

% Ottieni la data di nascita da una struttura person
dateofbirth(person(_, _, Date, _), Date).

% Ottieni lo stipendio di una persona che lavora
salary(person(_, _, _, works(_, S)), S).

% Se la persona è disoccupata, lo stipendio è 0
salary(person(_, _, _, unemployed), 0).
