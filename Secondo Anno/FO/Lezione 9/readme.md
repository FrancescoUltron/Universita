# 9 - Indecidibilità dell'Halting Problem e riduzioni

**Teorema 5.5:** *Il linguaggio $L_H$ non è decidibile*.

> **Nota:** Prima di procedere, dobbiamo modificare leggermente la notazione che abbiamo utilizzato sino ad ora. Osserviamo che - data una macchina di Turing $T$ ed un suo input $x$ - il valore di $o_T (x)$ e definito soltanto per gli $x$ tali che la computazione $T(x)$ termina. Poiche in quanto segue dovremo pesantemente utilizzare la possibilità che una computazione non termini, con un leggero abuso di notazione indicheremo con $T(x)$ sia la* computazione eseguita* dalla macchina $T$ sull’input $x$ che il suo *esito*. Piu in particolare, assumeremo: $$T(x)=  
\begin{cases}  
q\in Q_F\;\;\;\;\;se\;la\;computazione\;T(x)\;termina\\
\\
non\;termina\;\;\;\;\;se\;la\;computazione\;non\;T(x)\;termina
\end{cases} $$

Sappiamo che $L_H$ è accettabile - siccome non è decidibile - allora $L_H^C$ **non è accettabile**.

> Significa che quando stiamo aspettando se l'esecuzione del programma termini - la domanda difficile da rispondere è: *ma non è che è andato in loop?*

---
### Ancora sulla simulazione a scatola chiusa

Nel teorema di prima abbiamo costruito una serie di macchine partendo dalla macchina $T$ - che non sappiamo come è fatta. Quindi per costruire tutte le macchine successive abbiamo fatto una specie di simulazione **a scatola nera.**

> Ossia non sappiamo come è costruita la macchina e non ci interessano le quintuple. E' simile alla simulazione a scatola chiusa.

In generale possiamo usare una macchina $T_0$ all'interno di una macchina $T_1$ in un modo più complesso.

> Il linguaggio deciso/accettato da $T_0$ potrebbe essere diverso da quello deciso/accettato da $T_1$ - quindi posso modificare l'input di $T_1$ 'prima di passarlo' a $T_0$.

---
### Riduzioni fra linguaggi

Dati due linguaggi $L_1\subseteq\Sigma^*_1$ e $L_2\subseteq\Sigma^*_2$ diciamo che $L_1$ è **(many to one) riducibile** ad $L_2$ - scrivendo $L_1 \leq L_2$ se esiste una funzione $f:\Sigma^*_1\to\Sigma_2^*$ tale che:

1) $f$ è totale e calcolabile - ossia è definita per ogni parola $x\in\Sigma_1^*$ e esiste una macchina di Turing di tipo trasduttore $T_f$ tale che - per ogni parola $x\in\Sigma^*_1$ - la computazione $T_f(x)$ termina con la parola $f(x)\in\Sigma_2^*$ scritta sul nastro di output.

2) Per ogni $x\in\Sigma_1^*$ vale che: $x\in L_1 \iff f(x)\in L_2$.

Questo concetto ci permette di correllare tra loro linguaggi in modo tale che:

- La dedicibilità/accettabilità di un linguaggio implichi la dedicibilità/accettabilità dei linguaggi **ad esso riducibili.**

> Se dimostro che un linguaggio $L_3 \leq L_4$ e so che $L_4$ è decidibile - allora anche $L_3$ è decidibile.

> Se dimostro che un linguaggio $L_3 \leq L_4$ e so che $L_4$ è accettabile - allora anche $L_3$ è accettabile.

- La non dedicibilità/accettabilità di un linguaggio implichi la non dedicibilità/accettabilità dei linguaggi **cui esso si riduce.**

> Se dimostro che $L_1 \leq L_2$ e so che $L_1$ è non decidibile - allora $L_2$ non è decidibile.

> Se dimostro che $L_1 \leq L_2$ e so che $L_1$ è non accetabile - allora $L_2$ non è accettabile.

---
### Riduzioni (many to one) - Esempio

Facciamo una dimostrazione di non decidibilità di un linguaggio mediante riduzioni.
Consideriamo il seguente linguaggio: $$L_{H0} = \{i\in\mathbb{N}:i\;è\;la\;codifica\;di\;una\;macchina\;di\;Turing\;T_i\;e\;T_i(0)\;termina\}$$
> Quindi $i\in L_{H0}$ se $i$ è la codifica di una macchina $T_i$ e la computazione di $T_i$ con inputa la parola costituita da soli $0$ termina.

Questo linguaggio è una *restrizione* di $L_{H}$ - ma contiene solo coppie $(i,0)$. La macchina di Turing che accetta $L_H$ accetta anche $L_{H0}$ - sembra anche più semplice rispetto a $L_{H}$ quindi forse è decidibile. E invece non è vero che $L_{H0}$ è "più facile" di $L_H$.
