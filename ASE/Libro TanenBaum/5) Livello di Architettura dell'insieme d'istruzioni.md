Si trova tra il [[4) Livello di microarchitettura]] e il sistema operativo, fu il primo livello ad essere sviluppato e ==inizialmente era l'unico livello presente==.

==Questo livello costituisce l'interfaccia tra software e hardware==, anche se si potrebbero creare un hardware che esegue direttamente programmi scritti in linguaggio ad alto livello, ciò non conviene perché si perderebbe il ==l'incremento di prestazioni garantito dalla compilazione rispetto alla traduzione==, inoltre torna utile che le macchina comprendano più linguaggi.

Approccio generale:

- ==Partire da linguaggi ad alto livello e tradurli in un livello comune, cioè l'ISA
- ==Costruire l'hardware in grado di eseguire direttamente i programmi di livello ISA

Tale livello definisce ==l'interfaccia tra compilatori e hardware== ed è il linguaggio che entrambi 
possono comprendere.

Durante la progettazione i progettisti del compilatore e i progettisti dell'hardware devono trovare un accordo comune su che cosa si può implementare. Dopo le trattative verrà ==implementato il livello ISA ottimizzato per un linguaggio di programmazione.==

**Almeno in teoria**, infatti una ==funzionalità che viene sempre richiesta dai clienti è quella della **retrocompatibilità** che obbliga a mantenere l'ISA costante di modello in modello==.

Quindi i progettisti a livello hardware (Pochi sanno come funziona) hanno carta bianca a patto che l'ISA sia retrocompatibile.

**La sfida principale è creare macchine migliori vincolate dalla retrocompatibilità.**

Il motivo per il quale la progettazione ==ISA è molto importante è per l'aumento di potenza grezza di calcolo a parità di costi==, sfortunatamente il mercato rende difficile l'abbandono di un vecchio ISA, ciononostante ogni tanto escono nuovi ISA (Specialmente nei settori specializzati).

2 fattori principali servono per creare un buon ISA:

- ==Definire un insieme d'istruzioni che può essere implementato efficientemente da tecnologie presenti e future==, ciò risulta in progetti duraturi ed economicamente vantaggiosi.

- Deve ==favorire una compilazione del codice "Pulita"==, quindi devono operare la scelta migliore tra le alternative limitate.

**In generale un buon ISA soddisfa sia i progettisti dell'hardware che del software.**

## Panoramica del livello ISA

Per rispondere alla domanda: Che cosa è il livello ISA? Dobbiamo parlare prima di alcune proprietà.

**5.1.1) Proprietà del livello ISA**:

==Il codice di un livello ISA è l'output di un compilatore.==
Per produrre un ISA il progettista deve conoscere:

- ==Il modello di memoria. 
- ==I registri che ci sono.
- ==I tipi di dati e istruzioni.

**L'insieme di queste informazioni definisce il livello ISA**, inoltre alcune ==proprietà del livello di microarchitettura vanno ad influire sulle capacità dell'ISA.==
A volte ==questo livello è specificato attraverso un documento formale di definizione==, per esempio ARM v7 (Nel caso di ARM la microarchitettura non influisce sulle prestazioni, poiché ARM rilascia solo i brevetti dei chip).

Tali documenti contengono:

- **==Sezioni normative**: Che impongono alcuni requisiti.==
- **==Sezioni informative**: Concepite per aiutare il lettore nella comprensione.

ARM v7 ha un ==documento che ne definisce l'ISA== per fare in modo che ==tuti i chip ARM possano eseguire lo stesso codice==, anche se non è sempre così, per esempio Intel non per molti anni non voleva pubblicare il documento formale per l'ISA IA-32, ciò ha causato l'inizio di una faida in tribunale che ha visto Intel come perdente, alla fine degli anni '90 venne pubblicato il documento forse perché Intel sentì di aver sbagliato o forse perché Giappone, Stati Uniti ed Europa stavano accusando la società per **possibili violazioni delle leggi Antitrust.**

La maggior parte dei processori ha due modalità d'esecuzione:

- **Kernel**: Serve ad eseguire il Sistema operativo e permette l'esecuzioni di tutte le istruzioni.
- **Utente**: Ha lo scopo di eseguire programmi applicativi e non tutte le istruzioni.

**5.1.2) Modelli di memoria**:

==Tutti i computer suddividono la memoria in celle indirizzate in modo consecutivo, la dimensione più comune per le celle è di 8 bit e sono chiamate **byte**.==
Il motivo per cui ==usiamo queste dimensione è per il codice ASCII,== infatti i caratteri nella tabella ASCII occupano ==7 bit più un bit di parità,== altre codifiche come UTF-8 o Unicode usano multipli di 8 bit per rappresentare caratteri.

I ==byte sono raggruppati in parole di 4 byte o 8 byte== ed esistono istruzioni apposite per manipolare intere parole, **molte architetture vogliono che le parole siano allineate lungo le loro estremità**, in questo modo la memoria funziona in modo più efficiente.

La ==capacità di leggere parole che cominciano a indirizzi arbitrari richiede nel chip funzionalità logiche supplementari==, il che lo rende più grande e costoso.

==Molti processori dispongono di un solo spazio lineare di indirizzi, solitamente fino a $2^{32}$ o $2^{64}$ byte.
Ma esistono anche macchine con indirizzi separati per istruzioni e dati==, ciò comporta una complessità maggiore, ma comporta anche vantaggi come sicurezza e impossibilità di sovrascrivere il programma accidentalmente.

Altro aspetto importante è quello della semantica della memoria, poiché  le microistruzioni vengono riordinate al livello precedente è possibile che la memoria esibisca comportamenti inattesi, esistono diversi modi per risolvere il problema:

- ==Serializzare tutte le richieste di accesso alla memoria==
- ==Eseguire un operazione SYNC che blocca le richieste finché non vengono svolte quelle precedenti.==

**NB: La microarchitettura può generare un po' di problemi a livello ISA**

**5.1.3) Registri**:

==Qualche registro è visibile nel livello ISA ==e tutti i registri visibili nel livello ISA sono visibili anche nel livello di microarchitettura, dove vengono implementati.

Sono divisi in due categorie:

- **Registri specializzati**: Come il Program Counter e il puntatore allo stack
- **Registri d'uso generale**: Utilizzati per mantenere le variabili locali più importanti

La loro funzione è di ==consentire un accesso rapido ai dati senza dover leggere la memoria.==
Le macchine RISC ==hanno solitamente 32 registri e sono interscambiabili== tra di loro.

==Non esiste un standard== specifico nella creazione dei registri, infatti in alcuni casi dei registri ad uso generale sono anche specializzati (Vedi Core i7), ma conviene sempre ==rispettare delle convezioni.==

Esistono anche dei registri non visibili all'utente che possono essere impiegati solo dal sistema operativo, solitamente hanno a che fare con l'hardware della macchina.

Il **Registro di flag** è un ibrido tra registri utente e kernel, si chiama anche **PSW (Program Status Word)** e contiene vari bit necessari alla CPU tra cui i ==codici di condizione che riflettono lo stato delle ultime operazioni svolte:==

- **N**: Posto ad 1 dopo un ==risultato negativo.==
- **Z**: Posto ad 1 dopo un risultato ==Uguale a zero.==
- **V**: Posto ad 1 se il risultato ha causato un ==overflow.==
- **C**: Posto ad 1 se il risultato ==ha un riporto.==
- **A**: Posto ad 1 se si è verificato un ==riporto oltre il terzo bit.==
- **P**: Posto ad 1 se il ==risultato è pari.==

**Questi codici sono utili per le istruzioni di confronto e salto condizionale.**
Il PSW è diverso per ogni macchina e il contenuto può variare anche se è modificabile solo in modalità Kernel.

**5.1.4) Istruzioni:**

**L'insieme di istruzioni ISA definiscono ciò che la macchina può fare**, comprende sempre le istruzioni ==STORE e LOAD per il trasferimento dati== e ==l'istruzione MOVE per la copia di dati ==tra i registri, inoltre ci sono le ==classiche istruzioni aritmetiche, booleane e di salto condizionale.==

