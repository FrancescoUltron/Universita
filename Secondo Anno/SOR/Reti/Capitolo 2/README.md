# Capitolo 2 - Livello di applicazione

Le applicazioni sono il motivo principale per cui abbiamo bisogno delle reti. Senza applicazioni utili o funzionale sarebbe inutile farle comunicare. I protocolli usati a questo livello - come **HTTP** - sono semplici da comprendere per l'uomo.

> Una cosa particolare è che molte applicazioni molto usate - social network, e-mail, voice over IP e molte altre - sono state sviluppate dopo la nascita dell'Internet. 

---
### Principi delle applicazioni di rete

Immagina di volere create un'applicazione che permetta la comunicazione tra end system - avendo visto tutti i livelli della rete - potremmo immaginare che sia un qualcosa di molto complicato. In realtà è molto più semplice di quello che sembra. Infatti dovremmo soltanto preoccuparci di due cose:

- **I protocolli forniti dal livello di trasporto** - gestiranno la comunicazione.

- **Le API che *nasconderanno* il livello di trasporto** - Permettono di interagire con la rete senza essere a conoscenza degli elementi del nucleo.

Quindi lo sviluppatore deve stabilire l'**architettura dell'applicazione** - completamente separata dall'archittetura della rete - questa fornisce alle applicazione uno specifico insieme di servizi. Esistono due tipi principali di archittetura dell'applicazione:

- **Client-Server**
- **peer-to-peer (P2P)**

Nel primo modello abbiamo un client e un server; Il *server* si trova sempre su un host attivo della rete - con un indirizzo **IP permanente** e solitamente si trova all'interno di data center - per ragioni di scalabilità. Il suo compito è quello di rispondere alle richieste dei client.
I client invece dovranno comunicare tra di loro **tramite server** e potrebbero avere degli indirizzi IP dinamici. (Ex: HTTP).

//Aggiungi immagine

Nel secondo modello non abbiamo server - ma **peers** - cioè coppie di host - questi **comunicano direttamente tra di loro** e sono essenzialmente degli end-system che fanno richieste e forniscono servizi fra di loro. Uno dei punti di forza è la sua **scalabilità** - perchè ogni peer aggiunge sia carico di lavoro che capacità di servizio del sistema. Sono economiche - ma la gestione è molto complessa - anche da un punto di vista di sicurezza.

//Aggiungi immagine

---
**Processi comunicanti**

Dobbiamo capire com fanno le applicazioni su host diversi a comunicare. Quando abbiamo un programma che gira su host possiamo riferirci ad esso come **processo.** Abbiamo già visto nella parte di sistema operativi come questi comunicano tra di loro nello stesso sistema (*Internprocess communication*). Quando invece trattiamo di processi su host diversi - possiamo farli comunicare tramite scambi di **messaggi.**

> **Processo client:** Processo che inizializza la comunicazione.
> **Processo server:** Processo in attesa di essere contattato.

> Notiamo che anche nelle architetture P2P ci sono processi client e server.

---
**L'interfaccia tra il processo e la rete**

Ogni messaggio inviato tra applicazione deve passare per la rete sottostante. Un processo invia e riceve messaggi dalla rete tramite un'interfaccia software detta **socket.**

> Analogia: Le socket sono come delle porte che ci permettono di accedere ad una casa - cioè l'applicazione.

> Possiamo considerare le socket come un **API** tra applicazione e rete.

Durante una comunicazione tra hosts ci saranno due socket.

//Immagine

---
**Indirizzamento**

Per ricevere dei messaggi - i processi riceventi  hanno bisogno di due informazioni:

- **L'indirizzo dell'host**
- **Identificatore del processo sull'host**

In Internet ogni host è indentificato tramite un **indirizzo IP** - un numero univoco composto da 32 bit. Questo non è abbastanza perché su un hot potrebbero essere in esecuzione più processi - dobbiamo identificare quello giusto. Questo compito spetta al **numero di porta di destinazione**.

>Alcuni numeri di porta sono standardizzati per delle applicazioni - per esempio il numero 80 fa riferimento al protocollo HTTP.

