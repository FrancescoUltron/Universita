Gli obiettivi di questo capitolo sono quelli di comprendere il funzionamento dei principi dei servizi del livello di trasporto - ossia come funziona il *mutiplexing - demultiplexing - trasferimento affidabile dei dati - controllo del flusso e controllo della congestione.*
Contemporaneamente guarderemo nel dettaglio i due principali protocolli di questo livello:

- **UDP**: Protocollo senza connessione che offre un servizio *best-effort*.

- **TCP**: Protocollo affidabile controllato e con trasporto orientato alla connessione.

Servizi e protocolli di trasporto mettono a disposizione una **comunicazione logica tra processi applicativi** su diversi host. I tipi di azioni che avvengono sugli end systems sono quelle di:

- **Mittente** che ha il compito di *suddividere il messaggio* dell'applicazione in diversi **segmenti** per poi passarli al livello di rete.

- **Destinatario** che *riassembla i segmenti nel messaggio* originale per poi passarli al livello di applicazione.

> Tramite la comunicazione logica i due host che eseguono processi - anche se separati da altre componenti della rete - saranno connessi direttamente.


E' importante comprendere la differenza tra il lavoro svolto dal livello di rete e quello di trasporto. Il livello di rete è quello che permette di *creare una comunicazione logica fra i due host* - mentre il livello di trasporto permette di **creare una comunicazione logica fra i processi.**

> Il livello di rete crea il percorso tra due host, mentre il livello di trasporto assicura che i dati arrivino al processo corretto su quell'host.

Guardiamo adesso le operazioni che il mittente e il destinatario svolgono in questo livello:

- Il mittente tramite la socket passa un messaggio dal livello di applicazione al livello di trasporto - questo deve **determinare i valori dei campi header del segmento - quindi creare il segmento per poi passarlo al livello di rete** che invierà il datagrammo dall'host mittente al destinatario.

- Il destinatario - il segmento sarà ricevuto dal livello di rete - nel livello di trasporto vengono **controllati i valori presenti nell'header** - verrà **estratto il messaggio** e poi verrà applicato un **demultiplexing alle applicazione che richiedono il messaggio** tramite le socket.

> Il livello di rete verifica che il pacchetto sia arrivato all'host corretto tramite l'indirizzo IP.

Facciamo anche una breve panoramica dei due principali protocolli di trasporto:

- **TCP:** Transmission Control Protocol - offre una consegna in *ordine e affidabile* - fornisce *controllo della congestione e controllo sul flusso dei dati* - inoltre per utilizzare TCP abbiamo bisogno di *stabilire una connessione.*  

> La connessione - composta da *connection state* - è stabilità su entrambi gli host 

- **UDP:** User Datagram Protocol - tipo di protocollo '*best-effort*' - non ha alcun tipo di affidabilità e invia i messagi in maniera non ordinata.

>**Protocolli _best-effort_:** Sono protocolli che **cercano di consegnare i dati nel modo migliore possibile**, ma **non offrono garanzie di affidabilità, ordine o consegna sicura**.  Non eseguono controlli complessi o ritrasmissioni in caso di errore.  
>Esempi tipici: **IP** (livello di rete) e **UDP** (livello di trasporto).

> **Nota**: In questo livello non ci sono sicurezza sui ritardi e sulla larghezza di banda. 

---
### Multiplexing e Demultiplexing

Multiplexing e Demultiplexing avvengono i tutti i livelli della nostra pila - ma li prenderemo solo in considerazione nel livello di trasporto (Per il momento).

Il **demultiplexing** è il meccanismo con il quale riusciamo a dirigere i payload dei vari datagrammi a specifiche applicazioni o protocolli su un host.


Il **multiplexing** è l'opposto - per esempio abbiamo visto come nel livello di applicazione - molte applicazioni portano i messaggi al protocollo TCP.


Immaginiamo di avere due client e un server HTTP.


Se server decidesse di mandare un messaggio HTTP al client 1 - come possiamo essere certi che questo messaggio arrivi all'applicazione desiderata? Al contrario - se i due client mandassero dei messaggi al server - come possoamo essere certi che i messaggi arrivino ai due processi relativi?

> Praticamente ci chediamo come funziona il demultiplexing per client e server.

Riprendiamo l'esempio fatto sopra:

- Quando i processi del mittente devono mandare dei messaggi allora avverrà un multiplexing verso il livello di trasporto tramite le socket. Con il multiplexing andremo a gestire **informazioni ricevute da diverse socket** e aggiungeremo **header del livello di trasporto** al messaggio da spedire - che verrano poi usati per il demuktuplexing.

- Al contrario - quando arriva un messaggio dal livello di trasporto - per trovare l'applicazione corretta a cui spedirlo - vengono usate le **informazioni nell'header del messaggio** in modo tale da passarlo alla socket corretta.

---
**Funzionamento del demultiplexing:**

Quando un host riceve un datagramma IP:

- Il datagramma contiene un **indirizzo IP di origine e uno di destinazione**.

- Trasporta un **segmento del livello di trasporto** (ad esempio TCP o UDP).

- Ogni segmento include un **numero di porta di origine e un numero di porta di destinazione**.

L’host utilizza la **coppia formata da indirizzo IP di destinazione e numero di porta di destinazione** per determinare **quale socket locale** deve ricevere il segmento.  
Questo processo si chiama **demultiplexing**, ed è ciò che consente di instradare correttamente i dati ricevuti alle applicazioni giuste.

> Nel segmento (livello di trasporto) trasportati dai datagrammi (livello di rete) - le sezioni dei numeri di porta sono all'interno dell'header. Il **segmento del livello di trasporto** è **contenuto** nel **datagramma IP**.


