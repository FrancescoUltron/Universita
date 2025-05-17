# Capitolo 3 - Schema EntitàRelazione e Progettazione Logica

L'insieme dei costrutti usati per organizzare i dati di interesse e descriverne la dinamica è detto **modello di dati**. La quale componente fondamentale sono i **meccanismi di strutturazione**.

> Permettono di definire tipi di dati per ogni modello.

Nel modello relazionale abbiamo il **costruttore relazione** che permette di definire insiemi di record omogenei.

Possiamo dividere i modelli di dati per i database in:

- **Modelli logici:** Usati nei DBMS per l'organizzazione dei dati - questi sono indipendenti dalle strutture fisiche.

- **Modelli concettuali:** Rappresentano i dati in modo indipendente da ogni sistema e modello logico. Descrivono il mondo reale e sono usati nelle fasi preliminari della progettazione.

> L'esempio più noto è il modello **ER**. Ma ne esistono altri come il modello **EER** oppure **UML**..

Nella progettazione delle basi di dati - in base al grado di *astrazione* su cui lavoriamo troviamo:

- **Modello concettuale**.
- **Modello logico.**
- **Modello fisico:** Descrive il modo in cui i dati sono registrati nelle memorie di massa.

---
### Modello concettuale

Rappresentiamo in maniera astratta la realtà di riferimento - quindi dobbiamo definite l'insieme di dati presenti in natura e che rappresentano la natura stessa delle informazioni che si vogliono arhiviare.

> *Non esistono delle regole* fisse per l'individuazione dei dati.

Ma essenzialemente dobbiamo ripsondere a cinque quesiti:

1) *Cosa c'è* (oggetti).
2) *Come si collegano*
3) *Quanti tra loro*
4) *Cosa identifica gli oggetti*
5) *Quali informazioni utili non principali*

Il modello **Entità-Relazione** ci permette di costruire il modello concettuale rispondendo a queste domande.

---
### Modello ER

I costrutti principali del modello sono:

- **Entità** - Insieme di oggetti simili - dati dello stesso tipo raccolti insieme.

- **Relazioni** - Collegamento logico tra due o più entità.

- **Cardinalità** - Assegnazione di dimensionamento tra entità.

- **Chiave** - Campo identificativo di un'entità o relazione.

- **Attributi** - Campi informativi di un'entità o relazione.

- **Generalizzazione** - Legame logico tra un'entità più *generale* - detta padre e le entità *figlie*.

---
#### Entità

Oggetto - concreto o astratto - che ha un significato anche quando viene considerato in modo isoltato ed è di interesse per la realtà che si vuole modellare.

Entità che non hanno chiavi primarie e devono essere associata da altre entità per essere significative prendono il nome di **entità deboli**.

---
#### Relazione

La relazione è un legame che stabilisce un'interazione tra entità. Possono esistere delle relazioni tra un'entità e se stessa - si dicono **ricorsive** o **isA**. Relazioni tra tre entità si dicono **ternarie.**

---
#### Attributi e chiavi

Sono le proprietà delle entità e delle relazioni. Ogni attributo può essere soggetto a specifici **vincoli di dominio**, che ne definiscono il comportamento e i valori ammessi:

- *Formato* - Il tipo di valore che può assumere.
- *Dimensioni* - Quantità massima di caratteri inseribili.
- *Opzionalità* - Possibilità di non essere valorizzato.

La **chiave primaria** è un attributo o insieme di attributi che identifica in modo univoco un'istanza dell'entità. Anche le relazioni possono avere attributi.

> In assenza di chiave primarie palesi - si usa una **chiave artificiale** che solitamente consiste in un contatore che si autoincrementa ad ogni istanza aggiunta.

Ogni entità deve avere una **chiave primaria** - questa **non può** essere opzionale e **non** può avere valori ripetuti.

I vincoli di dominio per gli attibuti sono i seguenti:

- **Tipo di dato:** Intero - decimale - carattere - data - ...
- **Lunghezza:** Numero di cifre per rappresentare il valore dell'attributo.
- **Intervallo:** Limiti superiori o inferiori.
- **Vincoli:** Restrizioni sui valori ammessi.
- **Supporto del NULL:** quando non è assegnato alcun valore.
- **Valore di default**

