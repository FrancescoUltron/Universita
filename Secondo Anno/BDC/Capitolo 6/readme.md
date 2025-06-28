# Capitolo 6 - Indici e progettazione fisica

Progettare una base di dati significa definirne struttura, caratteristiche e contenuto usando apposite metodologie. In base al grado di astrazione, la progettazione prevede:

- Modello concettuale.
- Modello logico.
- **Modello fisico**: Descrive il modo in cui i dati sono registrati nelle memorie di massa.

La necessità di avere un modello per descrivere il modo in cui i dati sono mantenuti in memoria ansce dal fatto che l'accesso alle strutture logiche nella memoria secondaria non è efficiente.

> Memoria secondaria molto lenta rispeto alla principale.

Serve un'interazione fra memoria principale e secondaria che limiti il più possibile gli accessi alla memoria secondaria.

Nei DMBS relazionali, i dati sono rappresentati come **collezioni di record** memorizzati in diversi file. L'organizzazione dei dati nel file influenza il tempo di accesso alle informazioni.

> Ogni organizzazione rende alcune operazioni efficienti altre onerose. Di conseguenza non esiste una organizzazione efficiente per qualsiasi tipo di lettura o scrittura.

---
### Indici

Facciamo degli esempi, immaginiamo di avere la seguente tabella:

| id  | Nome   | Cognome | Residenza |
| --- | ------ | ------- | --------- |
| 1   | Elena  | Rossi   | Roma      |
| 2   | Andrea | Verdi   | Como      |
| 3   | Giulia | Neri    | Torino    |
| 4   | Paolo  | Bianchi | Como      |

E eseguiamo la seguente query: 
``` SQL
select * from Dipendente where Residenza = "Como";
```

Le operazioni eseguite per restituire la tabella risultante sono:

- Lettura sequenziale dell'intero file.
- Durante la lettura la selezione dei record interessati.
- Visualizzazione dei record.

L'efficienza dell'operazione può essere nettamente migliorata se mantenessimo i dati della tabella ordinati per residenza, infatti le operazioni da eseguire sarebbero:

- Lettura sequenziale del file fino al *primo* record interessato.
- Lettura sequenziale fino al orimo record non interessato.
- Visualizzazione dei record.

I vantaggi di questo tipo di organizzazione è che possiamo evitare la lettura sequenziale di tutto il file, ma l'ordinamento ha un costo, inotlre l'**organizzazione potrebbe non essere efficiente per altre interrogazioni**.

Un **indice**  è una struttura dati realizzata per migliorare i tempi di ricerca dei dati. Se una tabella non ha indici, ogni ricerca obbliga il sistema a leggere tutti i dati presenti in essa. L'indice consente invece di ridurre l'insieme dei dati da leggere per completare la ricerca.

| Concetto             | Descrizione                                                                                           | Scopo                                       |
| -------------------- | ----------------------------------------------------------------------------------------------------- | ------------------------------------------- |
| **Struttura fisica** | Struttura dati ausiliaria che associa valori di un attributo (es. "residenza") alle locazioni fisiche | Velocizzare l'accesso ai dati               |
| **Locazione fisica** | Posizione fisica di un record all’interno del file                                                    | Accedere direttamente a un record specifico |

Adesso se provassimo ad eseguire la query di prima le operazioni da svolgere saranno:

- Lettura dells struttura fisica per recuperare le locazioni fisiche.
- Acesso ai soli record del file interessato.
- Visualizzazione dei record.

In questo modo evitiamo la lettura sequenziale, accediamo ai soli record interessati e il costo di mantenimento della struttura fisica è meno oneroso rispetto a una struttura ordinata. Ma comunque è necessario mantenere **spazio supplementare** per memorizzare la struttura fisica e potrebbe essere necessario averne diverse per **combinazioni di attributi.**

Possiamo realizzare gli indici mediante:

- Alberi.
- Hash Table.

