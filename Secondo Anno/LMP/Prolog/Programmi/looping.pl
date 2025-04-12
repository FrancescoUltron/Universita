/*looping*/

%Regola che conta fino a 10 partendo da un numero casuale.

count_to_10(10) :- write(10), nl.
count_to_10(X) :- 
    write(X), nl,
    Y is X + 1,
    count_to_10(Y).

count_down(Low, High) :-
    between(Low, High, Value),
    Z is High - Value,
    write(Z), nl.

count_up(Low, High) :-
    between(Low, High, Value),
    Z is High + Low,
    write(Z), nl.

/*GUESSING GAME*/

guess_num :- loop(start).
loop(15) :- write('You guessed it').
loop(X) :-
    X \= 15.
    write('Guess number '),
    read(Guess),
    write(Guess),
    write(' is not the number'), nl.

    