**5.1.5) Panoramica del livello ISA del Core i7:**

E' frutto dell'evoluzione di molte generazioni di ISA, ha il completo supporto per programmi scritti per l'8086  e 8088 più qualcosina per l'8080.

==Tutte queste macchine più quelle più recenti hanno tutte la stessa architettura: **IA-32**.==

Ha essenzialmente tre modalità operative:

- **Modalità Reale**: Disattiva tutte le modalità introdotte dopo l'8088, e quindi si comporta come tale.

- **Modalità virtuale 8086**: Consente di eseguire i programmi del 8088 in modo protetto.

- **Modalità protetta**: Si comporta come un Pentium 4 e sono previsti 4 livello di privilegi controllati dai bit di PSW, il livello 0 per il sistema operativo, il livello 3 per l'utente e i restanti sono raramente utilizzati.

==Ha uno spazio di indirizzi enorme, la memoria è divisa in 16384 segmenti, ciascuno va dall'indirizzo 0 a $2^{32}$ - 1,== ma i sistemi operativi supportano solo un segmento alla volta.

I registri sono:

- **EAX, EBX, ECX, EDX**: ==32 bit e per uso generale==, anche se hanno le loro particolarità, **EAX** è il registro aritmetico principale, **EBX** è usato per contenere i puntatori, **ECX** è usato nei cicli, **EDX** necessario per le moltiplicazioni e divisioni (Contiene prodotti e dividendi di 64 bit).

- **ESI e EDI**: ==Contengono puntatori alla memoria soprattutto per la manipolazione di stringhe==, **ESI** punta alla stringa sorgente, **EDI** punta alla stringa destinazione.

- **EBP**: Puntatore analogo al LV nella IJVM, ==punta all'indirizzo base del record d'attivazione.==(Frame Pointer).

- **ESP**: ==Il puntatore allo stack.==

- **CS, SS, DS, ES, FS, GS**:  ==Sono registri di segmento.==

- **EIP**: ==Extended Instruction Pointer, cioè il Program Counter.==

- **EFLAGS**: ==Analogo al PSW.==

## Tipi di Dati

A livello ==ISA ci sono diversi tipi di dati, ogni dato richiede un formato specifico== altrimenti non potrebbe essere supportato dall'hardware.

**5.2.1) Tipi di dati numerici:

==Costituito dai numeri interi ne esistono diverse lunghezze: 8 bit, 16 bit, 32 bit, 64 bit.==
La maggior parte dei computer moderni ==memorizza gli interi tramite il **complemento a due**== e alcuni supportano anche la presenza di numeri interi negativi.
Invece per ==i numeri decimali si usano i numeri in **virgola mobile**== di lunghezza 64 bit o 128 bit.

**5.2.2) Tipi di dati non numerici:**

I computer moderni sono ==impiegati per svolgere applicazioni non numeriche== che richiedono altri tipi di dato supportati dall'ISA, ==i caratteri (Più importante ASCII e UNICODE) sono un tipo di dato importante, ma non tutte le macchine le supportano.==

==Altri dati importanti sono i **booleani**: vengono usati interi byte per rappresentarli e 0 significa falso, mentre tutto il resto è vero, l'unica situazione in cui si usano i bit per la rappresentazione e nelle **bit map**.==

L'ultimo tipo di dato importante sono i ==**Puntatori** che sono estremamente utili, ma sono anche la  causa di un grande numero di errori di programmazione.==

## Formati d'istruzione

==Un'istruzione consiste in un opcode (codice operativo), corredato da altre informazioni l'argomento generale che tratta la provenienza degli operandi è detto **Indirizzamento**.==

**5.3.1) Criteri progettuali per i formati d'istruzione:**

==La lunghezza dell'istruzione varia da macchina in macchina, ma avere istruzioni tutte della stessa lunghezza semplifica la loro decodifica anche se comporta uno spreco di spazio==, poiché ogni istruzione deve essere lunga almeno quanto la più lunga.