Per le chiavi primarie il valore deve essere unico è non può essere NULL. 

Per le chiavi esterne - i vindoli di dominio - devono essere **uguali** a quelli della corrispondente chiave primaria.

---
#### Molteplicità

La molteplicità di una relazione è il numero di possibili istanza di un'entità che sono messe in corrispondenza con un'altra entità.

Il valori di *min* e *max* sono rappresentati con: $(1,1),(1,N),(0,1),(0,N)$.

> Al valore minimo è associato il concetto di partecipazione facoltativa $0$ o obbligatoria $1$.

Il valore massimo definisce la cardinalità della partecipazione è assume due valori: $1,N$.

Esistono tre principali tipi di **associazioni:**

- **Uno a uno - 1:1** - A ogni istanza della prima entità corrisponde una sola istanza della seconda entità e viceversa.

- **Uno a molti - 1:N** - A ogni istanza della prima entità corrispondono una o più istanza della senconda entità e a ogni istanza della seconda entità corrisponde una sola istanza della prima.

- **Molti a molti - N:N** - A ogni istanza della prima entità corrispondono una o più istanza della senconda entità e viceversa.

Esiste anche **l'associazione IsA** tra due entità - ossia ogni istanza di un'entità è anche istanza dell'altra. Viene definita tra due entità dette *padre e figlia*.

> Un'entità può avere al massimo un'entità padre - quindi niente ereditarietà multipla.

**Principio di ereditarietà:** Ogni proprietà del padre è anche una proprietà della sottoentità - questo non si riporta esplicitamente nel diagramma. Inoltre l'associazione IsA è **transitiva**.

> L'entità figlia può avere ulteriori proprietà.

---
#### Generalizzazione

Si parla di **generalizzazione** quando - un'entità padre - generalizza diverse sottoentità rispetto ad un unico criterio. **Il principio di ereditarietà e tutto quello detto per le associazioni IsA vale anche per le generelizzazioni.**

---
### UML - Diagramma delle Classi

Il *diagramma delle classi* è un grafo che descrive tutti gli oggetti in un sistema - le relazioni statiche fra essi - gli attributi e le operazioni di una classe - nonchè i vincoli sulle relazioni.

Una classe è rappresentata da un rettangolo diviso in tre parti contenenti<:

- Nome della classe
- Attributi della classe 
- Opereazioni della classe

Un attributo è una **proprietà locale** della classe ed è caratterizzato da nome e tipo di valori associati. Questa proprietà locale è valida per tutte le istanze della classe.

> Un attributo $A$ della classe $C$ si può considerare una funzione che associa un valore $T$ ad ogni oggetto che è istanza di $C$. Ossia $A: C\to T$

Tra oggetto è istanza si traccia un arco **Instance-of**.

> Oggetti formano il *livello estensionale* - Classi formano il *livello intensionale*.
 
Gli attributi delle classi determinano gli attributi delle istanze. Due oggetti diversi devono avere identificatori diversi - anche se possono avere gli stessi valori per gli attributi.

I *Diagrammi a struttura statica* vengono usati per:

- Documentare classi che compongono un sistema.
- Descrivere *associazioni, generalizzazioni e aggregazioni* fra le classi.
- Evidenziare le caratteristiche di una classe.

Possono esere usati per docimentare come interagiscono le classi di un particolare sistema con librerie già esistenti oppure per rappresentare istanze di oggetti di una classe.

---
### UML - Attributi e operazioni

La sintassi è la seguente:
```
<nomeCaratteristica>:<tipo>
```

- La prima parte identifica un attriubo o operazione.
- Il secondo il tipo di dato dell'attributo.

La visibilità degli attributi può essere:

- **- private:** disponibile sono nella classe che la definisce.
- **+ public:** disponibile solo per le classi associata alla classe che la definisce.
- **# protected:** disponibile solo all'interno della classe che la possiede e di ogni sottoclasse.

> Un attributo può avere un minimo e un massimo.

Un'associazione tra una classe $C_1$ ed una classe $C_2$ modella una relazione matematica tra l'insieme delle istanza di $C_1$ e l'insieme delle istanza di $C_2$.

> Le associazioni modellano proprietà che coinvolgono altre classi. In alvuni casi può tornare utile specificare un verso per la relazione.

