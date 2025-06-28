# Capitolo 5 - Livello di Rete (Piano di controllo)

Quello che interessa studiare in questo capitolo è il modo in cui il router decide l'**instradamento** dei pacchetti.

> Ricorda inoltro è funzione locale, mentre instradamento è una funzione globale.

Ci sono due modi principali per implementera l'instradamento:

- **Pre-ruoter control plane:** Ogni ruoter contiene una parte del piano di controllo. Ossia una compoente di instradamento che comunica con le componenti degli altri router in modo tale da calcolare la propria tabella di inoltro.

> I prorocolli maggiormente usati sono **OSFP** e **BGP**.

- **Software-Defined Networking:** Un controller esterno ai router calcola e distribusice le tabelle di inoltro che devono essere usate da questi.

> I router non comunicano fra di loro.

---
### Algoritmi di Instradamento

I due principali tipi di algoritmi usati sono noti come:

- **algoritmo link state**
- **algoritmo distance vector**

L'obiettivo di un algoritmo di routing è quello di trovare un percorso "buono" che connette l'host mittente con quello destinatario attraverso dei router.

- **Percorso:** Sequenza di router che da un host sorgente iniziale che arriva ad un host destinazione.

> Con *buono* possiamo intedere diverse cose, per esempio il percorso che scegliamo deve essere il meno 'costosto', il più 'veloce' oppure quell con meno congestione.

Possiamo rappresentare il problema dell'instradamento con un grafo:

//Immagine min 3.19 - 5.2

Il grafo $G = (N,E)$  è rappresentato nella seguente maniera:

- $N:$ Insieme dei router = $\{u,v,w,x,y,z\}$
- $E:$ Insieme di links = $\{(u,v),(u,x),(v,x),(x,w),(x,y),(w,y),(w,z),(y,z)\}$

Il costo del collegamento lo denotiamo come: $c(u,w)$ con $u,w$ due nodi qualsiasi del grafo.
Se due nodi non sono collegati direttamente allora il loro costo sarà $\infty$.

> Il costo **non è standard**, ma viene definito da un operatore di rete. Questo potrebbe assegnarlo in relazione alla larghezza di banda o alla congestione.

Possiamo classificare gli algoritmi di routing in base a due caratteristiche, **se sono globali o decentralizzati e se sono statici o dinamici.** Nel spcecifico abbiamo la seguente classificazzione:

- **Globali** - Ogni router ha una topologia completa (algoritmi link-state).

- **Decentralizzati** - Processo iterativo di computazione, i router inizialmente conoscono solo il costo dei router vicini. (Algoritmi distance-vector).

- **Statici** - I router non cambiano con il passare del tempo.

- **Dinamici** - I router cambiano molto velocemente quindi abbiamo bisogno cdi aggirnare costantemente il costo dei collegamenti.

---
##### Algoritmo di Dijkstra - Link-state

Siccome l'instradamento è link-state allora i costi di tutti i nodi sono noti e vengono passati come input all'algoritmo. L'algoritmo che usiamo associato all'instradamento link-state è **l'algoritmo di Dijkstra.**

> Ci permette di ottenere il costo minimo per raggiungere ogni nodo partendo da un nodo di input. (Singola sorgente).

Dopo aver applicato l'algoritmo il router riceve in output una tabella con i costi minimi cei collegamenti che il router deve usare per raggiungere gli altri router.

In termini di complessità computazionale, considerando una semplice implementazione con matrice di adiacenza, l'algoritmo di Dijkstra ha complessità **O(n²)**, dove _n_ è il numero di nodi. Per quanto riguarda i **messaggi di aggiornamento**, ogni router trasmette le proprie informazioni link-state in broadcast agli altri router. Poiché ogni messaggio può attraversare fino a $O(n)$ collegamenti e ci sono $n$ router, la complessità complessiva della diffusione delle informazioni è **O(n²)**.

> Quando i costi dei collegamenti **dipendono dinamicamente dal traffico**, ad esempio in reti adattive, è possibile che i percorsi cambino frequentemente. Questo può portare a **oscillazioni** nei percorsi e instabilità nell'instradamento.

---
##### Algoritmo di Bellman-Ford - Distance vector

L'algoritmo distance vector si bassa sull'**equazione di Bellman-Ford:** $$D_x(y)=min_v\{c_{x,v}+D_v(y)\}$$
dove:

- $D_x(y)$ è la stima del costo minimo per andare dal nodo $x$ al nodo $y$,
- $c_{x,v}$ è il costo del collegamento diretto tra $x$ e un vicino $v$,
- $D_v(y)$ è la stima del vicino $v$ per raggiungere la destinazione $y$.

In altre parole, per calcolare la distanza da $x$ a $y$, il nodo $x$ considera **tutti i suoi vicini diretti** $v$ e sceglie il percorso che ha il **minimo costo totale**, sommando il costo per raggiungere $v$ e la stima di $v$ per raggiungere $y$.

Ogni nodo mantiene una tabella con i costi verso tutte le destinazioni conosciute, e aggiorna periodicamente tali costi sulla base delle informazioni ricevute dai vicini.
L'algoritmo è **distribuito e iterativo**: ogni nodo conosce solo i costi verso i suoi vicini e apprende le distanze verso le altre destinazioni tramite scambi di messaggi con essi.

Distance-vector è **iterativo, asincrono e distribuito.**

- Distribuito - ciascun nodo riceve parte dell’informazione da uno o più dei suoi vicini direttamente connessi, a cui, dopo aver effettuato il calcolo, restituisce i risultati.

- Iterativo - il processo si ripete fino a quando non avviene ulteriore scambio informativo tra vicini. Aspetto interessante, l’algoritmo è anche auto-terminante: non vi è alcun segnale che il calcolo debba fermarsi, semplicemente si blocca.

- Asincrono - non richiede che tutti i nodi operino al passo con gli altri.

Ogni nodo della rete **mantiene una stima dei costi** per raggiungere tutte le destinazioni conosciute. Quando riceve aggiornamenti dai nodi vicini o rileva un cambiamento nei costi dei collegamenti, **ricalcola** i propri costi minimi utilizzando l’equazione di Bellman-Ford.  
Se la nuova stima comporta una modifica rispetto alla precedente, il nodo **propaga il proprio distance vector** aggiornato **a tutti i suoi vicini**.  Questo processo continua in modo **iterativo e distribuito**, finché non si raggiunge una situazione stabile (convergenza), in cui i costi non cambiano più.

La complessità dei messaggi dipende dallo scambio di messaggi tra router adiacenti, inoltre può convergere molto lentamente. Non è sicurissimo come algoritmo rispetto ai link-state.

---
### OSPF

Lo studio degli algoritmi di instradamento visto ad adesso è stato molto teorico. Nella pratica non è così semplice. Ci sono due problemi principali:

- **Scala su cui lavoriamo** - Ci sono milioni di host e un router non ha modo di mantenere tutte la destinazione per ogni altro router.

- **Autonomia** - Quanto autonomo può essere una rete nei confronti del proprio router e come facciamo a controllare l'inoltro all'interno di una rete. 

Per risolvere i problemi facciamo utilizzo di **Sistemi Autonomi** (AS), ossia dei router organizzati tutti all'interno di una regione, questi sono controllati dalla stessa amministrazione.

Possiamo suddividere i protocolli di instradamento in due categorie:

- **Intra-AS**: Eseguiti all'interno di un singolo AS, tutti i router confdividono le informazioni e agli estremi della rete abbiamo i router **gateway** che si interfacciano con altri AS.

> Sono algoritmi in esecuzione in un AS - Per esempio: **OSPF**, **RIP**,**EIGRP**.

- **Inter-AS**: Utilizzati per l'instradamento tra diversi AS. I **router gateway** hanno il compito di comunicare con i router di altri AS.

**OSPF (Open Shortest Path First)** è un protocollo link-state che usa *flooding* per inviare in broadcast le informazioni sugli stati dei collegamenti e l'algoritmo di *Dijkstra* per la determinazione di un percorso.
Con il OSPF, un router ha il compito di costruire un grafo dell'interno del AS per poi usare l'algoritmo di Dijkstra per trovare un albero dei cammini minimi verso le sottoreti.

**Vantaggi**

- _Sicurezza_: Tutti i messaggi OSFP sono autenticati per preventire intrusioni dannose.

- _Percorsi con lo stesso costo_: Se ci sono più percorsi con lo stesso costo, OSPF consente di usarli senza dover sceglierne uno particolare.

- _Gerarchia a due livelli_: Una AS può essere stutturata in due aree, **locale** e **dorsale (backbone**).