> Il modo in cui funziona per TCP e UDP e diverso - per il secondo è più semplice.

---
**Demultiplexing senza connessione**

Quando creiamo una socket dobbiamo specificare il numero di porta dell'host:
```
mySocket = socket(AF_INET, SOCK_DGRAM)
mySocket.bind(('',9157))
```
Oppure lasciamo - soprattutto per lato client - che il sistema operativo le assegni un numero di porta **effimero.** Nel nostro esempio abbiamo collegato una socket UDP alla porta 9157.

> I server hanno quasi sempre delle porte **note**.

> Se non si usa il `bind()` sarà il Sistema Operativo ad assegnare una porta - questà 
> è detta effimera. E' temporanea ed è solitamente associata a numeri alti.

Quando creiamo un datagramma da mandare nella socket UDP specificarne:

- **L'indirizzo IP di destinazione.**
- **Il numero della porta di destinazione.**

Quando l’host riceve un **datagramma UDP**, il sistema operativo fa:

1. Guarda il **numero di porta di destinazione** nel **segmento UDP**.

2. Cerca una socket che è stata creata e `bind()`ata a quella porta.

3. Se la trova, **consegna il segmento a quella socket**.

4. Se **non c’è** una socket legata a quella porta: il segmento viene **scartato**.

Datagrammi IP/UDP che hanno lo **stesso indirizzo IP e numero di porta di destinazione** - ma indirizzi IP e/o numeri di porta di origine diversa vengono inviati alla **stessa socket** sull'host ricevente.

> Indirizzi IP e numero di porta di origine servono come *indirizzo di ritorno* per una eventuale risposta. Quando il server vuole **rispondere**, deve sapere **dove mandare la risposta**. Quindi il **numero di porta di origine e l’indirizzo IP di origine** del datagramma ricevuto sono usati come **destinazione per la risposta**.


---
**Demultiplexing orientato alla connessione**

Siccome stiamo usando il protocollo TCP abbiamo una connessione tra sorgente e destinatario. Possiamo identificare le socket tramite **quattro parametri:**

- Indirizzo IP della sorgente.
- Numero di porta della sorgente.
- Indirizzo IP del destinatario.
- Numero di porta del destinatario.

Il demultiplexer del lato ricevente userà **tutti è quattro i valori** per indirizzare il segmento alla socket approriata.

> Server possono supportare simultaneamente diverse socket TCP - ognuna delle quali identificata dai **suoi parametri** e associata a **diversi client**.

Un host server crea una **socket passiva** specificando un numero di porta. La socket passiva viene usata per *accettare le richieste di connessione*, per ciascuna delle quali verrà creata una **nuova socket connessa** (con la medesima porta e indirizzo IP locale - ma diversa porta e indirizzo remoto (client) - discriminando pertanto le socket connesse di client diversi)

---
**Riassunto multiplexing e demultiplexing**

Questi meccanismi si basano sui **valori del campo dell'intestazione** del segmento o datagramma.

Nel caso del protocollo UDP - il demultiplexing usa solo il *numero di porta e indirizzo IP di destinazione* mentre - nel caso del protocollo TCP - il demultiplexing usa la *quadrupla di valori* descritta nel paragrafo sopra.

> Il multiplexing e demultiplexing avviene a **tutti i livelli** ogni volta che entità diverse vogliono usare servizi del protocollo di livello inferiore.

>Nella creazione di una socket abbiamo usato '' (che in Python equivale a '0.0.0.0' nel caso di IPv4) per indicare qualunque indirizzo IP dell'host; tuttavia, avremmo potuto specificare uno in particolare.

---
### Trasporto non orientato alla connessione: UDP

UDP è un protocollo molto semplice - è un tipo di protocollo bassato sul servzio *'best-effort'* quindi - durante la trasmissione - i segmenti/datagrammi - potrebbero andare persi o consegnati in maniera non ordinata.

UDP è definito come protocollo **non orientato alla connessione** - perchè non ci sta bisogno di stabilire una connessione tra mittente e destinatario - inoltre ogni segmento UDP viene gestito in maniera indipendente rispetto agli altri.

> Siccome è **connectionless** non abbiamo bisogno di una **handshake a tre vie.**

I motivi per cui usare UDP sono i seguenti:

- **Nessuna connessione** viene stabilita - riduce il ritardo di RTT.

- **E' semplice** - non ci sono stati di connessione per mittente e destinatario.

- Header è di dimensione piccola.

- **Non ci sono controlli di congestione** - ciò significa che i datagrammi possono essere trasportati alla velocità massima disponibile sulla rete.

Queste caratteristiche sono molto utili per applicazioni come: Applicazioni di streaming multimediale - DNS - SNMP - HTTP/3.

> Se abbiamo bisogno di un trasferimento sicuro - ma vogliamo usare UDP - possiamo implementare affidabilità e controllo di congestione nel livello di applicazione.

Guardiamo ora nel dettaglio quali operazioni svolgono mittente e destinatario che utilizzano questo protocollo.
Dal lato del mittente è molto semplice - viene passato un messaggio dal livello di applicazione a quello di trasporto - questo **determina i valori dei campi di intestazione del segmento UDP** - fatto ciò crea il segmento e lo passa al livello sottostante.


> I livelli sottostanti a quello di trasporto hanno il compito di portare il messaggio al destinatario.

Dal lato del ricevente - il livello di trasporto - riceverà un segmento IP dal livello di rete su cui dovrà **controllare il valore del campo di intestazione UDP checksum** per poi estrarre il messaggio del livello di applicazione e fare il demultiplexing dei messaggi attraverso le socket. 


La struttura dei pacchetti dei segmenti UDP è molto semplice.


Come possiamo vedere ci sono quattro campi nell'header - questi sono:

