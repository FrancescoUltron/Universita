L'obiettivo principale dell'industria dei calcolatori è da sempre orientata verso ==l'incremento delle performance.==
In passato ciò è stato possibile tramite ==l'incremento della frequenza di clock==, ma abbiamo ormai raggiunto un limite fisico(**La velocità della luce**), inoltre dobbiamo tenere conto del ==problema della dissipazione del calore==, si e deciso di ottenere maggiore potenza di calcolo attraverso le **architteture parallele** (Molte ==CPU che collaborano==).

--------------------------------------------------------------------------
## Classificazione delle architetture

La **classificazione di Flynn** ci permette di ==distinguere i diversi tipi di architetture in base alla modalità d'esecuzione delle istruzioni e del flusso dei dati.==![[Flynn.png]]
## Sintesi delle architetture

Questo quadro riassume tutti i possibili tipi di architetture![[SintesiPC.png]]
==Per accoppiamento intendiamo il grado di interconnessione e dipendenza tra i componenti del sistema.==
Nel parallelismo nel chip ci sta un accoppiamento stretto perchè tutti processi condividono lo stesso spazio di memoria e CPU, e quindi sono stretti, altrimenti in una griglia dove ognuno ha la sua memoria e CPU sono laschi.
## Parallelismo nel chip

Un modo efficiente per migliorare le prestazioni è facendo ==svolgere più operazioni parallelamente alla CPU==, esistono diversi modi per fare ciò:

