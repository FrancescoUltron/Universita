
==Il compito di gestire la memoria spetta ad una parte del Sistema Operativo.==

Le applicazione moderne richiedono sempre più memoria, quindi è ==importante riuscire a gestirla nel migliore nei modi==, specialmente la memoria principale(RAM).

Il modo in cui utilizziamo i moderni sistemi operativi è tramite la presenza di "**Astrazioni**" che ==ci permettono di farci manipolare oggetti, tramite dei software, senza che sappiamo come effettivamente quell'oggetto viene implementato nell'hardware==, per esempio i file.

---------------------------------------------------------------------

## No memory abstraction

Il modo più semplice per gestire la memoria è tramite ==l'assenza di astrazioni==, infatti i primi computer ==**muovevano i dati tramite l'uso di celle di memorie fisiche.**==
Ciò ==non== permetteva l'==esecuzione di due programmi in modo parallelo==, ogni cosa doveva essere allocata in una parte della memoria fisica il che comportava ==grandi rischi nel caso di eliminazione di dati==, infatti in caso di errore l'intero processo andava buttato, oltre ad esserci un ==problema di inefficienza.==

Esporre la memoria fisica ai processi presenta delle criticità:

- Possono facilmente ==sovrascrivere il sistema operativo.== 
- è difficile avere un'organizzazione stabile

Una soluzione iniziale venne proposta con **IBM 360** che utilizzava una ==tecnica di **riallocazione statica** per svolgere processi in parallelo,== Svantaggi:

- **Rigidità**
- **Utilizzo inefficiente della memoria**
- **Flessibilità limitata**

## Un astrazione della memoria: Spazio di indirizzamento

Per consentire a più applicazioni di stare in memoria allo stesso tempo senza interferire devono essere risolti due problemi:

- **Protezione**
- **Riposizionamento** 

La soluzione iniziale era quella del IBM 360, ==ma una soluzione migliore era quella di inventare una nuova astrazione per la memoria:== **lo Spazio di indirizzamento.**

**Spazio di indirizzamento:** ==L'insieme di indirizzi che servono a pilotare il dispositivo, la RAM è organizzata ad indirizzamento.==

Abbiamo astratto la memoria e il Sistema Operativo la potrà usare per semplificare il posizionamento di processi e dati.
Lo spazio degli indirizzi è indipendente da tutti gli altri elementi del computer.

==Adesso il problema principale è gestire il modo in cui vengono assegnati gli indirizzi di memoria==, per risolvere ciò sono stati inventati due registri:

- **Registro Base**: Contiene ==l'indirizzo di partenza della partizione di memoria assegnata== quando un processo è caricato in memoria l'indirizzo fisico iniziale del suo spazio di memoria viene memorizzato nel registro base.

- **Registro Limite**: Questo registro ==contiene la dimensione (length) della partizione di memoria assegnata al processo==, definisce il numero massimo di indirizzi di memoria che il processo può utilizzare a partire dall'indirizzo di base.

**Funzionamento**:

1) **Calcolo dell'Indirizzo Fisico**: L'indirizzo logico (indirizzo virtuale) generato dal processo viene aggiunto al valore del registro base per ottenere l'indirizzo fisico.

2) **Verifica del Limite**: Prima di eseguire l'accesso, il sistema verifica che l'indirizzo logico sia inferiore al valore del registro limite. Se l'indirizzo logico è maggiore o uguale al valore del registro limite, si genera un errore di protezione della memoria (spesso chiamato fault di memoria o violazione di accesso).

**Svantaggi**: ==Ogni volta che dobbiamo andare a consultare questi registri dobbiamo fare delle operazione abbastanza complesse.==

--------------------------------------------------------------------------
Dobbiamo trovare un modo di gestire la memoria e gli spazi di indirizzamento, esistono due approcci:

- **Swapping**
- **Memoria virtuale**
--------------------------------------------------------------------------
## **Swapping**

Lo swapping ci permette di ==swappare processi da la memoria principale ad una memoria secondaria e viceversa per un determinato periodo di tempo==, il processo nella memoria principale è quello che viene eseguito.

![[swapping.png]]