- Numero di porta di origine.
- Numero di porta di destinazione.
- La lunghezza in byte del segmento UDP compresa l'intestazione.
- Il **checksum**.

> Le prime due si usano per multiplexing e demultiplexing - la terza serve perché la lunghezza del payload (messaggio a livello di applicazione) può variare e ci serve sapere esattamente la lunghezza di tutto il segmento.

---
#### Checksum

 L'obiettivo del checksum è quello di **rilevare gli errori** - ossia bit alternati - nel segmento trasmesso.

L'idea dietro è molto semplice - immaginiamo una situazione in cui i messaggi trasmessi siano dei numeri - il mittente invierà tre numeri - i primi due sono i segmenti che vuole inviare - il terzo è la somma dei messaggi. Il destinatario riceverà quei tre numeri e controllerà se la somma dei primi due è uguale al terzo - se non è così allora significa che durante la trasmissione del segmento è successo qualcosa di inaspettato.
Nella tabella qua sotto facciamo lo stesso esempio:

|               | 1° numero | 2° numero | Somma |
| :-----------: | :-------: | :-------: | :---: |
| **Trasmessi** |     5     |     6     |  11   |
| **Ricevuti**  |     4     |     6     |  11   |
I mittenti e destinatari UDP lavoreranno in maniera analoga all'esempio fatto sopra con un paio di differenza:

- Il mittente tratterà il contenuto del segmento come **sequenza di interi a 16 bit.**

> Nei 16 bit sono inclusi anche i campi dell'intestazione UDP e gli indirizzi IP.

Il checksum dal lato del mittente avviene facendo il **complemento ad uno** della **somma dei contenuti dei segmenti** - il valore finale viene inserito poi nel campo di checksum di UDP.
Dal lato del destinatario - questo  dovrà **computare la checksum del segmento ricevuto** per poi controllare se uguale a quella inserita nel campo di checksum:

- Se sono **diverse** - Abbiamo trovato un errore.

- Se sono **uguali** - Non abbiamo trovato errori - in realtà non proprio - ma lo tratteremo dopo.

**Esempio:**

```
	1 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 (a)
	1 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 (b)
  ---------------------------------
  1 1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 (c)
  ---------------------------------
    1 0 1 1 1 0 1 1 1 0 1 1 1 1 0 0 (somma)
    0 1 0 0 0 1 0 0 0 1 0 0 0 0 1 1 (checksum)
```

$a$ e $b$ sono i segmenti che sono stati sommati - $c$ è la somma - ma come possiamo vedere questa è più lunga di 16 bit. Il numero più a sinistra si chiama *wraparound* - questo **va tolto e sommato con il restante.** 
In questo modo troviamo la somma che ci interessa - non resta che farne il complemente ad uno.

> Per fare il complemento ad uno basta **invertire tutti i bit.** Il range dei valori rappresentabili in complemento ad uno avendo $n$ bit è: $$-(2^{n-1}-1)\;fino\;a\;+(2^{n-1}-1)\;più\;il\;doppio\;zero$$

Ma siamo sicuri che questo metodo sia davvero sicuro? - se nell'esempio si dovessero invertire gli ultimi due bit di $a$ e gli ultimi due bit di $b$ - magari a causa di un problema durante la trasmissione -  nonostante i numeri siano cambiati il risultato della **checksum rimane uguale** - e quindi gli errori non vengono rilevati. Esistono delle tecniche molto più potenti - che vedremo nel livello di collegamento - per gestire questo problema.

> La checksum è un meccanismo di protezione debole.

---

Per riassumere UDP è un protocollo "*senza fronzoli*" con **servizio best effort** nel quale i segmenti potrebbero essere persi e vengono spediti in maniera **non ordinata** - i suoi vantaggi sono: il **non avere bisogno di una connessione**; il fatto che funziona anche se la rete è compromessa; per l'**affidabilità abbiamo il checksum** e possiamo implementare altre funzionalità aggiuntive ad UDP a livello superiore.

---
### Princìpi del trasferimento dati affidabile - Prima parte

Uno degli argomenti più importanti della materia è quello rigurdante la possibilità di inviare dati in maniera affidabile fra due host.

L'astrazione che vogliamo implementare è la seguente:



Ossia un canale **unidirezionale** che permette ad un processo mittente di mandare dati ad un processo ricevente. L'implementazione sarà data da un protocollo del livello di trasporto.



A livello di trasporto avremo due lati - quello del mittente e quello del ricevitore - che comunicherano in maniera **bidirezionale** su un canale **non affidabile.**

>La complessità dell'affidabilità del protocollo di trasporto **dipende pesantemente** dalle caratteristiche del canale inaffidabile.

>Mittente e destinatario **non conoscono lo stato dell'altro** - a meno che non viene comunicato tramite un messaggio.

Prima di andare ad implementare il protocollo dobbiamo comprendere il concetto di **interfaccia.**



> Le interfacce sono essenzialmente API che permettono di inviare e ricevere dati.

Adesso possiamo incrementalmente sviluppare entrambi i lati del nostro *reliable data transfer (RDT)*.

> Consideriamo solo le comunicazioni unidirezionali per i dati - ma le informazioni per il controllo viaggeranno in entrambi i lati.

Per descrivere gli stati del protocollo useremo delle **macchine a stati finiti (FSM)**.
Costruiremo diverse macchine sia per mittenti che destinatari.

---
##### RDT1.0 - Trasferimento affidabile su canale affifabile

La prima versione del nostro protocollo sarà molto semplice - immaginiamo di volere *mandare dei dati in maniera affidabile su un canale affidabile*. Quindi il canale sottostante ci assicura l'assenza di:

- Errori su bit.
- Perdita dei pacchetti.

