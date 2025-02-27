# Capitolo 1 - Reti di computer e Internet

### Cos'è Internet 
---

**Internet come un insieme di dispositivi:**

Internet è una rete di computer composta da miliardi di dispositivi chiamati **host** o **end system**, notiamo anche che la terminologia di rete di computer è outdated considerando i migliaglia di dispositivi connessi ad Internet che non sono dei PC desktop. Gli host sono i **nodi** della rete su cui girano le applicazioni.
Il modo in cui gli host comunicano è tramite dei **packet switches**, il loro compito è quello specificare il percorso che i pacchetti (Insiemi di informazioni) devono prendere per arrivare a destinazione.
Infine abbiamo i **communication links**, ne esistono di diversi tipi e hanno il compito di trasferire informazioni fra gli host e i packet swithces. La **velocità di trasmissione** è misurata in *bits/second*.
Un insieme di device, packet switches e communication links si chiama **rete**, mentre la sequenza di comunicazione fatte per portare un pacchetto a destinazione si chiama **route**.
Possiamo quindi dire che Internet è una **rete composta da reti**. Ogni end system accede ad Internet tramite un **Internet Service Provider (ISP)**.

Le comunicazioni tramite gli host avvengono tramite dei **protocolli**, i quali descrivono una maniera standardizzata per far si che dispositivi su reti diverse possano comunicare senza problemi. Questi standard sono definiti dalla **IETF (Internet Engineering Task Force)** e i documenti standard sono chiamati **RFC (Requests for comments).**

---
**Internet come un infrastruttura che fornisce servizi ad applicazioni:** 

I servizi possono essere: web, email, giochi, e-commerce e molti altri. Queste applicazioni si dicono **applicazioni distribuite**, cioè che involvono molti end system che si scambiano informazioni. Queste applicazioni vengono eseguite sugli end system e 'siedono' ad un livello superiore rispetto alla rete.

Inoltre gli end system attaccati ad Internet forniscono delle **interfacce socket** che specificano il modo in cui l'applicazione eseguita sull'end system chiede all'infrastruttrura Internet di spedire messaggi ad un altro specifico end system o programma di destinazione.

---
Prima abbiamo parlato di protocolli, cerchiamo di dare una definizione su che cosa fanno:

>Un protocollo definisce il **formato**, **l'ordine** dei messaggi mandati e ricevuti fra entità all'interno di una rete e le **azioni** che vengono svolte durante la ricezione o la trasmissione dei messaggi.

![Immagine non trovata](Immagini/HumanProtocol.png)

### Network edge

Un nodo della rete, cioè un host, può essere distinti in:

- **Client** - Riceve servizi.
- **Server** - Solitamente si trovano nei data center e hanno il compito di offrire servizi.

 Consideriamo adesso la **rete di accesso**, cioè la rete che connette fisicamente un end system al primo router (**Edge router).** Esistono diversi tipi di reti di accesso divisibili in: 

- Reti ad accesso residenziale;
- Reti ad accesso istituzionale;
- Reti ad accesso mobile;

Oggi, i due tipi di accesso a banda larga residenziale più diffusi sono la **Digital Subscriber Line** (DSL) e **tramite cavo.**

---
**Accesso tramite DSL:**

> L'accesso ad Internet tramite DSL viene fornito dalla stessa compagnia telefonica che fornisce i cavi del telefono per i trasferimenti di dati. Quindi i modem degli utenti della rete usano la linea telefonica (**doppino telefonico**) per collegarsi ad un **DSLAM**.

Il modem DSL prende i dati digitali e li trasforma in analogici ad alta frequenza, questi vengono inviati attraverso i cavi alla centrale e poi vengono convertiti di nuovo in digitali quando arrivano al DSLAM.
La linea telefonica di casa porta sia i dati di Internet che i segnali telefonici tradizionali, ma questi vengono condificati a frequenze diverse:

- Una banda ad **alta velocità** per i dati in arrivo (da 50 kHz a 1 MHz).
- Una banda a **velocità media** per i dati in uscita (da 4 kHz a 50 kHz).
- La banda **tradizionale** per la telefonia (da 0 a 4 kHz).

Questo sistema permette di **usare la stessa linea telefonica** per fare una telefonata e per connettersi a Internet **contemporaneamente**, come se fossero tre collegamenti separati. Per fare in modo che i segnali non si mescolino, un "**splitter**" a casa divide i segnali telefonici e i dati, inviando quelli dati al modem DSL. Nella centrale, il DSLAM separa anche i segnali per mandarli dove devono andare: i dati vanno a Internet e le chiamate telefoniche continuano a viaggiare sulla linea telefonica. Molte case possono connettersi a un singolo DSLAM, permettendo così una **connessione condivisa tra molte abitazioni.**

![Immagine non trovata](Immagini/DSL.png)

---
**Accessi tramite cavo:**

Negli accessi tramite cavo, abbiamo un cavo fisico che connette diverse case ad un unico *fine* di cavo. I segnali che vengono mandati su questo cavo sono a **frequenze diverse.**
Per evitare che segnali a frequenze diverse si vadano ad intralciare si utilizza un *frequency division multiplexing (FDM).* (Ovviamente le stesse frequenze **condividono** lo stesso cavo quindi bisogna trovare un modo per gestirle, lo vederemo nei capitoli succesivi).

Questi tipo di rete è anche chiamato **Hybrid fiber coax (HFC)**, cioè che combina fibra ottica e cavo coassiale.
![Immagine non trovata](Immagini/Cavo.png)

---
Entrambi i tipi di rete di cui abbiamo parlato sono **assimetrici**, cioè abbiamo una differenza nella velocità di **downstream** e **upstream**, questa differenza è dovuta dal fatto che in entrambi i casi ci interessa essere dei client - quindi ricevere molti più dati rispetto ad inviarli.

| Tipo di rete | Velocità in downstream | Velocità in upstream |
| -------------| -----------------------|----------------------|   
| Cavo         | 40Mbps - 1.2GBps       | 30 - 100 Mbps        | 
| DSL          | 24 - 53Mbps            | 3.5 - 16Mbps         |

>Nel DSL la velocità di trasmissione dipende molto dalla distanza dall'ufficio centrale.

Guardando nello specifico un rete di una casa troveremo quindi: Un **cavo o un modem DSL**, connesso al modem abbiamo un **router** che connessione con e senza cavo ai dispositivi della casa, quindi il router sarà connesso a dispositivi tramite cavo (*wired Ethernet* - 1 Gbps) oppure ad un **access point** per il WiFi wireless (54 - 450 Mbps).

>Solitamente modem, router e access point sono posizionati nella stessa scatola.

![Immagine non trovata](Immagini/Home_Network.png)

---
**Wireless network**

Abbiamo già parlato di reti wireless all'interno di una casa, cerchiamo di capire meglio di cosa si tratta. Esistono due tipi di reti senza cavo:

- **Wireless local area networks** - WiFi
- **Wide-area cellular access networks** - 3G, 4G, 5G

In entrambi i casi abbiamo un *access point*, cioè un posto da cui gli end system possono mandare e ricevere dati.
Di seguito alcune reti senza cavo più usate:


**Wireless local area networks (WLAN):** Sono reti che si trovano all'interno di case o aziende, lavorano in un raggio di circa 10 - 100 metri e la velocità di trasmissione è circa: 11, 54 o 540Mbps.
Questi protocolli sono standardizzati dalla **IEEE** nella famiglia delle tecnologie 802.11, consociute come tecnologie WiFi.


**Wide-area cellular access networks:** Sono reti che vengono **fornite dagli operatori delle rete mobile e cellulare**, la velocità di trasmissione è sulle decine di Mbps. Al momento ci troviamo alla quarta generazione (4G) - è in sviluppo amche il 5G.