Istruzioni corte possono tornare utili perché occupano meno spazio, ma potrebbero diventare più difficili da decodificare o da sovrapporre, quindi ==il criterio di ottenere istruzioni di dimensione minima deve essere valutato in base al tempo richiesto per la decodifica e l'esecuzione delle istruzioni.==

==Altro motivo per fare istruzioni corte riguarda l'ampiezza della banda di memoria, poiché quest'ultimo rappresenta un collo di bottiglia==, perché i processori riescono ad eseguire sempre più velocemente le istruzioni, ma ciò non ha portato ad un aumento dell'ampiezza di banda.

**5.3.2) Codice operativo espandibile:**

==Una lunghezza degli indirizzi contenuta e una buona risoluzione di memoria si ottengono una a discapito dell'altra==, per questo motivo nasce un compromesso: il codice operativo espandibile.

Quindi ==l'opcode diventa di lunghezza variabile==, ciò è sicuramente ==utile per minimizzare la lunghezza dell'istruzioni però se usato in eccesso renderebbe tutte le istruzioni di lunghezza diversa complicando così la codifica.==
## Indirizzamento


==Molte istruzioni contengono operandi e abbiamo il problema di come specificarne la posizione,     l'**indirizzamento** è l'argomento che tratta queste problematiche.==

Ci sono diversi tipi di indirizzamento:

- **Immediato**: ==L'operando viene caricato direttamente dalla memoria nello stesso momento in cui viene effettutato il fetch dell'istruzione== e immediatamente disponibile all'uso.

- **Diretto**: Andiamo a ==specificare l'indirizzo completo dell'operando== che dobbiamo prendere dalla memoria.

- **A registro**: Simile all'indirizzamento diretto, ma ==andiamo a specificare il registro in cui dobbiamo prendere l'operando== invece dell'indirizzo.

- **A registro indiretto**: L'indirizzo è ==contenuto in un registro che punta all' operando che ci interessa==, infatti un indirizzo usato in questa maniera prende il nome di **puntatore**.

- **Indicizzato**: Utile quando vogliamo usare strutture come gli array, in questo tipo di indirizzamento, ==si utilizza un registro (o un valore costante) come un indice per calcolare l'indirizzo effettivo di memoria==, usiamo un registro per calcolare l'indirizzo di memoria dell'operando.

- **Indicizzato esteso**: Calcolato ==sommando tra di loro il contenuto di due registri più un offset,== un registro funge da base e l'altro da indice.

- **Indirizzamento a stack**: Alcune operazioni sono possibili solo se in associazione ad uno stack.

**Notazione polacca inversa**:

La notazione polacca inversa ha dei vantaggi rispetto alla notazione infissa, cioè quella classica:

==Per prima cosa possiamo rappresentare tutte le formule senza l'utilizzo di parentesi, che complicano la vita alla macchina, inoltre la valutazione delle formule si addice ai compilatori con stack, infine elimina le ambiguità della formula classica.==

==In questo tipo di formula le variabili mantegono lo stesso ordine, mentre invece gli operandi cambiano in base all'ordine in cui sono eseguiti.==

esempi:

*A + B x C* = **ABC x +**;
*A x B + C* = **AB x C +**;
*(A + B)/ (C - D)* = **AB + CD -/**;
*((A + B) x C + D) / (E + F + G)* = **AB + C x D + EF + G +/**;

L'algoritmo usa lo stack per valutare la formula e funziona così:

==Scorre la stringa da sinistra a destra, quando incontra un operando lo impila sullo stack. Invece quando incontra un operatore ne esegue l'istruzione corrispondente.==

**Modalità di indirizzamento per istruzioni di salto**:

Si utilizzano gli stessi tipi di indicizzazioni detti prima:

- Diretto
- A registro indiretto 
- Indicizzata
- **Indirizzamento relativo al pc**: ==Un offset (Con segno) contenuto nell'istruzione stessa viene sommato al program counter per ottenere l'indirizzo di destinazione,== sarebbe una modalità indicizzata che usa il program counter come registro base.

**Ortogonalità dei codici operativi e delle modalità d'indirizzamento**:

==Le istruzioni e l'indirizzamento dovrebbero manifestare una struttra regolare con un numero finito di formati d'istruzione, ciò permette al compilatore di produrre codice di qualità. Gli opcode dovrebbero consentire tutte le modalità di indirizzamento e ogni registro deve essere utilizzabile da tutte le modalità a registro.==

## Tipi di istruzioni

==Anche se differiscono nei dettagli, le istruzioni del livello ISA possono essere raggruppate in dei gruppi facilmente riconoscibili==, inoltre ogni macchina potrebbe disporre di alcune istruzioni insolite.

**5.5.1 Istruzioni di trasferimento dati**:

Poter copiare dati da una locazione all'altra è fondamentale per tutte le operazioni, acnhe se per essere precisi dovremmo riferirci a queste come ==istruzioni di duplicazione dei dati== invece che di trasferimento.

Ci sono due motivi per copiare dati da una parte a un'altra:'

- **Assegnamento di valori a variabili **
- **Prepararli ad un accesso ed uso efficiente**

==I dati possono provenire da due sorgenti (memoria o registri) ed andare in due destinazioni(memoria o registri).==

Ci sono quattro diversi tipi di trasferimento, alcuni ==computer dispongono di quattor istruzioni diverse, altri una sola istruzione per tutte le situazioni, altri ancora usano LOAD per traferire dati ai regitri e STORE dai registri alla memoria e MOVE per i trasferimenti tra i registri.==

==Sono istruzioni delicate perchè potrebbero metterci tempo ad essere eseguite, inoltre l'unità di trasferimento consueta standard è la parola.==

**5.5.2) Operazioni binarie**:

==Tutte quelle operazioni che producono un risultato dalla combinazioni di due operandi.==

Quindi sono operazioni:

- **Aritmetiche**: Somma, moltiplicazione, sottrazione, divisione
- **Booleane**: AND, OR, NOT, XOR, NOR, NAND

Un uso importante del ==AND è per l'estrazione di bit di una parola==, infatti si ==svolge l'AND della parola con una costante detta **maschera**, il risultato è che tutti i bit indesiderati sono posti a zero, viene poi **giustificato a destra**.==

==Un uso importante del OR è quello di impacchettare bit in una parola, che è l'operazione inversa dell'estrazione==, mascheriamo gli 8 bit indesiderati, dopo di che il nuovo carattere è inserito facendo l'OR.

**5.5.3) Operazioni unarie**:

==Operazioni che prendono in input un operando e restitutiscono un risultato==, sono in genere piu corte delle binarie.

Sono generalmente le ==istruzioni di **shift**==.

**5.5.4) Confronti e salti condizionali**:

==Servono per saltare ad un certo indirizzo di memoria se la condizione è soddisfatta==, la condizione può essere maggiore/minore o può riferirsi ad un certo bit di un registro.

**5.5.5) Invocazione di una procedura**: 

==Una procedura è un insieme d'istruzioni che può essere chiamata in qualsiasi punto del programma, spesso si chiamano **Subroutine**.==
Quando la procedura termina il programma riprende dall'istruzione successiva della chiamata, a tal fine abbiamo bisogno di un indirizzo per il ritorno che può essere salvato in tre posti diversi:

- Memoria 
- Registro
- Stack

Una procedura puo anche invocare un'altra procedura o a se stessa (Ricorsione), ma va implementata bene altrimenti si rischia di creare danni.

==La cosa migliore che può fare l'istruzione di chiamata di procedura è porre l'indirizzo di ritorno in cima ad un stack, quando la procedura termina fa un pop sull'indirizzo di ritorno e lo scrive nel program counter. Con questa forma di chiamata la ricorsionenon causa alcun problema, l'indirizzo di ritorno viene salvato senza sovrascrivere indirizzi di ritorno precedenti.==

**5.5.6) Istruzioni di ciclo**:

Capita di dover eseguire gruppi d'istruzioni più volte, molte macchine dispongono di istruzioni che semplificano il tutto.
Tutti gli schemi prevedono un ==contatore che viene incrementato o decrementato== di un certo valore ad ogni iterazione del ciclo, ==il contatore viene poi esaminato, il ciclo termina una volta che si incontra una determinata condizione.==

**5.5.7) Input/Output**:

Attualmente i computer usano tre schemi diversi di I/O:

- **I/O programmato con attesa attiva**
- **I/O *Interrupt driven* **
- **I/O con DMA**

Il piu semplice è il primo impiegato di solito nei microprocessori di fascia bassa, queste CPU di solito hanno una sola istruzione di input e output e trasferiscono un bit alla volta da un prefissato registro al dispositivo I/O scelto.
==Lo svantaggio è che la CPU deve attendere in un ciclo serrato finché tutti i dispositivi sono pronti(**Attesa Attiva**).==

_==Interrupt driven_ si riferisce a una modalità di gestione delle operazioni di input/output in cui il processore viene interrotto quando si verifica una richiesta di input o output== da parte di un dispositivo periferico, quando un ==dispositivo periferico deve comunicare con il processore, invia un segnale di interrupt che interrompe il normale flusso di esecuzione del processore. ==Il ==processore quindi interrompe quello che sta facendo, salva lo stato corrente e gestisce l'input o l'output richiesto dal dispositivo. Una volta completata l'operazione, il processore può riprendere ciò che stava facendo prima dell'interruzione, questo approccio è utile perché consente al processore di continuare a eseguire altre istruzioni mentre attende l'I/O.== Tuttavia, può comportare un'==inefficienza== se ci sono ==molte interruzioni che si verificano frequentemente==, poiché il processore deve interrompere e riprendere continuamente il suo lavoro principale. 

Con la ==DMA==, un controller DMA è in grado di ==trasferire dati direttamente tra dispositivi periferici e la memoria principale del computer senza coinvolgere direttamente il processore.==
Invece di richiedere costantemente l'attenzione del processore tramite interrupt per ogni singola operazione di I/O, la ==DMA può gestire grandi blocchi di dati in modo più efficiente==. Quando un ==dispositivo periferico deve trasferire dati in memoria, il controller DMA viene configurato per eseguire il trasferimento. Una volta avviato, il controller DMA controlla direttamente il bus di sistema per accedere alla memoria e trasferire i dati==, lasciando **il processore libero di eseguire altre attività**, questa modalità di gestione delle operazioni di I/O può essere ==significativamente più efficiente in termini di utilizzo delle risorse del processore, poiché riduce il carico di lavoro del processore stesso.== Tuttavia, **richiede una buona gestione della memoria e della sincronizzazione per evitare conflitti di accesso alla memoria tra il controller DMA e il processore o altri dispositivi.**
## Controllo del flusso

==Il controllo del flusso permette modificare l'ordine delle istruzioni da seguire in modo dinamico (Quando il programma è in esecuzione).==
Se non ci sono istruzioni di salto o di chiamate a procedure allora le istruzioni verranno eseguite in memoria nell'ordine in cui sono state memorizzate.

Cosa altera il flusso del programma:

- **Chiamate di procedura**
- **Coroutine**
- **Trap**
- **Interrupt**

**5.6.1) Flusso sequenziale e diramazioni**:

Molte istruzioni non alterano il controllo del flusso, di conseguenza, in ==un programma senza salti condizionali, il program counter incrementa in maniera lineare== alla fine di ogni istruzione.
In un ==programma con salti condizionali non ci sta una relazione tra l'esecuzione eseguita e la posizione in memoria dell'istruzione.==

**5.6.2) Procedure**:

In qualche modo si possono definire le procedure come ==istruzioni di salto== con l'unica differenza che una volta completate il programma ==riprende dall'ultima istruzione eseguite prima della procedura==, una tipologia di procedure interessanti sono quelle ricorsive.

Per poter ==gestire le procedure ricorsive abbiamo bisogno di uno stack per memorizzare parametri e variabili locali a ogni invocazione==, in ==cima== allo stack troviamo la ==chiamata più recente puntatata dal puntatore dello stack,== **inoltre è conveniente avere un puntatore al record d'attivazione, FP (Frame Pointer) che punta ad una locazione nota del record d'attivazione.**

Le prime cose che una procedura invocata deve fare sono:

- **Salvataggio del vecchio FP**
- **Copia di SP in FP**
- **Eventuale incremento di FP**

Tutto ciò con ==l'intento di poter effettuare un ritorno alla procedura chiamante== e il codice che fa tutto questo si chiama **Prologo della procedura**.
==All'uscita della procedura dobbiamo ripulire lo stack, **Epilogo della procedura**.==

==Prologhi e epiloghi devono essere il più veloci possibili, se non lo fossero i programmatori non li sfrutterebbero per chiamate a procedure brevi.==

**5.6.3) Coroutine**:

Sono simili alle procedure, ma permettono di ==sospendere l'esecuzione di un programma per poi riprenderlo dall'ultima istruzione eseguita nel momento in cui si richiama.==

Sono usate per simulare l'elaborazione parallela su singola CPU.

**5.6.4) Trap**:

Una specie di chiamata ad una ==procedura, ma avviene solo in condizioni di rara occorrenza in eventi non troppo importanti,== per esempio nel caso di **overflow**, infatti il controllo del flusso è interrotto e si ==riprende da una locazione di memoria prefissata, in tale locazione di memoria si trova l'indirizzo per un salto a una procedura detta **gestore di trap**.==

Le trap possono essere ==implementate tramite un test esplicito del microprogramma==, se viene rilevato un overflow, l'indirizzo di trap è caricato nel Program Counter, ciò fa scattare un trap che può essere tenuto sotto controllo da un programma a livello più basso.

**5.6.5) Interrupt:** 

==Cambiamenti del flusso d'esecuzione non dovuti da errori, solitamente dall'I/O==, interrompono il programma in esecuzione e trasferiscono il controllo ad un gestore che esegue delle operazione, una volta eseguite il controllo ritorna al programma interrotto.

==Le trap sono sincrone al programma, gli interrupt sono asincroni al programma.==
Di seguito ci sono i passi che si succedono durente una chiamata ad un interrupt:

- **AZIONI HARDWARE:**

1) ==Controllore del dispositivo attiva una linea di interrupt sul bus di sistema== per dare via alla sequenza di interrupt.

2) La ==CPU attiva un segnale di conferma dell'interrupt.==

3) Quando il ==controllore vede la conferma== di ricezione ==invia sulla linea dati un piccolo intero== che lo identifica (**Vettore di Interrupt**).

4) ==CPU preleva e salva il vettore di interrupt.==

5) ==CPU impila il program counter e il registro PSW sullo stack.==

6) ==CPU usa il vettore== di interrupt come indice ==per individuare il nuovo program counter== all'interno di una tabella posta all'inizio della memoria.

- **AZIONI SOFTWARE:**

1) ==Si salvano tutti i registri sullo stack per ripristinarli alla fine.==

2) Il vettore di interrupt è condiviso da tutti i dispositivi dello stesso tipo.

3) A questo punto si può leggere qualsiasi informazione sull'interrupt.

4) Le variabili globali ==ptr e count vengono aggiornate==; **La prima è incrementata perché punti al byte successivo**, **la seconda è decrementata a indicare che manca un byte in meno da visualizzare**, se ==count è maggiore di zero, allora ci sono altri caratteri da visualizzare==, e quello ora puntato da ptr viene copiato dal bufferdi output.

5) ==Se richiesto viene inviato un codice speciale per far capire al dispositivo che l'interrupt è stato trattato.==

6) ==Ripristino di tutti i registri salvati.==

7) ==Esecuzione dell'istruzioni di **Ritorno da Interrupt**== che ripristina la modalità e lo stato della CPU prima che venisse chiamato l'interrupt

==Al verificarsi di un interrupt il programma viene fermato e si intraprendono alcune azioni, quando tutto è finito bisogna ritornare allo stato precedente all'interrupt,== questa proprietà si chiama **Trasparenza**.

Se ci sono ==numerosi I/O da gestire== su una singola macchina, la gestione dell'interrupt può risultare diversa, ==esistono due strategie da eseguire in queste situazioni:==

- **Ogni routine di interrupt disabilita interrupt successivi**, ciò mantiene uno schema semplice, ma potrebbe generare problemi in dispositivi che non tollerano attese lunghe.

- **Assegnare una priorità ad ogni dispositivo di I/O**, durante l'esecuzione di un interrupt di priorità n, ogni tentativo di interruzione da parte di dipositivi di priorità inferiore sarà interrotta.

Esistono due tipi di interrupt:

- **Mascherabili**: Usati da tutti i dispositivi di I/O.

- **Non mascherabili**: Utilizzati solo per segnalare eventi catastrofici, come errori di parità nella memoria.

## Architettura IA-32 e IA-64

All'inizio degli anni 2000, ==gli ingegneri pensavano di aver spremuto al massimo le caparictà dell'architettura IA-32 e che quindi era arrivato il momento di abbandonarla== per creare un ISA più evoluto.
Viene quindi sviluppata la IA-64 funzionante appunto su 64 bit, il cui target erano server di fascia alta, ma intel sperava anche di raggiungere il mondo dei desktop, cosa che non riuscì.

**5.8.1) Il problema dell'ISA IA-32:**

E' un ==ISA CISC con una miriade di istruzioni di lunghezza diversa difficili== da decodificare, infatti la tecnologia moderna funziona meglio su ISA RISC, in fase d'esecuzione queste istruzioni possono essere suddivise in istruzioni RISC, ma ciò richiede grandi quantità di hardware.

Inoltre è un ==ISA orientato alla memoria== e la maggior parte dei programmatori e compilatori cerca di non fare riferimento continuo alla memoria, le CPU moderne accedono alla memoria solo per le operazioni di LOAD/STORE.
Per di più ==questa deficienza peggiorerà con il tempo perché la frequenza di clock delle CPU cresce molto più velocemente rispetto alla velocità delle memorie.==

Dispone di ==numero limitato di registri piccoli e irregolari== che mandano in confusione il compilatore.

Il ==numero limitato di registri causa molte dipendenze,== per evitare ciò bisogna implementare un qualcosa che gestisca la rinomina internamente, inoltre per evitare blocchi frequenti a causa di miss da parte della cache le istruzioni devono essere eseguite fuori sequenza.

Per ==svolgere queste operazioni serve una pipeline con molti stadi, l'uso di ciò comporta l'utilizzo di molti cicli per completare delle istruzioni==, un introduzione sbagliata delle istruzioni prevede lo svuotamento della pipeline.

Per ==alleviare il problema delle predizioni sbagliate il processore deve effettuare l'esecuzione speculativa== con tutti i problemi che ne derivano.

**5.8.2) Modelli IA-64 e calcolo che utilizza il parallelismo esplicito:**

==Idea base è quella di spostare il carico di lavoro dalla fase d'esecuzione a quella di compilazione.==
Nel Core I7, durante l'esecuzione la CPU riordina istruzioni, rinomina registri , fa lo scheduling ecc.
Nel modelli IA-64 il compilatore prevede ciò e crea un programma che può essere eseguito così come è.

==Il modello che rende il parallelismo sottostante nell'hardware visibile al compilatore si chiama **EPIC**== (Explicitly Parallel Instruction Computing) e può essere visto come il successore di RISC.

Il modello IA-64 presenta numerose funzionalità per incrementare le prestazioni:

- **Riduzione agli accessi in memoria**: ==Disporre di una grande cache di primo livello sul chip e di una cache di secondo livello ancora più grande vicino al chip.==

- **Scheduling delle istruzioni:** ==Dividere il programma in una sequenza di gruppi d'istruzioni==, in modo che entro un certo limite le istruzioni di un gruppo tra di loro.

- **Riduzione dei salti incondizionati**: Utilizzo della tecnica dell'==attribuzione dei predicati==.

- **Caricamenti speculativi:** Permette al ==compilatore di anticipare le istruzioni di LOAD== in posizioni antecedenti al loro effettivo bisogno, se prima iniziano è probabile che il loro risultato sia disponibile al momento opportuno.





