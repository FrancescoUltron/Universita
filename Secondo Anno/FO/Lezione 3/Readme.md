# 3 - Esercizi sulle macchine di Turing

In questa lezione introdurremo il concetti di **simulazione a scatola chiusa** tramite alcuni esercizi.

---
### Palindromia

Vogliamo progettare una macchina di Turing $T_{PAL}$ che, con una parola $x ∈ {a, b}^*$ scritta sul nastro, esegue una serie di passi che terminano in uno stato che attesta se $x$ è palindroma o meno.
#### Palindromia pari 

Cominciamo considerando un riconoscitore $T_{PPAL}$ che termina in uno stato che attesta se $x$ è una **parola palindroma di lunghezza pari o meno.** 

> Indichiamo gli stati di $T_{PPAL}$ con una $P$ in apice. Per esempio: $q_0^P$,$q_P^P$,$q^P_{NPP}$ - questi sono stato inziale e stati finali.

Descriviamo il funzionamento della macchina ad alto livello:

1) $T_{PPAL}$ esegue delle scansioni del nastro da sinistra a destra e viceversa.

2) Quando comincia una scansione da sinistra **memorizza** il simbolo letto nella prima cella - lo cancella - e si sposta sull'ultima cella a destra con un carattere di $x$.

3) Quando comincia una scansione da destra confronta il simbolo letto nella cella più a destra con il simbolo memorizzato. Se diversi allora la parola *non è palindroma* - quindi andiamo in $q^P_{NPP}$. Se uguali cancelliamo il carattere e torniamo alla cella più a sinistra occupata da un carattere e ricominciamo la scansione. Se invece mouvendo a sinistra troviamo subito un blank allora la parola *è palindroma* e andiamo in $q_P^P$ e terminiamo.

> Memorizziamo i valori negli stati interni $q_a^P$ e $q_b^P$.

Cominciamo adesso la descrizione a basso livello - le quintuple della macchina saranno:

Scansione da sinistra a destra:

- $<q_0^P,a,\square,q_a^P,D>$ e $<q_0^P,b,\square,q_b^{P},D>$ per leggere il carattere più a sinistra e memorizzarlo.

- $<q_{a/b}^P,a/b,a/b,q_{a/b}^P,D>$ per muoverci verso destra dopo aver memorizzato uno dei caratteri.

> Nota che le quintuple sopra sono quattro quintuple. La notazione $a/b$ indica che dobbiamo usare una quintupla per ogni carattere.

- $<q_{a/b}^P,\square,\square,q_{a1/b1}^P,S>$ per spostarci dal blank all'ultimo carattere della parola.

Scansione da destra a sinistra:

- $<q_{a1/b1}^P,a/b,\square,q_2^P,S>$ e $<q_2^P,a/b,a/b,q_2^P,S>$ per eliminare il carattere più a destra e per muovermi a sinistra verso il primo blank disponibile.

- $<q_2^P,\square,\square,q_0^P,D>$ per muovermi nel primo carattere a sinistra e mettermi nello stato iniziale.

- (/) Se nello stato $q_0^P$ la testina legge $\square$ allora sono stati cancellati tutti i simboli e la parola è quindi palindroma di lunghezza pari - $<q_0^P,\square,\square,q_{PP}^P,F>$.

