# Capitolo 7 - Wireless and Mobilità

### Introduzione

All'interno di una rete wireless riusciamo a trovare i seguenti elementi:

- **Host wireless** - ossia i dispositivi end-system come laptop o telefoni.

- **Collegamenti wireless** - un host si connette ad una **stazione base** o ad un altro dispositivo tramite un **collegamento di comunicazione wireless**.

- **Stazione base** - Responsabile del invio e della ricezione dei dati ai vari host wireless associati alla stazione base. La stazione base deve coordinare le trasmissioni degli host associati ad essa.

> Un host è associato ad una stazione base se:
> 
> 1) L'host è nella distanza di comunicazione della stazione base.
> 2) L'host usa quella stazione base passare informazioni alla rete.

Alcuni esempi sono le **torri cellulari** nelle reti telefoniche e gli **access point** nelle reti wireless LAN 802.11.

Gli host associati ad una stazione base operano in **modalità infrastuttura**, poiché tutti i classici servizi della rete sono forniti dalla stazione base. Nelle reti **ad hoc** gli host non hanno strutture a cui associarci, quindi sono gli stessi host a fornire i vari servizi.

Quando un dispositivo mobile va oltre il range di una delle stazioni base, ed entra nel range di una nuova stazione base, allora cambierà il suo punto di collegamneto nella rete. Questo processo di chiama **handoff** oppure **handover**. 

> Nelle reti ad hoc gli host wireless possono trasmettere solo a altri nodi entro la copertura del collegamento.

- **Infrastrutture di rete** - La rete con con cui un determinato host vuole comunicare.

Queste componenti possono essere combinate in diverse maniere, possiamo classificare la tassonomia di queste reti tramite due criteri:

1) Se un pacchetto nella rete wireless deve passare per un solo wireless hop o multipli.
2) Se ci sta o meno un infrastruttura come una stazione base nella rete.

|                            |                                             Hop Singolo                                             |                                                                                                        Hop Multipli                                                                                                         |
| :------------------------: | :-------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|     **Infrastruttura**     | L'host si collega a una stazione base, che lo collega al resto della rete: *Wi-Fi, Rete Cellulare.* | C'è una stazione base collegata al resto della rete. Tuttavia, un host può dover ritrasmettere la propria comunicazione attraverso altri nodi wireless per comunicare con la stazione base: *reti di sensori e Wi-Fi mesh.* |
| **Nessuna Infrastruttura** |        Senza stazione base, uno dei può coordinare la trsamissione degli altri: *Bluetooth.*        |        Nessuna stazione base, i pacchetti possono dover essere ritrasmessi attraverso diversi nodi Wireless prima di giungere a destinazione: *mobile ad hoc networks (MANETs) e vehicular ad hoc network (VANET).*         |

---
### Collegamenti wireless e caratteristiche della rete

I collegamneti Wireless si differiscono dalla loro controparte connessa per diversi motivi:

- **Forza del segnale minore** - Le radiazioni elettromagnetiche si attenuano attraverso ostacoli. Anche negli spazi aperti l'intensità del segnale diminuisce al crescere della distanza percorsa (dispersione), questo fenomeno è detto **path loss**.

- **Interferenze da parte di altre sorgenti** - Sorgenti radio che trasmettono alla stessa banda di frequenza possono interferire tra di loro. Anche rumori elettromagnetici possono interferire.

> Per evitare queste interferenza, standard 802.11 recenti usano banda 5 GHz.

- **Propagazione multipercorso** - Una parte dell'onda elettromagnetica del segnale viene riflessa da ostacoli. Percorsi di lunghezza diversa tra il trasmettitore e il ricevente potrebbero causare un offuscamento  del segnale.

Le reti wireless hanno molti più errori sui bit rispetto a reti cablate. Per questo motivo protocolli di collegamento wireless utilizzano anche protocolli di trasferimento di dati affidabile.

Un host ricevente un segnale wireless, essnzialmente riceve un segnale elettromagnetico che è un segnale degradato rispetto all'originale.

