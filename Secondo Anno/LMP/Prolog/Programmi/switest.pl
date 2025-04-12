%Capitolo 1 - An overwiew on Prolog

parent( pam, bob). 
parent( tom, bob). 
parent( tom, liz). 
parent( bob, ann). 
parent( bob, pat). 
parent( pat, jim).

female(pam). %pam is female
male(tom). 
male(bob). 
female(liz). 
female(pat). 
female(ann). 
male(jim).

offspring(Y,X) :- 
	parent(X,Y).

mother(X,Y) :-
    parent(X,Y),
    female(X).

grandparent(X,Z) :-
    parent(X,Y),
    parent(Y,Z).

sister(X,Y) :-
    parent(Z,X),
    parent(Z,Y), %%Z deve essere genitore di entrambi
    female(X).

%Translate the following statements into prolog rules:
%(a) Everybody who has a child is happy (introduce a one-argument
%relation happy).
%(b) For all X, if X has a child who has a sister then X has two children
%(introduce new relation hastwochildren)

hasachild(X) :- 
    parent(X,_).

happy(X) :-
    hasachild(X).

hastwochildren(X) :-
    parent(X,Y),
    parent(X,Z),
    Y \= Z.

%Define the relation grandchild using the parent relation. 
%Hint: It will be similar to the grandparent relation

grandchild(X,Z) :-
    parent(Y,X),
    parent(Z,Y).

%Define the relation aunt( X, Y) in terms of the relations parent and sister.
%As an aid you can first draw a diagram in the style of Figure 1.3 for the
%aunt relation

aunt(X,Y) :-
    sister(Z,X),
    parent(Z,Y).

%Queste regole non funzionano con pam e tome perché
%Per questi non sono stati definiti dei genitori 
%Quindi la regola sister non funziona.

predecessor(X,Z) :-
    parent(X,Z).

predecessor(X,Z) :-
    parent(X,Y),
    predecessor(Y,Z).