Costruiamo le FSM per mittente e destinatario - questi dovranno semplicemente:

- **Mandare** dati nel canale sottostante.
- **Ricevere** dati dal canale sottostante.

La FSM del mittente avrà un **unico stato** dove il mittente aspetterà per una chiamata dal livello soprastante - *se viene chiamata la funzione dal livello di applicazione per inviare dati allora entra nello stesso stato e deve creare un pacchetto con quei dati per poi spedirli al ricevitore*.

La stessa cosa avverrà nel ricevitore - ossia un unico stato in attesa di una chiamata dal livello sottostante - *se viene chiamata la funzione a livello sottostante per ricevere dati allora entra nello stesso stato e estrare i dati dal pacchetto e consegna i dati.*

> Le funzioni che vengono chiamate sono le interfacce viste prima.

---
##### RDT2.0 - Canale con errore sui bit 

Ora abbiamo un canale nel quale alcuni **bit potrebbero alternarsi**. Per rilevare gli errori usiamo il **checksum**. La vera domanda è come possiamo ricuperare dagli errori?

L'idea è molto semplice utilizziamo degli **acknowledgment**:

- **Acknowledgment (ACKs):** Il ricevitore dice esplicitamente al mittente che il pacchetto è *corretto.*

- **Negative Acknowledgment (NAKs):** Il ricevitore dice esplicitamente al mittente che il pacchetto ha degli *errori*.

> Il mittente **ritrasmette** i pacchetti in caso NAK.

Il modo in cui il mittente lavora si basa sullo *stop and wait* - ossia manda un pacchetto e poi aspetta per la risposta del destinatario.

Guardiamo ora la macchina a stati finiti di questo protocollo:


Quindi tramite la FSM descritta sopra il nostro protocollo riesce a recuperare i bit alternati - **ma ci sta un difetto gigantesco** in questo protocollo.

**Cosa succede se un messaggio ACK o NAK è corrotto?** Il mittente non riuscirebbe a capire cosa è successo al ricevitore e non posso ritrasmettere un pacchetto perchè potrebberro esserci dei pacchetti duplicati.

Il modo in cui gestire queste situazioni è il seguente:

- Il mittente ritrasmette il pacchetto corrente se ACK/NAK è corrotto.

- Il mittente aggiunge una **sequenza di numeri** a ciascun pacchetto.

- Il ricevitore scarterà tutti i pacchetti con lo stesso numero - ossia i duplicati.

> Questo nuovo protocollo lo chiameremmo **RDT2.1**.

---
### Princìpi del trasferimento dati affidabile - Seconda parte

Continuamo la creazione del nostro protocollo per il trasferimento di dati in maniera affidabile.

---
##### RDT3.0 - Canale con errori e perdita di pacchetti

Oltre a possibili errori su bit - possiamo anche avere canali in cui si possono *perdere* dei pacchetti. Il modo in cui possiamo capire se un host non ha ricevuto il pacchetto è il seguente:

- Mittente manda pacchetto.
- Mittente aspetta in attesa del ACK.
- Se dopo un **periodo di tempo** non arriva un ACK allora rimanda il pacchetto.

> Potrebbe essere anche che **il pacchetto è soltanto in ritardo e non è stato perso** - in quel caso abbiamo già introdotto l'idea di una sequenza di numeri per tenere traccia di possibili duplicati.

Dobbiamo mantenere un countdown che provoca il rinvio dopo un tempo di attesa *ragionevole*.


> Inseriamo una sequenza di numeri anche nei pacchetti del ricevitore.

In questo modo siamo riusciti a creare un protocollo affidabile che permette la trasmissione di pacchetti su un canale non affidabile.

---
##### Prestazioni di rdt3.0

Il metro che usiamo per calcolare le prestazioni del nostro protocollo è l'**utilizzazione** - ossia *la frazione di tempo in cui il mittente è stato effettivamente occupato nell'invio di bit sul canale* e lo indicheremo con $U_{mittente}$.

**Esempio:** Abbaimo un collegamento da $1\;Gbps$ con ritardo di propagazione di $15ms$ e pacchetti da $1000\;byte$ ($8000\;bit$).

Il tempo che ci mettiamo per trasmette tutti i dati nel canale è: $$D_{trans}=\frac{L}{R}=\frac{8000\;bits}{10^9\;bits/sec}=8\;microsecondi$$
Quindi: $$U_{mittente}=\frac{\frac{L}{R}}{RTT+\frac{L}{R}}=0.000267$$ 
Dove:

- $L$: lunghezza del pacchetto in bit
- $R$: velocità di trasmissione (bit/s)
- $RTT$: round-trip time, cioè il tempo impiegato per un segnale per andare dal mittente al destinatario e tornare indietro.



Il throughput effettivo generato dal mittente è di $267\;kbps$ - ossia le prestazioni fanno schifo e il protocollo limita le prestazioni dell'infrastruttura del canale. 

Il modo migliore per migliorare le prestazioni è tramite il **pipelining** - Invece di aspettare un ACK per ogni pacchetto inviato, il mittente **invia più pacchetti di fila** (fino a un certo limite), sfruttando meglio la banda disponibile.



> Bisogna introdurre molti più buffer tra mittente e destinatario.

Torniamo a vedere l'utilizzazione - possiamo notare che con una pipelien a tre pacchetti abbiamo migliorato di 3 volte l'utilizzazione:

// pipeline tempo

Infatti ora abbiamo che: $$U_{mittente}=\frac{\frac{3L}{R}}{RTT+\frac{L}{R}}=0.00081$$
Esistono due approcci principali per trasferimenti affidabili che fanno utilizzo delle pipeline:

- **Go-Back-N**
- **Selective repeat**