---
**Servizi di trasporto disponibili per le applicazioni**

Sappiamo che un protocollo definisce il formato, l'ordine dei messaggi e le azioni prese all'arrivo o invio di messaggi. Quindi per definire un protocollo del livello di applicazione dobbiamo definire: la sintassi dei messaggi, la semantica dei messaggi e le regole per inviare e rispondere a questi messaggi.

> Si usano solitamente protocolli *aperti* - cioè di cui si conosce il **RFC** e sono pubblici.

> È importante distinguere tra applicazioni di rete e protocolli a livello di applicazione. Un protocollo a livello di applicazione è solo una parte (benché molto importante) di un’applicazione di rete.

Quali tipi di servizi forniti dal livello sottostante (livello di trasporto) servono per sviluppare un'applicazione? - Dipende.

Alcune applicazione hanno bisogno che ci sia un **integrità totale dei dati** durante il trasferimento - come applicazioni per il trasferimento di dati - altre invece tollerano delle perdite.

> Se un'applicazione fornisce questo servizio allora si dice  che fornisce un **trasferimento dati affidabile** *(reliable data transfer)*, altrimenti potrebber essere un'**applicazione che tollera le perdite** *(loss-tolerant applications)*.

Altre applicazioni hanno bisogno di avere un **ritardo nella spedizione dei dati il più piccolo possibile** - come per esempio giochi multiutente.

Non solo - alcune applicazioni per essere efficaci - hanno bisogno di spedire un minimo quantitativo di dati per essere 'funzionanti'. 

> Le applicazioni che hanno requisiti di throughput sono dette **applicazioni sensibili alla banda** *(Bandwidth-sensible applications)* - per esempio la trasmissione di video. Esistono anche **applicazioni elastiche** *(elastic applications)* - cioè applicazioni che possono fare uso di tanto o poco throughput in base a quanto ce ne sia disponibile.

L'ultima cosa che un servizio di trasporto potrebbe fornire sono servizi relativi alla sicurezza come: Integrità dei dati o cifratura dei dati.

//Immagine

---
**Protocolli di trasporto offerti da Internet**

Internet - come ogni rete **TCP/IP** - mette a disposizione due tipi di protocolli di trasporto: **TCP** e **UDP**.

Il primo è un protocollo che fornisce un *servizio orientato alla connessione* - nella quale avviene uno sambio di messaggi *prima* che i messaggi a livello di applicazione cominciano a fluire. Questa procedura è detta **Handshake** è mette client e server in allerta preparandoli alla partenza dei pacchetti. Tramite la Handshake si crea una **connessione TCP** tra le socket dei processi. questa connessione deve essere chiusa alla fine della comunicazione. L'altra cosa che il protocollo fornisce è un *servizio di trasferimento affidabile* - quindi i messaggi vengono inviati senza errori e nell'ordine giusto. Inoltre offre un **meccanismo di controllo della congestione**.

> La connessione TCP è **full-duplex** - cioè che i processi possono scambiarsi messaggi contemporaneamente sulla connessione. 

Il secondo protocollo - UDP - è un protocollo leggero con servizio minimalista. UDP è senza connessione (niente handshake) e il trasferimento dei dati **NON** è affidabile - quindi non è detto che il messaggio - dopo aver superato la socket - arrivi al processo giusto.
Non include un meccanismo di controllo della congestione e può 'spingere' i dati al livello sottostante a **qualsiasi velocità.**

//immagine

---
**Piccola sezioni sulla sicurezza**

Le prime versioni di TCP e UDP non avevano alcuni tipo di cifratura o altri servizi di sicurezza - per esempio sicurezza end to end. negli anni sessanta questi servizi erano implementati direttamente sull'applicazione. Per rendere sicuro TCP - questo è stato arricchito con il **Transport Layer Security (TLS)**  che fornisce servizi di sicurezza tra processi come:

- Controllo dell'integrità dei dati.
- Cifratura.
- Autenticazione end-to-end.

> Nota che non è un nuovo protocollo, bensì un arricchimento del TCP.

---
### Web e HTTP
