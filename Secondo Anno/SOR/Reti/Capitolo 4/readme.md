# Capitolo 4 - Livello di Rete (Piano dei dati)

Ci stiamo avvicinando al cuore della rete - questo livello è implementato in ogni dispositivi connesso all'Internet.
Dividiamo lo studio di questa parte in due parti:

- **Piano dei dati**: Funzione _locale_, a livello di singolo router. Determina come i pacchetti in arrivo a una porta di ingresso del router sono inoltrati verso una porta di uscita del router.

- **Piano di controllo**: Rappresenta la _logica della rete_. Determina come i pacchetti sono instradati tra i router lungo un percorso dall'host di origine all'host di destinazione. Ci sono due approcci fondamentali per il piano di controllo.

Il livello di rete trasporta i segmenti dall'host mittente a quello destinatario:

- **Mittente** - Incapsula i dati cha passa al livello di collegamento.
- **Destinatario** - Scompone i datagrammi e passa i segmenti al protocollo di trasporto.

I principali dispositivi presenti a questo livello sono i **router** che hanno il compito di ricevere i datagrammi da un collegamento di input e quindi indirizzarlo nel collegamento di output corretto.

Sono due le domande che ci possiamo porre sin dall'inizio:

- Come fanno i router a decidere il **collegamento di output** giusto (Problema locale).

- Come fanno tutti i router ha **coordinarsi** (Problema globale).

> Ricorda parliamo di problemi locali quando si tratta di decisioni prese al livello del singolo router, sono globali quando ci riferiamo ad una comunicazione end-to-end o su una rete molto ampia.

Le funzioni principali di questo livello sono:

- **Inoltro (Forwarding):** Implementato nell'hardware, consiste nel muovere i pacchetti dal cavo di input a quello di output.

- **Instradamento (Routing):** Determina il percorso che i pacchetti devono seguire tramite degli **algoritmi di routing**.

> Il forwarding viene svolto a livello locale e viene svolto su scala temporale molto piccola (nanosecondi), il routing è invece un processo globale è implementato nel software perché avviene su scale temporali più grandi (secondi).

Il piano di controllo può essere implementato tramite due approcci diversi:

- **Algoritmi di routing tradizionali** implementati nei router.

- **Software-Defined Networking (SDN)** implementato nei server.

All’interno di ogni router c’è una **local forwarding table**, che associa **intervalli di indirizzi IP di destinazione** (detti _prefissi_) alle **interfacce di uscita**. Il router guarda l’indirizzo IP di destinazione nell’header del pacchetto, trova il **prefisso più specifico corrispondente**, e inoltra il pacchetto sull’interfaccia indicata.

> Inizialmente queste tabelle erano configurate a mano. Oggi sono computate.

Il modo in cui le tabelle vengono **computate** definisce il modo in cui il piano di controllo è implementato.

- Nel metodo tradizionale abbiamo che l'algoritmo di instradamento determina i valori inseriti nelle tabelle. Questo algoritmo è implementato in ogni router, quindi questi svolgono sia le funzioni di inoltro che di instradamento.

- Nel nuovo metodo abbiamo un controller remoto che permette di calcolare le tabelle di inoltro, quindi abbiamo che il piano di controllo è gestito da un altro dispositivo. La funzionalità di instradamento è separata dall router che svolge solo l'inoltro.

Le caratteristiche principali che dovrebbero definire il "canale" di questo livello sono le seguenti:

- **Consegna garantita:** Il pacchetto arriverà a destinazione.

- **Consegna garantita con ritardo limitato:** Il pacchetto arriverà a destinazione con un ritardo minore di quantità prestabilità (40ms).

- **Consegna ordinata dei pacchetti.**

- **Banda minima garantita.**

- **Servizi di sicurezza.**

Il livello di rete mette a disposizione solo il tipo di servizio **best-effort** (*massimo impegno*). Ossia non abbiamo garanzia che i pacchetti vengano ricevuti nell'ordine in cui sono stai inviati, non è garantita nemmeno la consegna, essenzialmente non abbiamo garanzia su nulla di quello che abbiamo menzionato prima.

> Il motivo principale per cui viene usato queso modello di servizio è per la semplicità del meccanismo. Questa semplicità ha permesso l'ampia diffusione di Internet. Inoltre tutte le caratteristiche elencate prima possono essere implementate a livelli superiori.

