# Introduzione

Uno dei principali compiti dei sistemi informatici è quello di **raccogliere, mantenere e conservare** dati. I sistemi informatici garantiscono che i dati vengano: 

- Conservati in modo **permanente.**
- **Aggiornati** per riflettere variazioni.
- Resi accessibili ad **interrogazioni** da parte di utenti.

Questo corso serve a descrivere i concetti che servono per rappresentare dati in calcolatori, i linguaggi che consentono il loro aggiornamento e ritrovamento e tipi di architettura usate per fare ciò. In questo primo capitolo introdurremo i concetti di **sistema informativo** e **basi di dati**. Inoltre vedremo le principali caratteristiche dei sistemi informatici per gestire basi di dati.

---
### Sistemi informativi, informazioni e dati

Nello svolgimento di attività individuali o in organizzazioni è essenziale avere informazioni e poterle consultare in modo efficace. Quindi ogni organizzazione è dotata di un *sistema informativo* che ha il compito di gestire le informazioni dell'organizzazione.

>Il sistema informativo è indipendente dall'automatizzazione.

Per indicare la parte automatizzata del sistema informativo usiamo il termine *sistema informatico*.

L'uomo da sempre rappresenta **informazioni** secondo delle tecniche naturali tipiche della attività stesse: la lingua, la scrittura, disegni, numeri e altri. Con il corso del tempo si sono trovate delle opportune forme di organizzazione all'interno di attività.
Nei sistemi informatici - per ragioni tecnologiche e di facilità di rappresentazione - il concetto di rappresentazione e codifica è portato all'estremo. Le informazioni sono rappresentate per mezzo di **dati** che hanno bisogno di essere interpretati.

>**Informazione:** Notizia, dato o elemento che consente di avere conoscenza più o meno esatta di fatti o situazioni.

> **Dato:** Ciò che è immediatamente presente alla conoscenza - quindi prima di ogni elaborazione. Da soli non hanno alcun significato. Possiamo anche dire che - in informatica sono - elementi di informazione costituiti da simboli che devono essere elaborati.

Consideriamo adesso il concetto di **basi di dati** - la definizione più generale è la seguente:

> Collezione di dati, utilizzati per rappresentare le informazioni di interesse per un sistema informativo.

I dati hanno caratteristiche stabili rispetto alle procedure che operano su di essi - cioè le procedure possono essere sostituite. Inoltre durante la sostituzione di una procedura dobbiamo accertarci che la prcedura nuova *eredi* i dati della vecchia.

>La stabilità dei dati porta ad affermare che essi costituiscono delle 'risorse' da sfruttare e proteggere per l'organizzazione che li gestisce.

---
### Basi di dati e sistemi di gestione di basi di dati

Sistemi software dedicati alla gestione dei dati sono nati a partire dagli anni '60. Senza questi programmi la gestione era affidata ai linguaggi di programmazione tradizionali.
L'approccio 'convenzionale' della gestione dei dati fa uso dei *file* o *archivi* salvati su memoria di massa. 

> I file forniscono solo semplici meccanismi di gestione dei file.

Secondo questo approccio, le procedure scritte in linguaggi di programmazione sono autonome - quindi molti dati di interesse per più programmi potrebbero essere **ridondanti** e possibilmente **incoerenti**, inoltre la condivisione di dati attraverso applicazioni diventa difficoltosa.

Le basi di dati sono state ideate per evitare questi problemi - gestendo le informazioni in maniera integrata e flessibile per diversi utenti.

> Una base di dati è una **collezione di dati** di interesse per una o più applicazioni.

Cerchiamo di dare una definizione anche da un punto di vista tecnologico.

Un *sistema di gestione di basi di dati (Data Base Managment System - DBMS)* è un sistema software che gestisce collezioni di dati che siano **grandi, condivise e persistenti** assicurando **affidabilità e privatezza.**

> Una base di dati è collezione di dati gestita da un DBMS.

Di seguito ci sono alcune caratteristiche delle basi di dati e DBMS:

- Le basi di dati possono essere *grandi*, cioè che possono avere dimensioni gigantesche. I DBMS devono quindi **lavorare su memorie secondarie** che sono lente.

- Le basi di dati sono **condivise** tra utenti - quindi gli utenti accedono a dati in comune. Questo permette di ridurre la *ridondanza* dei dati, nonché la possibilità di *inconsistenze*, poiché memorizzate nello stesso sistema univoco. Per permette a diversi utenti di operare in contemporanea sui database - i DBMS usano dei meccanismi detti *controllo di concorrenza*.