Si può anche aggiugere all'associazione una informazione che specifica il ruolo che una classe gioca nell'associazione. L'unico caso in cui il ruolo è obbligatorio è quello in cui l'associazione insiste più volte sulla stessa classe.

Si possono definire dei **vinvoli di molteplicità** delle associazioni. Inolte un'associazione può essere definita su tre o più classi in quel caso si chiama **associazione n-aria**.

Un'**aggregazione** è usata per indicare che - oltre ad avere attributi propri - l'istanza di una classe può consistere di - o comprendere - istanza di altre classi. Si usa il termine **tutto-parte**.

In una **composizione** le parti non possono esistere senza la parte di **tutto**.

---
### UML - generalizzazioni

La relazione IsA in UML è definita mediante la nozione di **generalizzazione** - in questa abbiamo una *superclasse* e una o più *classi derivate*. Nella generalizzazione ogni istanza di una superclasse è anche istanza della superclasse. Per le generalizzazioni valgono i seguenti principi:

- **Ereditarietà:** Ogni proprietà della superclasse è proprietà della sottoclasse - ma non si riporta esplicitamente nel diagramma.

- Vengono usate anche per rappresentare la relazione gerarchica fra più classi rispetto ad un criterio.

La stessa superclasse può partecipare a generalizzazioni diverse. Inoltre può essere **disgiunta** o anche **completa** - altrimenti sono **incomplete**.

> **Completa:** l'unione delle istanze delle sottoclassi è uguale all'insieme delle istanza della superclasse.

Un'istanza può essere un'**overlapping** di più superclassi.

Le sottoclassi non possono avere proprietà aggiuntive rispetto alla superclasse - ma possono **specializzarsi** sulle proprietà ereditate. Le **classi di associazioni** permettono di esplicitare gli attributi e le associazioni di una associazione.

---
# Progettazione logica

Una volta definito lo schema ER possiamo passare al modello relazionale - nel quale:

- Ogni entità diventa una relazione.

- Ogni attributo di entità diventa un attributo di relazione - ossia una **colonna della tabella.**

- L'identificatore univoco diventa la **chiave primaria** della relazione.

Possiamo rappresentare una tabella nel seguente modo: $$nomerelazione(nome\;attributo\;1,nome\;attributo\;2,...)$$
In base al tipo di relazione tra entità - nello schema - otteniamo delle tabelle diverse:

- **Relazione 1:1** diventa un'unica relazione che contiene gli attributi delle entità che mette in relazione.

> Se abbiamo una relazione 1:1 con partecipazione opzionale viene trattata come associazione uno a molti. L'entità con partecipazione opzionale viene trattata come se fosse a molti.

> Se entrame sono opzionale allora la trattiamo come molti a molti.

- **Relazione 1:N** viene rappresentata aggiungendo - agli attributi dell'entità che svolge il ruolo di *molti* - l'identificatore univoco dell'entità col ruolo a uno.

> Questo identificatore è detto **chiave esterna.**

- **Relazione ricorsiva 1:N** è traducibile con una sola relazione che contiene due volte l'attributo identificatore - una volta come chiave primaria e una volta come chiave esterna.

- **Relazione N:N** si crea una nuova tabella composta dagli identificatori univoci delle entità coinvolte nell'associazione. La chiave della nuova tabella è l'insieme delle chiavi primarie di tutte le altre entità.

- **Relazione ricorsiva 1:N** viene tradotta con due relazioni - una per l'entità e una per la relazione. La chiave della relazione è composta da due attributi che riflettono il diverso ruolo dell'entità sono anche posti come chiave esterna.

---
### Da ER a modello relazionale - Generalizzazioni

Il modello relazionale non può rappresentare direttamente le genralizzazioni - le dobbiamo eliminare con delle entità e relazioni/associazioni. Esistono tre modi diversi per farlo:

- **Accorpamento delle entità figlie nell'entità padre**.

> Aggiungiamo un attributo *tipo* nell'entità padre.

In questo modo le operazioni di accesso sono **più efficienti** perchè non devono distinguere tra le occorrenze del padre e delle figlie. Non perdiamo molta conoscenza perché le entità figlie **introducono differenziazioni non sostanziali.**

- **Accorpamento dell'entità padre nelle figlie.**