- **Parallelismo a livello delle istruzioni:** ==Emettiamo più istruzioni per ciclo di clock==, ci sono due tipi principali di CPU ad emissione multipla: **CPU Superscalari**: ==composte da pipeline e unità funzionali parallele== in grado di emettere più istruzioni in un solo ciclo (determinate dall'hardware); **Processori VLIW**: ==indirizzano le diverse unità funzionali con una sola linea di pipeline==, ed effettuano il fetch e l'emissione di un pacchetto per volta.

Le CPU moderne a pipeline presentano un ==problema==: **Lo stallo della pipeline** e si verifica quando un ==riferimento in memoria fallisce nelle cache di primo e secondo== livello bisogna ==aspettare== che la parola interessata venga ==caricata in cache.==

- **Multithreading nel chip**: maschera queste situazioni attraverso lo ==switch tra thread.== Tre diversi approcci: **Multithreading a grana fine**: Ad ogni ==ciclo di clock, a turno, vengono eseguite le singole istruzioni dei thread==, nei cicli di ==stallo la CPU svolge le istruzioni degli altri thread==, ognuno avente il suo insieme di registri e il numero massimo di thread concorrenti e definito durante la progettazione del chip; **Multithreading a grana grossa:** prevede un ==cambio di thread solo se la CPU incontra un lungo ritardo== è ==inefficiente== rispetto al grana fine, ma ==richiede meno thread== per mantenere la CPU occupata; **multithreading Simultaneo**: ogni ==thread esegue due istruzioni per ciclo finchè può==, a questo punto ==si passa all'istruzione del thread successiva affinchè la CPU rimanga occupata.==

- **Multiprocessori in un solo chip**: L'idea è di inserire ==più CPU (detti **Core**) all'interno dello stesso chip (oppure **Die**)==, possono essere realizzati con ==Core identici== (**Multiprocessori omogenei**) che hanno la stessa cache e memoria centrale oppure con Core con ==specifiche funzionalità== (**Multiprocessori eterogenei**).
## Coprocessori

Si può migliorare la velocità di un calcolatore attraverso l'==aggiunta di un coprocessore specializzato.==
I coprocessori più utilizzati sono:

- **Processori di rete**
- **Processori grafici**
- **Crittoprocessori**

## MIMD - Multiprocessori

Nei calcolatori MIMD le ==comunicazioni tra componenti elettronici avvengono tramite lo scambio di messaggi==, le differenze tra i diversi tipi di MIMD risiedono in:

- ==Base dei tempi==
- ==Scala delle distanze==
- **Organizzazione logica**

**Multiprocessori:** E' un calcolatore in cui tutte le ==CPU hanno una memoria comune==, una particolarità è che una ==CPU può scrivere un valore in memoria, ma durante la lettura trovarlo modificato da un'altra CPU==, esistono diversi tipi di implementazioni:

- **UMA (Uniform Memory Access) con architettura basata sul bus**: Un ==singolo bus== che collega tutte le CPU alla memoria condivisa, ==ogni CPU deve attendere che si liberi il bus==, ma funziona per un numero ==limitato di CPU==, per risolvere questo problema ==si possono aggiungere cache per ogni CPU==, ma per mantenerne la coerenza bisogna utilizzare delle tecniche come:

--------------------------------------------------------------------------
**Cache Snooping**: Un controllore che carica le parole mancanti nelle CPU

**Protocollo MESI**: Si basa sui quattro stati in cui si possono trovare i blocchi di memoria delle cache ed è usato per lo snooping:

- **Modified**: Il blocco si trova solo in questa cache ed è stato modificato rispetto alla memoria

- **Exclusive**: Il blocco è oresente solo in una cache è non è stato modificato.

- **Shared**: Il blocco è condiviso in più cache ed è coerente con ciò che sta in memoria.

- **Invalid**: Il blocco non è valido, non  ci sono dati validi.
--------------------------------------------------------------------------

- **UMA con crossbar switch**: Per poter aumentare il numero delle CPU dobbiamo utlizzare una ==differente rete di connessione==, il circuito più semplice che permette di collegare n CPU a k memorie è il **crossbar switch**, in ==ogni intersezione tra CPU e RAM ci sta un interruttore che ne stabilisce il collegamento==. Questa è una **rete non bloccante**: ==ogni CPU raggiunge ogni memoria==, basta aprire o chiudere l'interruttore. Rimane il ==problema della competizione della memoria e caratteristica negativa è che il numero di incroci cresce come $N^2$.==

- **UMA con rete di commutazione a più stadi:** 
  ![[comuutazioneapistadi.png]]Questa è una **rete bloccante** in cui non tutti gli insiemi di richieste sono processati   contemporaneamente, ==conviene mantenere i riferimenti alla memoria in modo uniforme rispetto ai moduli ==(**Interleaved**) in modo tale da massimizzare il parallelismo.

- **Multiprocessori NUMA (Non Uniform Memory Access):** Per eccedere il numero di CPU utilizzabili in un UMA dobbiamo rinunciare a qualcosa: Tempi di accesso uniformi della memoria. I ==tempi di accesso in memoria son ridotti ed l'approccio più comune per costruire processori NUMA è basato sulle directory (**Directory-based Multiprocessor**)==, ogni processore  ha una memoria locale a cui accede velocemente e allo stesso tempo è accessibile ad altri nodi, l'idea è di ==mantenere una base di dati che viene interrogata quando è referenziata una linea di cache, in modo tale da sapere se è pulita o sporca e dove si trova.==

Esistono due tipi di NUMA:

- **No Cache** - Nonuniform Memory Access (NC-NUMA)
- **Cache Coherent** - Memory Access (CC-NUMA)

Le macchine NUMA hanno tre caratteristiche chiave:

1) C'è un ==unico spazio di indirizzamento== visibile a tutte le CPU
2) L'accesso alla ==memoria remota è attraverso istruzioni di LOAD e STORE==
3) L'accesso alla ==memoria remota è più lento dell'accesso rispetto alla memoria locale==

Esistono inoltre tre diversi approcci per i sistemi operativi multiprocessori:

1) **Ogni CPU ha un proprio sistema operativo:** ==Dividiamo staticamente la memoria in tante partizioni quante sono le CPU==, le CPU operano con computer indipendenti.

2) **Multiprocessori master-slave:** Il ==SO e le sue tabelle sono presenti solo su una CPU(Master)==, tutte ==le chiamate di sistema sono redirette a questa CPU==. Risolve molti problemi dell'implementazione precedente: Singola struttura dati tiene traccia di tutti i processi pronti, Master bilancia il carico, ma ==può diventare un collo di bottiglia==, pagine allocate dinamicamente tra processi e c'è solo una buffer cache, ==il modello funziona con un numero ridotto di CPU.==

