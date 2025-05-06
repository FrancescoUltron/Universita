# 15 - Da Macchina di Turing a Grammatica

Se un linguaggio $L$ è generato da una grammatica (di tipo $0$) allora esiste una macchina di Turing che accetta $L$. Questo dimostra:

- L'insieme dei linguaggi accettabili coincide con l'insieme dei linguaggi generabili ga grammatiche di tipo $0$.

- Le grammatiche di tipo $0$ sono un modello di calcolo che soddisfa la Tesi di Church-Turing.

**Teorema G5:** *Per ogni grammatica $G$ esiste una macchina di Turing $T$ che accetta* $L(G)$.

In conclusione i due teoremi dimostrano che:

**Corollario:** La classe dei linguaggi generati da grammatiche di tipo $0$ coincide con la classe dei linguaggi accettabili - detti anche: *Semi-decidibili* - *Semi-ricorsivi* oppure *Ricorsivamente enumerabili*.

**Osservazione:** Se $L(G)$ contiene un numero infinito di parole allora - poiché la macchina $NT_G$ non rigetta le parole contenenti qualche non terminale di $G$ - ogni computazione di $NT_G$ contiene qualche computazione deterministica che non termina.

Ossia la macchina **verifica se la parola in ottenuta coincide con quella in input** - quindi continua l'applicazione di produzioni fino a che non raggiungiamo una parola che coincide con quella in input. Se $x\in L(G)$ per poter rigettare la computazione deve continuare ad applicare produzioni fino a quando non ha confrontato $x$ con **tutte** le parole in $L(G)$.

Se $L(G)$ ha infinite parole allora la computazione non termina mai - ossia non tutte le computazioni deterministiche $NT_G(x)$ rigettano. Dunque quando $x\notin L(G)$ $NT_G(x)$ **non rigetta**.

> La caratteristica di $G$ di tipo $0$ che impedisce a $NT_G$ di rigettare?

Per ogni parola $x$ esiste sempre una computazione $NT_G(x)$ che non termina mai - fino a quando la parola generata contiene un non terminale - $NT_G$ non può sapere se - prima o poi - verrà generata $x$ - perché - anche se la parola generata ad un certo passo della computazione è più lunga di $x$ - nulla garantisce che non si possano applicare produzioni che riducano la lunghezza della parola generata a quel passo.

> **Perchè una grammatica di tipo 0 ammette produzioni in cui la parte sinistra è più lunga della destra.**

Questo vincolo è proibito dalle grammatiche di tipo $1$.