---
### Dentro al router - Parte 1

Le componenti principali di un router sono:

- **Porte di ingresso e di uscita**: Implementano il livello fisico e di collegamento. Il numero di porte può variare da poche a molte. I pacchetti si muovono dalle porte di ingresso a quelle di uscita tramite una **struttura di commutazione.** 

> Ogni router ha solitamente un processore di routing che computa le tabelle di inoltro e il percorso all'interno della struttura di commutazione.

Quindi le funzione delle porte di ingresso sono svolte da tre livelli diversi:

- *Fisico* - Ossia il medium fisico del mezzo di comunicazione che permette la ricezione dei bit.

- *Collegamento* - I bit vengono assemblati in frame.

- **Rete** - Svolgono le funzioni di **lookup e di forwarding**. Queste operazioni ha un tipo di comportamento definito come 'match plus action'.

> Nel livello di rete si possono creare dei ritardi dovuti a delle code.

L'inoltro può essere di due tipi:

- *Inoltro basato sulla destinazione:* Inoltro basato solo sull'indirizzo IP di destinazione.

- *Inoltro generalizzato:* Inoltro basato su più campi dell'header.

Altre componenti sono:

- **Struttura di commutazione:** Connette fisicamente le porte di ingresso a quelle di uscita è interamente contenuta nel router. Questo è il luogo in cui posso formarsi delle code.

- **Porte di uscita:** Memorizzano i paccheti che vengono dalla struttura di commutazione e li trasmettono sul collegamento in uscita.

- **Processo di instradamento:** Esegue le funzioni del piano di controllo. Nei router tradizionali esegue i protocolli di instradamento, gestisce le tablle di inoltro e le informazioni sui collegamenti attivi. Nei router SDN, il processore di instradamento è responsabile della comunicazione con il controller remoto, in modo da ricevere le occorenze della tabella di inoltro e installarle alle porte di ingresso.

---
##### Inoltro basato sulla destinazione

La prima cosa che dobbiamo pensare quando trattiamo con le tabelle di inoltro è che è impossibile mantenere tutti i possibili indirizzi IP all'interno della tabella.

> Ci sono $2^{32}$ possibili indirizzi IP.

Per questo motivo le voci delle tabella sono raggruppate per range. Tutto funziona bene, ma se volessi che un sottoinsieme di indirizzi IP di uno dei range venisse spedito ad un interfaccia diversa?

- Il primo metodo consiste nel **creare una nuova voce della tabella**, ma esiste un metodo molto più comodo.

- **Longest Prexif Match:** Non lavoriamo con dei range, ma semplicemente con dei prefissi per esempio:  

| Destination Range                         | Link Interface |
| ----------------------------------------- | -------------- |
| 11001000   00010111   00010///   //////// | 0              |
| 11001000   00010111   00010000   //////// | 1              |
| 11001000   00010111   00011///   //////// | 2              |
| Altrimenti                                | 3              |

Quindi ci basta controllare soltanto la **corrispondenza a prefisso più lungo** che combacia con l'indirizzo di destinazione.

Questo tipo di operazione avviene solitamente nell'hardware attraverso le **ternary content addressable memories (TCAM)**. Con una TCAM, un indirizzo IP a 32 bit è passato alla memoria che restituisce il contenuto della tupla nella tabella di inoltro corrispondente a quell’indirizzo in un tempo essenzialmente costante.

---
##### Struttura di commutazione

E' il cuore del router e ha il compito di far passare i pacchetti dalla porta di input a quella di output calcolata tramite il longest prefix match. Una delle caratteristiche più importanti della struttura è il suo:

- **Switching Rate:** Ossia il tasso al quale i pacchetti vengono trasferiti. Questo è misurato solitamente come multiplo del tasso di trasmissione delle linee. Se è composto da $N$ input allora vogliamo un tasso di trasferimento pari ad $N$ volte quello del tasso di trasmissione.

Ci sono tre approcci principali per la costruzione di una struttura di commutazione:

- **Commutazione in memoria.**

	I primi router erano essenzialmente dei computer tradizionali. La commutazione tra porte di ingresso e uscita era dettata dalla CPU. Entrambe le porte funzionavano come dei normali dispositivi di I/O. All'arrivo del pacchetto la porta d'ingresso lo segnalava tramite interrupt e quindi veniva copiato nella memoria del processore di instradamento che procedeva a estrarre dall'intestazione l'indirizzo di destinazione. Quindi, individuava tramite la tabella di inoltro l’appropriata porta di uscita nel cui buffer copiava il pacchetto.

