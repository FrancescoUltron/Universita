# 4 - Modelli di macchine di Turing

Quando lavoriamo con delle macchine di Turing possiamo distingurle in macchine con:

- **Testine indipendenti**: Quando viene eseguita una quintupla una testina su un nastro si può *muovere come vuole* indipendentemente dalle altre.

- **Testinte solidali**: Quando viene eseguita una quintupla se la testina su un nastro si muove in una certa direzione - anche le testine degli altri nastri si muovono nella stessa direzione.

- **Singolo nastro di lettura/scrittura.**

- **alfabeto con molti simboli.**

- **alfabeto binario.**

> Nota che per le prime due abbiamo bisogno di tanti nastri.

In questa lezione dimostreremo che: "*Tutto quello che riusciamo a fare con una macchina qualsiasi di questi modelli - riusciamo a farlo anche con una macchina di uno qualsiasi degli altri modelli.*"

---
### Macchine a testine indipendenti = Macchine a testine solidali

**Teorema:** I due modelli di macchine a $k$ nastri sono equivalenti - ossia possiamo simulare il comportamento di una macchina $T$ a testine indipendenti mediante una macchina $T'$ a testine solidali e viceversa.

Innanzitutto - poiché una macchina a testina solidali è una *particolare macchina a testine indipendenti* - tutto ciò che facciamo con il modello a testine indipendenti si può fare cin quello a testine solidali.

Trasformiamo quindi una quintupla $<q_1,(a,b),(c,d),q_2,(m_1,m_2)>$ di $T$ in un insieme di quintupe di una macchina $T'$ 'che si comporta come' la quintupla di $T$.

Ricordiamo una generica quintupla di $T'$ - ossia $<q_x,(u,v),(w,z),q_y,m>$ - controlliamo i possibili casi:

- Caso 1 - $m_1 = m_2$.
- Caso 2 - $m_1 \neq m_2$ - Lo faremo con $m_1$ = destra e $m_2$ = sinistra.

Il primo caso è semplice la quintupla $<q_1,(a,b),(c,d),q_2,m_1> = <q_1,(a,b),(c,d),q_2,m_2>$ di $T'$ fanno le stesse cose della quintupla di $T$. 

La situazione diventa più complicata nel momento in cui i movimenti dei nastri sono diversi. L'idea è quella di **ricordarci la coppia di celle da cui siamo partiti.**
Lavoriamo sul primo nastro e spostiamoci sul carattere più a destra **ricordiamo** il carattere letto lo **cancelliamo** e lo **copiamo** sulla cella alla sinistra - eseguiamo la stessa operazione su ogni carattere del nastro.
Terminiamo questo *shift* ricordandoci le **celle da cui eravamo partiti** - lavoriamo poi sul secondo nastro eseguendo lo *shift* - ma nella direzione opposta.

> In questo modo è come se avessimo tirato i nastri della macchina.

> Se $m_1=$ destra - allora il movimento dello shift è sinistra.

Ci rimane solo che posizionarci sulla cella di partenza - ma come la possiamo ricordare?
Usiamo un **terzo nastro** con un carattere che farà da *segnaposto* - usiamo $*$. 

Riassumiamo il funzionamento delle quintuple di $T'$:

- $T'$ sostituisce i caratteri del primo e secondo nastro e si prepara allo shift sul primo nastro.

// Immagine PPT3 - 10

- $T'$ ha appena fatto lo shift sul primo nastro si prepara a farlo sul secondo.

//Immagine PPt3 - 11

- $T'$ ha finito lo shift sul secondo nastro e si prepara a posizionare le testine.

//Immagine PPT3 - 12

- $T'$ posiziona la testina nella posizione indicata dal segnaposto e le testine sui primi due nastri leggono gli stessi caratteri letti dalle testine di $T$ al termine dell'esecuzione della quintupla $<q_1,(a,b),(c,d),q_2,(destra,sinistra)>$.

Abbiamo simulato la quintupla $<q_1,(a,b),(c,d),q_2,(destra,sinistra)>$ di $T.\;\;\;\square$ 

Quindi una computazione di $T'$ simula una computazione di $T$. In particolare - per ogni quintupla $p$ in $T$ - in $T'$ è definito **un insieme** di $p'$ di quintuple tali che quando:

- I contenuti dei nastri di $T$ e dei primi due nastri di di $T'$ sono uguali e
- Le testine di $T$ e le prime due testine di $T'$ leggono gli stessi caratteri e
- La quintupla $p$ può essere eseguita da $T$.

Allora le quintuple nell'insieme $p'$ **possono essere eseguite** da $T'$ e al termine dell'esecuzione di $p$ da parte di $T$ e dell'insieme $p'$ da parte di $T'$:

- I contenuti dei primi due nastri di $T'$  e dei nastri di $T$ sono **uguali.**
- Le testine di $T$ e le prime due testine di $T'$ **leggono gli stessi caratteri.**

L'immagine mostra la fine della computazione da parte di entrmbe le macchine:

//IMMAGINE PPT3 - 15.

> Per le altre coppie di movimenti si procede in modo analogo.

Abbiamo **simulato** il comportamento di una macchina con $k$ nastri e testine indipendenti mediante una macchina a $k+1$ nastri a testine solidali.

Questa è la tecnica della **simulazione a scatola aperta** - consiste nel progettare una macchina $T'$ con certe caratteristiche che "fa la stessa cosa" di un'altra macchina $T$ con caratteristiche diverse - **trasformando o utilizzando** una per volta le quintuple di $T$.

---
### Da tanti nastri a un solo nastro

La capacità computazionale di una macchina di Turing non è data dal numero di nastri di cui è dotata. Dimostreremo quindi che una macchina di Turing a $k$ nastri può essere simulata da una macchina di Turing $T_1$ ad un nastro.

> Useremo Macchine di Turing a testine solidali - con ciò abbiamo dimostrato precedentemente non abbiamo perdità di generalità.

Sia $T_k$ una macchina di Turing a testine solidali con $k$ nastri. $T_1$ è una macchina di Turing ad un nastro che usa lo steso alfabeto di $T_k$ e di cui l'insieme degli stati è $Q \times \Sigma^k$.

L'input della macchina $T_k$ è il seguente: $x = (x_{1_1},x_{1_2},...,x_{1_k})(x_{2_1},x_{2_2},...,x_{2_k})....(x_{n_1},x_{n_2},...,x_{n_k})$ 

Questo sarà scritto su $T_1$ a partire dalla cella 1 come concatenazione di tutti i simboli di $x$.

Abbiamo quindi la seguente quintupla $<q_1,(s_{1_1},s_{1_2},...,s_{1_k}),(s_{2_1},s_{2_2},...,s_{2_k}),q_2,m>$ per $T_k$ proviamo a trasformarla in una quintupla di $T_1$. 

1) Per $T_k$ è sufficiente una sola operazione di lettura per eseguire la quintupla - mentre $T_1$ deve eseguire $k$ operazioni di lettura consecutive - la quintupla può essere eseguita solo se viene letto $s_{1_1}$ seguito da $s_{1_2}$ fino a $s_{1_k}$. Per decidere se si può eseguire una quintupla dobbiamo **leggere e ricordare** $k$ simboli consecutivi: $$\begin{align}<q_1,s_{1_1},s_{1_2},q(q_1,s_{1_1}),D> \\ <q(q_1,s_{1_1}),s_{1_2},s_{1_2},q(q_1,s_{1_2},s_{1_2},D)> \\ ... \\ <q(q_1,s_{1_1},s_{1_2},...s_{1,k-1}),s_{1_k},s_{1_k},q(q_1,s_{1_1},s_{1_2}....s_{1_k-1},s_{1_k}),S>\end{align}$$
2) $T_1$ ha verificato che la quintupla può essere eseguita e - per poterlo fare - deve riportare la testina a sinistra di $k$ celle: $$\begin{align}<q(q_1,s_{1_1},s_{1_2},...s_{1,k-1},s_{1_k}),s_{1_k-1},s_{1_k-1},q(q_1,s_{1_1},s_{1_2}....s_{1_k-1},s_{1_k},k-2),S>  \\ <q(q_1,s_{1_1},s_{1_2},...s_{1,k-1},s_{1_k},i),s_{1_i},s_{1_i},q(q_1,s_{1_1},s_{1_2}....s_{1_k-1},s_{1_k},i-1),S> \forall i = 2,...,k-2 \end{align}$$
3) La testina di $T_1$ è ora posizionata sul carattere corrispondente al carattere scritto sul primo nastro di $T_k$ e quindi può procedere all'esecuzione della quintupla sovrascrivendo i $k$ caratteri: $$\begin{align} <q(q_1,s_{1_1},s_{1_2},...s_{1_k},1),s_{1_1},s_{2_1},q^{write}(q_1,s_{1_1},s_{1_2},...,s_{1_k},2),D> \\ <q(q_1,s_{1_1},s_{1_2},...s_{1_k},i),s_{1_i},s_{2_i},q^{write} (q_1,s_{1_1},s_{1_2},...,s_{1_k},i+1),D>\\ <q^{write}(q_1,s_{1_1},s_{1_2},...s_{1_k},k),s_{1_k},s_{2_k},q',m'>\end{align}$$
4) $T_1$ ha eseguito la prima parte della quintupla - scrivendo i $k$ nuovi caratteri sul nastro - la sua testina è posizionata sulla cella contenente l'ultimo simbolo scritto. Dobbiamo ora eseguire il cambio di stato interno ed il movimento della testina. Queste operazioni avvengono in maniera diversa in base al valore di $m$.

