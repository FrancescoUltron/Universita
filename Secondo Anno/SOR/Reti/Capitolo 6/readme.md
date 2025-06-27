# Capitolo 6 - Livello di collegamento

### Introduzione al livello di collegamento

Un qualunque dispositivo a livello di collegamento è un **nodo** - mentre i canali di comunicazione sono detti **collegamenti**. Su ogni collegamento - un nodo trasmittente incapsula il datagramma in un **frame del livello di collegamento** e lo trasmette sul collegamento.

il servizio di base del livello di collegamento è il trasporto di datagrammi da un nodo a quello adiacente lungo un singolo canale di comunicazione - ma altri servizi forniti possono essere:

- **Framing:** Incapsulamento dei datagrammi del livello di rete in un **frame**. La struttura del frame è specificata dal protocollo.

- **Accesso al collegamento:** Un **protocollo MAC** (*medium access control*) serve a regolare l'accesso al canale di comunicazione - cioè decide quando e come un nodo può inviare un frame sul mezzo trasmissivo

- **Consegna affidabile:** Alcuni protocolli a livello di collegamento garantiscono il trasporto senza errori di ciascun pacchetto. Questo servizio è spesso usato per i collegamenti soggetti a elevati tassi di errore.

> Non sempre la consegna affidabile è necessaria - per esempio non lo è nei collegamenti che presentano un basso numero di errori sui bit.

- **Rilevazione e correzione degli errori:** Gli errori sui bit sono causati dall'attenuazione del segnale e da disturbi elettromagentici. Molti protocolli forniscono un meccanismo per rilevarne la presenza tramite un **bit di controllo.**

> Il rilevamento degli errori è più sofisticato rispetto a quelli del livello di trasporto, perché implementato nell'hardware.

Il livello di collegamento è implementato nelle *line card* dei router.

Per un dato collegamento, il protocollo del livello di collegamento è realizzato da un **adattatore di rete** noto come **scheda di rete** (*nic - network interface controller*). Il cuore della scheda è il **controller** a *livello di collegamento* che implementa molti servizi a livello di collegamento.

> Maggior parte delle funzionalità del controller è implementata nell'hardware.

Il controller - lato mittente - prende un **datagramma** creato e memorizzato nella memoria dell'host dai livelli più alti della pila - lo **incapsula** in un frame riempiendo le intestazioni - e lo **trasmette** sul canale di di comunicazione tramite il protocollo di accesso al canale.

Dal lato destinatario - un controller riceve il frame per intero - estrae il datagramma e lo **consegna** a livello di rete.

> Se il protocollo del livello di collegamento fornisce servizi di controllo sui bit allora - il controller trasmittente imposterà i bit di rilevazione degli errori - mentre quello destinatario eseguirà i controlli.

Nonostante la maggior parte del livello di collegamento è implementato in hardware sulla scheda di rete - una parte è realizzata in un software e viene eseguita dalla CPU dell'host.
Le componenti software implementano funzionalità del livello più alto come: *Assemblaggio delle informazioni di indirizzamento e l'attivazione dell'hardware del controller*. Dal lato ricevente invece queste rispondono agli interrupt del controller ed effettuano la gestione delle condizioni di errore, nonché il passaggio dei datagrammi fino al livello di rete.

> Nella pila protocollare il livello di collegamento è il luogo in cui l'hardware incontra il software.

---
### Tecniche di rilevazione e correzione degli errori

Uno dei servizi principali che fornisce il livello di collegamento è quello del **rilevamento e correzzione degli errori sui bit**.

> Sono dei servizi forniti pure al livello di trasporto.

AI dati che vengono trasmessi sul collegamento soggetto ad errori vengono aggiunti degli *EDC - error-detection and-correction*. Questi vengono passati al nodo destinatario, insieme ad altri dati, che avrà il compito di verificare che i dati che gli sono arrivati sono gli stessi di quelli inviati.

Ci sono tra tecniche principali per rilevare gli errori nei dati trasmessi: **controllo di parità**, **Tecniche di checksum** e **controllo di ridondanza ciclica**. 

> Checksum usate anche nel livello di trasporto.

---
##### Controllo di parità