- (//) Se il carattero interno è diverso da quello che viene letto dalla testina allora concludiamo che la parola non è di lunghezza pari o non è palindroma - $<q_{a1/b1}^P,a/b,\square,q_{NPP}^P,F>$ o $<q_{a1/b1}^P,\square,\square,q_{NPP}^P,F>$..
#### Palindromia dispari

Proviamo ora a descrivere una macchina $T_{DPAL}$ che termina in uno stato che attesta se $x$ è una **parola palindroma di lunghezza dispari o meno.**

> Indichiamo gli stati della macchina con una $D$ in apice

Le fasi di scansione da sinistra a destra sono identiche a $T_{PPAL}$ - da destra a sinistra sono leggermente diverse.

- Se il carattere memorizzato nello stato interno è uguale a quello della testina - lo cancella e - entrando in $q_2^D$ - sposta la testina sul $\square$ a sinistra del carattere più a sinistra di $x$: $<q_{a1/b1}^D,a/b,\square,q_2^D,S>$ e $<q_{2}^D,a/b,a/b,q_2^D,S>$.

- Raggiunto il $\square$ entra di nuovo nello stato $q_0^D$ per iniziare una nuova fase da sinistra-destra: $<q_2^D,\square,\square,q_0^D,D>$.

- (/) Se nello stato $q_0^D$ - la testina legge $\square$ allora tutti i simboli sono stati cancellati e - siccome vengono cancellati a coppie significa che $x$ aveva lunghezza pari è dunque era palindroma - **ma di lunghezze pari**: $<q_0^D,\square,n,q_{NPD}^D>,F$.

- (//) Se il carattere letto dalla testina è diverso da $\square$ e da quello memorizzato nello stato interno - $x$ non era palindroma: $<q_{a1}^D,a/b,\square,q^D_{NPD},S>$.

- (//) Se il carattere letto dalla testina è uguale a $\square$ possiamo concludere che $x$ ha lunghezza dispari e - siccome non abbiamo mai concluso che non era palindroma - allora *deve esssere* palindroma: $<q_{a1/b1}^D,\square,p,q_{PD}^D,F>$.
#### Palindromia pari e dispari

Consideriamo la macchina $T_{PAL}$ che termina in uno stato che attesta se $x$ è una parola palindroma - di lunghezza pari o dispari che sia. Le quintuple di $T_{PAL}$ sono simili a quelle di $T_{DPAL}$ e $T_{PPAL}$.

> Bisogna solo creare le quintuple per i punti / e //.

Possiamo utilizzare le macchine $T_{PPAL}$ e $T_{DPAL}$ per costruire $T_{PAL}$ attraverso una **simulazione.**

> E' come se invocassimo le due macchine come delle funzioni.

Descriviamo con la seguente tecnica $T_{PAL}$:

$T_{PAL}$ è una macchina a 3 nastri - sul primo è scritto **l'input** - il secondo serve a simulare $T_{PPAL}$ - il terzo serve a simulare $T_{DPAL}$. Opera in tre fasi:

1) Copia l'input $x$ sul secondo e terzo nastro.

2) Simula $T_{PPAL}$ sul secondo nastro - se $T_{PPAL}(x)$ termina in $q_{PP}^P$ allora $x$ è un parola di lunghezza pari e palindroma e $T_{PAL}$ entra in $q_P$ - altrimenti passiamo alla fase 3.

3) Simula $T_{DPAL}$ sul terzo nastro - se $T_{DPAL}(x)$ termina in $q_{PD}^D$ allora $x$ è un parola di lunghezza dispari e palindroma e $T_{PAL}$ entra in $q_P$ - altrimenti significa che $x$ non è palindroma è $T_{PAL}$ entra in $q_{NP}$.

Esplicitiamo le fasi:

1) $<q_0,(a,\square,\square),(a,a,a),q_0,(D,D,D)>$, $<q_0,(b,\square,\square),(b,b,b),q_0,(D,D,D)>$, $<q_0,(\square,\square,\square),(\square,\square,\square),q_0,(S,S,S)>$, $<q_0,(a,a,a),(a,a,a),q_1,(S,S,S)>$, $<q_0,(b,b,b),(b,b,b),q_1,(S,S,S)>$, $<q_0,(\square,\square,\square),(\square,\square,\square),q_0^P,(S,S,S)>$

>L'ultima quintupla fa tornare tutti i nastri sui caratteri iniziali e fa entrare $T_{PAL}$ nello stato interno inziziale $q_0^P$ di $T_{PPAL}$. Quindi predisponiamo la macchina per l'esecuzione di $T_{PPAL}.$

2) Copiamo le quintuple di $T_{PPAL}$ e termina con le quintuple che fanno accettare $T_{PAL}: <q_{PP}^P,(a,\square,a),(a,\square,a),q_P,(F,F,F)>,<q_{PP}^P,(b,\square,b),(b,\square,b),q_P,(F,F,F)>$. Oppure con le quintuple con che fanno entrare la macchina nello stato $q_0^D$ ossia: $<q_{NPP}^P,(a,\square,a),(a,\square,a),q_0^D,(F,F,F)>,<q_{NPP}^P,(b,\square,b),(b,\square,b),q_0^D,(S,S,S)>$.

3) Adesso copiamo le quintuple di $T_{DPAL}$ e le facciamo lavorare solo sul terzo nastro queste quintuple termineranno con la macchina $T_{PAL}$ nello stato interno $q_{PD}^D$ o $q_{NPD}^P$. Queste quintuple portano $T_{PAL}$ ad accettare o riggettare: $<q_{PD}^D,(x,y,z),(x,y,z),Q_P,(F,F,F)>$ o $<q_{NPD}^D,(x,y,z),(x,y,z),Q_{NP},(F,F,F)>$ $\forall x,y,z\in[a,b,c]$.

Questa è una descrizione molto dettagliata della simulazione che non è molto conviniente da fare poiché faticosa - possiamo introdurre una notazione che ci semplifica il modo in cui esprimiamo le quintuple.

1) La prima fase la lasciamo invariata.

2) Invece di indicare in modo esplicito ogni quintupla scriviamo: $<q_0^P,N_2,N_2,T_{PPAL},(F,F,F)>$ 

> $N_x$ è l'x-esimo nastro.

Alla fine dell'"istruzione" precedente assumiamo che le testine di $N_1$ e $N_3$ sono **posizionate sui caratteri più a sinistra.** La testina $N_2$ legge $\square$ e lo stato interno di $T_{PAL}$ è quello nel quale termina $T_{PPAL}$. Cui seguono le quintuple mostrate precedentemente nel secondo passo.

3) Usiamo la stessa notazione per la fase 3: $<q_0^D,N_3,N,3,T_{DPAL},(F,F,F)>$