In questo caso le operazioni d'accesso distinguono tra le occorrenze delle diverse entità figlie - senza passare per l'entità padre. (accesso più **efficiente**).

- **Sostituzione delle generalizzazioni con le associazioni.

---
# Progettazione di una base di dati

Per progettare una base di dati dobbiamo definirne struttura - caratteristiche e contenuto.

La progettazione prevede:

- **Modello concettuale:** Rappresenta la relatà dei dati e le relazioni tra essi attraverso uno schema.

- **Modello logico:** Descrive il modo il quale i dati sono organizzati negli arrchivi del computer.

- **Modello fisico:** Descrive come i dati sono registrati nelle memorie di massa.

---
### Modello logico

Obiettivo di creare un *schema logico* che rappresenti lo schema concettuale in modo fedele in modo **indipendente** dal DBMS adottato. questa fase è divisa in:

1) **Ristrutturazizone dello schema Entità-Relazione:** Questa fase si basa su criteri di ottimizzazione dello schema.

2) **Traduzione verso il modello logico:** Riferimento ad uno specifico modello logico (**modello relazionale**).

---
### Ciclo di vita di un sistema informativo

- **Studio di fattibilità.**

- **Raccolta analisi e requisiti.**

- **Progettazione** divisa in *progettazione dei dati* e *progettazioni delle applicazioni*.

- **Implementazione.**

- **Validazione e collaudo.**

- **Funzionamento**

---
### Fasi della progettazione

- **Progettazione concettuale:** Rappresentiamo le specifiche non formali in modo formale e completo - ma indipendente dalla rappresentazione usata dal DBMS - ossia creiamo lo **schema concettuale.**

> Rappresenta il contenuto informativo e non la codifica.

- **Progettazione logica:** Traduciamo lo schema concettuale in quello logico - questo è ancora indipendente dalla realizzazione fisica della base di dati.

- **Progettazione fisica:** Usiamo lo schema logico e le **specifiche sulle operazioni** per implementare il sistema.

---
### Progettazione concettuale

Il risultato di questa parte è uno schema ER che descrivere le specifiche sui dati relativa ad una applicazione. Questa parte è divisa in:

- **Raccolta e analisi dei requisiti.** Ossia l'individuazione dei problemi da risolvere e chiarimento e organizzazione delle specidiche.

- **Definizione dello schema ER.**

I *requisiti* permettono di definire le caratteristiche dell'applicazione da realizzare. Quindi i dati e le operazioni da implementare. Questi sono solitamente espressi in maniera ambigua, per questo motivo, ci sta anche un *analisi*.

> Il reperimento dei requisiti e la loro analisi è un'**attività non standardizzata.**

Quindi la prima parte consiste nel realizzare la descrizione del problema in un linguaggio naturale che rispetti **criteri di completezza e non ambiguità.**

Per i dati è utile specificare il numero delle occorrenze previste, mentre per le operazioni il numero di volte che si prevede debbano essere usate in un arco di tempo.

Le fonti per i requisiti si ottengono da:

- **Utenti**
- **Documentazioni esistenti**
- **Realizzazioni preesistenti**

Bisogna tenere di conto anche delle informazioni che i vari utenti possono vedere, infatti non tutti gli utenti possono accedere alla visualizzazione di tutti i dati.

Tramite i costrutti cisti precedentemente possiamo dare vita ad uno schema concettuale, la realizzazione di questo può avvenire attraverso diverse strategie:

- **Strategia top-down**
- **Strategia bottom-up**
- **Strategia inside-out**
- **Approcci misti**

Le proprietà che bisogna mantenere in uno schema concettuale sono le seguenti:

- **Correttezza.**

- **Completezza.**

- **Leggibilità.**

- **Minimalità.**

Per garantire tutte queste proprietà, l'approccio alla creazione dello schema può essere suddiviso in una parte iniziale di *analisi dei requisiti* seguiti dalla definizione di uno schema scheletro con i concetti più rilevanti. Effettuare poi una decomposizione sui requisiti con riferimento ai concetti presenti nello schema scheletro.
Fatto ciò si ripetono questi passaggi per ogni specifica rappresentata, per poi integrare i vari sottoschemi allo scheletro dello schema che verrà analizzato seguendo le proprietà descritte sopra.