Utilizza gli stessi meccanismi che abbiamo descritto prima (checksum - ACK - rinvio di pacchetti) - ma li usamo in maniera leggermente diversa.

---
##### Go-Back-N

Il **mittente** mantiene una **finestra scorrevole** di massimo $N$ pacchetti **non ancora riscontrati** (cioè per cui non è stato ancora ricevuto un ACK).  
Ogni pacchetto include nell'**header** un numero di **sequenza** rappresentato con $k$ bit. Quindi i numeri di sequenza possibili vanno da $0$ a $2^k - 1$ e si riutilizzano in modo circolare.


Il **destinatario** invia un ACK per l'ultimo pacchetto **ricevuto correttamente e in ordine**, cioè per tutti i pacchetti con numero di sequenza minore o uguale a $n$.

- Alla ricezione dell’ACK per $n$, il mittente fa avanzare la finestra e può iniziare a trasmettere dal pacchetto $n + 1$.

- Il **mittente** imposta un **timer** per il pacchetto **più vecchio** nella finestra (il primo non ancora riscontrato).

- Se il timer scade (**timeout(n)**), **ritrasmette** il pacchetto $n$ e **tutti** i pacchetti successivi nella finestra (con numeri di sequenza maggiori).

Il destinatario mantiene un valore chiamato **rcv_base**, ovvero il numero di sequenza del prossimo pacchetto atteso. Alla **ricezione di un pacchetto con numero di sequenza  rcv_base**, il destinatario:

- Accetta il pacchetto.

- Aggiorna `rcv_base`.
 
- Invia un ACK cumulativo per il nuovo `rcv_base - 1`.

Alla **ricezione di un pacchetto fuori sequenza**:

- Può **scartarlo** oppure **inserirlo in un buffer**.

- In entrambi i casi, **rimanda un ACK** per il **numero di sequenza più alto ricevuto in ordine continuo**, ossia `rcv_base - 1`.

Questo può portare alla **generazione di ACK duplicati**, che il mittente ignora se già ricevuti.

Di seguito le FSM del mittente e del destinatario:

---
##### Selective repeat

Il ricevente **riscontra individualmente** tutti i pacchetti ricevuti correttamente. Il mittente manterrà un timer per ogni pacchetto non riscontrato. Un timeout può quindi avvenire per ogni singolo pacchetto non riscontrato - in questi casi viene *ritrasmesso il pacchetto* - inoltre mantiene una finestra di $N$ numeri di sequenza consecutivi


Quindi il mittente:

- Se ci sono dati disponibili dal livello superiore - se nella finestra è disponibile il successivo numero di sequenza - **invia il pacchetto**.

- In caso di timeout - viene **ritrasmesso il pacchetto** e riparte il timer.

- Quando arriva ACK allora viene marcato il pacchetto $n$ come ricevuto - se $n$ è il numero di sequenza più piccolo - la base della finestra avanza al successivo numero di sequenza del pacchetto non riscontrato.

Mentre il ricevente:

- Invia gli ACK - se sono fuori sequenza li inserisce in un **buffer** - altrimenti li consegna e la finestra avanza al successivo pacchetto non ancora ricevuto. Quando riecve un pacchetto deve inviare ACK altrimenti li ignora.

---
### Trasporto orientato alla connessione: TCP - Prima parte

In questa parte capiremo come funziona in dettaglio il protocollo TCP e di come fa uso di tutto quello che abbiamo descritto fino ad ora per permette un trasporto affidabile dei dati.

Il **Transmission Control Protocol (TCP)** è un protocollo orientato alla connessione e affidabile, progettato per fornire un **trasferimento ordinato e privo di errori** tra due host. TCP è un protocollo **point-to-point**, il che significa che esiste **una sola connessione attiva** tra un **mittente** e un **destinatario**. 

> Non è multicast o broadcast: la comunicazione è **univoca** e diretta.

TCP è **full-duplex**, quindi i dati possono essere trasmessi **simultaneamente in entrambe le direzioni** su una singola connessione TCP. Il numero massimo di **byte di dati TCP** che possono essere trasmessi in un singolo segmento TCP, **escludendo l’header TCP/IP** - si chiama **MSS (Maximum Segment Size)**.

Altre importanti cose da menzionare sono che:

- Usa gli **ACK cumulativi** come nel Go-Back-N.

- E' un protocollo basato su **pipeline.** 

- E' **orientato alla connessione** - quindi abbiamo un handshake che inizializza gli stati del mittente e destinatario.

- Offre un **flusso dei pacchetti controllato** - quindi il mittente non può sovraccaricare il destinatario.

- Offre un **controllo della congestione** - quindi il mittente riduce la velcità in funzione della congestione della rete.

All'interno del protocollo TCP possiamo trovare dei buffer per la ricezione e l'invio dei dati sia da parte del mittente che del destinatario. I due buffer sono:

- *buffer di invio* che ha il compito di mantenere i dati che il **processo ha chiesto di inviare** - in attesa che siano trasmessi o che sia ricevuto l'ACK. 

- *buffer di ricezione* che contiene i dati **pronti per essere letti dall'applicazione.**

La dimensione massima del segmento TCP predefinita per IPv4 è di 536. Per IPv6 è 1220. Se un host desidera impostare MSS su un valore diverso da quello predefinito, il suo valore viene specificata come opzione TCP, inizialmente nel pacchetto TCP SYN durante l'handshake TCP.

> $MSS+lunghezza(H_t)+lunghezza(H_n)\leq MTU$


Un host può determinare il MMS guardando la MTU del collegamento locale - non offre garanzie circa altri collegamenti intermedi. Con **Path MTU Discovery** permette di scoprire il valore più piccolo della MTU lungo il percorso da mittente a destinatario.