Tecnica più semplice di rilevazione di errori. Utilizza un unico bit. Supponiamo che l'informazioni da inviare siano costituite da $d$ bit, allor aggiungiamo un bit (**bit di parità**) e trasmettiamo quindi $d+1$ bit.

Il valore del bit deve essere tale che il numero di $1$ in $d$ sia pari nel controllo di parità pari, mentre dispari nel controllo di disparità. Il ricevente deve controllare il numero di bit ricevuti, se trova un numero dispari di bit $1$ sa che si è verificato almeno un errore in un bit.

> Sappiamo che sono verificati un numero di errori dispari.

Un grave problema nasce nel momento in cui avvengono un numero **pari** di errori, infatti in questo caso non possiamo rilevarli.

> Questa cosa va bene solo se la probabilità di errore è bassa e se gli errori siano indipendenti.

Possiamo migliorare l'idea con uno schema di **parità bidimensionale**. I $d$ bit sono suddivisi in $i$ righe e $j$ colonne per ognuna delle quali è calcolato un valore di parità. Se si verifica un errore possiamo usare gli indici della colonna e della riga per identificare e correggere il bit errato.

> Questo schema può rilevare, ma non correggere qualsiasi combinazione di due errori in un pacchetto.

La capacità del ricevente sia di rilevare sia di correggere gli errori è conosciuta come **forward error correction **(FEC, correzione degli errori in avanti).

> Queste tecniche sono usate principalmente in dispositivi di registrazione e riproduzione.

---
##### Chekcsum

i $d$ bit sono trattati come una sequenza di numeri interi da $k$ bit. Si sommano questi interdi da $k$ bit e si usano i bit del risultato come bit per la rilevazione degli errori.
Il **checksum di Internet** funziona in questo modo:

- I dati sono trattati come interi di $16$ bit e sommati.

- Il complementeo ad uno della somma costituisce il checksum di Internet trasposto nell'itnestazione dei segmenti.

Il ricevente controlla il checksum calcolando il complemento ad uno della somma dei dati ricevuti compreso il checksum e verifica che tutti i bit del risultato siano $0$.

---
##### Controllo a ridondanza ciclica (CRC)

Tecnica molto usata basata sui **codici di controllo a ridondanza ciclica (CRC)**, questi sono anche chiamati **codici polinomiali.**

Consideriamo i $d$ bit da trasmette e supponiamo che sorgente e destinazione si siano accordati su una stringa di $r+1$ bit conosciuta come **generatore.**

> E' necessario che il bit più significativo di $G$ sia $1$.

Il mittente deve scegliere $r$ bit addizionali da aggiungere ai $d$ bit iniziali in modo da ottenere una stringa $d+r$ bit che interpretata come binario sia divisibile per $G$.

Se la divizione $\frac{d+r}{G}$ ha un resto diverso da zero allora si è verificato un errore. I calcoli del CRC sono eseguiti in aritmetica modulo $2$ senza riporti su addizioni e sottrazioni. Quindi addizione e sottrazione sono equivalenti a OR e XOR.

---
### Collegamenti e protocolli di accesso multiplo

Esistono due tipi di collegamento di rete: **punto a punto** e **broadcast**. Il primo è costituito da una trasmittente da un'estremità del collegamento e da un unico ricevente all'altra. Molti protocolli di questo livello sono stati ideati per questo tipo di collegamento, per esempio: 

- **PPP** - *Point-to-Point Protocol*
- **HDLC** - *High-level Data Link Control*

Il collegamento broadcast ha invece più nodi trasmittenti e riceventi connessi allo stesso canale broadcast condiviso. Il problema principale dei canali di broadcast è quello dell'**accesso multiplo**. Ossia il modo in cui dobbiamo coordinare più nodi trasmittenti e riceventi.

> I nodi su un canale broadcast possono sia ricevere che trasmettere.

Come per il primo tipo, esistono dei protocolli che fissano le modalità con cui i nodi regolano le trasmissioni su un canale condiviso. Ossia i **protocolli di accesso multiplo.**

> Questi protocolli sono usati in reti LAN cablate, wireless e reti satellitari.

Se due o più nodi trasmettono frame sullo stesso canale potrebbe generarsi una **collisione**. A causa di questa nessuno dei nodi ricevitori riuscirà ad interpretare i frame, quindi avremo una perdita di frame mentre il canale rimane inutilizzato. Il coordinamento dell'invio dei frame spetta ai protocolli di accesso multiplo.

