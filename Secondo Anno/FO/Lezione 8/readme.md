# 8 - Linguaggi non accettabili e Halting Problem

### Cantor e grandezza dell'infinito

Prima di potere parlare degli argomenti della lezione dobbiamo introdurre alcuni concetti riguardanti il lavoro di Cantor.

Essenzialemente - Cantor - dimostrò che esistono insiemi **infiniti** *piccoli* e insiemi **infinito** *grandi*. 

> *piccolo e grande* sono basati sull'idea di **corrispondenza biunivoca**.

> Un **numero transfinito** è la "grandezza" di un insieme infinito. 

Cantor ha dimostra che non esiste una corrispondenda biunivoca fra l'insieme dei numeri naturali e l'insieme dei numeri reali. Ossia che **l'insieme dei numeri reali è strettamente più grande dell'insieme dei numeri naturali.**

Più nel dettaglio Cantor ha dimostrato che non esiste una corrispondenza biunivoca fra l'insieme dei numeri naturali e l''intervallo reale $[0,1]$ - ossia in questo ci sono **infinitissimamente** più punti di quanti sono i numeri naturali.

> Questo vale per ogni intervallo $[0,\epsilon]$ - anche per $\epsilon$ piccolissimi.

---
### Problemi irrisolvibili

Possiamo individuare la cardinalità dell'insieme delgi algoritmi - infatti la **tesi di Church-Turing** afferma che ad ogni algoritmo corrisponde una macchina di Turing che calcola la stessa funzione.
Quello che ci interessa è quindi calcolare la **cardinalità** dell'insieme $T_{corsivo}$ delle macchine di Turing definite sull'alfabeto $\{0,1\}$ e dotate di un singolo nastro. 

**Teorema 5.1:** *Sia $\Sigma$ un insieme finito. Allora l'insieme $\Sigma^*$ costituito dalle parole di lunghezza finita di caratteri di $\Sigma$ è numerabile*.

Tramite questo teorema possiamo dimostrare che:

**Teorema 5.2:** *L'insieme $T_{corsivo}$ delle macchine di Turing definite sull alfabeto $\{0,1\}$ e dotate di un singolo nastro - più eventuale nastro di output - è numerabile*.

> Molto velocemente - abbiamo una macchina $T$ che può essere rappresentata come segue: $$\beta_T=b^Q(q_0)\otimes b^Q(q_{1_1})-b_{1_1}-b_{1_2}-b^Q(q_{1_2})-m_1\oplus...\oplus b^Q(q_{h_1})-b_{h_1}-b_{h_2}-b^Q(q_{h_2})-m_h\oplus$$
> Siccome queste parole sono uniche allora abbiamo dimostrato un biezione tra $T_{corsivo}$ e $\Sigma^*$.

Dimostrata la numerabilità di $T_{corsivo}$  - osserviamo che $\forall T\in T_{corsivo}$ - possiamo esprimere esplicitamente la biezione tra $T_{corsivo}$ e un sottoinsieme dei numeri naturali.
Ossia trasformiamo la codifica $\beta_T$ di una macchina di Turing $T$ in un numero naturale $v(T)$ in modo tale che per ogni coppia di macchine di Turing $T$ e $T'$ - $v(T)\neq v(T')$.

Sia $T\in T_{corsivo}$ una maccchina di Turing ad un nastro e $B_T$ la sua codifica. Iniziamo a trasformare la parola in una parola in $\{0,1,2,3,4,5,6,7,8,9\}^*$ in questo modo:

- Sostituiamo i caratteri $s,f,d \in \beta_T$ con  $5,6,7$.

- Sostituiamo ogni carattere $-$ in $\beta_T$ con il carattere $4$.

- Sostituiamo ogni $\oplus$ e $\otimes$ con i caratteri $3,2$.

- Sostituiamo $\square$ con il carattere $9$.

- Premettiamo davanti a questa stringa il carattere $1$.

La parola in $\{0,1,2,3,4,5,6,7,8,9\}^*$ così ottenuta può essere considerata come un **numero espresso in notazione decimale** - quindi $v(T)\in \mathbb{N}$ associato - univocamente a $T$.

Ora che sappiamo rappresentare le macchine di Turing mediante numeri naturali - una macchina di Turing rappresentata dall'intero $h$ la indichiamo come $T_h$.
Ordiniamo le macchine nel seguente modo: $$T_h < T_k\;\;\;\;se\;\;h<k$$
Quindi abbiamo una 'prima' macchina che è rappresentata dall'intero più piccolo di tutti - una 'seconda' e così via - quindi:

- $T_{h_1}$ è la prima macchina.
- $T_{h_2}$ è la prima macchina.
- $T_{h_i}$ è la $i-esima$ macchina.

Se ci limitiamo a considerare macchine di Turing su alfabeto binario - anche l'input di una $TM$ è rappresentato da un numero intero.

> Come distinguiamo $101$ da $0101$ o $00101$? Semplicemente inseriamo un $1$ di fornte alla parola.
> Quindi il 'primo' $1$ rappresenta $\square$ - il 'secondo' input $10$ rappresenta la parola $0$ e così via.

Ossia - una generica parola binaria $b$ è rappresentata dal numero $n$ la cui rappresentazione binaria è ottenuta premettendo un $1$ alla parola $b$.

> La parola $b=0011$ è rappresentata dal numero $19$ perchè la rappresentazione binaria di $19$ è $10011$.

Quindi l'input di una macchina di Turing può essere rappresentata con un numero intero.

Possiamo costruire una matrice $M$ binaria con infinite righe e infinite colonne tale che: $$ M[i,k] =  
\begin{cases}  
1\;\;\;\;\;se\;T_{h_i}(k)\;termina\;in\;q_A\\
\\
0\;\;\;\;\;se\;T_{h_i}(k)\;non\;termina\;in\;q_A
\end{cases} $$
Ossia:

|           |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  | 10  |
| :-------: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| $T_{h_1}$ |  0  |  0  |  1  |  0  |  1  |  0  |  0  |  1  |  1  |  1  |
| $T_{h_2}$ |  0  |  1  |  1  |  0  |  0  |  1  |  0  |  1  |  0  |  1  |
|    ...    |     |     |     |     |     |     |     |     |     |     |
| $T_{h_i}$ |  1  |  0  |  1  |  0  |  1  |  1  |  1  |  0  |  1  |  0  |

> $T_{h_2}$ accetta gli interi: $2$ (parola $0$) - $3$ (parola $1$) - $6$ (parola $10$) e così via.

$M$ rappresenta *tutti* i linguaggi accettati dalle macchine di Turing. Perché tutte le macchine di Turing sono rappresentate in $M$.

> Per esempio gli $1$ sulla riga $1$ rappresentano il linguaggio $L_1$ accettato da $T_{h_1}:L_1=\{3,5,8,9,10,...\}$. equivalentemente $L=\{1,01,000,001,010,...\}$.

Consideriamo la diagonale della matrice e costruiamo il linguaggio $L$ che rappresenta la diagonale *complementata*. Ossia tutti e soli i numeri cui corrisponde $0$ sulla diagonale. Formalemente: $$L = \{k:T_{h_k}(k)\;\;non\;\;accetta\}$$ oppure: $$L =\{k:M[k,k]= 0\}$$
Questo linguaggio **non è accettato da nessuna macchina di Turing** della matrice $M$ - ma ci sono tutte le macchine di Turing - quindi non esiste macchina di Turing che accetti $L$ - ossia $L$ è un linguaggio non accettabile.

Quindi tramite la diagonalizzazione abbiamo capito esiste almeno un **problema che non può essere risolto** dalle macchine di Turing. Questa dimostrazione non ci definisce il modo in cui questi linguaggi vengono costruiti. 

> Magari questi problemi irrisolvibili non sono importanti e quindi non ci interessano - e invece no.

Turing ha costruito il seguente problema irrisolvibile - anzi ha inventato la sua macchina per arrivare a dimostrare che questo problema è irrisolvibile.

---
### Halting Problem

Turing considerò il seguente linguaggio - sottoinsieme di $\mathbb{N}\times \mathbb{N}$ : $$L_H = \{(i,x) \in \mathbb{N}\times \mathbb{N}:i\;è\;la\;codifica\;di\;una\:macchina\;di\;Turing\;T_i\;e\;T_i(x)\;termina\}$$
Detto anche **Halting Problem** - Turing dimostro che $L_H$ è accettabile - ma non decidibile.

> Quindi $L_H^C$ non è accettabile.

Quale è la rilevanza di questa domanda?

Immagina di scrivere un programma - lo esegui su un inout $x$ e aspetti la risposta. E continui ad aspettare. E attendi. Ti viene il dubbio: E se fosse andato in loop?

Sarebbe utile avere un programma che - se gli do in input un altro programma $P$ e un suo input $x$ - quello mi dice se l'esecuzione su di $P$ su $x$ termina oppure no.

> Ossia sarebbe bello avere un **programma che decide l'Halting Problem**.

**Teorema:** $L_H$ è un linguaggio accettabile.