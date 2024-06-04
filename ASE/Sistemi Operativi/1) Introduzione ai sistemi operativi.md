Il Sistema Operativo è un ==software che avvolge la macchina e permette l'utilizzo delle risorse hardware in maniera semplificata==, in generale ciò che fa è astrarre le risorse della macchina.
Non esiste una definizione universale di Sistema Operativo.

NB: ==GUI non è una parte del sistema operativo==, è solo un applicazione che lo utilizza ed è una cosa diversa dall'interfaccia utente.

Abbiamo due modalità d'utilizzo:

- **User mode**
- **Kernel mode**

--------------------------------------------------------------------------
==I Sistemi Operativi forniscono concetti e servizi di base, sottoforma di astrazione come:==

- **Processi** 
- **File**
- **Spazio degli indirizzi**
- **Dispositivi I/O**
- **Shell**

==SHELL: E' il software che ci permette la comunicazione con il Sistema Operativo.==

NB: Compilatori e Assemblatori non fanno parte del Sistema Operativo

--------------------------------------------------------------------------
Tramite delle API possiamo fornire delle funzioni al kernel

**Garbage Collector**: ==Quando non viene chiamata la funzione free, questo processo automatico libera la memoria occupata,== in C questa cosa non esiste

Il Sistema Operativo è tutto ciò che ci aiuta per gestire la macchina e ==non sono solo i classici sistemi operativi,== infatti fra questi troviamo anche **Sistemi Embedded o Sistemi Real-time**, usati nei sistemi critici.

--------------------------------------------------------------------------
## Processi

Tutti i ==computer spesso fanno molte cose allo stesso tempo==, un ==processo ci permette di seguire l'evoluzione di un programma== durante l'esecuzione, **ci dà l'illusione del multitasking**, infatti la CPU durante il ciclo di clock può solo eseguire una cosa alla volta.

**La CPU cambia da un processo all'altro in maniera troppo veloce per rendercene conto.** (Time Sharing).

**Il modello dei processi:**

Il processo ==è l'istanza di un programma, mantiene per se alcune risorse virtuali==, come per esempio il Program Counter (non quello della macchina, bensì uno virtuale)

Un processi ==si può trovare in diversi stati durante la sua vita==, uno scheduler organizza il modo in cui i vari processi sono eseguiti:

**PRONTO**: Ha tutte le ==risorse necessarie per esere eseguito==, deve solo aspettare il via libera da parte dello scheduler.

**ESECUZIONE**: Il processo è in ==esecuzione==

**BLOCCATO**: Il processo viene bloccato in ==mancanza di risorse==, non può essere eseguito per qualche motivazione esterna.


Un modo utile per programmare con i processi e tramite dei segnali, per esempio sulle macchine UNIX un segnale che può essere usato è KILL.


## Modello per la multiprogrammazione
  
Il modello di multiprogrammazione è una tecnica utilizzata nei sistemi operativi per ==consentire l'esecuzione simultanea di più processi sulla CPU==, migliorando l'utilizzo delle risorse di sistema. In sostanza, consente al sistema operativo di eseguire più processi contemporaneamente, condividendo la capacità di elaborazione della CPU tra di essi.
Negli esercizi proposti dobbiamo calcolare la percentuale di utilizzo della CPU:

**Esercizio 1:**

- 512 MB di memoria
* SO ne occupa 128MB
* Per ciascun  processo servono 128MB
* Attesa media del I/O è di 80% 

==Siccome ogni processo occupa 128MB possiamo occupare fino a 3 processi nella memoria rimanente.==

$$512 - 128 = 384 / 128 = 3$$

Se l'attesa media del I/O è 80% l'uso della CPU è pari a $1-0.8^3$ = ==49% ==

Aggiungendo 512 MB miglioriamo al ==79%== = $1 -0.8^7$

## I Thread:

==Un thread è una sequenza di istruzioni all'interno di un processo che può essere eseguita indipendentemente dagli altri thread all'interno dello stesso processo.==

