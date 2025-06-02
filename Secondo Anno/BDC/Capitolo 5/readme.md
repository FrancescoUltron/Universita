# Normalizzazione

### Anomalie di uno schema

Ogni tupla in una relazione rappresenta un'istanza della relazione. E' importante che attributi di diverse entità **non devono essere mescolati** nella stessa relazione.

> Dobbiamo mantenere gli attributi di diverse relazione il più separati.

Mischiando attributi di più entità possono esserci dei problemi nati dalla **ridondanza** delle informazioni.

> Spreco dello spazio di archiviazione.

I problemi principali con le anomalie di aggiornamento sono:

- Anomalie di **inserimento**.
- Anomalie di **eliminazione**.
- Anomalie di **modifica**.

**Anomalie dell'aggiornamento:**
	Rientrano nelle anomalie di aggiornamento tutte quelle situazioni in cui una modifica normalmente lecita della base di dati porta questa in uno stato inconsistente.

**Anomalia d'inserimento:** 
	Rientrano nelle anomalie di inserimento tutte quelle situazioni che impediscono la registrazione di informazioni normalmente accettate dalla base di dati.

**Anomalia di cancellazione:**
	Rientrano nelle anomalie di cancellazione tutte quelle situazioni in cui la cancellazione di alcuni dati implica la perdita di informazioni correlate che invece si desidererebbe mantenere.

> Queste anomalie nascono da una progettazione errata o incompleta dello schema del database.

---
### Normalizzazione

E' una formalizzazione teorica di alcuni dei problemi che possono emergere durante l'utilizzo della base di dati. Ci permette di costruire un DB corretto e ben definito. 

> Non è sempre applicabile.

CI permette di **eliminare le ridondanze** delle informazione e per ridurre il rischio di inconsistenza della base di dati.

E' un processo di analisi degli schemi basandosi sulle loro dipendenze funzionali e chiavi primarie per raggiugere le proprietà di:

- **Minimizzazione della ridondanza.**
- **Minimizzazione delle anomalie di inserimento, cancellazione e modifica.**

---
### Dipendenza funzionale

Vincolo che deriva dal significato di una relazione di un attributo con un altro attributo.  Viene indicata con: $$X\to Y$$
Vengono usate per definire le **forme normali** per le relazioni, un insieme di attributi $X$ determina funzionalmente un insieme di attributi $Y$ se il valore di $X$ determina un valore univoco per $Y$.

> Nel caso sopra $Y$ dipende da $X$.

Se un attributo è candidato a chiave di una relazione allora è un determinante di ogni attributo.

> Vale anche viceversa.

Le dipendenze funzionali sono transitive. Possiamo dividerle in tre tipi:

- **Dipendenza funzionale completa**
- **Dipendenza funzionale parziale**
- **Dipendenze transitive

---
### Forme Normali 

Quando normalizziamo uno schema, stiamo strutturando il database, in modo tale che non può esprimere delle informazioni ridondanti.

> In questo modo evitiamo vincoli di integrità.

Le tabelle normalizzate non sono solo un modo per **proteggerci da dati contraddittori**, ma sono anche:

- Più semplici da capire.
- Più semplici da migliorare ed espandere.
- Permettono di evitare le **anomalie**

---
### Prima forma Normale

Si dice che una relazione è in 1NF se e solo se:

- Ogni attributo è definito su un dominio con **valori atomici**, ovvero indivisibili.
- Ogni istanza di attributo continene un **singolo valore del dominio** di riferimento.

---
### Seconda forma Normale

Si dice che una relazione è in 2FN se e solo se:

- E' in 1NF
- Tutti gli attributo non chiave presentano una **dipendenza funzionale** dalla chiave nella sua interezza.

> ovvero non dipendono solo da una parte della chiave.

---
### Terza forma Normale

Si dice che una relazione è in 3NF se e solo se:

Una relazione è in Terza Forma Normale se, per ogni **dipendenza funzionale non banale** `X → A`, vale almeno **una** delle seguenti condizioni:

 1. `X` è **superchiave**
     
 2. `A` è **attributo primo** (cioè fa parte di una chiave candidata)

---
### Quarta e quinta forma Normale

Vengono raramente usate in quanto ad un incremento del rigore nell'eleminazione della ridondanza corrisponde un degrado nelle prestazioni.

La quarta forma normale (4NF), o forma normale di Boyce-Codd (BCNF), **è un livello di normalizzazione di database che mira a eliminare le dipendenze multivalore nelle tabelle.** Questo significa che se un attributo di una tabella può assumere più valori indipendenti per una singola chiave primaria, la tabella non è in 4NF e deve essere ulteriormente normalizzata.

La Quinta Forma Normale (5NF) **è il livello più alto di normalizzazione di un database, e mira a eliminare le dipendenze dei join che non sono implicate dai vincoli chiave.** Questo significa che una tabella è in 5NF se è già in 4NF e non può essere ulteriormente scomposta senza perdita di dati.

---
### Boyce Codd Normal Form(BCNF)

Nase come versione più rigorosa della 3NF.

Una relazione è in BCNF se per ogni dipendenza funzionale non banale `X → A`, `X` è una **chiave candidata**.

Una dipendenza funzionale $A\to B$ è **non banale** se $B$ non è un sottoinsieme di $A$, mentre invece $A$ è una superchiave se determina univocamente ogni attributo nella tabella.