Quando si svolgono degli swap succede che vengono creati dei buchi nella memoria, il modo migliore per gestirli è semplicemente ==unire tutti i buchi per crearne uno molto grande spostando tutti i processi verso la parte bassa della memoria== (**Memory Compaction**).

**Gestione della memoria libera:**

==Quando la memoria viene assegnata dinamicamente il sistema operativo deve gestirela e mantenere traccia di quest'ultima==.
Swapping e gestione della memoria libera sono correlate, esempio: quando un processo viene swappato, allora le unità di memoria libere devono essere gestite con uno dei seguenti metodi:


**Gestione memoria con bitmap**: 

La ==memoria fisica è divisa in unità di allocazione== di dimensione fissa, la dimensione di unità può essere ==fino a qualche KB==, **presso ogni unità ci sta una bitmap che specifica se l'indirizzo di memoria è libero(0) o occupato(1).** Una cosa importante della bitmap è la grandezza di quest'ultima. Il problema principale è che ==quando vogliamo inserire un processo di k unità, la memoria dovrà cercare per k bit impostati a 0 consecutivi il che è un'operazione lenta==, oltre ad avere un tempo di ricerca con complessità O(n).


**Gestione della memoria con liste concatenate**: 

Gestiamo la memoria tramite una lista come in C, ==viene creata una struttura per ogni segmento della memoria,== nella lista abbiamo:

- **Processo/buco**
- **L'inizio dell'indirizzo di memoria**
- **Lunghezza processo**
- **Puntatore al nodo successivo**

Esistono diversi algoritmi per allocare memoria nel momento in cui nasce un nuovo processo.

- **First fit:** ==Appena trovo uno spazio libero nella lista inserisco il nuovo processo==, è molto veloce e la situazione peggiore si verifica se lo spazio libero si trova alla fine.

- **Next fit**: Variante del First fit, ==funziona uguale al First fit==, ma ==tiene traccia della posizione dove ha tenuto l'ultimo spazio libero==, il problema è che ==non si tiene conto di possibili posti di memoria che si liberano==, quindi si hanno perfomance peggiori.

- **Best fit**: ==Scorre la lista è cerca la segmentazione== (Quella più piccola che può contentere il processo) ==migliore per il processo==, problema deve ==scorrere tutta la lista==, ciò ==crea tanti piccoli spazi vuoti fra dei processi, ci sta uno spreco di memoria==, first fit ha buchi più grandi.

- **Worst fit**: ==Logica inversa del Best fit== non è buon algoritmo, quindi si creano buchi di memoria piu grandi da riutilizzare, fa un po schifo.

- **Quick fit**: ==Ci sono liste separate di dimensioni diverse==, il ==processo è inserito nella lista della stessa dimensione==, ha lo stesso tipo di ==problematiche degli altri processi, quello della ricerca==, ma ciò è un problema della struttura dati lista che ti obbliga a vedere tutta la lista per la ricerca.

## **Memoria virtuale**

E' una tecnica di gestione della memoria usata dai sistemi operativi per estendere la capacità della memoria fisica (RAM) utilizzando spazio su disco rigido. ==Permette ai computer di eseguire programmi più grandi di quanto la memoria fisica possa gestire direttamente== e si basa sul concetto di paginazione.

==Ogni programma ha un suo spazio di indirizzi, mappati sulla memoria fisica, ma non per forza mantenuti su quest'ultima.==
Quando il ==programma fa riferimento a una parte del suo spazio di indirizzi== che si trova:

- **In memoria fisica,** l'hardware esegue la mappatura on-the-fly
- **Non in memoria fisica,** il Sistema Operativo avverte che manca il pezzo e riesegue l'istruzione

==In questo modo traduce l'indirizzo virtuale in uno fisico.==

Molti Sistemi Operativi utilizzano il concetto di **paginazione o paging**, ciò è possibile tramite un unità chiamata **MMU (Memory Managment Unit)** ==che tramite bit di presenza/assenza tiene conto delle pagine presenti fisicamente in memoria, oltre a mantenere una **tabella delle pagine**.==