3) **Multiprocessori simmetrici:** ==Elimina l'assimetria del master-slave, poiché ogni CPU può diventare master, una copia del SO è in memoria e ciascuna CPU può eseguirla.== Elimina il collo di bottiglia, ma introduce nuovi problemi: Sincronizzazione delle CPU dovuta alla condivisione del SO, deadlock.

Su un ==monoprocessore lo scheduling è mono-dimensionale==, eseguiamo i thread in ordine, nei ==multiprocessori invece è bidimensionale==, lo scheduler deve decire chi mandare in esecuzione, esistono diversi algoritmi per decidere:

- **Timesharing**: Utilizzato se i ==thread sono indipendenti==, si ha un ==vettore di liste di thread a diverse priorità d'esecuzione==, le CPU eseguono in ordine i thread, fornisce un sistema di bilanciamento del carico, ma abbiamo due ==problemi: Dispute d'accesso alla struttura con l'aumento delle CPU, sovraccarico durante lo scambio di contesto,== in alcuni sistemi si possono aggiungere diversi tipi di scheduling per migliorare l'efficienza: **Smart Schdeuling**, avviene quando il ==quantum di tempo termina mentre un thread mantiene uno spin lock che serve ad altre CPU==, in questo caso si **aggiunge un extra-quantum.** Altro tipo è l'**affinity scheduling** cioè si cerca di ==far eseguire lo stesso thread sempre dalla stessa CPU.==

- **Space Sharing**: Se i ==thread sono interrelati== si può usare lo scheduling a condivisione di spazio, si vuole svolgere un intero gruppo di thread correlati, ad ==ogni thread viene assegnata una CPU e viene avviato, se ciò non accade significa che non ci sono abbastanza CPU disponibili.== **Distribuisci i tuoi processi in maniera equa fra le varie risorse.**

- **Gang Scheduling**: Cerca di unire il time e lo space sharing e si compone in tre passaggi:

1) I gruppi di ==thread correlati sono schedulati come un unica squadra== (**gang**).
2) Tutti i membri della ==gang girano simultaneamente su differenti CPU in timesharing.==
3) Tutti i ==thread della gang iniziano e terminano la loro porzione di tempo contemporaneamente.==

   Il tempo è diviso in quantum e le CPU sono schedulate in sincronia, ad ogni nuovo quantum tutte le CPU sono rischedulate, se un thread si blocca la CPU rimane in idle finché non scade il quantum.

## MIMD - Multicomputer

Uno dei problemi principali dei ==multiprocessori è quello del costo==, per ovviare questi problemi sono nati i ==Multicomputer che sono accoppiati in modo lasco==, e non condividono memoria.
==Ognuno ha la sua memoria==, sono anche chiamati **Cluster di Computer o Cluster di Workstation**.

Sono facili da costruire, il ==componente== base è un **PC con una scheda di rete con alte performance**, i messaggi sono spediti nell'ordine di microsecondi.

I Multicomputer ==sono formati da nodi==: **CPU, una memoria e un interfaccia di rete**, questa e ==connessa ad altri nodi o switch tramite dei cavi.==

Esistono diversi tipi di sistemi, alcuni molto famosi sono:

- **Topologia a stella:** ==Switch connesso a tutti i nodi==

- **Topologia ad anello**: Ogni ==nodo  è collegato ad alti due nodi per formare un anello== circolare (Non si usano switch).

- **Griglia**: struttura bidimensionale per sistemi complessi è ==altamente scalabile== e il ==percorso più lungo tra due nodi, il diametro, aumenta come la radice quadrata del numero di nodi.==

- **Doppio toro**: Variante della griglia, ma ==i nodi estremi si congiungono è più tollerante ai guasti della griglia, ma con un diametro più piccolo.==

- **Cubo**: Topologia tridimensionale regolare.

- **Ipercubo**: ==Un cubo di dimensione 4 ottenibile sommando due cubi di dimensione 3, il diametro cresce in modo lineare con la dimensione==, possiamo ottenere cubi di n+1 dimensione in maniera ricorsiva partendo un cubo di n dimensione.

Nei multicomputer sono usati ==due sistemi di switching:==

- **Store-and-forward packet switching (Connection-Less)**: Ogni messaggio è suddiviso in pacchetti inseriti nella rete, il ==pacchetto raggiunge il nodo di destinazione tramite delle politiche, flessibile ed efficiente==, ma ha un problema di incremento dei tempi di latenza lungo la rete di interconnessione.

