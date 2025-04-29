# 12 - Modelli generativi

Abbiamo già detto che sono stati definiti numerosi modelli di calcolo che sono **turing-equivalenti**.

> Per esempio il PascalMinimo.

Sia la Macchina di Turing che il PascalMinimo si occupano dei linguaggi nella medesima maniera. Dato un linguaggio - ossia un insieme di parole - possiamo *progettare* una macchina di Turing o un programma in PascalMinimo che **riconosce** le parole appartenti al linguaggio.

> Sono **modelli di calcolo riconoscitori**.

Possiamo approcciarci ai linguaggi in maniera duale - ossia possiamo progettare *uno strumento capace di generare tutte e sole le parole di un linguaggio.*

> Questo è un modello di calcolo **generativo** che prende il nome di **Grammatica.**

---
### Grammatiche

Una grammatica è una quadrupla $G=<V_T,V_N,P,S>$ dove:

- $V_T$ è l'insieme dei simboli terminali - ossia $\Sigma$.
- $V_N$ è l'insieme dei simboli non terminali.
- $S\in V_N$ è un particolare simbolo non terminale detto **Assioma**.
- $P$ è l'insieme delle produzioni - ossia - un insieme di coppie di parole $$(\alpha,\beta)\in(V_T\cup V_N)^* \times (V_T\cup V_N)^*$$ 
Una produzione $(\alpha,\beta)$ è indicata nella forma $\alpha\to\beta$ e deve esistere almeno una produzione del tipo $S\to\beta$.

L'obiettivo di una grammatica è quello di generare le parole di $V_T^*$ partendo dall'assioma e applicando diverse produzioni in $P$ una dopo l'altra.

---
### Notazioni per le Grammatiche

Siano $\alpha,\beta$ due parole indichiamo con $\alpha\beta$ la concatenazione di $\alpha$ e $\beta$.

Sia $G$ una grammatica:

- Siano $x,y \in (V_T\cup V_N)^*$ scriviamo $x \to_G y$ e dichiamo che **y deriva direttamente in G da x** se: $x=\alpha_1\alpha \alpha_2, y=\alpha_1\beta\alpha_2$ e $\alpha\to\beta$ è una produzione in $P$.

- Scriviamo $x\to_G^* y$ e diciamo che **y deriva in G da x** se esiste una sequenza di parole tali che da $x$ arrivo in $y$ attraverso delle produzioni.

Una parola $y$ è **generata da G** se $S\to^*_G y$.

> Una parola generata da $G$ prende il nome di **forma di frase**.

Sia $G=<V_T,V_N,P,S>$ una grammatica - il **linguaggio generato da G** che indichiamo con $L(G)$ - è *l'insieme delle parole generate da G* - ossia: $$L(G)=\{y\in V_T^*:S\to_G^*y\}$$
Se due o più produzioni hanno la stessa parte sinistra esse si possono raggruppare mediante il simbolo '|' - che si legge come "oppure".

$\epsilon$ rappresenta la **parola vuota** - una produzione $\alpha\to\epsilon$ è detta $\epsilon$**-produzione**.

> Con $\wedge$ indichiamo il *linguaggio vouto*.


