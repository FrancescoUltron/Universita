# Liste, Operatori e Aritmetica in Prolog

 Una delle strutture più importanti del Prolog è quella delle liste che ci permetteranno di svolgere moltissime operazioni.

---
### Rappresentazione delle liste

La lista è una *struttura dati semplice* - è una sequenza di elementi numerici o di oggetti,
``` Prolog
[ann,tennis,tom,skiing]
```

Abbiamo già visto come in realtà come tutti gli oggetti strutturati in Prolog sono **alberi** - lo stesso vale per le liste.

Come facciamo a rappresentare una lista come un oggetto standard in Prolog - molto semplice - abbiamo due casi:

- La lista è **vuota**.
- La lista **non** è vuota.

Nel primo caso la possiamo rappresentare come un atomo in questo modo:
``` Prolog
[]
```

Nel secondo caso invece la lista sarà costiuita da due parti:

- **Testa** - ossia il primo elemento.
- **Coda** - Il restante della lista.

Nell'esempio di prima:
``` Prolog
[ann,tennis,tom,skiing]

/*ann è la testa.
[tennis,tom,skiing] è la coda.*/
```

La testa può essere qualsiasi tipo di elemento - la coda può essere solo una lista. Testa e coda sono combinate da un **funtore speciale** 

> Il funtore dipende dall'implementazione - noi useremo il carattere ".".

``` prolog
.(Head, Tail)

/*La coda è sempre una lista quindi possiamo concatenare nuovi elementi con lo stesso principo di come concateniamo testa e coda*/

.(ann, .(tennis, .(tom, .(skiing, []))))
```

> Notiamo come la rappresentazione con le parentesi quadrate è solo per semplificare il lavoro del programmatore - ricordiamo infatti che la struttura della lista è rappresentata internamente da un albero binario.

> Gli elementi di una lista possono essere altre liste.

Torna molto utile poter lavorare sulla testa e sulla coda come due entità separate. Possiamo separarle tramite la seguente notazione:
```
L = [H|T] %dove L è una lista - H è la testa - T è la coda

/*Possiamo utilizzare | in senso più generale comprendendo più elementi nella testa - per esempio:*/

L = [H1, H2, T] %Nella testa avremo i primi due elementi della testa
```
---
### Alcune operazioni sulle liste

Proviamo ad implementare alcune operazioni sulle liste - in particolare:

- Controllare se se un oggetto **appartiene** ad una lista.
- **Concatenazione** di due liste.
- **Aggiungere o eliminare** un elemento da una lista.

> Nota: La posizione degli elementi in una lista conta.

---
**Appartiene**

Implementiamo la regola nel seguente modo: **appartiene(X,L)** dove $X$ è un oggetto e $L$ è un lista - è vero se $X\in L$.

``` Prolog
/*
X appartiene ad L se:
	X è la testa di L oppure
	X appartiene alla coda di L
*/

appartiene(X, [X|T]).
appartiene(X, [H|T]) :-
    appartiene(X,T).
```