**SCHEDULING:**

E' un modulo del sistema operativo che ==ordina e coordina i processi==, gli dice quando possono essere messi in attesa, bloccati o eseguiti.

==Non sono tutti uguali, infatti esistono degli algoritmi specifici chiamati **Algoritmi di Scheduling**.==

Bisogna fare una distinzione sui processi, ne esistono due tipi:

- **Processi Preemptive:** ==Possono essere interrotti da altri;==
- **Processi Non-Preemptive:** ==Non possono essere interrotti; ==

Quello che ci interessa principalmente degli algoritmi di scheduling è:

- **Equità**: ==Il tempo deve essere equo per ogni processo, per equo intendiamo che ogni processo necessita di un suo tempo specifico.==

Esistono diverse aree applicative e sistemi operativi hanno diversi obiettivi:

- **Sistemi Batch** (Banche)
- **Sistemi Interattivi** (Windows)
- **Sistemi Real-Time** (Aerei, centraline)

==NB: Nei sistemi rel-time è essenziale che i risultati siano prodotti entro determinati limiti di tempo.==

## Algoritmi usati nei sistemi Batch

- **First Come First Served**: Il più semplice algoritmo non-preemptive, ==il primo che arriva è il primo che viene svolto,== ovviamente ha come problema che ==non ci sta priorità fra i processi.== 

- **Shortest job first**: Vengono ==svolti prima quelli che ci mettono meno tempo per essere eseguiti==, è utile ma solo ==se ci sono tutti i processi di cui ho bisogno nello stesso momento e quando i processi non si mofìdificano nel tempo.==

- **Shortest Remainng time next**: ==E' una versione preemptive del precedente algoritmo.==

## Algoritmi nei sistemi Interattivi

- **Round Robin**: ==Viene assegnato un tempo specifico di tot minuti a testa per processo, lo stesso per tutti i processi.==

- **Scheduling con priorità:** Alcuni processi hanno più importnaza e a questi viene data una priorità, ==è un Round-Robin con priorità, basta riordinare i processi per priorità==, queste le possiamo dare all'inizio o dinamicamnete.

- **Shortest Process next:** ==Simile al Shortest job First.==

- **Scheduling garantito**: ==garantisce che i processi critici abbiano accesso alle risorse di sistema secondo vincoli temporali prefissati==

- **Scheduling a lotteria**: ==Ogni processo partecipa ad una "lotteria" e il processo vincente viene eseguito==, **NON PUO' ESSERE USATO NEI SISTEMI CRITICI O REAL-TIME.**

- **Scheduling fair-share**: ==L'obiettivo è garantire che ciascun utente riceva una quota equa delle risorse di sistema.==

## Algoritmi nei sistemi Real-time:

I sistemi real time sono generalmente suddivisi in due categorie:

- **Hard real-time**: ==scadenze vanno rispettate== (Sistemi Critici, un lavoro deve finire in un lasso di tempo) 
- **Soft real-time:** ==Il mancato rispetto di una scadenza non è auspicabile, ma comunque tollerabile.==

I sistemi Real-time funzionano ad eventi, inoltre possono essere classificati come:

- **Periodici**: ==Si verificano a intervalli regolari.== (Interrupt periodici)
- **Non Periodici**: ==Si verificano in modo imprevedibile.== 

Ci sono ==moltissimi algoritmi per i sistemi Real-time==, con la particolarità che ==devono dare una risposta in un lasso di tempo preciso==.

## Esercizi

**Esercizio 1:**

- 2 GB di memoria 
- SO ne occupa 1GB
- I processi in media occupano 256 MB
- Il tempo d'attesa dell'IO è 80%

Quale il tempo di Usage della CPU?

Si calcola $1-P^n$ 

P = 80% = 0,8%
n = non lo so lo dobbiamo ricavare = 2048MB - 1024MB = 1024MB = 4 Processi

$1-0,8^4$ = Risultato = ==59,04%.==