Possiamo classificare i protocolli ad accesso multiplo in:

- **Protocolli a suddivisione del canale** (*channel partiotioning protocol).
- **Protocolli ad accesso casuale** (*random access protocol*).
- **Protocolli a rotazione** (*taking-turn protocol*).

In generale un protocollo di accesso multiplo per una canale broadcast con velocità $R$ bit al secondo deve avere le seguenti caratteristiche: 

1) Quando un solo nodo deve inviare dati, questo dispone di un throughput pari a $R$ bps.

2) Quando ci sono $M$ nodi, questi dispongono di un throughput pari a $M/R$ bps.

3) Il protocollo deve essere **decentralizzato**.

4) Il protocollo deve essere **semplice**.

---
##### Protocolli a suddivisione del canale

Questi protocolli si basano sul multiplexing a **divisione di tempo** e quelli a **divisione di frequenza**. Questi suddividono la larghezza di banda di un canale broadcast fra i nodi che lo condividono.

IL **TDM** suddivide il tempo in **intervalli di tempo** e poi suddivide ciascun intervallo di tempo in $N$ slot temporali. Ogni slot è assegnato a uno degli $N$ nodi e un nodo può trasmettere bit del pacchetto solo durante lo slot di tempo assegnatogli.

I vantaggi del TDM è che riesce ad evitare collisioni ed è perfettamento imparziale, ma ci sono due incovenienze:

- Quando un nodo vuole trasmettere è vincolato ad attendere il suo turno anche se altri nodi sono inattivi.

- Quando tutti i nodi vogliono ricevere e solo uno trasmette si creano delle situazioni sconvenienti.

Il **FDM** divide il canale in frequenze diverse e assegna ciascuna frequenza a un nodo. Quindi partentdo da un canale di $R$ bps, FDM crea $N$ canali di $R/N$ bps. FDM evita le collisioni e divide equamente la larghezza di banda, ma pure in questo caso la larghezza di banda per ogni nodo è limitata a $R/N$ bps anche se ci sta un unico nodo trasmittente.

Un terzo tipo di protocollo di suddivisione del canale è **l'accesso multiplo a divisione di codice** (*CDMA - Code Division Multiple Access*). IL CDMA assegna ai nodi un **codice**. CIascun nodo usa il codice univoco per codificare i dati inviati. Se i codici sono scelti correttamente allora consentiranno a nodi differenti di trasmettere simultaneamente e ai rispettivi destinatari di ricevere correttamente i bit dei dati codificati (assumendo che il ricevente conosca il codice) nonostante le interferenze derivanti dalle trasmissioni degli altri nodi.

---
##### Protocolli ad accesso casuale

I nodi trasmettono i pacchetti sempre alla velocità massima consentità dal canale. Se si verifica una collisione allora i nodi coinvolti ritrasmettono anche più volte i loro frame fino a quando non raggiungono destinazione. La ritrasmissione non è immediata, infatti i nodi attendono un periodo di tempo casuale. Ogni nodo ha un ritardo casuale indipendente dagli altri.

Uno tra i protocolli più semplici è lo **slotted ALOHA** che opera nel seguente modo:

- Quando un nodo ha un frame da spedire attende fino all'inizio dello slot successivo e poi trasmette l'intero frame.

- Se non si verifica una collisione, l'operazione ha avuto successo, quindi niente ritrasmissione e il nodo si prepara per l'invio di un nuovo frame.

- Se si verifica una collisione allora il nodo la rileva prima del termine dello slot e ritrasmette con probabilità $p$ il suo frame durante gli slot successivi fino a quando l'operazione non ha successo.

Questo protocollo risolve il problema di un singolo nodo che trasmette i dati. Inoltre ogni nodo può trasmette fino al massimo della capacità del canale. E' anche fortemente **decentralizzato**, infatti le operazioni eseguite da un nodo sono indipendenti dagli altri.

> Gli slot sono sicronizzati ai nodi.

Il problema principale di questo protocollo è che molti degli slot vengono sprecati, gli unici che saranno 'validi' sono quelli utilizzati da un unico nodo.

> Questo tipo di slot è detto **slot riuscito**.