- **Circuit switching (Conection oriented):** Il primo ==switch stabilisce un collegamento fisico con tutti gli switch coinvolti, fino alla destinazione, poi quando la connessione è creata i bit vengono spediti alla massima velocità possibile==, ha bisogno di una fase di preparazione. Una variante è il **wormhole routing** in cui viene ==spezzato il pacchetto in sottopacchetti che possono iniziare il tragitto prima che sia stato preparato il collegamento.==

Ogni ==nodo deve avere una scheda di rete che permette di collegare il nodo alla rete di interconnessione==, il nodo contiene una **RAM** per memorizzare i pacchetti che entrano ed escono, la scheda di rete può avere più **canali DMA o anche una CPU completa.**
I canali DMA copiano i pacchetti dalla scheda di rete alla  RAM molto velocemente.

==Il principale difetto dei multicomputer è l'eccessiva copiatura dei pacchetti==, per evitare ciò ==viene mappata la scheda d'interfaccia nello spazio utente così che il processo utente possa inserire dati direttamente nella scheda di rete==, ma ha due problematiche:

1) **Competizione dei processi concorrenti sullo stesso nodo** (Meglio un solo processo per nodo).

2) **Condivisione della scheda di rete tra kernel e il processi utente** (Meglio avere due schede di rete per funzione).

I ==processi sulle diverse CPU comunicano attraverso lo scambio di messaggi== (Possibile grazie a dei **software di comunicazione**).

==Il SO fornisce ai processi utenti delle funzioni che consetono di inviare(send) o ricevere(recieve) messaggi.==

```
send (destination, &message_pointer)
receive (address, &message_pointer)
```

Il campo address di compone di:

- Identificativo della CPU.
- Identificativo del processo o della porta sulla CPU selezionata.

Queste due primitive possono essere **bloccanti**(**chiamate sincrone**) o **non bloccanti **(Chiamate asincrone).

Se una send() è non bloccante, essa restituisce il controllo al chiamante immediato dopo la sua esecuzione e prima che il messaggio venga realmente spedito, nel caso contrario il chiamante rimane in attesa finchè il messaggio non è inviato.

Da un lato ==abbiamo un vantaggio di performance==, dall'altro uno ==svantaggio dovuto al fatto che il processo che ha spedito il messaggio non può accedere al buffer fino a che il buffer non è vuotato e il messaggio spedito,== tre soluzioni:

1) **Kernel copia il messaggio in un buffer interno**
2) **Il mittente riceve un interrupt in modo tale che possa usare il buffer**, al termine della spedizione.
3) Fare una **copia del buffer nel caso in cui venga riscritto.**

==Il processo destinatario può usare una receive() non bloccante: indica dove il buffer al kernel==, l'arrivo di un messaggio può essere gestito:

- Tramite ==Interrupt==.
- Richiamando una procedura ==poll().==
- Attraverso la ==creazione automatica di un thread.==
- Attraverso un ==interrupt che attiva nella ISR il codice di gestione.==

**Remote Procedure Call:** Il modello a scambio di messaggi è conveniente, ma usano I/O tramite ==RPC i programmi possono chiamare procedure che si trovano su le altre CPU del multicomputer indipendentemente dall'I/O.==
==Permette ai programmi di chiamare procedure che stanno su altri computer==
RPC è alla base dei multicomputer, bisogna far si che il **programma client abbia una procedura client stub**, mentre il **programma server una chiamata server stub.**

![[RPC.png]]
**Insidie nell'uso delle RPC:**

- ==Non== si possono usare ==puntatori come parametri ==
- Se ci sono ==array come parametri bisogna specificare le dimensioni==
- ==Non è sempre possibile capire il tipo dai parametri==
- ==Non ci sono variabili globali==

**Distributed Shared Memory:** Alcuni programmatori utilizzano questo modello anche sui multicomputer con cui è possibile ==fornire una visione astratta della memoria.==
==Ciascuna macchina ha una memoria virtuale==, quando la ==CPU scrive o legge un dato su una pagina che non ha, avviene una trap del SO==:

- Il SO localizza la pagina
- Chiede l'unmap della pagina alla CPU
- Dopo aver ricevuto la pagina, viene rimappata e l'istruzione che ha generato l'errore rieseguita
![[DSM.png]]

**Scheduling sui multicomputer:** Su un multicomputer ogni nodo ha una propria memoria e un proprio insieme di processi, ==lo scheduling è simile ai multiprocessori==, ma non tutti gli algoritmi sono applicabili.
==Una volta che il processo è assegnato ad un nodo funziona qualsiasi algoritmo di scheduling locale==, nei multicomputer **la scelta di quale processo spedire su un nodo è cruciale** e gli algoritmi per l'assegnazione di un processo al nodo si chiamano **algoritmi di allocazione dei processi**, ==differiscono per i requisiti e per l'obiettivo.==

## Virtualizzazione

==Tramite la virtualizzazione un computer può ospitare più macchine con un proprio Sistema Operativo==, questo sistema mantiene l'==affidabilità di un multicomputer== ad un ==costo ridotto ==ed ad una maggiore semplificazione della manutenibilità.
Ma un ==guasto al server delle macchine virtuale risulta gigantesco rispetto a quello di un nodo==, I *guasti di natura hardware sono minori rispetto a quelli di natura software*.

Vantaggi:

- **Isolamento** tra le macchine virtuali.
- **Riduzione delle macchine fisiche**, dello spazio e del consumo di energia.
- creazione di **checkpoint**, posso fare il restore a quei checkpoint.
- Si possono **far girare ambienti obsoleti** che non funzionerebbero su hardware attuali.
- Si possono **effettuare test su diversi SO** senza averli.

Esistono 2 approcci per il monitor delle VM:

- **Hypervisor di tipo 1**: ==E' nel SO della macchina reale, gira in modalità virtuale kernel, supporta le VM come accade per thread e processi== (Eseguiti direttamente sull'hardware fisico).

- **Hypervisor di tipo 2**: E' un ==programma utente che interpreta le istruzioni delle VM e le traduce sul SO della macchina reale== (Eseguiti sul SO host).

Ogni processore possiede il proprio set d'istruzioni:

- **Modalità utente**: Istruzioni non sensibili
- **Modalità kernel**: ==Istruzioni sensibili==, a loro volta divise in **preemptive**, cioè ==privilegiate== e **non preemptive**, ==non privilegiate.==

==Una macchina è virtualizzabile con tipo 1 solo se tutte le istruzioni sensibili sono privilegiate.==

**hypervisor di tipo 1:** La ==MV è eseguita come un processo utente in modalità utente==, non può eseguire istruzioni sensibili anche se pensa di essere in modalità kernel, ==quando il SO guest esegue un istruzione sensibile:==

- Se ==la CPU non ha la VT (virtal technology) la virtualizzazione non è possibile.==
- Altrimenti ==avviene una trap== nel kernel e l'hypervisor può vedere se l'istruzione è stata inviata da: Una **VM del SO guest**, è la ==esegue==, un **programma utente**, ==emula il comportamento dell'hardware reale==.

**hypervisor di tipo 2:** Un esempio è VMware, ==viene eseguito come programma utente==, quando si avvia per la prima volta si comporta come un computer senza SO, ==per eseguire il programma si usa la **traduzione binaria**:== 

1) Esegue la ==scansione del codice alla ricerca dei blocchi base== (JMP, CALL, TRAP).
2) ==Ricerca nei blocchi le istruzioni sensibili== e le traduce con VMware.
3) Il ==blocco è messo nella cache di VMware e può essere eseguito alla velocità della macchina fisica.==

**Paravirtualizzazione:** Gli hypervisor ==non modificano il SO guest, ma non sono performanti,== un approccio diverso consiste nella ==modifica del codice sorgente del SO guest e si effettuano le chiamate di procedure definite dall'hypervisor.==
Quindi ==l'hypervisor definisce== un'interfaccia, cioè delle **API** che i Sistemi Operativi possono attivare.
==L'hypervisor diventa un **micorkernel**== e ==il SO guest modificato viene detto **paravirtualizzato**==, le ==performance migliorano poichè le trap si trasformano in chiamate di sistema.==