- **Commutazione tramite bus.**

	Funziona alla stessa maniera di prima, ma saltiamo lo step intermedio del salvare il pacchetto nella memoria. In questo caso la larghezza di banda della commutazione sarà data soltanto dalla larghezza di banda del bus.

> All'interno di un bus si può traferire soltanto un pacchetto alla volta.

- **Attraverso rete di interconnessione.**

	Possiamo usare delle reti di interconnessione per superare il problema della limitazione di banda dei bus. Possiamo usare una **matrice di commutazione**, ossia una rete di interconnessione composta da $2n$ bus è $n$ porte di ingresso e $n$ porte di uscita.
	
	Quando un pacchetto giunge alla porta $A$ e deve essere inoltrato alla porta $Y$, viene chiuso l'incrocio tra le due porte. La porta $A$ quindi invia il pacchetto sul suo bus, questo sarà ricevuto solo dal bus di $Y$. Notiamo come pacchetti diversi usano dei bus diversi, quindi se una connessione è chiusa, NON significa che non posso inviare altri pacchetti su altre connessioni.
	
	Le matrici di commutazione sono **non-blocking**, ossia un pacchetto in via di inoltro non verrà mai bloccatto a meno che non ci sia un altro pacchetto in via di inoltro sulla stessa porta di uscita.

> Questo tipo di commutazione sfrutta il parralelismo perche frammenta il datagramma in celle di lunghezza fissa all'ingresso, commuta le celle attraverso la rete di commutazione, per poi riassemblare il datagramma in uscita.

---
### Dentro al router - Parte 2

Come detto precedentemente ci sta la possibilità che si formino delle **code** se il tasso di trasmissione dei pacchetti nella struttura di commutazione è più lento rispetto alle porte di ingresso e di uscita.

---
##### Code nell'input

Se due pacchetti in ingresso sono destinati alla stessa porta di uscita, allora uno dei pacchetti sarà bloccato è dovrà attendere. Questo problema è chiamato **HOL (Head-Of-The-Line blocking).

---
##### Code nell'output

E' utile mantenere dei **buffer** per i datagrammi che arrivano dalla struttura di comunicazione. Il problema nasce nel momento in cui la struttura di commutazione è più veloce rispetto alla velocità di trasmissione del cavo di uscita. Se ciò dovesse accadere allora avremmo dei buffer **sempre pieni** con conseguente perdita di pacchetti.

> E' inutile rallentare la struttura di commutazione. Perché il problema nasce dal fatto che ci sono troppi utenti che mandano troppi dati troppo velocemente.

Per questo motivo dobbiamo implementare una **Drop policy** che decide quali pacchetti  perdere. Tramite uno **schedulatore di pacchetti** stabiliamo in quale ordine trasmettere i pacchetti. 

---
##### Buffering

I buffer sono una parte importantissima delle reti (e di molti altri ambiti) quindi potrebbe tornare utile sapere quanto spazio di buffer abbiamo bisogno.

> Non esiste una quantità ideale, ma ci sono delle linee guida che si possono usare.

Abbiamo due scuole di pensiero:

- **Tradizionale:** $$B = RTT*C$$
La grandezza del buffer $B$ deve essere uguale al tempo di round-trip medio $RTT$ per la capacità del mezzo di comunicazione $C$.

- **Moderna:** $$\frac{RTT*C}{\sqrt{n}}$$
$N$ rappresenta il numero di flussi TCP.

Avere più buffer non è sempre la scelta ideale. E' vero che **si perderebbero molti meno pacchetti**, ma ci sta il potenziale **rischio di ritardi di coda più lunghi.**

In assenza di memoria nel buffer bisogna stabilire se scartare il pacchetto appena arrivato (politica **drop-tail**) oppure rimuoverne uno da quelli che stanno nel buffer tramite dei meccanismi di **priorità**.

Possiamo anche **marcare** alcuni pacchetti che arrivano in modo tale da segnalare la presenza di congestione al mittente. (ENC, RED).

---
### Politiche di scheduling dei pacchetti

##### First-In-First-Out (FIFO):

- **Descrizione:** I pacchetti vengono inseriti in una **singola coda** e trasmessi **nell'ordine di arrivo**.