Nei *computer senza memoria virtuale*, l'indirizzo virtuale è messo direttamente sull'address bus, quindi *memoria fisica e virtuale sono la stessa cosa*.

**Paginazione**: ==La memoria virtuale è divisa in blocchi di dimensioni fisse chiamati **pagine**.==== ==La memoria fisica è divisa in blocchi della stessa dimensione chiamati **frame**==.Il sistema operativo mantiene una tabella di pagine che mappa le pagine virtuali ai frame fisici.

**Nella paginazione una qualsiasi pagina della memoria virtuale può essere caricata in un qualsiasi frame della memoria fisica.**

Se una ==pagina virtuale non è in memoria==, la MMU si accorge che il riferimento non è in memoria e causa un ==errore di pagina (**page fault**)== al sistema operativo, in questi casi succede che:

1) Il ==sistema operativo seleziona il frame meno utilizzato e lo scrive sul disco==;
2) Poi ==sovrascrive il suo contenuto con la pagina appena creata==;
3) ==Cambia la mappa dentro la MMU;==
4) ==Riavvia l'istruzione che aveva causato la trap==;

**Struttura di una riga nella tabella delle pagine:**

![[tabellaPagina.png]]

**Algoritmi di sostituzione delle pagine**

Quando avviene il ==page fault il SO deve scegliere la pagina da rimuovere dalla memoria per far spazio alla pagina mancante.==

L'algoritmo ottimo di sostituzione delle pagine è facile da descivere, ma impossibile da realizzare:

Durante il Page Fault, ==vengono etichettate tutte le pagine con il numero di istruzioni da eseguire prima che vengano referenziate==, quindi occore ==rimuovere quella con il numero più grande==, ma è **impossibile** perché il ==sistema operativo non sa quando ciascuna pagina verrà referenziata di nuovo==.

**Not Recently Used(NRU):**

Ad ogni pagina vengono associati due bit:

- **R** impostato nel caso la pagina è ==referenziata==
- **M** Viene impostato quando la pagina viene ==scritta==

Quando il processo è avviato Il SO ==imposta a 0 tutti i bit di pagina==, ad ogni impulso di clock viene azzerato il bit R, così da distinguere le pagine non usate recentemente.
Durante il Page Fault, il SO controlla le pagine e le categorizza nelle 4 possibili combinazioni.
E' ==l'algoritmo elimina una delle pagine con il valore più basso, poiché significa che è non stata usata da molto tempo.==
Le performance sono adeguate, ma non ottimali.

**First In First Out**:

Il SO ha una ==lista con tutte le pagine in memoria==: la più **recente in coda e la meno recente in testa**, quando ci sta un ==Page Fault la pagina in testa è rimossa e la nuova pagina è aggiunta in coda.==
Algoritmo poco usato perché non tiene conto dell'importanza di una pagina.

**Second Chance:**

==Variante del FIFO== ed è progettata per migliorare le decisioni di sostituzione, ogni pagina ha un bit di riferimento impostato ad 1 quando viene acceduta, quando ci sta un Page Fault viene letta la pagina in testa e se il ==bit è impostato ad 1 allora la pagina riceve una seconda chance di rimane nella lista== venendo spostata in fondo e impostando il bit a 0, se la pagina in testa ha un bit a 0 allora viene sostituita con la nuova pagina.

Inefficiente perché le pagine sono sempre in movimento nella coda, ==conviene usare una lista circolare.==

**Least Frequently Used(LSU)**: 

Approssimazione dell'algoritmo ottimo, ==osserva le pagine usate più frequentemente e ipotizza che continueranno ad essere referenziate, al contrario delle pagine non utilizzate da molto tempo.==
Quando si verifica un ==Page Fault viene buttata la pagina inutilizzata da più tempo.==
E' fattibile, ma non è economico.

Esiste anche una versione con un contatore a 64 bit che viene incrementato dopo ogni istruzione, viene eliminata la pagina con il valore del contatore più basso.

**Not Frequently Used (NFU):

Un altro algoritmo implementato via software, ==ogni pagina è associata ad un contatore==, ad ogni ==interruzione di clock il SO scansiona tutte le pagine in memoria e per ogni pagina il bit R e sommato al contatore.==