L’efficienza di un protocollo di accesso multiplo che fa uso di slot temporali è definita come la frazione di slot riusciti in presenza di un elevato numero di nodi attivi che hanno sempre un elevato numero di pacchetti da spedire.

Il primo protocollo **ALOHA** era completamente decentralizzato ed era privo di slot. Appena arrivava un frame, il nodo lo trasmetteva immediatamente e integralmente nel canale broadcast. Se un frame va in collisione allora il nodo lo ritrasmette immediatamente con probabilità $p$. Altrimenti, attenderà il tempo di trasmissione del frame. Trascorso questo tempo, il nodo ritrasmetterà il frame con probabilità $p$ o aspetterà, restando inattivo per un altro periodo di tempo, con probabilità $1 – p$.

---
##### CSMA: accesso multiplo con rilevamento della portante

Nei protocolli ALOHA i nodi sono indipendenti e quindi non prestano attenzione al fatto che vi siano altri nodi che stano trasmettendo. Quindi alla base del protocollo **CSMA** e **CSMA/CD** abbiamo due regole che permettono ai nodi di coordinarsi:

- *Ascoltare prima di parlare*. Un nodo ascolta il canale prima di trasmettere, il nodo aspetta finché il canale è libero per un intervallo di tempo e quindi trasmette.

> Questo comportamento si chiama **rilevamento della portante.**

- *Se qualcun altro comincia a parlare insieme a voi, smettete di parlare.* Il nodo che sta trasmettendo rimane contemporaneamente in ascolto del canale. Se si accorge che un altro nodo sta trasmettendo un frame che interferisce con il suo allora e aspetta un intervallo di tempo casuale per ricominciare. 

> Questo comportamtento si chiama **rilevamento della collisione.** Questa parte qui è presente soltanto nel protocollo CSMA/CD.

L'idea di avere intervalli di tempo casuale è abbastanza ovvia (se due nodi trasmettono lo stesso frame e hanno lo stesso tempo di attesa allora rimarrano in uno stato di collisione perenne). Ma quale è l'intervallo di tempo (**tempo di backoff**) opportuno da scegliere?

Occorrerebbe un **i*ntervallo di tempo piccolo, quando il numero di nodi in collisione è piccolo**, e uno **grande quando il numero di nodi è grande.**


| Numero di nodi che collidono | Intervallo |                                    Risultato                                    |
| :--------------------------: | :--------: | :-----------------------------------------------------------------------------: |
|            grande            |  piccolo   |          Valori scelti saranno simili e i nodi torneranno a collidere           |
|           piccolo            |   grande   | I nodi aspetterano per un lungo intervallo di tempo prima di ripetere il ciclo. |
> Per la scelta dell'intervallo usiamo l'algoritmo di **attesa binaria esponenziale.**

---
##### Protocolli a rotazione

I protocolli del paragrafo sopra soddisfano la prima, ma non la seconda proprietà auspicabili per un protocollo di accesso multiplo. Quindi è stata creata un'altra classe di protocolli, i **protocolli a rotazione.**

> Esistono molti protocolli, noi ne vediamo due.

Il primo è il **protocollo polling** nel quale abbiamo un nodo principale che coordina gli altri nodi. comunicandogli che possono trasmettere fino a un dato numero massimo di frame. Il nodo principale può determinare che un nodo ha terminato di inviare i propri frame, osservando la mancanza di segnale sul canale. La procedura prosegue in questo modo, con il nodo principale che interpella in modo ciclico tutti gli altri nodi.

Questo protocollo elimina le collisioni e gli slot vuoti, ma presenta uno svantaggio a causa dell'introduzione del **ritardo di polling**, ossia il tempo che il nodo principale impiega per notificare a un nodo che gli è permesso il trasferimento. Inoltre se il nodo principale si guasta allora l'intero protocollo diventa inutilizzabile.

Il secondo protocollo è quello di **token-passing**, in questo caso non abbiamo un nodo principale, ma un frame che svolge la funzione di messaggio di controllo (**token**) che circola fra i nodi seguendo un ordine prefissato.

> Se il nodo che riceve il token non ha pacchetti da inviare, lo inoltra immediatamente al successivo. Altrimenti, procede a trasmettere il numero massimo di frame consentito, prima di inoltrare il token al nodo successivo