- **Comportamento:** Se il link è occupato, i pacchetti aspettano. Se il buffer è pieno, si applica una **drop policy** (tipicamente Tail Drop).

- **Pro:** Semplice e facile da implementare.

- **Contro:** Nessuna distinzione tra tipi di traffico → può causare ritardi per pacchetti urgenti o interattivi.

---
##### Priority Queuing (PQ)

- **Descrizione:** I pacchetti sono classificati in **code separate** in base alla priorità (es. VoIP > dati > best-effort).

- **Meccanismo:** Viene sempre servita **la coda con la priorità più alta**, se non è vuota.

- **All'interno di ogni coda:** si applica FIFO.

- **Pro:** Ideale per dare priorità a traffico sensibile alla latenza.

- **Contro:** Le code a bassa priorità possono **soffrire di starvation** (mai servite).

---
##### Round Robin (RR)

- **Descrizione:** I pacchetti sono ancora suddivisi in classi, ma invece di dare priorità fissa, si serve ogni coda **a turno**.

- **Meccanismo:** Il sistema cicla tra le code, inviando **un pacchetto per ogni coda** ad ogni giro.

- **Pro:** Equo tra le classi, evita starvation.

- **Contro:** Se i pacchetti hanno lunghezze molto diverse, può risultare **inefficiente** (le code con pacchetti lunghi consumano più banda).

---
#####  Weighted Fair Queuing (WFQ)

- **Descrizione:** Estende il Round Robin assegnando a ciascuna coda un **peso** $w_i$, che determina **quanta banda riceve** in proporzione.

- **Meccanismo:** In ogni ciclo di servizio, la coda `i` riceve una frazione di servizio pari a: $$\frac{w_i}{\Sigma_j*w_j}​​$$
- **Pro:** Garantisce **equità ponderata** tra flussi, adatta a reti complesse (es. MPLS, QoS avanzato).

- **Contro:** **Più complesso** da implementare rispetto a FIFO o RR.

---
### Internet Protocol (IP) - Parte 1

Il protocollo IP è quella parte del livello di rete che ha a che fare con il formato dei datagrami e il modo in cui questi vengono indirizzati.

Guardiamo nel dettaglio il formato dei datagrammi IP, questo contiene:

- **Numero di versione:** Quattro bit che specificano la versione del protocollo.

- **Lunghezza dell'intestazione:** Quattro bit che indicano dove iniziano i dati nel datagramma.

- **Tipo di servizio:** Bit relativi al tipo di servizio usato. (Servizio ECN per i bit 6 e 7, diffserv da 0 a 5).

- **Lunghezza del datagramma:** Sedici bit che rappresentano la lunghezza totale del datagramma IP. (Lunghezza massima = 65535 byte, Lunghezza media = 1500 byte).

- **Identificatore, flag, offset di frammentazione:** Tre campi usati per la frammentazione. Un datagramma IP grande viene frammentato in daragrammi più piccoli che vengono inoltrati in modo indipendente e poi riassemblati insieme al livello di trasporto.

- **Tempo di vita:** Incluso per assicurare che i datagrammi non restino troppo in circolazione nella rete. Il campo è decrementato per ogni router che elabora il datagramma.

- **Protocollo:** Indica il protocollo usato dal livello di trasporto (6 = UDP, 17 = TCP).

- **Checksum dell'intestazione:** Consente ai router di rilevare errori sui bit dei datagrammi ricevuti. È calcolato trattando ogni coppia di byte dell’intestazione come numeri che sono poi sommati in complemento a 1.

- **Indirizzi IP sorgente e destinazione:** Quando l'host crea il datagramma inserisce il proprio indirizzo IP e quello del destinatario negli effettivi campi (16 bit).

- **Opzioni:** Altre opzioni da inserire nel datagramma IP.

- **Dati:** Contiene il segmento a livello di trasporto da consegnare a destinazione. Può trasportare anche altri tipi di messaggi come ICMP.

---
##### Indirizzamento

Un indirizzo IPv4 è un identificatore a 32 bit assegnato a un'**interfaccia di rete** su un host (o router). Un'interfaccia è un punto di connessione tra un dispositivo (host o router) e il mezzo fisico o logico di comunicazione di rete (es. scheda hardware che si collega con Ethernet).

> I router hanno solitamente diverse interfacce, gli host sono solitamente una o due.

