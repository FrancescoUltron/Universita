E' un livello presente su tutte le macchine odierne, la ==differenza rispetto ai livello visti fino ad adesso è che questo livello è implementato mediante **traduzione**.==
I programmi che permettono la traduzione si chiamano **==traduttori==**, il linguaggio nel quale è scritto il programma originale si chiama ==**linguaggio sorgente**==, mentre invece si chiama ==**linguaggio destinazione**== quello nel quale viene convertito.

==Si usa la traduzione quando si ha a disposizione un processore per il linguaggio destinazione==, ma non per quello sorgente.

**Traduzione:** ==Programma originale convertito in un programma equivalente==, chiamato **oggetto** o **eseguibile binario** la cui ==esecuzione è portata avanti solo dopo che la traduzione== è stata completata, inoltre avviene in due passaggi:

1) Generazione di un programma equivalente nel linguaggio destinazione
2) Esecuzione del programma generato

**Interpretazione**: ==Si esegue direttamente il programma originale e non occorre generare nessun programma equivalente.==

Mentre si esegue il programma oggetto sono ==coinvolti tre diversi livelli==: **Microarchitettura, ISA, Sistema Operativo.**

## Introduzione al linguaggio assemblativo

Esistono essenzialmente due tipi di traduttori:

- **Assemblatore**: ==Il linguaggio sorgente è una rappresentazione simbolica del linguaggio macchina numerico==, in questo caso il linguaggio sorgente è chiamato **linguaggio assemblativo**

- **Compilatore**: I==l linguaggio sorgente è ad alto livello e il linguaggio destinazione è un linguaggio macchina numerico== o una sua rappresentazione.

**7.1.1) Che cos'è un linguaggio assemblativo:**

E' un ==linguaggio nel quale ciascuna istruzione produce esattamente un'istruzione macchina.==
Si usano perché è molto ==più semplice ricordarselo== rispetto al linguaggio macchina, poiché vengono utilizzati dei simboli che sono più semplici da usare rispetto a valori numerici, la stessa osservazione vale per gli indirizzi.

Inoltre il **linguaggio assemblativo ha accesso a tutte le istruzioni disponibili nella macchina** di destinazione, cosa che non succede con i linguaggi ad alto livello .

Quindi tutto quello che si può fare in linguaggio macchina si può fare anche a livello assemblativo, ma un programmatore ad alto livello non ha tutte le stesse funzioni di uno a livello assemblativo.

==Inoltre i linguaggi a livello assemblativo vengono scritti per un tipo specifico di macchine.==

**7.1.2) Perchè usare il linguaggio assemblativo:**

Per due motivi:

- **Prestazioni**: Per ==molte applicazioni la velocità e la dimensione presentano fattori critici.==

- **Possibilità di accesso alla macchina**: Alcune ==procedure devono avere accesso a tutta la macchina.==

Un compilatore deve produrre un risultato utilizzabile da un assemblatore oppure eseguire esso stesso il processo di assemblaggio.

**7.1.3) Formato delle istruzioni del linguaggio assemblativo:**

==Rispecchia la struttura del linguaggio macchina==, ma con dei simboli al posto dei numeri, esistono diversi assemblatori con caratteristiche proprie, ma generalmente le istruzioni del linguaggio assemblativo sono così composte:

- **Etichetta**: Servono per ==fornire nomi simbolici agli indirizzi==, necessarie per **effettuare salti e definire le destinazioni**, molti linguaggi limitano il numero di caratteri utilizzabili per l'etichette.

- **Opcode**: ==Abbreviazione simbolica del codice operativo== oppure un comando per l'asseblatore stesso.

- **Operandi**: Usato per ==specificare indirizzi e i registri utilizzati come operandi dall'istruzione macchina==, nel caso di una somma indica quali elementi sommare, nel caso di un salto invece a quale indirizzo deve portare l'istruzione.

- **Commenti**: Serve per andare a ==spiegare cosa fanno le varie istruzioni==, un codice assemblativo senza commenti e quasi sempre incomprensibile.

**7.1.4) Pseudoistruzioni:**

==I comandi diretti all'assemblatore sono chiamati **pseudoistruzioni** o **direttive dell'assemblatore**.==

![[Pseudoistruzioni.png]]

## Macroistruzioni

Le macroistruzioni o macro ==risolvono il problema del dover ripetere idefinitivamente una sequenza di istruzioni identiche== o quasi, fornendo una soluzione semplice ed efficiente, infatti solitamente si dovrebbe chiamare una procedure il che è un processo tedioso.

**7.2.1 Definizione, chiamata ed espansione:**

==E' un modo di assegnare un nome ad una parte di testo==, dopo che la macro è stata definita, il programmatore può scriverne il nome al posto della parte di programma corrispondente.

Anche se assemblatori diversi utilizzano istruzioni diverse, in tutti i casi la definizione di una macro è composta da:

- **Intestazione che indica il nome della macro**
- **Il testo che costituisce il corpo della macro**
- **Pseudoistruzione che ne segna la fine**

Quando ==l'assemblatore trova una macro la inserisce nella tabella delle definizioni macro==, quando poi la ==trova come codice operativo== (**Chiamata di macro**) ==sostituisce l'istruzioni== (**Espansione di macro**).

Le ==chiamate di macro sono effettuate durante l'assemblaggio== ed **è impossibile capire se vengono utilizzate macro durante il l'esecuzione del programma, perché l'assemblatore elimina le definizioni dopo averle usate.**

Non sono da confondere con le chiamate di procedure:

- Le ==macro sono istruzioni dirette all'assemblatore==
- Una ==procedura è un'istruzione macchina== inserita nel programma oggetto è viene eseguita in un secondo momento.

Possiamo dividere il processo assemblativo in due passaggi:

1) Vengono ==salvate le definizioni delle macro== e le loro ==chiamate vengono espanse==.
2) Il testo ==risultante viene elaborato come un programma ==normale.

Il ==programma sorgente è letto e trasformato in un programma in cui sono state eliminate le definizioni della macro.==

**7.2.2 Macro con parametri:**

Le istruzioni presenti in una macro non danno sempre lo stesso risultato, infatti quando ==specifichiamo una macro possiamo anche specificare dei parametri formali==, durante le chiamate invece dovremmo fornire dei parametri attuali.

Ovviamente avremo risultati diversi in base ai parametri che usiamo.

**7.2.3 Caratteristiche avanzate:**

Ci sono alcune caratteristiche che semplificano la vita dei programmatori.
Un problema è la duplicazione delle etichette, immaginiamo un'istruzione di salto ad una macro, se la macro viene chiamata più volte, l'etichetta verrà duplicata causando un errore nel linguaggio assemblativo.

Una soluzione consiste nel lasciare che ==l'assemblatore produca un etichetta diversa ad ogni chiamata di macro.==

Inoltre MASM ed altri assemblatori permettono di ==definire macro all'interno di macro==, infinne ==le macro possono chiamare se stesse in maniera ricorsiva.==

**7.2.4) Implementazioni delle funzionalità macro negli assemblatori:**

Per implementare una funzione macro un assmeblatore deve essere in grado di:

- ==Salvare le definizioni delle macro==
- ==Espandere le loro chiamate==

==L'assemblatore deve mantenere una tabella con tutti i nomi delle macro con dei puntatori alla definzione di quest'ultime.==

Alcuni assemblatori sono dotati di tabelle che contengono sia le macro che tutte le istruzioni macchina e e le pseudoistruzioni

==Se si incontra la definizione di una macro si aggiunge nella tabella un elemento che indica il nome della macro, il numero di parametri formali e un puntatore ad una tabella contenente i corpi delle macro.==

Per la definizione del corpo usiamo alcuni caratteri speciali: 

- "**Ritorno a capo**" è rappresentato dal ==punto e virgola==;
- Il **simbolo dei parametri formali** è la "==&==";

**Il corpo di ciascuna macro, nella tabella delle definizione, è una stringa di caratteri.**

Nel primo passo del processo di assemblaggio ==vengono cercati i codici operativi e le macro sono vengono espanse==, quando ==si incontra una definizione di macro si inserisce nella tabella delle macro.==
Quando ==viene chiamata una macro, l'assemblatore ne legge il corpo precedentemente memorizzato.== ==I parametri formali nel corpo vengono sostituiti da quelli attuali della chiamata==, la presenza di & permette all'assemblatore di riconoscerli più facilmente.
## Processo di assemblaggio

Descrizione generale del processo di assemblaggio:

**7.3.1 Assemblatori a due passate:**

Dato che ogni programma in linguaggio assemblativo consiste in una serie di istruzioni che possono avere dei “salti” in avanti, ==l’assemblatore non può conoscere in anticipo la posizione dell’istruzione richiamata== (problema del riferimento in avanti).

È possibile gestire i riferimenti in avanti in due modi:

- l’assemblatore può ==leggere il programma sorgente due volte==. Ciascuna lettura del programma sorgente è chiamata **passata**. Durante la prima lettura questi assemblatori ==raccolgono in una tabella tutte le definizioni dei simboli==, comprese le etichette delle istruzioni. Prima di iniziare la seconda passata si conoscono quindi i valori di tutti i simboli. In tal modo ==non rimane alcun riferimento in avanti ed è possibile leggere ogni istruzione==, assemblarla e generare il codice corrispondente.

- ==leggere il programma sorgente una sola volta, convertirlo in un formato intermedio e memorizzare in una tabella della memoria questa forma intermedia==. In seguito viene effettuata una ==seconda passata sulla tabella.==

In entrambi gli approcci, ==la prima passata ha anche il compito di salvare tutte le definizioni di macro e di espanderle== quando si incontrano le loro chiamate.

**7.3.2) Prima passata:**