E' decentralizzato ed efficiente, ma non privo di errori, se un nodo si guasta o se è impossibilitato nell'inviare il token, allora sono richieste delle procedure di recupero per il token.

---
### Reti locali commutate

Ci rimane solo lo studio delle *switched local area network*. I principali dispositivi che usiamo a questo livello sono gli **switch.**

> Gli switch commutano frame a livello di collegamento, non leggono indirizzi a livello di rete e non usano algoritmi di instradamento.

Ogni host oltre all'indirizzo a livello di rete, ha anche un indirizzo a livello di collegamento, il protocollo che fornisce una mappatura di indirizzi IP in indirizzi a livello di collegamento è detto **ARP (Address Resolution Protocol).**

Non sono i nodi ad avere indirizzi a livello di collegamento, bensì le loro interfacce, quindi un host può avere più indirizzi di collegamento. Inoltre le interfacce degli switch **non hanno indirizzi a livello di collegamento.**

Chiamiamo questi indirizzi come: **Indirizzi MAC**, questo è lungo 6 byte per un totale di $2^{48}$ possibili indirizzi. Sono generalmente espressi in esadecimale, scrivendo due cifre per ogni byte.

> E' possibile modificarli con dei software.

Non esistono schede di rete con indirizzi uguali, perché la IEEE sovrintende la gestione di questi indirizzi. L'indirizzo MAC non ha una struttura **gerarchica** quindi rimane sempre lo stesso ovunque.

Quando una scheda di rete vuole spedire un frame, vi inserisce l’indirizzo MAC di destinazione e lo immette nella LAN. Soltanto le schede di rete dei nodi destinatari generano degli interrupt ai propri nodi quando ricevono un frame.

> Se una scheda di rete vuole che tutte le altre schede di rete processino il frame allora deve usare uno speciale **indirizzo MAC broadcast.**

Il compito della mappatura tra indirizzi IP e MAC è affidato al protocollo ARP. Un modulo ARP nel nodo sorgente riceve in input un indirizzo IP della stessa LAN e restituisce l’indirizzo MAC corrispondente. ARP risolve soltanto gli indirizzi IP per i nodi nella stessa sottorete.

Nella RAM dei nodi ci sta una **tabella AEP** che contiene le corrispondenze fra indirizzi IP e MAC. La tabella contiene anche un **TTL** che indica quando sarà eliminata la voce della tabella. Ottenere un il MAC  partendo da un indirizzo IP presente nella tabella è semplice, ma se non c'è l'abbiamo? Si usa il protocollo ARP:

1) Nodo trasmittente crea un **pacchetto ARP**, lo scopo di un pacchetto ARP di richiesta è interrogare tutti gli altri nodi della sottorete riguardo all'indirizzo MAC corrispondente all'IP.

2) il nodo trasmittente passa alla scheda di rete un pacchetto di richiesta ARP insieme all’indicazione di inviare il pacchetto all’indirizzo broadcast della rete.

3) Ciascuna scheda di rete controlla se il pacchetto ARP ricevuto corrisponde all'indirizzo IP richiesto.

4) L’unico nodo (se esiste) che ha l’indirizzo corrispon 6.4 Reti locali commutate 395 dente invia al nodo richiedente un frame di risposta ARP con la corrispondenza desiderata.

5) Il nodo trasmittente aggiorna la tabella.

> ARP è un protocollo tra il livello di rete e il livello di collegamento.

Cosa succede se un nodo vuole inviare un datagramma a un host esterno alla propria sottorete, ossia attraverso un router. Semplicemente dobbiamo creare un fram che ha come indirizzo MAC di destinazione l'**indirizzo della scheda di rete del router**, questa può essere acquisita tramite indirizzo MAC.

Quindi il nodo trasmittente trasmette un frame sulla sottorete. La scheda di rete del router di quella sottorete capisce che il pacchetto è indirizzato a lui, allora lo passa al livello di rete del router. Questo consulta la propria tabella di rete del router e individua la scheda a cui va inoltrato il datagramma. Quindi incapsula il dato in un frame e lo spedisce alla scheda di rete dell'host destinatario.

---
##### Ethernet

> Un **hub** è un dispositivo a livello fisico che agisce sui singoli bit. Quando un bit arriva sull'interfaccia, l'hub amplifica il bit e lo trasmette su tutte le interfacce.