Se aggiungiamo altri 512 MB di memoria? Lo andiamo semplicemente aggiungere alla memoria totale.

--------------------------------------------------------------------------

**Sistemi di batch:**

**Esercizio 2:**

*Supponendo di utilizzare l'algoritmo First-Come First-Served per lo scheduling in un sistema batch, con 5 job che arrivano nel seguente ordine: Primo P1, poi P2 dopo 3ms, P3 dopo 1ms da P1, P4 dopo 1 ms da P3, ed P5 dopo due ms da P4.
Se i tempi di esecuzione sono rispettivamente: 4, 2, 4, 2, 4, quali saranno i tempi medi di attesa e turnaround?*

==In questo tipo di algoritmo i processi sono eseguiti in ordine di arrivo==, i tempi da considerare sono:

- **Tempo Turnaround**($T_T$): Intervallo di tempo che intercorre dall'ingresso in coda del processo fino al termine della sua esecuzione;

- **Tempo di Attesa/Wait**($T_W$): Intervallo di tempo che il processo trascorre in coda prima di essere eseguito;

- **Tempo Arrivo**($T_A$): Tempo di arrivo in coda del processo;

- **Tempo d'esecuzione**($T_X$): Intervallo necessario per l'esecuzione del processo; 

- **Tempo di Inzio esecuzione**($T_S$): Tempo di inizio esecuzione del processo; 

- **Tempo di Fine esecuzione**($T_E$): Tempo di fine esecuzione del processo; 

Determiniamo l'ordine d'arrivo dei processi:

|  i  | $P_k$ | $T_A(i)$ | $T_X(i)$ |
| :-: | :---: | :------: | :------: |
|  1  | $P_1$ |    0     |    4     |
|  2  | $P_3$ |    1     |    4     |
|  3  | $P_4$ |    2     |    2     |
|  4  | $P_2$ |    3     |    2     |
|  5  | $P_5$ |    4     |    4     |
Ricordiamo le formule per trovare gli altri tempi:

$T_S(i) = T_A(i) = 0$ se i = 0, **ALRIMENTI**  $T_E(i-1)$ se i > 1
$T_E(i) = T_S(i) + T_X(i)$ 
$T_W(i) = T_S(i) - T_A(i)$ 
$T_T(i) = T_E(i) - T_A(i)$

La tabella completa in millisecondi risulta:


|  i  | $P_k$ | $T_A(i)$ | $T_X(i)$ | $T_S(i)$ | $T_E(i)$ | $T_W(i)$ | $T_T(i)$ |
| :-: | :---: | :------: | :------: | :------: | :------: | :------: | :------: |
|  1  | $P_1$ |    0     |    4     |    0     |    4     |    0     |    4     |
|  2  | $P_3$ |    1     |    4     |    4     |    8     |    3     |    7     |
|  3  | $P_4$ |    2     |    2     |    8     |    10    |    6     |    8     |
|  4  | $P_2$ |    3     |    2     |    10    |    12    |    7     |    9     |
|  5  | $P_5$ |    4     |    4     |    12    |    16    |    8     |    12    |

I tempi medi quindi risultano:

$$
1/5\sum_{1} ^{5} T_W(i) = (0+6+6+7+8)*1/2 = 4.8ms
$$

$$
1/5\sum_{1} ^{5} T_T(i) = (4+7+8+9+12)*1/5 = 8ms
$$

**NB: 1/5 deriva dal fatto che stiamo lavorando con 5 processi, se ci sono n processi allora sarà 1/n.**

**Esercizio 3:**

Supponendo di utilizzare l'algoritmo Shortest Job First per lo scheduling in un sistema batch, dovendo eseguire 4 processi con i seguenti tempi d'esecuzione: 8, 10, 12 e 6, quali saranno il tempo medio di turnaround e quello d'attesa?

==Ordiniamo i processi in tempo d'esecuzione crescente==:

|  i  | $P_k$ | $T_x$(i) |
| :-: | :---: | :------: |
|  1  | $P_4$ |    6     |
|  2  | $P_1$ |    8     |
|  3  | $P_2$ |    10    |
|  4  | $P_3$ |    12    |
In questo i temppi risultano:

$T_S(i) = T_S(1) = 0$  Altrimenti $T_E(i-1)$ 
$T_E(i) = T_S(i) + T_X(i)$
$T_W(i) = T_S(i)$
$T_T(i) = T_E(i)$

La tabella in millisecondi risulta:

|  i  | $P_K(I)$ | $T_X(I)$ | $T_S(I)$ | $T_E(I)$ | $T_W(I)$ | $T_T(I)$ |
| :-: | :------: | :------: | :------: | :------: | :------: | :------: |
|  1  |  $P_4$   |    6     |    0     |    6     |    0     |    6     |
|  2  |  $P_1$   |    8     |    6     |    14    |    6     |    14    |
|  3  |  $P_2$   |    10    |    14    |    24    |    14    |    24    |
|  4  |  $P_3$   |    12    |    24    |    36    |    24    |    36    |
Quindi i tempi medi risultano:

$$
1/4\sum_{1} ^{4} T_W(i) = (0+6+14+24)*1/4 = 11ms
$$
$$
1/4\sum_{1} ^{4} T_T(i) = (6+14+24+36)*1/4 = 20ms
$$


**Esercizio 4:**

Supponendo di utilizzare l'algoritmo **Shortest Job First** per lo scheduling in un sistema batch,se arrivano 5 job (in ordine $P_1$, poi $P_4$ dopo 2ms da $P_1$, $P_2$ dopo 3ms da $P_1$, $P_3$ dopo 1 ms da $P_1$, $P_5$ dopo 3ms da $P_1$) Con i seguenti tempi d'esecuzione espressi in millisecondi: 4, 2, 1, 3 e 2, quali saranno il tempo medio di Turnaround e quello di attesa?

L'unico processo presente all'inizio è $P_1$ che dura 4ms, dopo questo arrivano tutti gli altri processi che metteremo in ordine dal più corto al più lungo:

|  i  | $P_k$ | $T_x$(i) | $T_A(i)$ |
| :-: | :---: | :------: | -------- |
|  1  | $P_1$ |    4     | 0        |
|  2  | $P_3$ |    1     | 4        |
|  3  | $P_2$ |    2     | 3        |
|  4  | $P_5$ |    2     | 3        |
|  4  | $P_4$ |    3     | 2        |
Adesso calcoliamo i restanti tempi:

|  i  | $P_k$ | $T_x$(i) | $T_A(i)$ | $T_E(I)$ | $T_W(I)$ | $T_T(I)$ | $T_S(i)$ |
| :-: | :---: | :------: | -------- | :------: | :------: | :------: | -------- |
|  1  | $P_1$ |    4     | 0        |    4     |    0     |    4     | 0        |
|  2  | $P_3$ |    1     | 4        |    5     |    0     |    1     | 4        |
|  3  | $P_2$ |    2     | 3        |    7     |    2     |    4     | 5        |
|  4  | $P_5$ |    2     | 3        |    9     |    4     |    6     | 7        |
|  4  | $P_4$ |    3     | 2        |    12    |    7     |    10    | 9        |

$$
1/5\sum_{1} ^{5} T_W(i) = (0+0+2+4+7)*1/5 = 2.6ms
$$
$$
1/5\sum_{1} ^{5} T_T(i) = (4+1+4+6+10)*1/5 = 5ms
$$


**Esercizio 5:**

Tabella in millisecondi