La principale funzione della prima passata è quella di ==costruire la tabella dei simboli==, contenente i valori di tutti i simboli. *Un simbolo è un’etichetta, oppure un valore, al quale è stato assegnato un nome simbolico attraverso una pseudoistruzione.*

Per assegnare nel campo etichetta di un’istruzione un valore a un simbolo, ==l’assemblatore deve conoscere quale indirizzo avrà tale istruzione durante l’esecuzione del programma.== Durante il processo di assemblaggio l’assemblatore mantiene una variabile, chiamata **ILC (Instruction Location Counter, “contatore di locazioni delle istruzioni”),** per tener traccia dell’indirizzo che l’istruzione che sta assemblando avrà a tempo di esecuzione.

Nella maggior parte degli assemblatori la ==prima passata utilizza tre tabelle interne== per i **simboli**, le **pseudoistruzioni** e i **codici operativi.**

**7.3.3)Seconda passata:**

La funzione della seconda passata è la ==generazione del programma oggetto==, oltre a ciò la seconda passata ==deve generare delle informazioni richieste dal linker per collegare in un unico file eseguibile le procedure assemblate in momenti distinti.==


Prima passata: Si legge il programma e si salvano le definizione/dati/indirizzi
Seconda passata: Si produce il file oggetto

**7.4.4)Tabella dei simboli:**

Durante la prima passata l’==assemblatore accumula informazioni sui simboli== e i loro valori. Tali informazioni ==devono essere memorizzate== nella tabella dei simboli per essere utilizzate durante la seconda passata. Può essere descritta come una **tabella associativa**, ==costituita da un insieme di coppie **<simbolo, valore>** accessibili tramite il simbolo.==

Esistono varie tecniche per realizzarla:

- ==Implementare la tabella dei simboli come un vettore di coppie==, in cui il primo elemento è il simbolo (oppure un puntatore a esso) e il secondo è il valore (oppure un puntatore a esso). Quando si vuole recuperare un simbolo, la routine della tabella dei simboli effettua semplicemente una ==ricerca lineare all’interno dell’array finché non trova l’elemento desiderato==. Questo metodo è **facile** da programmare, ma allo stesso tempo è **lento**, dato che per ciascuna ricerca occorre esaminare, in media, metà della tabella.

- ==Ordinare la tabella rispetto ai simboli e utilizzare un algoritmo di ricerca **dicotomica (Bynary Search)** ==per cercare il simbolo desiderato. Questa tecnica ==richiede che sia conservato l’ordine degli elementi della tabella==.**(O(log n))**.

- ==Usare una codifica che mappa i simboli nell’intervallo di interi compreso tra 0 e k-1==. Questa tecnica richiede però che **la funzione hash fornisca una distribuzione uniforme dei valori hash e la gestione delle collisioni**. **(O(1)).** 

## Collegamento e caricamento

La ==maggior parte dei programmi è composta da più di una procedura==, compilatori e assemblatori ==traducono una procedura alla volta== e ne salvano il risultato nella memoria, per eseguire il programma dobbiamo ==prendere tutte le procedure e riunirle in modo appropriato==, bisogna anche ==caricare il programma in memoria centrale.==

I programmi che ci servono per fare ciò sono:

- **Linker**
- **Linking Loader**
- **Linkage Editor**

La traduzione di un programma sorgente richiede due passaggi:

1) **Compilazione o assemblaggio del file sorgente** (Compilatore/Assemblatore)
2) **Collegamento dei moduli oggetto** (Linker)

==La traduzione che trasforma una procedura sorgente in un modulo oggetto è costituisce un cambio di livello==, poiché i due hanno un diverso set di istruzioni a cui fanno riferimento.
La funzione di ==linkaggio invece ci permette di riunire le procedure tradotte sotto un unico programma eseguibile binario.==
La ==fase di collegamento è più veloce rispetto a quelladi traduzione==, ciò ci fà guadagnare molto tempo quando usaimo tanti moduli.

**7.4.1) Compiti del linker:**

L’idea è di ==creare all’interno del linker un’immagine esatta dello spazio di indirizzamento virtuale del programma eseguibile e di posizionare tutti i moduli oggetto nelle locazioni corrette.==
Il linker unisce gli spazi degli indirizzi separati dei diversi moduli oggetto all’interno di un ==unico spazio lineare degli indirizzi== seguendo questi quattro passi:

1) Costruisce una ==tabella di tutti i moduli oggetto e delle loro lunghezze;==

2) In base a questa tabella ==assegna un indirizzo di partenza per ciascun modulo;==

3) Cerca tutte le ==istruzioni che fanno riferimento alla memoria e aggiunge a ciascuna di loro una costante di rilocazione== uguale all’indirizzo di partenza del suo modulo;

4) ==Cerca tutte le istruzioni che fanno riferimento ad altre procedure e inserisce in quei punti gli indirizzi delle procedure corrispondenti.==