**Enterprise network:** Sono come le home network, ma consistono in un insieme di diverse tecnologie, quindi sia ethernet - (100Mbps, 1Gbps, 10Gbps) - che senza cavo. La differenza principale rispetto alle home network è la **maggiore quantità di router e switches** usati per dirigere grandi quantità di pacchetti.


**Data center network:** Sono un tipo particolare di Enterprise network, ma sono molto più potenti, poichè connettono **centinaia di server insieme e diretti ad Internet.** 

Con questo abbiamo finito la visione generale sui vari tipi di reti di accesso, adesso controlliamo cosa significa **inviare dei pacchetti su una rete** quali sono i **dispositivi fisici** con il quale possiamo inviare i pacchetti.

---
**Inviare pacchetti sulla rete**

Immaginiamo di avere un host che deve spedire un file ad un end system, il modo in cui questa trasmissione avviene è semplice:

1) Si prende il messaggio che si vuole spedire.
2) Si divide il messaggio in piccoli *chunk* - cioè i **pacchetti** - ognuno di $L$ bits.

>All'interno di ogni pacchetto abbiamo anche delle informazioni aggiunte contenute in un **header**, le informazioni dell'header dipendono dal protocollo che effettua la trasmissione.

3) I pacchetti sono inviati sulla rete di accesso ad **tasso di trasmissione** $R$.

>Possiamo considerare $R$ come la **capacità** oppure la **larghezza di banda della connessione.** Inoltre: $$\text{Ritardo di trasmissione} = \frac{L \, (\text{bits})}{R \, (\text{bits/sec})}$$
>Il ritardo di trasmissione è il tempo necessario per spedire pacchetti da $L$ bits nella rete.

![Immagine non trovata](Immagini/trasmissione_dati.png)

---
**Caratteristiche fisiche della trasmissione dei dati**

Sappiamo che i bit vengono propagati tra coppie di ricevitori e mandanti, la **connessione fisica** è il mezzo con la quale si possono spedire bit. Li possiamo dividere in:

- **Mezzi guidati - Guided media**: Trasmettono segnali tramite dei mezzi fisici.
- **Mezzi non guidati - Unguided media**: I segali vengono propagati liberamente.

Di seguito alcuni tipi di mezzi di comunicazione più usati:

**Twisted pair (TP):** Sono due **fili di rame intrecciati** - inizialmente facevano riferimento solo alla linea telefonica - oggi invece si riferiscono anche all'ethernet o adsl, il tasso di trasmissione è nell'ordine di centinaia di Mbps fino a centinaia di Gbps. Sono suscettibili a rumori elettromagnetici.


**Cavo coassiale:** Sono due conduttori fatti di rame, ma sono **concetrici** e non paralleli. Con le giuste protezioni possono raggiungere tassi di trasmissione molto elevati e possono avere **diversi canali con diverse frequenze.**
>I primi cavi ethernet funzionavano tramite cavi coassiali.


**Cavi in fibra ottica:** Sono fibre di vetro che trasmettono impulsi di luce - ogni impulso equivale ad un bit - lavorano su velocità di trasmissione elevatissime ed hanno un tasso di errore molto basso rispetto ai mezzi visti precedentemente - sono anche immuni a rumori elettromagetici. Vengono usate per comunicazioni molto distanti, ma hanno come problema il costo elevato.


**Wireless radio:** I segnali vengono trasportati in diverse *bande* dello spettro elettromagetico, quindi non ci sta nessun cavo. I segnali vengono trasferiti in **broadcast**, quindi tutti i dispositivi nel raggio di azione possono intercettarli.
Il problema principale sono gli effetti che ha l'ambiente circostante sui segnali, questi infatti possono essere: **riflessi, bloccati da oggetti, essere intercettati creando rumore.**
Esistono diversi tipi di segnali radio wireless, sono molto complicati da ottenere, alcuni sono:

- **Wireless LAN (WiFi)** - Decine di metri con velocità sui 10 - 100 Mbps.
- **Wide-Area** - Decine di Mbps su svariati Km.
- **Bluetooth** - Piccole distanze con tasso di trasmissione limitato.
- **Microonde terrestri** - Comunicazioni punto-punto con canali a 45 Mbps.
- **Satelliti** - Fino a 45 Mbps con ritardi end to end di 270msec.

---
### The network Core

Abbiamo visto fino ad ora i nodi che si trovano ai vertici della rete, guardiamo adesso il cuore della rete. Il cuore è composto da un insieme di router che sono interconnessi tra di loro. Abbiamo già visto come gli end system si possono spedire pacchetti, ma non abbiamo menzionato che ogni pacchetto per arrivare a destinazione deve viaggiare attraverso dei **canali di comunicazione** e **packet switches.** la rete *inoltra* i pacchetti da un router all'altro attraverso i canali di comunicazione she creano il **percorso da sorgente a destinazione.** Esistono due principali *modalità di commutazione* per costruire una rete, basate sul **Packet switching o sul Circuit switching.**

---
**Packet Switching (Commutazione a pacchetto)**

Ci sono due funzioni in particolare che il cuore della rete deve svolgere:

- **Forwading - Switching - Inoltrare**
- **Routing**

Controlliamo come è composto l'interno di un router per capire meglio di cosa trattano.

![Immagine non trovata](Immagini/Switching.png)

All'interno di ogni router abbiamo una tabella che viene fornita da un **algoritmo di routing**, questa tabella serve per decidere in quale canale di comunicazione inoltrare il pacchetto che sta arrivando. In base al valore del **valore di destinazione nell'header** il pacchetto è inoltrato in diversi canali. La differenza tra le due funzioni descritte è che il forwarding è un'operazione **locale** che consiste solo nel decide in quale canale muovere il pacchetto una volta che arriva ad un determinato router, il routing invece è **globale**, ha il compito di determinare il percorso da sorgente a destinazione.

Il ritardo di trasmissione è dato dal rapporto tra la grandezza dei pacchetti da inviare e la velocità del mezzo di comunicazione, ma prima di poter inviare nuovamente un pacchetto da un router dobbiamo aspettare che questo riceva l'intero paccheto, questa operazione si chiama **store-and-forward.**

![Immagine non trovata](Immagini/coda.png)

Cosa succede se degli end system vogliono mandare dei pacchetti ad un router che poi li deve inoltrare ad altri end system, ma la velocità di trasmissione tra end system e router è molto maggiore rispetto a quella della rete di accesso? Semplicemente si forma una **coda.**

>Una coda si forma quando una quantità di lavoro arriva più velocemente rispetto al tempo che ci vuole per servirla.

Quando la velocità di arrivo dei pacchetti è maggiore rispetto a quella di trasmissione avviene che i pacchetti formano una coda, se arrivano troppi pacchetti succede che il buffer di memoria del router non riesce a mantenerli tutti, di conseguenza molti pacchetti andrebbero persi. Se la rete non controlla gli end system che mandano i pacchetti si possono creare queste situazioni poco convenienti.

![Immagine non trovata](Immagini/store-and-forward.png)

---
**Circuit Switching (Commutazione a circuito)**

Tutto quello che abbiamo visto nella sezione precedente ricade nel **Packet Switching**, ma non è l'unico modo per muovere dati in una rete, basti pensare a come funzionano le reti telefoniche; queste infatti si basano su un tipo di struttura di commutazione chiamata **circuit switching.**

Il circuit switching si basa sull'idea di allocare delle risorse end-end riservate per delle chiamate fra sorgente e destinazione, queste sono risorse non condivise che garantiscono una connessione senza perdita di dati o formazioni di code. Il problema è che sono molto inefficienti, se la larghezza di banda non è usata dalla chiamata allora viene persa.