- I DBMS garantiscono *affidabilità* - cioè mantengono intatto il contenuto della base di dati o ne permettono il la ricostruzione - anche in casi di malfunzionamento. Per questo motivo hanno anche delle funzioni di *salvataggio e ripristino.*

- I DBMS garantiscono la *provatezza dei dati* - ogni utente riconusciuto può svolgere solo un determinato insieme di azioni sui dati - attraverso meccanismi di *autorizzazione.*

- I DBMS sono *efficienti* - cioè capaci di svolgere le operazioni usando un insieme di risorse (tempo e spazio) accettabile per l'utente.

- I DBMS sono *efficaci* - cioè capaci di rendere produttive le attività dei loro utenti.

Grandi collezioni di dati possono essere gestiti anche da strumenti meno sofisticati dei DBMS - per esempio tramite i file. Questi però vanno bene per una gestione **locale**. Per questo i DBMS sono stati progettati anche per estendere le capacità e le funzioni dei file system.

> Non sempre avere un'unica base di dati è conveniente - ma è sempre importante integrazione, condivisione e coordinamento del flusso dei dati tra utenti.

In generale un'organizzazione complessa deve usare un insieme di basi di dati ciascuna dedicata a un insieme di applicazioni strettamente correlate e che siano coinvolte in operazioni di interscambio di informazioni.

> Ultima nota per il paragrafo - esistono molte architetture per discutere con i database: terminali 'stupidi', architettura client-server o attraverso il World Wide Web.

---
### Modelli dei dati

Un **modello di dati** è un insieme di concetti usati per organizzare i dati di interesse e descriverne la struttura in modo che essa risulti comprensibile a un elaboratore. I modelli forniscono *meccanismi di strutturazione* che permettono la creazione di dati complessi partendo da quelli elementari. 

> **Definizione di Ullman:** Un formalismo matecamtico composto da una notazione per descrivere i dati e un insieme di operazioni per manipolare tali dati.

> I database devono essere in grado di gestire dati eterogenei. 

Per gestire la base di dati dobbiamo fornire uno schema dei dati al DBMS. Lo schema è costruito tramite un modello. Questo modello permette l'indipendenza dei dati dalla rappresentazione fisica.

Un modello di dati deve essere costituito da:

- **Costrutti sintattici** - Definiscono i dati.
- **Regole semantiche** - Servono ad interpretare i dati.
- **Linguaggi** - Per manipolare i dati.

Il modello più usato è quello **relazionale** che organizza i dati in insiemi di record a struttura fissa. Le relazioni sono rappresentate con **tabelle**. Le righe sono specifici record, le colonne sono i campi dei record.

> Una relazione è una tabella che rappresenta dei dati organizzati in modo strutturato.

Possiamo dividere i modelli di dati in:

- **Logici** - Descrivono l'organizzazione dei dati in base alla *visibilità* dell'utente. I principali tipi sono: **Gerarchico e reticolare** basati su delle strutture ad albero e collegamento espliciti a record, **Ad oggetti** dove l'informazione è rappresentata in forma di oggetti.

- **Concettuale**  - Descrivono i concetti del mondo reale e descrivono i dati in maniera indipendente dalla scelta del modello logico. Sono usati agli inizi di un progetto, alcuni modelli famosi sono: **Entita-Relazione** e **Modello Classi Associazioni (UML)**.

- **Fisico** - Per il momento non ci interessa.

>**Concettuale**: Che cosa rappresentano i dati (e come si connettono a livello di concetto). **Logico**: Come strutturiamo questi dati in un sistema per l'elaborazione e la gestione.

> Il modello relazionale è basato sui valori dei record e quindi sulle **chiavi** in modo tale da 'discriminare' elementi con gli stessi valori, mentre nel modello ad oggetti ogni oggetto ha una proprià entità.

---
**Schemi e istanze**

In ogni base di dati abbiamo una parte invariante nel tempo - *schema* - e una parte variabile - *istanza o stato*.

Esempio tabella: Docenza

|    Corso     | NomeDocente |
| :----------: | :---------: |
| Basi di dati |    Rossi    |
|     Reti     |    Neri     |
|  Linguaggi   |    Verdi    |

Le colonne - dette **attributi** - costituiscono lo *schema di relazione* - in questo caso lo schema è:  **Docenza(Corso, NomeDocente)**.

Le righe rappresentano l'istanza e possono *variare nel tempo* - **l'istanza di una relazione** è costituita dall'insieme delle sue righe.

