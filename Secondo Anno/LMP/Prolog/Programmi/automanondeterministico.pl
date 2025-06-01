/*Automa non deterministico*/

final(s3).

trans(s1,a,s1).
trans(s1,a,s2).
trans(s1,b,s1).
trans(s2,b,s3).
trans(s3,b,s4).

silent(s2,s4).
silent(s3,s1).

/*is true if the automaton, 
starting from the state State as initial state, 
accepts the string String. 
The accepts relation can be defined by three clauses*/

accepts(S, []) :- /*Automa accetta la stringa vuota se lo stato corrente di accettazione è uno stato finale*/
    final(S).

accepts(S, [X|Rest]) :- /*Automa accetta la stringa se leggendo X esiste una transizione da S a S1*/
    trans(S, X, S1),    /*E se l'automa accetta il resto della stringa partendo da S1*/
    accepts(S1, Rest).

accepts(S, String) :- /*Simile a prima, ma con mossa silenziosa*/
    silent(S, S1),
    accepts(S1, String).






