> Gli indirizzi IP sono scritti nella notazione decimale-puntata (Dotted-decimal notation).

La lunghezza è di 32 bit, ci sono in totale $2^{32}$ indirizzi IP, ossia circa 4 milioni.

---
##### Sottoreti

Gli indirizzi IP non vengono scelti in maniera arbitraria, una parte dell'indirizzo è determinata dalla sottorete a cui l'interfaccia è collegata.

Una **sottorete** è un insieme di dispositivi che possono raggiungersi fisicamente senza passare attraverso un router intermedio.

Un indirizzo IP è composto da due parti:

- **Parte della sottorete:** Dispositivi nella stessa sottorete hanno i bit di ordine maggiori in comune.

- **Parte dell'host:** Composta da bit di ordine minore.

La **subnet mask** indica il numero di bit che host all'interno della stessa sottorete condividono. Per determinare le sottoreri dobbiamo sganciare le interfacce da host e router in modo tale da avere delle zone isolate delimitate dalle interfacce.

Per indicare la subnet mask usiamo la seguente notazione: $$a.b.c.d/Numero\;bit\;subnet$$Questa si chiama **CIDR**, ossia **Classless InterDomain Routing** (Pronunciata "cider"). Qusta generalizza la nozione di indirizzamento di sottorete. gli $x$ bit più a sinistra costituiscono la porzione di rete dell'indirizzo IP, sono spesso detti **prefissi** dell'indirizzo.

> Prima dell'adozione di CIDR, le parti di rete di un indirizzo IP dovevano essere lunghe, 8, 16, 24 bit. Tale schema di indirizzamento era noto come **classful addressing**, dato che le sottoreti con indirizzi di sottorete da 8, 16, 24 bit erano note come reti di classe A, B, C.

L'indirizzo IP $255.255.255.255$ è l'indirizzo IP di broadcast. Il messaggio è inviato a tutti.

---
#### DHCP

Quando abbiamo degli indirizzi IP, l'amministratore della rete configura manualmente le interfacce dei router, per quanto riguarda gli host abbiamo due maniere per assegnargli un indirizzo IP:

- L'amministratore di rete configura in maniera hard-coded gli indirizzi della rete e li inserisce in un file di configurazione.

- Tramite **DHCP: Dynamic Host Configuration Protocol** ci permette di ottenere l'indirizzo IP in maniera dinamica da un server. 

> Con il DHCP, l'host può ottenere un indirizzo IP in modo automatico, allo stesso tempo può anche apprendere altre informazioni come: La maschera di sottorete, l'indirizzo di **gateway**, l'indirizzo del suo DNS locale.

Possiamo configurare il DHCP in due maniere diverse:

- Assegna a ciascun host un indirizzo IP **persistente**.
- Assegna a ciascun host un indirizzo IP **temporaneo**.

> Ogni volta che si collega alla rete gli viene dato un IP diverso.

E' un tipo di protocollo **plug-and-play** perché permette di autimatizzare la connessione degli host all'interno della rete.

Il protocollo DHCP è diviso in quattro parti:

1) **Individuazione del server DHCP:** l'host manda un messaggio **DHCP discover** in broadcast con indirizzo sorgente $0.0.0.0$.

> E' un pacchetto UDP sulla porta 67 incapsulato con un datagramma IP.

2) **Offerta server DHCP:** Il server DCHP risponde con un messaggio **DHCP offer** inviato sempre in broadcast. L'host deve decidere se accettare o meno l'offerta.

> Ci possono essere diverse offerte, perché ci sono diversi server DHCP, per questo motivo ogni messaggio di offerta contiene un ID relativo alla transazione, un indirizzo IP proposto al client, la maschera di sottorete e la durata della connessione (**lease time**), ossia il tempo di durata della connessione.

3) **Richiesta DHCP:** Il client sceglie una delle offerte e risponderà con un messaggio **DHCP request** che riporta i parametri di configurazione.

4) **Conferma DHCP:** Il server risponde al messaggio di richiesta con un messaggio **DCHP ACK** che conferma i parametri richiesti.

> In genere, il server DHCP è collocato nel router e serve tutte le sottoretia cui il router è collegato.

Altre informazione che il DCHP ritorna sono:

- Indirizzo del primo router vicino al client.
- Nome e indirizzo del DNS server del client.
- La maschera di sottorete.