Guardiamo alla struttura di un segmento TCP:


**Campi principali dell’header TCP**:

- **Porta sorgente e porta di destinazione (16 bit ciascuna)**: servono per **multiplexing/demultiplexing** delle connessioni.

- **Numero di sequenza (Sequence Number, 32 bit)**: identifica la posizione del primo byte di dati nel flusso TCP.

- **Numero di riscontro (Acknowledgment Number, 32 bit)**: usato per confermare la ricezione; indica il **prossimo byte atteso**.

- **Lunghezza dell’header (4 bit)**: indica dove finisce l'header e iniziano i dati. È necessaria per supportare **opzioni di lunghezza variabile**.

- **Bit di controllo (flags)**:

    - **SYN, ACK, FIN, RST**: gestione della connessione (inizio, fine, reset).

    - **URG**: indica che il campo "puntatore urgente" è valido (poco usato).

    - **PSH**: chiede al ricevente di consegnare subito i dati all'applicazione (anch’esso poco usato).

    - **ECE, CWR**: per il **controllo di congestione esplicito (ECN)**.

- **Finestra di ricezione (Window Size)**: specifica quanti byte il ricevente è pronto a ricevere, quindi usata per il **controllo di flusso**.

- **Checksum**: per verificare l'integrità dell'intero segmento.

- **Opzioni (lunghezza variabile)**: ad esempio per gestire timestamp, MSS, scaling, SACK, ecc.

> **URG** e **PSH** che in pratica non vengono usati.

Nella parte più bassa abbiamo i **dati trasmessi dal livello di applicazione** la cui lunghezza è variabile. 

> Questa dipende dalla dimensione dell'MTU meno la dimensione dellìheader IP+TCP.

Una domanda a cui non abbiamo ancora risposto è: Come impostiamo il valore del **timeout** TCP? Questo deve dipendere in qulache modo dal RTT - ma dobbiamo prestare attenzione a come viene impostato:

- *Troppo breve* - allora avremo un timeout **prematuro** e quindi ritrasmetteremo dei pacchetti che non sono stati mai persi.

- *Troppo lungo* - allora TCP avrà un tempo di reazione **lento** alla perdita di un pacchetto.

> Ovviamente il valore del timeout deve essere **maggiore** del RTT - ma questo può variare.

Dobbiamo in qualche modo estimare RTT - semplice possiamo **misurare l'intervallo** nel momento in cui il pacchetto è inviato fino a quando non viene ricevuto un ACK - ciò non basta - dobbiamo in qualche modo avere una **stima media del RTT**.

Quello che quindi vogliamo calcolare è: $$EstimatedRTT = (1-\alpha)*EstimatedRTT+\alpha*SampleRTT$$
Questa formula è la **stima esponenziale mobile** (Exponential Weighted Moving Average - o **EWMA**) usata da TCP per calcolare RTT stimato.

- $EstimatedRTT$ è la **stima attuale** del tempo che impiega un pacchetto a fare andata e ritorno tra mittente e destinatario.

- **SampleRTT** è un **tempo reale misurato** per un pacchetto specifico - cioè il tempo tra l'invio del segmento e la ricezione del relativo ACK.

- $\alpha$ è un **coefficiente di smorzamento** - tipicamente impostato a $0.125$

> L'influenza dei sample passati diminuisce in maniera esponenziale man mano che ricalcoliamo la formula.


Quindi l'intervallo di timeout dovrà essere $EstimatedRTT$ sommato ad un *margine di sicurezza*. Se abbiamo una grande variazione negli $EstimatedRTT$ allora avremo un *margine di sicurezza maggiore*: $$TimeoutInterval = EstimatedRTT + 4*DevRTT$$
$DevRTT$ è EWNA della deviazione si $SampleRTT$ da $EstimatedRTT$ - ossia $$DevRTT = (1-\beta)*DevRTT+\beta*|SampleRTT-EstimatedRTT|$$
> Solitamente $\beta = 0.25$.

---
Guardiamo ora in maniera semplificata il modo in cui il mittente e il destinatario lavorano nel protocollo TCP.

**Mittente**

Dopo avere **ricevuto i dati dall'applicazione** - il mittente deve creare un segmento con il numero di sequenza - ossia il numero del primo byte del segmento nel flusso di byte - quindi avviare il timer. Pensate a questo come se fosse associato al più vecchio segmento non ancora riscontrato. Infine fa impostato l'intervallo di scandeza - $TimeoutInterval$.

Se avviene un **Timeout** - allora ritrasmettiamo il segmento che ha causato il timeout e riattiviamo il timer.

Se il mittente riceve un **ACK** - se riscontra segmenti precedentemente non riscontrati - allora aggiorna ciò che si sa essere stato riscontrato e avvia il timer se ci sono segmenti ancora non riscontrati.

**Destinatario:**

**Ritrasmissione rapida:**

Nel TCP, gli **ACK sono cumulativi**: ogni ACK conferma la ricezione di tutti i byte fino a un certo punto. Quindi, se il ricevente continua a ricevere segmenti _successivi_ a uno che è andato perso, continua a inviare **ACK duplicati** per l’ultimo byte ricevuto in ordine corretto.

- Il mittente riceve **3 ACK duplicati** per lo stesso numero di sequenza.

- Questo è un **forte indizio** che il **segmento immediatamente successivo è andato perso** - perché il ricevente sta ricevendo dati dopo, ma non riesce a ricomporre l’ordine.

- TCP **non aspetta il timeout**, ma **ritrasmette immediatamente** quel segmento con il **numero di sequenza più basso non ancora riscontrato**.

---
### Trasporto orientato alla connessione: TCP - Seconda parte

Abbiamo visto come nell'implementazione di TCP abbiamo due buffer che vengono usati 
per mantenere i dati.