>La larghezza di banda rappresenta la "larghezza" del "canale" e quanto spazio ci sta per fare passare dei dati.

Il circuit switching può essere ottenuto in due modi:

- **Frequency Division Multiplexing (FDM):** Le frequenze elettromagnetiche sono divise in delle bande strette, ogni chiamata ha una sua banda che viene trasmessa al massimo della capacità della banda.

![Immagine non trovata](Immagini/FDM.png)


- **Time Division Multiplexing (TDM):** divide il tempo in piccole "fette" e assegna a ciascun flusso di dati una "fetta" di tempo specifica per inviare i suoi dati.

![Immagine non trovata](Immagini/TDM.png)

---
**Packet switching VS Circuit Switching**

Immaginiamo di trovarci in questa situazione, abbiamo una rete con $n$ possibili utenti la velocità di trasmissione è di 1Gb/s, ogni utente ha bisogno di 100Mb/s quando attivo e ogni utente è attivo il $10$% del tempo. Quale tipo di commutazione è meglio usare?

Circuit Switching: $10$ Utenti;

Packet Switching: $35$ Utenti --> La probabilità che 10 utenti lavorano contemporaneamente è di $0.04$%;

Si potrebbe quindi pensare di usare il packet switching in modo tale da avere molti utenti a discapito della perdità di pochi pacchetti (**Guadagno statistico del multiplexing del Packet Switching).**

Il packet switching è il vincitore, anche la telefonia moderna lo usa. E' comodo perché è perfetto per *dati bursty*, cioè per dati mandati in maniera occasionale, le risorse possono essere condivise ed è molto semplice da installare.
Il problema principale riguarda la possibilità di **congestione**, cioè la perdita di pacchetti dovuta ad un overflow del buffer di memoria del router, ma molti protocolli moderni prendono in considerazione questa situazione, trovando delle soluzioni al problema.

> In alcune situazioni è possibile creare delle reti che commutano tramite packet switching, ma usando un comportamento da circuit switching, ma è molto complesso.

---
**Struttura di Internet: Una rete di reti**

Gli host che si connettono ad Internet accedono tramite degli **Internet Service Provider (ISP)**, questi devono essere connessi in modo tale che due host possano comunicare.
Esistono diversi modi in cui possiamo connetterli:

- Possiamo connettere ogni *access ISP* fra di loro, ma è una soluzione troppo complicata: $O(n^2)$ connessioni, troppo difficile da scalare.

- Possiamo connettere ogni *access ISP* ad un *ISP di transito*, è come se avessimo una sottorete di ISP che ci permetto di connetterci a tutti gli ISP, può funzionare per reti più piccole e non globali, inoltre significa che ci dovrebbe essere un solo ISP.

- Siccome ci sono tanti ISP avremo tante sottoreti, queste comunicano tra di loro tramite degli **IXP (Internet Exchange Point)**, quando due sottoreti sono connesse direttamente allora avremo un **peering link.**

- Possiamo anche introddure degli ISP regionali che si pongono fra le reti d'accesso e gli ISP di transito, inoltre anche aziende di come google possono avere le loro reti globali.

![Immagine non trovata](Immagini/Internet.png)

Al centro dell'Internet avviamo delle reti molto grandi ben connesse, chiamate anche **Tier 1 commercial ISP** ed hanno una copertura a livello nazionale e internazionale. Allontanandoci abbiamo gli **IXP**, gli **ISP regionali** e poi le **reti d'accesso**.
In tutto questo ci sono anche i **contenti provider networks**, cioè reti privatre che connettono i loro data center ad'internet bypassando ISP regionali e di tier 1.

![Immagine non trovata](Immagini/Rete.png)

>**POP (Point Of Presence)**: è un **punto di accesso fisico** che un Internet Service Provider (ISP) utilizza per collegare i suoi utenti alla rete.