> **Definizione:** struttura ausiliaria per l'accesso (efficiente) ai record di un file sulla base dei valori di un campo (o di una "concatenazione di campi") detto chiave (o, meglio, pseudochiave, perché non è necessariamente identificante).

Un indice $I$ di un file $f$ è un altro file, con record a due campi: chiave e indirizzo ordinato secondo i valori della chiave.

Ci sono delle istruzioni anche in SQL:
```
create [unique] index <IndexName> on <TableName(AttributeList)>;
drop index <IndexName>;
```

---
### Query processor

Il Query processo è un modulo fondamentale nell'architettura di un DBMS in quanto responsabile dell'esecuzione efficiente di operazioni di livello molto alto.

Questo riceve in ingresso un'interrogazione scritta in SQL e ci lavora nel seguente modo:

- Analizza la query e rileva **Eventuali errori sintattici o semantici**.

- L'interrogazione è tradotta in forma interna di tipo algebrico.

- Ottimizzazione basata sui costi dell'esecuzione.

Quando un **DBMS** (Database Management System) deve eseguire una **query**, ha bisogno di sapere **com’è fatta la base di dati** per decidere il modo migliore per farlo.

Per questo, il sistema tiene traccia di alcune **informazioni quantitative** per ogni **relazione (tabella)**, chiamate anche **statistiche**. Alcuni esempi di queste informazioni sono:

- **Cardinalità**: quante righe (tuple) ha una tabella.

- **Dimensione delle tuple**: quanto spazio occupa in media ogni riga.

- **Dimensioni dei valori**: quanto spazio occupano i valori degli attributi.

- **Numero di valori distinti**: ad esempio, in una colonna "residenza", quanti luoghi diversi sono presenti.

- **Valore minimo e massimo**: utile per capire l'intervallo dei valori (es. età da 18 a 90).

Queste **statistiche sono salvate nel catalogo del database** e aggiornate con comandi tipo `UPDATE STATISTICS`.

Servono per:

- **Stimare** quanti dati saranno prodotti da una query.
- **Scegliere il piano di esecuzione più efficiente** (quello che costa meno in tempo o risorse).

Quando scrivi una query SQL, il database **la trasforma in operazioni algebriche** (algebra relazionale).   L’**ottimizzatore** cerca un **modo alternativo ma più efficiente** per ottenere lo stesso risultato.

Il DBMS **non trova sempre la soluzione migliore assoluta**, ma usa **euristiche** (regole pratiche) per **avvicinarsi a una soluzione buona**.

Due modi diversi di eseguire una query sono **equivalenti** se *Restituiscono lo stesso risultato**, qualunque siano i dati presenti nel momento della query.

> Il DBMS cerca tra queste espressioni equivalenti **quella che costa meno** da eseguire (meno tempo, meno memoria).

---
### Euristiche principali

#### 1. **Push selections down** (spingere le selezioni in basso):

- Applica i **filtri (WHERE)** il prima possibile.
- Così si lavora con **meno dati** nei passaggi successivi.

### 2. **Push projections down** (spingere le proiezioni in basso):

- Mantieni **solo le colonne necessarie** il prima possibile.
- Così si risparmia spazio e tempo.

L'operazione più costosa con il quale possiamo operare è il **join.** Quindi il processo di ottimizzazione di una query è il seguente:

- Costruzione di un albero di decisione con le varie alternative.
- Valutazione del costo di ciascun piano.
- Scelta del piano di costo minore.

> L'ottimizzatore trova una soluzione buona, non l'ottimo.

Quindi la fase di progettazione fisica vede come input lo schema logico e le informazioni sul carico di lavoro e come output lo schema fisico, costituito dalle definizioni delle relazioni con le relative strutture fisiche.

Vantaggio dei DBMS relazionali è il possibile uso degli indici e definirli sulel chiavi primarie delle relazioni. Altri indici sono scelti in base a selezioni e join 'importanti'.

> Se le prestazioni non sono sufficienti possiamo eliminare o aggiungere altri indici.