> Schema - Componente *intensionale* - struttura della base di dati.
> Istanza - Componente *estenzionale* - contenuto della base di dati.

---
**Livelli di astrazione nei DBMS**

Possiamo continuare a sviluppare le idee scritte in precedenza, tenendo in mente che esiste un architettura standardizzata per i DBMS. Questa è chiamata architettura **ANSI/SPARC** ed è basata su tre livelli:

- **Schema logico:** Descrizione della base di dati nel modello logico DBMS - quindi il modo in cui le entità nel database si relazionano fra di loro.

- **Schema fisico o interno:** Rappresentazione del database per mezzo di un struttura fisica di memorizzazione.

- **Schema esterno:** Descrive una porzione della base di dati - detta **vista** - che rappresenta un parte di interesse solo ad un certo insieme di utenti. Possiamo usare dei meccanismi di *autorizzazione di accesso* per far si che solo alcuni utenti possano accedere a queste viste. 

---
**Indipendenza dei dati**

Un'architettura definita in questo modo ci garantisce l'*indipendenza dei dati* che permette agli utenti di interagire con un livello di astrazione elevato evitsndo di interagire con i dettagli realizzativi usati per costruire il db. Esistono due tipi di indipendenze:

- **Fisica**: Possiamo interagire con il DBMS in modo indipendente dalla struttura fisica dei dati. Il livello logico e esterno sono indipendenti da quello fisico. Possiamo modificare le strutture fisiche senza modificare i livelli superiori.

- **Logica**: Consente di interagire con il livello esterno della base di dati in modo indipendente dal livello logico. Possiamo modificare il livello logico senza alterare quello esterno e viceversa.

> Gli accessi alla base di dati avvengono solo tramite il livello esterno che può coincidere con quello logico. Il DBMS traduce le operazioni in termini di livelli sottostanti.

---
**Linguaggi e utenti nelle basi di dati**

I database sono caratterizzati dalla presenza di molti linguaggi per la gestione dei dati e di molte tipologie di utenti.

I linguaggi che vengono usati si dividono in:

- **DDL (Data Definition Language):** Definiscono le opreazioni sugli schemi.
- **DML (Data Manipulation Langiage):** Definiscono le operazioni sui dati.

> SQL è un misto tra i due linguaggi.

L'accesso ai dati può avvenire tramite diversi linguaggi e comandi:

- *Linguaggi testuali interattivi* come **SQL**..
- Comandi immersi in **linguaggi ospite**.
- Comandi immersi in un **linguaggio ad hoc**.
- Tramite interfaccie amichevoli non testuali.

---
**Utenti e progettisti**

Diverse categorie di persone possono interagire con una base di dati, in breve sono:

- *Amministratore della base di dati (DBA)*: Responsabile della progettazione, controllo e amministrazione del db. Deve garantire le **prestazioni, l'affidabilità, le autorizzazioni.**  

- *Progettisti e programmatori di applicazioni*: Definiscono i programmi che realizzano l'accesso alla base di dati.

- *Utenti*: Usano la base di dati per le proprie attività. Si dividono in **utenti finali (terminalisti)** che interrogano il database tramite *transazioni*. Oppure **utenti casuali** che eseguono operazioni non previste a priori usando linguaggi interattivi.

> **Transazioni**: Programmi che realizzano attività frequenti e predefinite, con poche eccezioni, previste a priori. Sono realizzate con programmi in linguaggi ospite.

>NB: **Transazione** ha un altro significato: Sequenza indivisibile di operazione.

---
**Vantaggi e svantaggi dei DBMS**

I vantaggi sono:

- Permettono di considerare i **dati come una risorsa comune** - quindi a disposizione di tutte le componenti di una attività.

- Controllo e **gestione centralizzata dei dati** che può essere arricchito da frome di standardizzazione.

- La condivisione **riduce ridondanze e inconsistenze**.

- Indipendenza dei dati.

Gli svantaggi sono:

- Sono costosi e complessi. Il costo deriva da investimenti **diretti** - l'acquisizione del DBMS - e **indiretti** - acqusizione risorse hardware, formazione del personale.

- **Non scorporabilità** delle funzionalità - riduzione di efficienza.

Possiamo dire che esistono delle situazioni in cui non conviene usare DBMS - applicazioni con pochi utenti o dove non è necessario l'accesso concorrente. Tuttavia i DBMS si sono evoluti negli anni diventando sempre più convenienti e meno costosi.