Alla fine degli anni '90 la maggior parte delle reti locali erano installazioni Ethernet a tipologia a stella basata su hub.

All'inizio del 2000 invece si sperimentato un nuovo tipo di cambiamento con installazioni Ethernet sempre a tipologia a stella, ma l'hub è stato sostituito da uno **switch.**

Esaminiamo la struttura dei frame Ethernet. Il payload di Ethernet è un datagramma IP

> Supponiamo che la scheda di rete trasmittente, A, abbia indirizzo MAC AAAA-AA-AA-AA-AA e che il ricevente, B, abbia indirizzo MAC BB-BB-BBBB-BB-BB. La scheda di rete A incapsula il datagramma IP in un frame Ethernet e lo passa al livello fisico. B ottiene il frame dal livello fisico, estrae il datagramma IP e lo passa al livello di rete

E' composto da sei campi:

- **Campo dei dati** (da 46 a 1500 byte) - Se il campo è maggiore di 1500 allora il datagramma va frammentato, se è minore di 46 allora deve essere rimepito fino a quella dimensione.

- **Indirizzo di destinazione** (6 byte) - Contiene indirizzo MAC di destinazione.

- **Indirizzo sorgente** (6 byte) - Contiene indirizzo MAC sorgente.

- **Tipo** (2 byte) - Serve per capire quale protocollo di rete usare. Infatti non si usa sempre IP.

- **Controllo a Ridondanza Ciclica** (CRC) (4 byte) - Consente alla scheda di rete di rilevare possibili errori nei bit del frame.

- **Preambolo** (8 byte) - I primi sette hanno i bit $10101010$, mentre l'ultimo ha $10101011$. I primi sette sono usati per risvegliare le schede di rete dei riceventi e sincronizzari i loro clock con il trasmittente.Gli ultimi due bit degli otto byte avvisano la scheda di rete di destinazione che stanno per arrivare i frame.

Le tecnologie Ethernet forniscono servizi senza connessione, quindi senza handshake, similmente a UDP o IP. Inoltre non è un servizio affidabile. L'host destinatario esegue il CRC, ma non manda alcun tipo di acknowledgmente relativo al superamento o meno del controllo.

Ci sono molte tecnologie Ethernet, queste sono denominate secondo la seguente struttura:

```
<VelocitàDelloStandar><BASE><AcronimoDelMezzoFisico>
```

Possiamo ottenere segmenti più lunghi di Ethernet usando dei **ripetitori**, dispositivi a livello fisico che riceve un segnale sul lato di ingresso e lo rigenera in output.

> L’odierna Ethernet è molto diversa dal progetto originale con topologia a bus e cavo coassiale. Installazioni odierne i nodi sono collegati a uno switch con segmenti punto a punto, realizzati con doppini in rame o fibre ottiche.

---
##### Switch a livello di collegamento

Gli switch hanno il compito di ricevere frame in entrata e inoltrarli sui collegamenti in uscita. Questi sono **trasparenti**, inoltre siccome la capacità degli switch può eccedere quella del collegamento di quell'interfaccia, questi sono dotati di buffer in uscita.

Abbiamo due operazioni possibili:

- **Filtraggio** - Lo switch determina se un fram vada scartato o inoltrato a qualche interfaccia.

- **Inoltro** - Individuazione dell'interfaccia verso cui il frame deve essere diretto e quindo inviato.

Queste operazioni sono possibili mediante una **tabella di commutazione**. Le voci delal tabella sono composte da:

- Indirizzo MAC del nodo.
- Interfaccia dello switch che conduce al nodo.
- Momento in cui la voce è stat inserita nella tabella.

> Non tutti i nodi della LAN hanno un voce.

Quando uno **switch** riceve un **frame**, guarda l’**indirizzo MAC di destinazione** e lo confronta con la sua **tabella di commutazione** (MAC table), che associa MAC address alle **interfacce**.
Tre possibili casi:

1. **Indirizzo MAC non presente nella tabella:**

    - Lo switch **non sa dove si trova la destinazione**, quindi:

    - Invia il frame in **broadcast** su tutte le interfacce **eccetto quella di ingresso** (in questo caso, su tutte tranne x).