|  i  | $P_k$ | $T_x$(i) | $T_A(i)$ | $T_E(I)$ | $T_W(I)$ | $T_T(I)$ | $T_S(i)$ |
| :-: | :---: | :------: | -------- | :------: | :------: | :------: | -------- |
|  1  | $P_1$ |    5     | 0        |    9     |    0     |    9     | 0        |
|  2  | $P_2$ |    3     | 1        |    5     |    0     |    4     | 1        |
|  3  | $P_3$ |    1     | 2        |    3     |    0     |    1     | 2        |
|  4  | $P_5$ |    1     | 9        |    10    |    0     |    1     | 9        |
|  4  | $P_4$ |    2     | 8        |    12    |    2     |    4     | 10       |
$$
1/5\sum_{1} ^{5} T_W(i) = (2)*1/5 = 0.4ms
$$
$$
1/5\sum_{1} ^{5} T_T(i) = (9+4+1+1+4)*1/5 = 3.8ms
$$

--------------------------------------------------------------------------
**Sistemi interattivi:**

Algoritmo **Round Robin** con ordine d'esecuzione $P_1$, $P_2$, $P_3$, $P_4$, $P_5$ e con quatum di 200 nanosecondi, il cambio di contesto avviene in 2 nanosecondi:

- Dopo quanto tempo sarà eseguito $P_5$?
- Quale è il rapporto tra cambio di contesto e tempo d'esecuzione?
- Come cambiano i tempi se il quantum fosse 12 nanosecondi?
- Quale delle due soluzioni è più favorevole?

==Il tempo d'attesa di $P_5$ sarà il tempo di esecuazione di ogni processo più il cambio di contesto==,quindi:

$2+200+2+200+2+200+2 = 608$ nanosecondi;

Il rapporto tra cambio di contesto e quantum è:

$2/200 = 0.01$ Quindi solo l'1% della CPU è utilizzata per realizzare l'algoritmo.

Nel secondo caso il tempo di attesa di $P_5$ è:

$2+12+2+12+2+12+2 = 44$ nanosecondi;

Il rapporto tra cambio di contesto e quantum è:

$2/12 = 0.16$ Quindi 16% della CPU è utilizzata per realizzare l'algoritmo.

Nel primo caso ci sta un ==tempo maggiore d'esecuzione dei processi, il che è favorevole per processi che hanno bisogno di molto tempo di calcolo==, ma l'ultimo processo attende molto di più rispetto al primo.

Nel ==secondo caso i processi sono eseguiti più frequentemente, ma ci sta una maggiore inefficienza, questa situazione è vantaggiosa per processi di I/O==, ma sono sfavoriti processi che richiedono tempo di calcolo.

Nei sistemi interrativi la ==scelta del quantum è essenziale== in quanto un valore troppo alto porta ad un degrado delle prestazioni mentre un valore trtoppo basso conduce ad un sovraccarico del sistema in quanto si avrebbero troppi scambi di contesto e la CPU non riuscirebbe a portare a termine i processi.

**Esercizio 7:**

Algoritmo Shortest Process Next con tecnica di aging, stimare il tempo di esecuzione $T_4$ conoscendo i precedenti tempi d'esecuzione:

$T_0 = 970$ $T_1 = 630$ $T_2 = 230$ $T_0 = 140$

$!T_i(a) = T_0$ se i = 0, ALTRIMENTI $a * T_{i-1}+(1-a) * !T_{i-1}(a)$ se i >= 2;

Ponendo a = 1/2 possiamo calcolare $!T_4$:

$!T_4 = T_3/2 + !T_3/2$ Si puo così calcolare $!T_3$ finche non raggiungamo una fine, alla fine otteniamo:

$$
0,14/2 +0,23/4 + 0.63/8 +0,97/8 = 0.325 
$$


**Esercizio 8:**

Un sistema interrativo utilizza la'algoritmo di scheduling a lotteria ed ha assegnato 60 biglietti al processo $P_1$ e 20 ai processi $P_2$, $P_3$ e $P_4$. Al momento dell'estrazionequal'è la probabilità di vincita del processo $P_1$ e degli altri processi? Nel caso il processo $P_1$ stia collaborando con $P_2$ e $P_3$ per raggiungere un determinato obiettivo, quanti biglietti deve acedere affinchè le probabilità di estrazione degli altri due salga al 35%? Ora qual'è la probabilità di estrazione di $P_1$ e $P_4$?