Il **rapporto segnale-rumore (SNR)** è una misura relativa della forza del segnale ricevuto (cioè, l'informazione trasmessa) rispetto al rumore.

> l'SNR indica **quanto è forte il segnale utile** (cioè i dati che vogliamo ricevere) **rispetto al rumore di fondo** (interferenze).

Questa misura è misurata in decibels (dB). Più è alto il valore del SNR, più sarà semplice per il ricevente estrarre il segnale utile.

Il **BER (Bit per Error Rate)** invece è la probabilità che un bit trasmesso viene ricevuto come errore dal ricevitore. Il rapporto tra SNR e BER per un dato schema modulazione è il seguente:

- Aumentare la potenza del segnale implica aumentare l'SRN e quindi diminuire il BER.

- Trasmissioni a potenza maggiore implicano un maggiore consumo di energia e possono andare ad interferire con altre trasmissioni.

- Per un dato SNR, una tecnica di modulazione con più elevato tasso di trasmissione dei bit avrà una BER più alto.

- L'SNR può cambiare con la mobilità adattando dinamicamente il livello fisico.

Nei collegamenti wireless, oltre a un tasso di errore di bit più alto e variabile nel tempo rispetto ai collegamenti cablati, esistono problemi specifici come il **problema del terminale nascosto** e **l’attenuazione del segnale**.

- **Problema del terminale nascosto:** quando due stazioni (A e C) trasmettono verso la stessa destinazione (B) ma non riescono a "sentirsi" tra loro a causa di ostacoli fisici, causando interferenze a B senza accorgersene.

- **Attenuazione del segnale:** anche senza ostacoli, A e C possono essere troppo lontane per rilevare le reciproche trasmissioni, ma abbastanza vicine da interferire a destinazione (B).

Questi fenomeni rendono l’**accesso multiplo** (cioè la condivisione del mezzo trasmissivo) molto più complicato nelle reti wireless rispetto a quelle cablate.

---
##### CDMA

Come abbiamo visto nel capitolo precedente il CDMA appartiene alla famiglia dei protocolli a suddivisione di canale.
Nel **protocollo CDMA** (Code Division Multiple Access), **ogni bit** che vogliamo trasmettere **viene "moltiplicato" per un codice speciale**, chiamato **codice di chipping**, che cambia molto più velocemente dei bit originali.

- Immagina di voler inviare una sequenza di bit, ad esempio: `1 0 1`.

- Ogni bit viene **codificato** usando un codice (una sequenza di valori, tipo `+1, -1, +1, -1,...`) che rappresenta il "chipping".

- Questo codice cambia più rapidamente: ad esempio, **mentre un bit dura 1 unità di tempo, il codice può avere 4 o più cambi in quella stessa unità**.

In questo modo, **più dispositivi possono usare lo stesso canale contemporaneamente**, ognuno con un **codice diverso**, senza confondersi tra loro. Il ricevitore, conoscendo il codice, riesce a **decifrare solo il segnale del mittente desiderato**, ignorando gli altri.

In breve: **CDMA codifica ogni bit in modo da poter condividere il canale con altri, senza interferenze, grazie a codici unici e rapidi**.

---
### WiFi: 802.11 Wireless LANs

Fra le varie tecnologie e standard nati durante gli anni '90, la 802.11 Wireless LAN, detta anche **WiFi**, è quella che è risultata la vincitrice.

> Ci sono molti standard diversi che operano su diverse frequenze d'onda o che hanno dei cambiamenti a livello di rete. 

La componente principale dell'architettura è il **BSS (Basic Server Set)**. Una BSS contiene una o più stazioni wireless, con una stazione base centrale detta **access point (AP).**

> Nelle reti casalinghe ci sta solitamente un unico AP unito al router che permette di collegare la BSS ad Internet.

Come per l'Ethernet anche queste interfacce wireless sono identificate da un indirizzo MAC.

> Reti LAN che usano AP sono anche dette *infratrutture wireless LAN*. 

Notiamo che per creare una BSS non abbiamo bisogno di un AP, infatti stazioni IEEE 802.11 possono raggrupparsi fra loro e creare una rete ad hoc.

Nel 802.11, ogni stazione wireless deve essere associata con un AP prima di poter ricevere o mandare dati del livello di rete. Quando un amministratore installa un AP gli deve associare una o due parole chiamate **Service Set Identifier (SSID)**. Deve anche assegnare un numero di canale all'AP.

La rete Wi-Fi usa una banda di frequenza che va fra 2.4GHz a 2.4835 Ghz. In questa banda abbiamo 11 canali numerati che utilizzano una parte della banda disponibile, sono larghi 22 MHz quindi in alcuni punti si sovrappongono.

> Due canali non sono sovrapposti solo se distano di almeno quattro numeri.

Il numero di canale è il canale su cui può lavorare un determinato AP.

Una situazione molto comune è quella della **giungla di WiFi**, ossia una locazione fisica dove una stazione wireless riceve un segnale abbastanza potente da parte di due o più AP. In questo caso per avere l'accesso ad Internet dobbiamo *associarci* con esattamente un AP. 

> **Associazione:** Creazione di un canale virtuale tra un host wireless e un AP. Solo l'AP associato potrà inviare dati all'host e l'host può spedire frame di dati solo attraverso l'AP.

Lo standard 802.11 richiede ad ogni AP periodicamente di spedire dei **beacon frame**, ognuno dei quali include SSID e il MAC dell'AP. Il nostro dispositivo controlla gli undici canali cercando questi frame per poi sceglierne uno con cui associarsi. Lo standard non specifica alcun algoritmo da usare per selezionare l'AP con cui associarsi, solitamente si usa quello che il beacon frame con il segnale più forte.

> La forza del segnale non è l'unica caratteristica che ci interessa, per esempio ci interessa anche quanti dispositivi sono collegati ad un AP.

Questo processo di scan dei canali e di ascolto si chiama **scanning passivo**, esiste anche lo **scanning attivo** quando l'host manda in broadcast un *frame probe*, gli AP rispondo con un *probe request* e quindi l'host decide a quale AP associarsi. Dall'associazione avviene uno scambio di messaggi *association request/response* (Handshake).

Per potersi associare con un AP, il dispositivo wireless deve potrsi identificare al'AP. Si possono usare indirizzi MAC oppure attraverso un sistema di username e password, in ogni caso AP comunica con un server di autenticazione che tramite protocolli come **RADIUS** o **DIAMETER** supporta più AP.