> Il livello di rete consegna i datagrammi IP nei socket buffer del TCP.

> L'applicazione rimuove i dati dai socket buffer TCP.

Cosa succede il livello di rete riempe i buffer più velocemente di quanto il livello di applicazione riesce a rimuovere i dati? Ci sta un **overflow**.

Il controllo del flusso serve proprio ad *evitare che il mittente mandi troppi dati al buffer del destinatario* e che quindi quest'ultimo non riesca a gestirli.

Il modo in cui il flusso di controllo è implementato nel TCP è abbastanza semplice - il destinatario **avverte** il mittente - dicendogli quanto spazio di buffer libero ha ancora.


Nell'immagine sopra `rwnd` è lo spazio libero rimasto del buffer - mentre `RcvBuffer` è lo spazio totale del buffer.

> Lo spazio totale è solitamente di $4096$ bytes - lo si sceglie tramite l'impostazioni delle socket - molti sistemi operativi la auto-aggiustano.

Questa informazione viene spedita dal destinatario al mittente all'interno del **campo della finestra di ricezione del segmento TCP**.

---
##### Two-way handshake

Come abbiamo già visto, **TCP è un protocollo orientato alla connessione**. Questo significa che **il mittente e il destinatario devono prima stabilire una connessione** prima di poter iniziare a comunicare. Durante questa fase iniziale, i due host **condividono e sincronizzano alcuni stati**, come ad esempio i numeri di sequenza iniziali e altri parametri della comunicazione. In pratica, è necessario che **entrambi siano d’accordo nel voler comunicare** e che **stabiliscano alcune regole comuni** prima di procedere con lo scambio effettivo di dati.

Di seguito vediamo quella che si chiama **stretta di mano a 2-vie** che permette di stabilire una connessione fra due host:

> $x$ è il canale di connessione - `req_conn(x)` è una richiesta di connessione - `acc_conn(x)` è un'accettazione della connessione.

Questa stretta di mano a 2-vie funziona sempre? - ossia funziona anche di fronte a problemi come quelli di:

- Ritardi di variabile.
- Messaggi ritrasmessi a causa di perdite.
- Messaggi riordinati.

Ovviamente funziona se tutto va bene come nella seguente immagine:


Ma se invece il mittente - poichè non vede il messaggio di accettazione - decide di **rimandare la richiesta** e poi gli arriva l'accettazione della richiesta precedente?
In questo caso il mittente* terminerà la connessione perché gli è arrivata l'accettazione* ma - siccome ne ha mandate due - il destinatario dovrà nuovamente rispondergli. Però il mittente ha chiuso la connessione quindi l'accettazione andrà persa e il destinatario rimmarra nello **stato di connessione stabilita** anche se non comunica con nessuno.

> Questo è un problema di **connessione aperta per metà.**


Un'altra possibile situazione - molto simile a quella precedente - è che - oltre a rinviare una richiesta - viene ritrasmesso lo stesso dato o operazione - ciò potrebbe portare ad un problema di **duplicazione dei dati**.


---
##### Three-way hanshake

Abbiamo visto sopra che la 2-way handshake non funziona sempre e potrebbe portare a diversi problemi - per questo motivo TCP usa la **3-way handshake.**

Il funzionamento è semplice:

1) Client e Server creano delle socket che gli permettono di comunicare tra loro.

2) Entrambi entrano in un stato di *ascolto* - dove si preparano a ricevere dei messaggi.

3) Il client manda poi un messaggio **SYN** - ossia un segmento TCP che ha il bit di **SYN** impostato ad $1$ (Sceglie anche un numero di sequenza $x$) - quindi il client entra in un stato di **SYNSENT.**

4) Il server riceve il messaggio entra nello stato **SIN RCVD** - quindi invia al client un messaggio di **SYNACK** che conferma l'arrivo del messaggio di **SYN**.

5) Il client ora sà che il server può comunicare - quindi entra nello stato di **connessione stabilita** e invia al server un **ACK** per il messaggio **SINACK**.

6) Quando il server riceve il messaggio - allora capisce che il client sta nello stato di connessione stabilita e ci entra anche lui.


Dobbiamo tenere conto anche della chiusura della connessione - sia il client che il server devono **chiudere il loro lato della connessione** mandando un segmento TCP con il bit **FIN** impostato ad $1$. All'arrivo del segmento FIN entrambi i lati devono rispondere con un messaggio **FINACK** per poi aspettare un periodo di tempo che serve a ricevere possibili messaggi ritrasmessi.

> Si può implementare un protocollo in grado di gestire diversi messaggi **FIN** simultaneamente. 

Di seguito abbiamo la FSM del TCP con il three-way-handshake:


---
### Princìpi del controllo della congestione

Informalmente la **congestione** avviene quando abbiamo troppi host di origine che trasmettono troppi dati in maniera troppi veloce per la rete da gestire.

Si manifesta con:

- **Lunghi ritardi** - quando si creano delle code nei buffer dei router.
- **Perdita di pacchetti** - quando abbiamo un overflow sui buffer dei router.

> E' diverso rispetto al flusso di controllo.

---
**Primo scenario**

Immaginiamo di avere un singolo router con due host mittenti e due destinatari - il router ha buffering infinito - ci sono due flussi di dati:

- Il primo dall'host $A$ al server $A$.
- Il secondo dall'host $B$ al server $B$.

Non ci sta bisogno di ritrasmissione di pacchetti e la capacità di trasmissione dei canali è di $R$.


- $\lambda_{in}$ è la velocità con cui i dati vengono passati dal livello di applicazione a quello di trasporto.

- $\lambda_{out}$ è il **throughput** - ossia il tasso di velocità a cui i dati arrivano al livello di applicazione.