Possiamo verificare se e come gli indici sono utilizzati con il comando SQL: 
``` SQL
show plan;
```

---
# Progettazione fisica - Parte 2

Abbiamo la necessità di gestire la memoria secondaria per due motivi fondamentali:

- Le memorie principali **non sono abbastanza grandi** da mantenere un intera base di dati.

- Le memorie principali sono volatili, questa caratteristica va contro la caratteristica della **persistenza dei dati.**

Ricordiamo che le applicazioni non possono accedere direttamente alla memoria secondaria, quindi i dati prima di essere utilizzati devono essere trasferiti sulla memoria principale.

I dispositivi di memoria secondaria sono organizzati in **blocchi** di lunghezza **fissa**

> Le uniche operazioni su un disco sono quella di scritttura o lettura di un blocco.

L'accesso alla memoria secondaria dipende:

- tempo di **posizionamento della testina**
- tempo di **latenza**
- tempo di **trasferimento**

Nelle applicazioni I/O bound il costo dipende esclusivamente dal numero di accessi a memoria secondaria.

> L'accesso a blocchi vicini costa di meno (**contiguità**).

L'interazione tra memoria principale e secondaria è realizzata nei DBMS tramite una grande zona di memoria centrale detta *buffer* che permette di evitare inutili accessi alla memoria secondaria quando un dato è usato molte volte.

> Viene gestita dal DBMS.

E' organizzato in **pagine** che hanno dimensione pari a un numero intero di blocchi.
Il gestore del buffer carica e scarica delle pagine dalla memoria principale alla memoria di massa e riceve dai programma delle richiste di lettura o scrittura dei blocchi, queste vengono poi eseguite sulla base.

Lo scopo del buffer è quello di ridurre il numero di accessi alla memoria secondaria:

- In caso di lettura, se la pagina è presente nel buffer, possiamo anche non accedere alla memoria secondaria. 

- In caso di scrittura, il gestore del buffer può decidere di differire la scrittura fisica.

Oltre alle pagine, il buffer deve anche mantenere delle informazioni relative alle pagine, in particolare:

- Un **direttorio** descrrive il contenuto del buffer, indicando per ogni pagine il file fisico e il numero di blocco corrispondente.

- Per ogni pagine vengono mantenute delle **variabili di stato** tra cui un contatore che indica quanti accessi sono stati fatti e un bit di stato che indica se la pagina è stata modificata.

Le politiche della gestione del buffer sono simili a quelle della gestione della memoria da parte del sistema operativo:

- *Localita di dati*
- *Legge 80-20*

Quindi un buffer:

- Riceve richieste di lettura e scrittura.
- Le esegue accedendo alla memoria secondaria quando è indispensabile.
- Esegue delle operazioni primitive.

Alcune primitive sono:

- **fix** - Richiesta di una pagina. Richieda la lettura se la pagina non è nel buffer.
- **setDirty** - Comunica al buffer manager che la pagina è stata modificata.
- **unfix** - Indica che la transazione ha concluso l'utilizzo della pagina.
- **force** - Trasferisce in modo sincrono una pagina in memoria secondaria.

Il buffer manager può richiedere delle scritture in due modi diversi:

- **Sincrono** quando è richiesto l'uso esplicito della force.
- **Asincrono**, ossia in modo indipendente dall'applicazione se lo reputa necessario.

---
**Esecuzione della fix (gestione del buffer):**

1. **Ricerca nel buffer:**  Se la pagina è già presente, viene restituito il suo indirizzo.

2. **Se non è presente:**

    - Si cerca una pagina libera (con contatore a zero).

    - Se trovata, si usa quella e si restituisce l'indirizzo.

3. **Se non ci sono pagine libere:**

    - **Steal:** si sceglie una pagina occupata ("vittima"), si scrive il suo contenuto in memoria secondaria, si carica la nuova pagina e si restituisce l'indirizzo.

    - **No-steal:** l’operazione viene messa in attesa finché non si libera una pagina.