I contatori così hanno il numero di riferimenti fatti a ciascuna pagina, ==verrà sostituita la pagina con il contatore più basso.==

Però ci sta la ==possibilità che il SO elimini pagine ancora utili,== rispetto a pagine che hanno finito la loro esecuzione.

**Aging:**

==Ogni pagina ha un contatore impostato a 0 e un valore, una stringa di bit uguale a 0==.
A intervalli regolari i ==valori vengono aggiornati== facendo lo **shift da destra del valore del contatore.**
Se il contatore di una pagina sta a 0 significa che nell'intervallo non è stata referenziata, altrimenti è stata referenziata.
Il bit del contatore viene poi fatto tornare a 0.

==I valori vengono aggiornati regolarmente e quando ci sta un Page Fault viene sostituita la pagina che ha il valore minore all'interno della memoria.==

## Working Set


Tecnica utilizzata nei sistemi operativi per gestire la memoria virtuale in modo efficiente, ==riducendo il numero di page fault==.

Il Working Set è l'insieme delle pagine che un processo sta usando in un determinato intervallo di tempo

1. **Monitoraggio dell'Uso delle Pagine**:

- Il sistema operativo tiene traccia delle pagine di memoria utilizzate da ciascun processo.
- ==Ogni volta che una pagina viene acceduta, il suo timestamp di accesso viene aggiornato.==

2. **Calcolo del Working Set**:

- Periodicamente, il sistema operativo ==esamina i riferimenti di memoria di un processo nell'intervallo Δ per determinare il working set corrente.==
- Le pagine che sono state accedute entro questo intervallo sono considerate parte del working set.

3. **Gestione della Memoria**:

- Se il working set di un processo cresce troppo, potrebbe indicare che il processo ha bisogno di più memoria. Il sistema può decidere di allocare più frame di memoria a quel processo.
- Se il working set diminuisce, il sistema operativo può deallocare alcune pagine e utilizzare quei frame per altri processi.

4. **Sostituzione delle Pagine**:

- ==Le pagine che non fanno più parte del working set possono essere candidate per la sostituzione.==
- Quando una nuova pagina deve essere caricata in memoria e non ci sono frame liberi, il sistema operativo può scegliere di rimuovere una pagina non inclusa nel working set corrente.
## WS Clock

Nell'algoritmo **clock** inseriamo ==tutte le pagine in una lista circolare== a forma di orologio con ==una lancetta che punta allla pagina più vecchia.==

Quando ci sta un ==Page Fault si controlla il bit R della pagina a cui punta la lancetta== se:

- 0, la ==pagina è sostituita con la nuova pagina== e lancetta si muove sulla successiva posizione.
- 1, esso viene ==azzerato== e la lancetta si muove alla successiva posizione fino a che non trova una pagina con il bit R = 0

E' estremamante simile al Second Chance.

Nel **WSClock** ad ogni Page Fault la lancetta punta alla pagina da esaminare:

- Se il **bit R = 1,** la ==pagina è stata appena usata e il bit viene messo a 0==.

- Se il bit **R = 0 e la pagina non è nel working set ed esiste una copia nel disco (M=0)**, la ==pagina può essere sovrascritta dalla nuova.==

- Se il bit **R = 0 e la pagina non è nel Working Set e non ci sta un copia sul disco (M=1)**, per evitare un cambiamento del processo viene ==schedulata la scrittura sul disco e la lancetta passa alla pagina successiva.==

**Cosa succede se la lancetta torna al punto di partenza senza aver trovato nessuna pagina da rimuovere?**

Esistono due possibilità:

- **E' stata programmata una o più scritture**: Dal momento che sono state programmate delle ==scritture sul disco, al termine di attività di scrittura ci sarà almeno una pagina "Pulita" (M = 0)==. La prima pagina "Pulita" ==verrà eliminata.==

- **Non sono presenti richieste di scrittura:** Significa che ==tutte le pagine sono nel Working Set== e **non ci sono pagine "Pulite"**, allora ==la vittima sarà la pagina corrente che verrà scritta sul disco.==