Quindi abbiamo appena visto il modo in cui un host riesce a prendere un indirizzo IP dalla rete che glielo offre. Ma come fa la rete ad ottenere gli indirizzi IP che deve può dare ai vari host? 

**la rete riceve una porzione di spazio di indirizzi IP dal suo ISP**. Tuttavia, **all'interno della rete locale** (LAN), si usano spesso **indirizzi privati**, assegnati dal **server DHCP**, mentre la connessione a Internet avviene attraverso un singolo **IP pubblico** fornito dall’ISP e gestito tramite **NAT**.

> Nelle reti domestiche si ha un solo IP pubblico al router e all'interno della rete il router usa DHCP per assegnare IP privati.

> Nelle reti aziendali, ISP può assegnare un blocco di IP pubblici.

---
### Internet Protocol (IP) - Parte 2

##### NAT - Network Address Translation

Un dei problemi principali degli indirizzi IP è la loro scarsità. Per evitare questo problema allora si sono divisi gli indirizzi IP in **privati** e **pubblici**. Quando ci troviamo in una sottorete locale tutti hli host di quella sottorete utilizzeranno degli indirizzi privati. Le interfacce che si collegano con la rete esterna invece useranno un indirizzo pubblico.

> Gli indirizzi privati hanno senso solo per i dispositivi della sottorete. Quindi possono esistere degli indirizzi privati uguali in sottoreti diverse.

Tutti gli host in una sottorete **condividono** un solo indirizzo IPv4 publico per il mondo esterno.

Il **NAT** ci permette di gestire l'indirizzamento dei pacchetti relativi all'Internet globale. I router abilitati al NAT si comportano come unico dispositivo con un unico indirizzo IP.

Se tutti i datagrammi in arrivo al router NAT dalla rete geografica hanno lo stesso indirizzo IP di destinazione (nello specifico, quello dell’interfaccia sul lato WAN del router NAT), allora come apprende il router a quale host interno dovrebbe essere inoltrato un determinato datagramma? Il trucco consiste nell’utilizzare una **tabella di traduzione NAT (NAT translation table)** nel router NAT e nell’includere nelle righe di tale tabella i numeri di porta oltre che gli indirizzi IP.

> Tutti i dispositivi della rete locale hanno indirizzi a 32 bit in uno spazio di indirizzi IP _privato_,(prefissi 10/8, 172.16/12, 192.168/16) che possono essere utilizzati solo nella rete locale.

**Vantaggi del NAT:**

- Necessario un solo indirizzo IP dal ISP per tutti i dispositivi.

- Possiamo cambiare gli indirizzi degli host senza notificare il resto del mondo.

- Possiamo cambiare ISP senza modificare gli indirizzi della rete locale.

- I dispositivi interni alla rete non possono essere direttamente indirizzati - più sicurezza, poiché invisibili al mondo esterno.

Quando al router NAT arriva un datagramma in uscita da un host locale, sostituisce l'indirizzo IP sorgente e la porta con l'indirizzo IP del NAT e una nuova porta. Ricorda in una tabella apposita ogni coppia di traduzione. Quando al router NAT arriva in ingresso un datagramma esterno, sostituisce l'indirizzo IP NAT con l'indirizzo dell'host all'interno della rete locale e la porta giusta. 

> Solitamente si usano delle tabella hash.

---
##### IPv6

La motivazioni principali dal passaggio da IPv4  a IPv6 sono:

- **Scarsità** degli indirizzi IPv4.
- **Intestazione ottimata** di 40 byte a lunghezza fissa.
- Trasmissione audio e video può essere trattata come **flusso**.

> IPv6 usa indirizzi a 128 bit.

Il formato dei datagrammi è il seguente:

- **Versione** - 4 bit che identificano il numero di versione IP.

- **Classe di traffico** - Imposta priorità a datagrammi all'interno di un flusso o di applicazioni specifiche.

- **Etichetta di flusso** - Identifica datagrammi dello stesso flusso.

- **Lunghezza del payload** - 16 bit che identificano la lunghezza del payload.

- **Intestazione successiva** - Identifica il protocollo a cui verrà consegnato il contenuto.

- **Limite di hop** - Campo TTL in IPv4.

- **Indirizzi di sorgente e destinazione**

- **Dati** - Payload.