- Se $m=$ destra - allora $q'=q_2$ e $m'=$ destra e l'esezione della quintupla è terminata.

- Se $m$ = fermo - allora $q'=q^{sin}(q_2,k-1)$ e $m'=$ sinistra - l'esecuzione della quintupla termina con le seguenti quintuple: $$\begin{align} <q^{sin}(q_2,i),x,x,q^{sin}(q_2,i-1),S> \forall x\in \Sigma \cup \square \forall i =2,...,k-1 \\ <q^{sin}(q_2,1),x,x,q_2,F>\end{align}$$
- Se $m$ = sinistra - allora $q'=q^{sin}(q_2,2k-1)$ e $m'$ = sinistra e l'esecuzione delle quintupla termian con le seguenti quintuple: $$\begin{align} <q^{sin}(q_2,i),x,x,q^{sin}(q_2,i-1),S> \forall x\in \Sigma \cup \square \forall i =2,...,2k-1 \\ <q^{sin}(q_2,1),x,x,q_2,F>\end{align}$$

> Gli insieme degli stati di $T_1$ è maggiore rispetto a quello di $T_k$ - ma l'insieme degli stati finali coincidono.

> Possiamo usare lo stesso insieme degli stati sia per $T_1$ che per $T_k$ incrementando la cardinalità dell'alfabeto. In particolare - definiamo l'alfabeto di $T_1$ come $\Sigma_1=\Sigma^k$ - ossia ogni simbolo scritto in una cella di $T_1$ è una $k-upla$ di simboli di $\Sigma$. In questo modo ciascun simbolo di letto o scritto dalla testina di $T_1$ corrisponde ad un unico insieme di $k$ simboli letti o scritti dalle $k$ testine di $T_k$ e la simulazione di $T_k$ da parte di $T_1$ avviene mantenendo *inalterato* l'insieme delle quintuple.

---
### Da un alfabeto ricco a un alfabeto binario

Mostriamo che ogni macchina di Turing $T$ definita su un alfabeto $\Sigma$ tale che $|\Sigma| >2$ possa essere simulata da una macchina di Turing $T_{01}$ definita sull'alfabeto composto solo da $0$ e $1$. 

> Senza perdità di generalità - possiamo assumere che $T$ abbia *un solo nastro*.

Sia $\Sigma=[\sigma_0,\sigma_1,....\sigma_{n-1}]$ e rappresentiamo ogni elemento di $\Sigma$ mediante una **codifica binaria** $b$ che userà $k=\lceil log_2(n) \rceil$ cifre. Indichiamo la codifica binaria di ciascun $\sigma \in \Sigma$ con: $$b(\sigma) = (b_1(\sigma),b_2(\sigma),...,b_{k}(\sigma))$$
Con $b(\square)$ indichiamo una sequenza di $k\;\; \square$ - infatti ogni volta che $T$ legge o scrive un singolo $\square$ - $T_{01}$ dovrà scrivere una sequenza di $k\;\square$ al fine di **sovrascrivere tutti i simboli** della codifica binaria dell'elemento di $\Sigma \cup \square$ sovrascritto da $T$.  

Costruiamo adesso una macchina a $k$ nastri $T_{01}$ - cominciando con lo scrivere sui $k$ nastri la codifica binaria dell'input sull'unico nastro $T$.

Sia $x_1x_2...x_h$ l'input di $T$ - nelle celle di indirizzo 1 dei nastri di $T_{01}$ scriviamo i simboli binari della codifica di $x_1:$ se $b_1(x_1)b_2(x_1)...b_k(x_1)$ allora scriviamo il primo nella cella del primo nastro - il secondo nella cella del secondo nastro e così via.

A questo punto le quintuple $<q,\sigma,\sigma',q',m> \in P$ vengono trasformate in un insieme di quintuple $P_{01}(p)=P_1(p)\cup P_2(p)$ tali che le quintuple in $P_1(p)$ verificano l'eseguibilità di $p$ e sovrasrivono i bit di $b(\sigma)$ con $b(\sigma')$ e le quintuple di $P_2(p)$ simulano il movimento della testina e portano la macchina in un nuovo stato.

//Immagine

Quindi a partire da una macchina $T$ siamo riusciti a costriuire una macchina $T_{01}$ su $k$ nastri che 'fa le stesse cose di $T$'.

> Siccome sappiamo che avere tanti nastri o un solo nastro è la stessa cosa potremmo anche creare una macchina $T_{01}$ che funziona su un solo nastro.

Diamo una definizione formale per 'fare le stesse cose' - possiamo infatti dire "L'esito della computazione di una macchina su input **coincide** con l'esito della computazione dell'altra macchina sullo stesso input (**eventualemente codificato**)".

