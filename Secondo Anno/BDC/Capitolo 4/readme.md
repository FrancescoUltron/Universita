# Capitolo 4 - Algebra Relazionale

### Linguaggi di interrogazione

Esistono diversi linguaggi per le basi di dati divisi in:

- **DDL** - Data Definition Language. Usato per le operazioni sullo schema.

- **DML** - Data Manipulation Language. Usato per le operazione sui dati.

> Operazioni sui dati sono *interrogazioni e aggiornamenti.*

Si dividono anche in:

- **Dichiarativi** - Specificano le proprietà del risultato.
- **Procedurale** - Specificano le modalità di generazione del risultato.

I linguaggi relazionali sono stati progettati per **interrogare, manipolare e gestire** basi di dati che seguono il modello relazionale e sono:

- **Algebra relazionale** (Procedurale) - composto da un insieme di operatori che su delle relazioni producono nuove relazioni.

- **Calcolo relazionale** (dichiarativo) - basato sul calcolo dei predicati del primo ordine.

- **SQL (Structured Query Language)** - Linguaggio dichiarativo con estensioni procedurali ed è il linguaggio standard per le basi di dati.

- **QBE (Query By Example)** - Dichiarativo, è solitamente usato su interfacce grafiche per chi non conosce la sintassi del SQL.

---
### Algebra relazionale

Linguaggio procedurale - in cui le operazioni vengono descritte descrivendo la procedura per ottenere la soluzione. Gli operatori di questo linguaggio sono:

- **Unione, differenza, intersezione** (Teoria degli insiemi).
- **Ridenominazione, selezione, proiezione** (Specifici dell'algebra relazionale).
- **Join** (Operatore speciale che può assumere diverse forme).

---
### Operatori insiemistici

Le relazioni sono **insiemi** possiamo quindi estendere le relative operazioni ad esse ma, ha senso definirle su tuple definite sugli stessi attributi.

>Le relazioni sono insiemi di tuple **omogenee**.

- **Unione:** L'unione fra due relazioni $r_1$ e $r_2$ definite sullo stesso insieme di attributi $X$ è indicata con $r_1\cup r_2$ ed è una relazione su $X$ contenente le tuple che appartengono a $r_1$ o $r_2$ oppure entrambe.

- **Intersezione:** L'intersezione fra due relazioni $r_1$ e $r_2$ definite sullo stesso insieme di attributi $X$ è indicata con $r_1 \cap r_2$ ed è una relazione su $X$ contenente le tuple che appartengono sia a $r_1$ che a $r_2$.

- **Differenza:** La differenza fra due relazioni $r_1$ e $r_2$ definite sullo stesso insieme di attributi $X$ è indicata con $r_1 - r_2$ ed è una relazione su $X$ contenente le tuple che appartengono a $r_1$ e non a $r_2$.

---
### Ridenominazione

Serve a cambiare il nome degli attributi per facilitare le operazioni insiemistiche. E' un operatore che consente di **modificare il nome di un attributo** per poterlo associare ad un altro in una operazione algebrica.

> E' un operatore **monadico** e modifica lo schema lasciando inalterata l'istanza dell'operando.

$$\rho\;nuovo\;nome \leftarrow vecchio\; nome(Relazione)$$
In questo modo cambia il nome dell'attributo.
$$\rho_{B_1,B_2,...B_k \leftarrow A_1,A_2,...,A_k}  (R)$$
Restituisce una nuova relazione che ha gli attributi $B_1,B_2,...B_k$ al posto di $A_1,A_2,...,A_k$ e in cui ogni tupla $t'$ è ottenuta da una tupla $t\in\mathbb{R}:t'[B_i]=t[A_i],\forall i=1,..,k$.
Quindi:

- I **nomi degli attributi vengono cambiati**.
- I **valori rimangono inalterati**.

> **Schema** è la struttura della relazione, ossia l'elenco degli attributi con i loro nomi.

---
### Introduzione a Selezione e Proiezione

Si applicano ad una relazione e ne restitutiscono una porzione. 

> Sono **complementari**, una opera sulle **righe**, l'altra sulle **colonne**.

- **Selezione** --> Insieme di tuple, su **tutti** gli attributi.

- **Proiezione** --> Risultato su un insieme di attributi cui contibuiscono tutte le tuple

---
### Selezione

E' un operatore monadico che produce come risultato lo **stesso schema** dell'operando contenente un sottoinsieme delle ennuple dell'operando che soddisfano una **condizione di selezione.**
$$\sigma_F(r)\;\;oppure\;\;SEL_F(r)$$
dove:

- $F$ è una condizione da verificare.
- $r$ è la relazoine a cui la selezione èapplicata definita su insieme di attributi $X$.

La selezione produce ua relazione sugli stessi attributi di $r$ contenente le ennuple su cui $F$ è vera.
$$\sigma_F(r(X))$$
$F$ è una **formula proposizionale** su $X$ - ossia una formula con i simboli di AND, OR, NOT ed espressioni del tipo $A \theta B$ oppure $A\theta c$ dove:

- $\theta$ è un operatore di confronto.
- $A$ e $B$ sono attributi di $X$ su cui il confronto ha senso.
- $c$ è una costante.

Viene definito un **valore di verità** di $F$ su una ennupla $t\in r$:

- $A \theta B$ è vera se e solo se $t[A]\theta t[B]$ è vero.
- $A \theta c$ è vera se e solo se $t[A]\theta c$ è vero.
- Le operazioni di AND,OR,NOT sono le stesse.

Per riferirsi a valori nulli esistono delle forme apposite di condizioni: **IS NULL; IS NOT NULL**.

---
### Proiezione

Dati una relazione $r(X)$ e un sottoinsieme $Y$ di $X$ - la proiezione si $r$ su $Y$: $$\Pi_Y(r)\;oppure\;PROJ_Y(r)$$
è l'insieme delle tuple su un sottoinsieme $Y$ di attributi $X$ di $r$ ottenura dalle tuple di $r$ considerando solo i valori su $Y$, ossia: $$\Pi_Y(r)=\{t[Y] : t\in\mathbb{R}\}$$

---
### Join

Il **join** dell'aglebra relazionare ci permette di correlare dati in relazioni diverse.

> Proiezione e Selezione possono lavorare solo su una relazione.

Esistono due tipi di join:

- **Naturale**
- **Theta join**

Il join naturale $r_1\bowtie r_2$ correla dati in relazioni divese sulla base di valori uguali in attributi con lo stesso nome. E quindi una relazione definita su $X_1 \cup X_2$: $$r_1\bowtie r_2 = \{t\;su\;X_1X_2:t[X_1]\in r_1\;\;e\;\;t[X_2]\in r_2\}$$
Il grado della relazione ottenura è minore o uguale al grado della somma dei gradi delle due relazioni in quanto gli attributi omonimi compaiono una sola volta.

- Se $X_1\cap X_2$ è vuoto allora il join naturale equivale al **prodotto cartesiano** fra relazioni.

- Se $X_1 = X_2$ il join naturale equivale all'intersezioni fra le relazioni.

> Se ciascuna ennupla contribusice al risultato del join allora si dice **join completo.** Altrimenti le ennuple si dicono **dangling**.

Alcune proprietà:

- Il join di due relazioni contiene un numero di ennuple compreso fra 0 e il prodotto delle relazioni.

- Se un join è completo allora contiene un numero di tuple pari al massimo fra le tuple delle due relazioni.

- Se $X_1\cap X_2$ coinvolge una chiave di $r_2$ allora il join contiene almento $|r_2|$ tuple.

- Se il join conivolge una chiave di $r_2$ è un vincolo di integrità referenziale allora il numero di tuple è pari a $|r_1|$.

- Il join è **commutativo.**

- Il join è **associativo.**

Il prodotto cartesiano è essenzialmente un join su **relazioni che non hanno attributi in comune.** Quindi conterrà un numero di ennuple pari al prodotto delle cardinalità degli operandi.

Il **prodotto cartesiano** $r_1 \times r_2$ di $r_1(X)$  e $r_2(X)$ è una relazione definita su $X_1\cup X_2$: $$r_1\times r_2 = \{t\;su\;X_1X_2:t[X_1]\in r_1\;e\;t[X_2]\in r_2\}$$
Con il joon naturale tralasciamo tutte le ennuple in cui non ci sta un corrispondenza tra gli attributi legati dal join. con il **join esterno** possiamo fare in modo che tutte le ennuple diano sempre un contributo al risultato. Esistono tre tipi di join esterno:

- **left join:** Contribuiscono tutte le ennuple del primo operando estese con valori nulli.

- **right join:** Contribuiscono tutte le ennuple del secondo operando estese con valori nulli.

- **full join:** Contribusicono tutte le ennuple di entrambi gli operandi estese a valori nulli.

> il left join ritorna tutte le tuple della relazione a sinistra a prescindere che siano combinabili. Stessa cosa con il right join.

Il full join tiene conto di tutte le tuple della relazione di sinistra e della relazione di destra, estendendo il risultato anche a valori NULL.

---
### Theta-join

Per correlare attributi con nome diversi e possibile fare il *theta-join* definito come prodotto cartesiano seguito da una selezione: $$r_1\bowtie r_2 = \sigma_F(r_1 \times r_2)$$
Dove:

- $F$ è una formula e $r_1,r_2$ non hanno attributi in comune.

Se $F$ è una relazione di uguaglianza, con una attributo della prima relazione e uno della seconda, allora si chiama **equi-join.**

> Join naturale basato sui **nomi** degli attributi.

> Theta-join e equi-join basati sui **valori**. 

---
### Query

Un'**interrogazione** è una funzione $E(R)$ che applicata ad istanze di una base di dati $R$ produce una relazione su un dato insieme di attributi $X$.

Le interrogazione su uno schema di base di dati $R$ in algebra relazionale sono espressioni i cui atomi sono relazioni in $R$ oppure costanti.

Due espressioni sono **equivalenti** se:

- $E_1(r) = E_2(r)$ per ogni istanza $r$ di $R$. - equivalenza dipendente dallo schema.

- $E_1 =_r E_2$ per ogni schema $R$ - equivalenza assoluta.

Alcune equivalenze imporatnti sono:

- **Atomizzazione delle selezioni.**
- **Idempotenza delle proiezioni.**
- **Anticipazione della selezione rispetto al join.**
- **Inglobamento dii una selezione in un prodotto cartesiano a formare un join.**
- **Distributività della selezione rispetto all'unione.**
- **Distributività della selezione rispetto alla differenza.**
- **Distributività della proiezioni rispetto all'unione.**

---
### Algebra con valori nulli

A IS NULL --> Vero su una ennupla $t$ se il valore di $A$ è nullo.
A IS NOT NULL --> Vero su una ennupla $t$ se il valore di $A$ è specificato.

---
### Viste

Le viste sono delle rappresentazioni diverse per gli stessi dati - sono quindi lo **schema esterno.**

Nel contesto dei database relazionali, le **viste**, o **relazioni derivate**, rappresentano una modalità per ottenere rappresentazioni diverse degli stessi dati, adattandole alle esigenze specifiche degli utenti o delle applicazioni. Si distinguono dalle **relazioni di base**, che contengono direttamente i dati memorizzati nel sistema, in quanto il loro contenuto è calcolato a partire da altre relazioni tramite interrogazioni.

Le **relazioni virtuali**, comunemente chiamate **viste**, sono definite mediante espressioni o funzioni del linguaggio di interrogazione, come SQL. Queste viste **non sono memorizzate fisicamente** nel database, ma vengono **ricalcolate ogni volta** che vengono utilizzate. Ciò consente una maggiore flessibilità e una rappresentazione personalizzata dei dati, senza duplicare le informazioni.

Un caso particolare è rappresentato dalle **viste materializzate**, che invece **vengono memorizzate fisicamente** nel database. Questo le rende immediatamente disponibili e più efficienti da consultare, ma introduce la complessità della **sincronizzazione**: i dati devono essere mantenuti coerenti con le relazioni di base da cui derivano. Per questo motivo, le viste materializzate sono raramente supportate in modo completo dai sistemi di gestione di basi di dati (DBMS).

Le viste svolgono un ruolo fondamentale nella progettazione dello **schema esterno** del database, cioè la parte visibile agli utenti. Ogni utente può vedere solo ciò che gli interessa o ciò che è autorizzato a vedere, secondo una logica di sicurezza e semplicità. In questo modo, è possibile **nascondere informazioni sensibili o non rilevanti**, semplificare le interrogazioni e migliorare l’usabilità del sistema.

Dal punto di vista della **programmazione**, le viste permettono di definire **espressioni complesse** una sola volta e riutilizzarle più volte, rendendo più semplice e modulare lo sviluppo delle applicazioni. Inoltre, in caso di **ristrutturazione dello schema del database**, le vecchie strutture possono essere simulate tramite viste, permettendo alle applicazioni esistenti di continuare a funzionare correttamente.

Tuttavia, **l’aggiornamento di una vista** rappresenta una sfida. In teoria, aggiornare una vista dovrebbe significare modificare le relazioni di base in modo tale che, una volta ricalcolata, la vista rifletta il cambiamento. In pratica però, questo **aggiornamento non è sempre univoco**, e nella maggior parte dei casi **non è nemmeno ammissibile**. Di conseguenza, le viste sono utilizzate principalmente per **lettura e consultazione**, più che per operazioni di modifica.

Infine, dal punto di vista della **sicurezza**, le viste offrono uno strumento efficace per **definire diritti di accesso** a specifiche porzioni della base di dati. Questo permette di limitare l’accesso ai dati sensibili, proteggendo la privacy e garantendo che ogni utente possa vedere solo ciò che è autorizzato a consultare.