Alla fine assumiamo che la testina di $N_1$ è posizionata sul carattere più a sinistra - mentre le testine su $N_2$ e $N_3$ leggono $\square$ - lo stato interno di $T_{PAL}$ è quello nel quale termina $T_{DPAL}.$ Cui seguono le quintuple mostrate precedentemente nel secondo passo.

> **OSSERVAZIONE:** Quelle che abbiamo indicato prima **NON** sono quintuple sono solo una notazione. Usiamo queste notazioni per evitare di riscrivere quintuple di macchine già create.

---
### Somma di k (non costante) interi

Progettare una macchina di Turing a due nastri che, avendo sul primo nastro una serie di numeri interi separati dal carattere ‘+‘, calcola il valore della loro somma scrivendo il risultato sul secondo nastro – ossia, si richiede di progettare una macchina di Turing che esegua la somma “in riga” di una quantità imprecisata di numeri.

Abbiamo già visto come costruire una macchina che fa la somma di due cifre con dimensioni diverse - chiamiamo la macchina che svolge questa funzione $T_{A+B}$. 

Progettiamo una macchina di Turing che risolve l'esercizio simulando $T_{A+B}$.

> $N_1$ - Nastro che contiene l'input.
> $N_2$ - Nastro di input e di lavoro di $T_{A+B}$.
> $N_3$ - Nastro di output di $T_{A+B}$.

Descriviamo la macchina $T_{SOMMATORIA}$ ad alto livello. Ha come input $x_1+x_2+....+x_n$ su $N_1$ opera come segue:

1) Scrive $'0+'$ su $N_2$.

2) Se $N_1$ non è vuoto copia il primo addendo da $N_1$ a $N_2$ a destra del '+' cancellandolo da $N_1$ - altrimenti cancella il '+' da $N_2$ e termina.

3) Simula $T_{A+B}$ su $N_2$ e scrivi il risultato su $N_3$.

4) Cancella il contenuto di $N_2$ e - cancellandolo da $N_3$ - copia il contenuto di $N_3$ su $N_2$; aggiungi il carattere '+' come ultimo carattere a destra di $N_2$ e ricomincia dalla fase 2.

Descriviamo la macchina a basso livello:

1) $\forall a\in[0,...,9] <q_0,(a,\square,\square),(a,0,\square),q_1,(F,D,F)>$ e $<q_1,(a,\square,\square),(a,+,\square),q_2,(F,D,F)>$

2) $<q_2,(a,\square,\square),(\square,a,\square),q_2,(D,D,F)>$ e $<q_2,(+,\square,\square),(\square,\square,\square),q_3,(D,F,F)>$ Altrimenti: $<q_2,(\square,\square,\square),(\square,\square,\square),q_4,(F,S,F)>$ e $<q_2,(\square,+,\square),(\square,\square,\square),q_F,(F,F,F)>$

3) $<q_3,N_2,N_3,T_{A+B},(F,F,F)>$ il cui significato è: Esegui le quintuple della macchina $T_{A+B}$ utilizzando $N_2$ come nastro di input e $N_3$ come nastro di output.

> Indichiamo con $q_F^{A+B}$ lo stato finale di $T_{A+B}$ e assumiamo che - al termine della simulazione la macchina $T_{SOMMATORIA}$ si trovi nello stato $Q_F^{A+B}$. Supponiamo anche che al termine della simulazione la testina di $N_3$ sia posizionata sul carattere più a sinistra.

4) $\forall a,c \in [0,1,..9,+,\square]$ e $\forall b \in[0,1,..,9,+]$ allora $<q_F^{A+B},(a,b,c),(a,\square,c),q_F^{A+B},(F,S,F)>$ e $<q_F^{A+B},(a,\square,c),(a,\square,c),q_4,(F,S,F)>$. Altrimenti $<q_4,(a,\square,c),(a,c,c),q_4,(F,D,D)>$ e $<q_4,(a,\square,\square),(a,+,\square),q_2,(F,D,D))>$.

---
### Note sulla simulazione

La tecnica della simulazione ci permette di *semplificare il progetto* per nuove macchine di Turing - ma quando è che la possiamo usare?

In particolare quando possiamo usare una certa macchina di Turing data così com'è - **ossia senza doverla modificare** - come negli esempi.

Questa simulazione si definisce **a scatola chiusa**.

> Quando usiamo questo tipo di simulazione **non è necessario conoscere le quintuple della macchina** che stiamo simulando.

Esiste un'altra tecnica di simulazione che prevede di simulare esplicitamente una quintupla alla volta - quindi dobbiamo conoscere tutte le quintuple che definiscono la macchina - per tale ragione si definiscono **a scatola aperta.**

> Conviene sempre usare la simulazione a scatola chiusa.

> Usiamo la simulazione a scatola aperta quando dobbiamo simulare un certo numero di macchine che - su alcuni input (a noi sconosciuto) - potrebbere andare in *loop* - ossia non raggiungere mai lo stato finale - ma continuano ad eseguire quintuple all'infinito.