==Calcoliamo le probabilità d'estrazione:==

- $P_1$ ha 60 biglietti su un totale di 120, quindi ha un 50%;
- Gli altri processi hanno 20 biglietti, quindi hanno un 16,6% di probabilità d'estrazione.

Affinchè $P_2$ e $P_3$ abbiamo un 35% di vincita occorre che incrementi la loro probabilità di X punti.

$$
20/120 + x/120
$$
Quindi:
$$
x = 22
$$
Se $P_1$ e cede 22 biglietti a $P_2$ e $P_3$, allora rimarrà con 16 biglietti è una probabilità del 13,33%.

Viceversa la probabilità di $P_2$ e $P_3$ salirà a 35%, quindi il processo $P_4$ avrà le stesse possibilità di uscire.

--------------------------------------------------------------------------

**Esercizi soft real-time:**

Dobbiamo valutare la fattibilità di un sistema soft real-time con eventi periodici:

$$
P_0 = 0,5 - P_1 = 300 - P_2 = 15ms - P_3 = 800ms
$$
Con i ripettivi tempi di elaborazione:

$C_3 = 50$ ns
$C_1 = 0.1$ ms
$C_2 = 3$ ms
$C_3 = 0.2$ s

Il sistema è sostenibile? Se aggiungo un $P_4$ = 1000 nanosecondi con $C_4 = 0.12$ ms, il sistema rimane sostenibile?

==**Ricordiamo**: Un sistema soft real-time che riceve k eventi periodici è sostenibile se:==

$$
\sum_{0} ^{k} C_i/P_i <= 1
$$


|     i     |       0       |       1       |      2       |      3       |
| :-------: | :-----------: | :-----------: | :----------: | :----------: |
|   $C_i$   | $50*10^{-9}$  | $0.1*10^{-3}$ | $3*10^{-3}$  |    $0.2$     |
|   $P_i$   | $0.5*10^{-6}$ | $300*10^{-6}$ | $50*10^{-9}$ | $50*10^{-9}$ |
| $C_i/P_i$ |     $0.1$     |     $0.3$     |    $0.2$     |    $0.25$    |

Il sistema è quindi sostenibile:

$$
\sum_{0} ^{4} C_i/P_i = 0.1 + 0.3+0.2+0.25 = 0.883
$$

Se arriva l'evento periodico $P_4$,  che porta un contributo di 0.12, allora il sistema non è più sostenibile.

**Esercizio 10:**

Supponendo di dover valutare la fattibilità di un sistema soft real-timecon eventi periodici $P_0 = 53ms$, $P_1 = 0.22ns$, $P_2 = 145ns$. Con rispettivi tempi d'elaborazione: $C_0 = 13 ms$, $C_1 = 10 ps$, $C_2 = 55ns$. Il sistema è sostenibile? Se si aggiunge un nuovo evento periodico $P_3 = 100ms$, qual'è il valore limite per $C_3$ affinchè il sistema rimane sostenibile?

Iniziamo scrivendo i valori dei periodi $P_i$ e dei relativi tempi d'elaborazione $C_i$:

|     i     |      0       |       1        |       2       |
| :-------: | :----------: | :------------: | :-----------: |
|   $C_i$   | $13*10^{-3}$ | $10*10^{-12}$  | $55*10^{-9}$  |
|   $P_i$   | $53*10^{-3}$ | $0.22*10^{-9}$ | $145*10^{-9}$ |
| $C_i/P_i$ |   $0.2452$   |    $0.0454$    |   $0.3793$    |
Il sistema è sostenibile.

$$
\sum_{0} ^{2} C_i/P_i = 0.2452 + 0.0454 + 0.3703 = 0.67
$$

Se arriva l'evento periodico $P_3$ occorre calcolare il valore limite per $C_3$:

$1-0.67 = C_3/P_3$
$C_3 = P_3 * 0.3299 = 32.99ms$ 