Cosa succede al througput se $\lambda_{in}$ si avvicina a $R/2$?

Se abbiamo un buffer infinito allora non abbiamo alcuna perdita di pacchetti e quindi il troughput si stabilirà a $R/2$.

Ricordiamo però che i **ritardi** sul canale di trasmissione aumentano man mano che $\lambda_{in}$ si avvicina alla capacità del canale di trasmissione.

> **Teoria delle code:** Quando il tasso di arrivo si avvicina alla capacità del canale - il **ritardo medio di coda** tende a **infinito**.

---
**Secondo scenario**

Stesso scenario di prima - ma abbiamo un buffer finito.

Dobbiamo anche tenere di conto di possibili pacchetti ritrasmessi persi e pacchetti timeouted - quindi:

- $\lambda_{in}^*$ è la velocità dei dati del livello di applicazione insieme a quelli ritrasmessi dal livello di trasporto.   

> Nota: $\lambda_{in} = \lambda_{out}$ - mentre $\lambda_{in}^* \geq \lambda_{in}$.

Adesso potremmo avere diversi casi:

- **Conoscenza perfetta** - ossia ipotizziamo che il mittente sa sempre quando ci sta spazio libero sul buffer. In questo caso questo scenario non si distingue da quello che abbiamo visto precedentemente.

- **Conoscenza quasi perfetta** - In questo caso i pacchetti possono essere persi a causa della pienezza del buffer - ma il mittente sà quando un pacchetto viene perso - quindi lo può ritrasmettere senza problemi.

> Nel secondo caso - il **throughput non rispecchierà uno ad uno** $\lambda_{in}^*$ perché - man mano che ci avviciniamo alla velocità del canale - perderemo dei pacchetti che dovremmo ritrasmettere.


> Nell'immagine vediamo come i pacchetti persi rappresentano solo una piccola parte del throughput - infatti **i pacchetti persi saranno molti di meno rispetto a quelli non persi.**

- **Caso realistico: duplicati che non servono** - I pacchetti possono essere persi - il buffer è di dimensione finita e nella situazione in cui un pacchetto non riesce ad arrivare al destinatario in tempo - allora viene ritrasmesso un altro pacchetto.

> Ciò significa che un pacchetto che non è stato perso - ma è stato troppo tempo nel buffer - viene ritrasmesso anche se non c'è né bisogno.


> A causa di tutte queste ritrasmissioni non necessarie - il **throughput totale diminuisce** ancora di più.

---
**Terzo scenario**

Immaginiamo di avere $4$ mittenti e diversi router - nonché percorsi. I router sono di dimensione finita e ci sono possibili ritrasmissioni.


Anche in questo caso divifiamo in $\lambda_{in}$ e $\lambda_{out}$. Il problema principale adesso è che se vogliamo spedire un pacchetto da $A$ a $C$ - questo divrà passare per due router.

> Se viene perso al secondo router deve essere ritrasmesso nel primo.

Essenzialemente siccome abbiamo perso il pacchetto al secondo router è come se avessimo sprecato la larghezza di banda del canale di trasmissione quando lo abbiamo spedito al primo router. 

Cosa succede se $\lambda_{in}$ e $\lambda_{in}^*$ incrementano? Diventa complicata la situazione.

Infatti l'host $A$ non avrà problemi nello spedire i sui pacchetti al primo router - ma un qualsiasi altro host che 'prende la stessa strada' - facciamo $D$ - si troverà la maggior parte del canale occupata da pacchetti di $A$. Quindi il throughput di $D$ tenderà a $0$.

Quindi quando abbiamo una rete *multi-hop* dobbiamo tenere di conto che quando un pacchetto è perso - allora la capacità di trasmissione e buffering usata per quel pacchetto sarà persa.

//Immagine grafo

|Simbolo|Significato|
|---|---|
|$\lambda_{in}$|**Tasso "utile"** con cui i dati originali dell'applicazione entrano nella rete. Sono i dati **nuovi**, senza contare duplicati.|
|$\lambda_{in}^*$|**Tasso effettivo** di dati inviati nel sistema: include sia i dati **originali** che quelli **ritrasmessi** (perché magari si sono persi o hanno avuto timeout).

---
**Approcci principali per la gestione della congestione**

Ci sono due approcci principali per la gestione:

- **Controllo della congestione End-End**: Quando un mittente rileva delle situazioni congestione allora diminuisce la velocità con cui spedisce i pacchetti - ossia $\lambda^*_{in}$. 

> Non ci sta alcun supporto esplicito da parte della rete.

> La congestione è **dedotta** osservando le perdite e i ritardi nei sistemi terminali.

> Metodo adottato da TCP.

- **Controllo della congestione assistito dalla rete**: I router (livello di rete) forniscono un feedback agli host - mittenti e destinatari - tramite un *chokepacket* che lo avvisa dello stato di congestione. 

> In alternativa i router possono marcare i pacchetti che li attraversano in modo tale che il destinatario alla sua ricezione informi il mittente.

> I protocolli che usano questo metodo sono TCP - ECN - ATM - DEBbit.

---
### Controllo della congestione TCP

L'approccio dietro al controllo della congestione nel protocollo TCP è semplice - il mittente può incrementare il numero di pacchetti inviati finché non avviene una pedita di pacchetti - allora diminuisce il tasso di spedizione dei pacchetti.


Questo tipo di controllo è anche chiamato **AIMD** - ossia *Additive Increase Multiple Decrease*

> **Assitive Increase:** Incrementa il tasso di spedizione di $1$ segmento di massima dimensione ogni RTT finché non viene rilevata una perdita.

> **Multiplicative Decrease:** Taglia a metà il tasso di spedizione ad ogni perdita.