2. **Indirizzo MAC presente nella tabella e associato alla **stessa interfaccia** da cui è arrivato il frame (x):**

    - Significa che la destinazione è **già sul segmento da cui il frame è arrivato**.
    
    - Lo switch **scarta il frame** (filtraggio), perché non serve inoltrarlo altrove.

3. **Indirizzo MAC presente nella tabella ma associato a **un’altra interfaccia** (y ≠ x):**

    - Lo switch sa **dove inoltrare** il frame.
    
    - Invia il frame all’**interfaccia corretta (y)**, senza broadcast.

Gli switch hanno la proprietà di costruire in maniera dinamica le proprie tabelle. Chiamiamo questa proprietà **auto-apprendimento** e funziona nel seguente modo:

1) La tabella è inizialmente vuota.

2) Per ogni frame ricevuto, lo switch archivia nella tabella **indirizzo MAC sorgente del frame**, **interfaccia da cui arriva il frame**, **momento di arrivo**. La tabella è completa quando tutti i nodi hanno inviato un frame.

3) Se lo switch non riceve più frame da un determinato indirizzo, allora dopo un periodo detto **aging time**, la voce relativa a quel indirizzo viene cancellata.

> Gli switch sono dispositivi **plug-and-play**, in quanto non richiedono interventi dell’amministratore di rete o dell’utente.

Possiamo identificare diversi vantaggi relativi all'uso degli switch piuttosto che dei collegamenti broadcast come bus o topologie a stella basate su hub:

- *Eliminazione delle collisioni*
- *Collegamenti etrogenei*
- *Gestione*

Sia switch che router sono commutatori di pacchetto **store-and-forward**. Il primo inoltra pacchetti usando indirizzi a livello di rete, i secondi indirizzi MAC.

> Gli switch moderni "match-action" possono essere usati anche su indirizzi IP.


|                         | HUB | Router | Switch |
| :---------------------: | :-: | :----: | :----: |
| Isolamento del traffico | No  |   Sì   |   Sì   |
|      Plug and Play      | Sì  |   No   |   Sì   |
| Instradamento ottimale  | No  |   Sì   |   No   |

---
##### Lan virtuali (VLAN)

Si possono identificare tre grandi incovenienze nelle LAN istituzionali moderne configurate in modo gerarchico:

- **Mancanza di isolamento del traffico**
- **Uso inefficiente degli switch**
- **Gestione degli utenti non adeguata**

Queste difficoltà possono essere superate da uno switch che support una **virtual local area network - VLAN**, questa permette di definire più reti locali virtuali su una singola infrastruttura fisica di rete locale.

Gli host nella VLAN comunicano come se fossero tutti connessi allo stesso switch. In questo tipo di swithc possiamo isolare le porte, ossia le interfacce, per creare delle sottoreti. Ma in questo modo saremmo impossibilitati dal mandare dei pacchetti da una rete all'altra.

Come possiamo far comunicare due VLAN isolate? Esistono diverse maniere:

1) Connettere una porta dello switch VLAN a un router esterno e configurarla in modo che appartenga ad esntrambe le VLAN.

**VLAN trunking** è un metodo scalabile per collegare più switch VLAN tramite una porta speciale detta porta di trunking. La porta di trunking appartiene a tutte le VLAN e permette di trasmettere frame di qualsiasi VLAN tra switch. Per identificare a quale VLAN appartiene un frame in transito sul trunk si usa lo standard IEEE 802.1Q.

Lo standard IEEE 802.1Q aggiunge un'etichetta VLAN di 4 byte all'intestazione del frame Ethernet. L'etichetta VLAN contiene un campo TPID di 2 byte con valore fisso 0x8100 e un campo Tag Control Information di 2 byte.Il campo Tag Control Information include l'identificativo della VLAN a 12 bit e un campo di priorità a 3 bit

Lo switch che invia il frame aggiunge il tag VLAN mentre quello ricevente lo legge e lo rimuove. Oltre alle VLAN basate su porte esistono anche VLAN basate su indirizzo MAC dove l'associazione alla VLAN avviene in base all'indirizzo del dispositivo collegato. Altri tipi di VLAN si possono basare sul protocollo di rete come IPv4 IPv6 o AppleTalk o su altri criteri. Le VLAN possono anche estendersi oltre la rete locale attraverso router IP per collegare LAN distanti come parte di un'unica VLAN