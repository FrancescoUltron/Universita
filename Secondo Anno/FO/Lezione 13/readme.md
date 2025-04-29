# 13 - La gerarchia di Chomsky

Le grammatiche formali vennero formalizzate da Noam Chomsky negli anni '50. Al fine di individuare uno strumento che permetesse di formalizzare proprietà sintattiche dei linguaggi naturali.

Chomsky classificò le grammatiche in quattro tipi:

- **Tipo 0**
- **Tipo 1**
- **Tipo 2**
- **Tipo 3**

Ogni tipo di grammatica ha **vincoli sempre più stretti** - inoltre i vincoli delle grammatiche di tipo $i$ sono rispetatti anche dalle grammatiche di tipo $i+1$. La sequenza dei quattro tipi di grammatica è nota come **gerarchia di Chomsky.**

Indichiamo con:

- $G0$ - Insieme delle grammatiche di tipo $0$ o dei linguaggi generati da grammatiche di tipo $0$.

- $G1$ - Insieme delle grammatiche di tipo $1$ o dei linguaggi generati da grammatiche di tipo $1$.

- $G2$ - Insieme delle grammatiche di tipo $2$ o dei linguaggi generati da grammatiche di tipo $2$.

- $G3$ - Insieme delle grammatiche di tipo $3$ o dei linguaggi generati da grammatiche di tipo $3$. 

// Immagine gerarchia

> Nota come $G3\subseteq G2 \subseteq G1 \subseteq G0$.

---
### Tipi di grammatiche

Dette anche *grammatiche illimitate* include tutte le grammatiche formali. 

> Qualsisasi grammatica formale è di tipo $0$.

Le Grammatiche di tipo $1$ - dette anche **grammatiche dipendenti dal contesto** o **context-sensitive** generano linguaggi context-sensitive - hanno soltanto produzioni in cui la lunghezza della parte destra è maggiore o uguale alla lunghezza della parte sinistra.

> Non sono ammesse produzioni che riducono la lunghezza della parola.

Le Grammatiche di tipo $2$ - dette anche **grammatiche libere dal contesto** o **context-free** generano linguaggi context-free - hanno solo produzioni la cui parte sinistra consiste solamente di un carattere non terminale.

> Le produzioni di una grammatica di tipo 2 hanno tutte la forma $A\to \alpha$ con $A\in V_N$ e $\alpha \in (V_T \cup V_N)^*$.

Le produzioni di una grammatica di tipo 2 soddisfano i vincoli imposti dalle grammatiche di tipo 1 - di conseguenza anche di tipo 0.

Le Grammatiche di tipo 3 - dette anche **grammatiche regolari** - dispongono solo di produzioni la cui parte sinistra consiste di un singolo carattere non terminale e la cui parte destra consiste di un singolo simbolo terminale - che può essere eseguito o preceduto (non entrambe nella stessa grammatica) - da un singolo carattere non terminale.

> Le produzioni di una grammatica di tipo 3 hanno tutte la forma $A\to a$ oppure $A\to aB$ con $A,B\in V_N$ e $a\in V_T$.

---
### Gerarchia di Chomsky e la parola vuota

La grammatica di tipo 0 è l'unica che può generare la parola vuota $\epsilon$ - infatti necessitiamo di una produzione nella forma $\alpha \to \epsilon$. dove $\alpha$ contiene almeno un non terminale.

> In questa produzione la lunghezza della parte sinistra (1) è maggiore della lunghezza della parte destra (0).

Quindi una grammatica che contiene una produzione del tipo: $\alpha \to \epsilon$ non può essere del tipo 1.

> Tuttavia grammatiche del tipo 1/2/3 possono essere estese in modo tale da poter generare lo *stesso linguaggio della grammatica di partenza arricchito della parola vuota.*

Semplicemente aggiungiamo un nuovo non terminale $S'$ che prendere il posto di assioma dal vecchio non terminale $S$ e aggiungiamo le seguenti produzioni:

- $S'\to \epsilon$
- $S'\to S$

**Teorema G1:** Sia $G$ una grammatica di tipo $t > 0$ e sia $G'$ la grammatica ottenuta:

- Aggiungendo a $G$ un nuovo non terminale $S'$ che sarà l'assioma in $G'$.
- Inserendo la produzione $S'\to \epsilon$.
- Inserendo la produzione $S'\to S$.

Allora $L(G') = L(G) \cup \{\epsilon\}$

> Possiamo leggere il teorema anche nel seguente modo: In una grammatica di tipo $t>0$ la produzione $S\to \epsilon$ è permessa - ma solo se $S$ non appare nel lato destro di alcuna produzione.

---
### Gerarchia di Chomsky e le $\epsilon$ produzioni

Una $\epsilon-$produzione  è una produzione della forma $\alpha \to \epsilon$ - ossia la parte destra è la parola vuota.

> Le regole delle grammatiche di tipo 1 impediscono che le grammatiche di tipo $t>0$ dispongono di $\epsilon-$produzioni.

Queste restrizioni le possiamo togliere per le grammatiche di tipo 3 e di tipo 3 grazie al seguente teorema.

**Teorema G2:** Sia $G$ una grammatica di tipo $t>1$ e sia $G'$ la grammatica ottenuta aggiungendo a $G$ un numero qualsiasi di $\epsilon-$produzioni. Allora $L(G')=L(G)\cup \{\epsilon\}$.

Da questo teorema concludiamo che: *se rimuovendo le $\epsilon-$produzioni di una grammatica $G$ otteniamo una grammatica di tipo $t>1$ - allora il linguaggio generato da $G$ è un linguaggio di tipo $t$ aumentato della sola parola vuota.*

**Teorema G3:** Per ogni grammatica $G$ di tipo 0 esiste una grammatica $G'$ ottenuta aggiungendo a una grammatica di tipo 1 opportune $\epsilon$-produzioni tale che $L(G)=L(G')$.