> Mancano rispetto a IPv4 i campi di: Frammentazione e riassemblaggio, di chekcsum, il campo opzioni.

La transizione da IPv4 a IPv6 è nota come **tunneling**.

L'idea alla base del tunneling è la seguente: Supponiamo che due nodi IPv6 vogliano utilizzare datagrammi IPv6 ma siano connessi da router intermedi IPv4, che chiameremo **tunnel**. Il Nodo B, al lato di invio del tunnel, prende l’intero datagramma IPv6 pervenutogli da A e lo pone nel campo dati di un datagramma IPv4. Quest’ultimo viene quindi indirizzato al Nodo E, al lato di ricezione del tunnel e inviato al primo nodo nel tunnel (C). I router IPv4 intermedi instradano il datagramma IPv4, come farebbero per qualsiasi altro datagramma, ignari che questo contenga un datagramma IPv6 completo. Il nodo IPv6, sul lato di ricezione del tunnel, riceverà quindi il datagramma IPv4, determinerà che questo ne contiene uno IPv6 osservando che il valore del campo numero di protocollo nel pacchetto IPv4 è 41 corrispondente a payload IPv4, lo estrarrà e lo instraderà esattamente come se l’avesse ricevuto da un nodo IPv6 adiacente.

---
### Inoltro generalizzato e SDN

Ha il compito di generalizzare il l'idea di inoltro basato sulla destinazione utilizzando una tabella **match-action**. Siccome le decisioni di inoltro posso essere effettuare usando sia indirizzi del livello di rete che del livello di collegamento, i dispositivi di inoltro vengono chiamati **packed switch**.

> Invece di usare i nomi classici, ossia router e switch.

Ogni voce della tabella match-action, chiamata anche **tabella dei flussi** ha:

- Un insieme di valori dei campi dell'intestazione con i quali il pacchetto entrante e confrontato.

- Un insieme di *contatori* che sono aggiornati quando i pacchetti vengono associati ad una voce della tabella.

- Un insieme di *azioni* che vengono intraprese quando un pacchetto è associato a'un occorrenza della tabelal dei flussi.

Il pacchetto che arriva ad un packet switch è formato da un **frame** a livello di collegamento che al suo interno contiene un **datagramma IP** che a sua volta contiene un **segmento** a livello di trasporto.

Quindi degli undici campi dell'intestazione del pacchetto gli unici che non conosciamo sono:

- **MAC address**, ossia l'indirizzo a livello di collegamento della sorgente e del destinatario associati alle interfacce di invio e ricezione del frame.

- **Ethernet Type**, ossia il protocollo del livello sovrastante.

- **VLAN**, ossia campi corrispondenti a reti virtuali locali.

Alcune delle azioni più importanti che possono essere intraprese sono:

- **Inoltro** - Ad una determinata porta di uscita, in broadcast o in multicast ad un insieme di porte.

- **Scarto** - Una voce della tabella dei flussi senza azioni indica che il pacchetto deve essere scartato.

- **Modifica dei campi**

> Possono essere modificati tutti i campi tranne "Protocollo IP".

Questo tipo di inoltro riesce ad astrarre diversi tipi di dispositivi:

| Device   | Match                                  | Action                             |
| -------- | -------------------------------------- | ---------------------------------- |
| Router   | Prefisso IP di destinazione più lungo  | Inoltro attraverso un collegamento |
| Firewall | Indirizzi IP e numeri di porta TCP/UDP | Consentire o negare l'accesso      |
| Switch   | Indirizzo MAC di destinazione          | Inoltra o inonda                   |
| NAT      | Indirizzo IP e porta                   | Riscrive l'indirizzo e la porta    |

---
### Dispositivi Middlebox

E' un qualsiasi box intermedio che svolge funzioni diverse da quelle standard di un router IP sul percorso dei dati tra host di origine e destinazione.

Ci sono tre principali tipi di servizi middlebox:

- **Traduzione NAT** - I box NAT implementano un indirizzamento di rete privato, la riscrittura degli indirizzi IP e dei numeri di porta dell'intestazione del datagramma.

- **Servizi di sicurezza** - I firewall bloccano il traffico in base ai valori del campo di intestazione o reindirizzano i pacchetti per ulteriori elaborazioni.

- **Miglioramento delle prestazioni** - Eseguono servizi come l acompressione, i caching dei contenuti e il bilanciamento del carico delle richieste di servizio.