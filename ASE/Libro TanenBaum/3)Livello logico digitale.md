E' la base della gerarchia della nostra struttura, cioè ==l'hardware del calcolatore==.
Gli elementi con cui lavoriamo in questo livello sono le **porte logiche** che ci permettono di costruire ==circuiti logici.==
E' quindi il livello con cui possiamo costruire una CPU funzionante ed il modo in cui andiamo ad ==analizzare il comportamento dei circuiti logici è tramite l'algebra di Boole.==

--------------------------------------------------------------------------

## Porte logiche e algebra di Boole

Nei circuiti digitali sono presenti due valori: 0 e 1, le ==porte logiche calcolano una funzione tra== questi ==segnali==.
Il funzionamento delle porte logiche è dovuto a degli elementi presenti a un livello inferiore di quello che ci interessa: **Livello dei dispositivi**, infatti ==possiamo implementarle== tramite l'uso di **==transistor==**.

I transistor funzionano come ==velocissimi interruttori binari== e ci permettono di creare le diverse porte logiche, sono composti da: **Collettore, base ed emettitore**.

Una porta logica è composta da un transistor che prende come input dei segnali elettrici.
Il valore di output viene scelto in base al valore degli input, se il ==valore in input è basso allora il transistor agirà come una resistenza infinita==, di conseguenza il valore in uscita si comporterà come la $V_{cc}$: una tensione regolata esternamente, ==altrimenti lo lascierà passare e si comporterà da conduttore ideale== facendo scaricare a terra l'output.
![[transistor 1.png]]
Esistono diverse porte logiche: AND, OR, NOT, NAND, NOR e si preferisce usare ==NAND o NOR== al posto di AND e OR, perchè richiedono ==meno transistor== per la creazione.

Sono due i tipi principali di tecnologie per la costruzione di porte logiche:

- **Bipolare**: divisa in **TTL** (Transistor-Transistor Logic) e la **ECL**(Emitter Couple Logic).
- **MOS**(==Metal Oxide Semiconductor==): usata su ==larga scala sono più lente==, ma occupano ==meno spazio== e richiedono ==meno potenza==.

Il modo in cui analizziamo il comportamento dei circuiti è tramite l'[[Algebra di Boole]] che ci descrive il comportamento di un circuito ==tramite la tabella di verità==, per scrivere la formula invece basta prendere i ==risultati positivi della tabella e unirli come somma di prodotti o prodotti di somma==.
Esistono ==diverse leggi che ci permettono di capire se due circuiti logici sono equivalenti== come per esempio: **Le Leggi di De Morgan**.

## Circuiti logici digitali elementari

Esistono dei ==circuiti elementari che sono già pronti== per la costruzione di un computer, solitamente ci si riferisce a questi come **chip o IC (Integrated Circuit)**.
Per collegare il chip al modo esterno si usano dei supporti di plastica e si collegano con pin ai chip.
I supporti più utilizzati sono:

- **DIP**(==Dual Inline Package==): Contenitori a due file di piedini, usati per circuiti piccoli.

- **LGA** (==Land Grid Array==) o **PGA** (==Pin Grid Array==): Vengono usati per chip più grandi, il primo è composto da piccoli pad piatti sotto sul fondo del chip, mentre l'altro ha direttamente dei pin.

Quando pensiamo alle porte logiche le consideriamo ideali, cioè che ==l'output arriva in tempo zero, mentre in realtà non è così==, poichè hanno un **ritardo dovuto al tempo di propagazione del segnale.**

Molte applicazioni di questo livello richiedono circuiti chiamati **rete combinatoria** dove gli ==output sono determinati solo dall'input, e ci sono più input e output== non come nei circuiti sequenziali.

I circuiti generici usati più frequentemente sono:

- **Multiplexer**: $2^n$ Dati in input, un valore di output e n valori di controllo, questi permettono di ==selezionare uno dei dati in input che viene instradato verso l'output.==

- **Decodificatori**: n dati in input utilizzati per ==impostare ad 1 una sola delle $2^n$ linee di output.==

- **Comparatori**: Permette di ==confrontare due stringhe di bit==, l'output è 1 se le stringhe sono uguali e viene usato come porta logica la XOR facnedola bit per bit.

- **Array logici programmabili** (PLA): Serve a ==calcolare la somma di prodotti.==

Reti combinatorie più usate:

- **Registri a scorrimento**: Output corrisponde all'input traslato di un bit.

- **Sommatori:** Circuito in grado di ==eseguire le somme tra due parole==, è composto da un circuito più semplice messo in sequenza, **half adder** che ==composto da una porta AND per calcolare il riporto e una porta XOR per calcolare la somme.== Questi tipo di sommatori si chiamano a **propagazione di riporto**, perchè ogni ==half adder deve aspettare il riporto del half hadder precedente per eseguire la somma==, un'alternativa più conveniente sarebbe un **sommatore ad anticipo di riporto.**

- **Unità aritmetico logiche:** Circuito in grado di ==eseguire le seguento operazioni: AND, OR, NOT e somma,== la scelta dell'operazione avviene tramite **due bit di controllo** che ==attivano il segnale== corrispondente all'operazione da eseguire ==tramite un decodificatore.==

In molti circuiti è importante ==l'ordine di esecuzione degli eventi== , quindi si utilizza il **clock** per ==gestirne la sincronizzazione==, **emette impulsi di larghezza definita ad intervalli costanti**, l'intervallo di tempo fra due impulsi è detto **ciclo di clock** e tramite il clock ==possiamo permette di far eseguire ad un circuito un evento in ritardo rispetto ad un altro in modo tale da mantenere la sequenzialità richiesta.==

## Memoria

Tramite l'utilizzo delle porte logiche possiamo creare dei circuiti, detti sequenziali, che ci permettono di ==creare una memoria.==

Il tipo di memoria più semplice da creare è un **latch SR**: usiamo due porte NOR e due input: ==S che serve a settare il valore del latch e R per azzerarlo==, inoltre ci sono due output complementari.

E' spesso ==utile impedire che un latch cambi di stato se non in specifici momenti==, per questo possiamo costruitre un **latch SR temporizzato** che ==come input anche un clock==, che se uguale a 0 impedisce il cambio di stato all'interno della memoria.

==Esiste un ambiguità nella situazione S=1 R=1==, per evitarla costruiamo un **latch D temporizzato**, che ha un solo input D, che prenderà il posto di S e R. Il valore D verrà memorizzato nel latch solo quando il clock è uguale a 1. ==Questa è una memoria ad 1 bit.==

Esiste un ultimo tipo di memoria: **flip-flop**, che permettono di ==evitare i possibili paradossi logici del D-latch==, per costruirlo mettiamo in sequenza deu latch e l'uscita di uno dei d-latch è l'input di quello successivo, ==inoltre il clock è impostato ad 1 nel primo e a 0 nel secondo.
Un'altra differenza è che il flip flop **commuta sul fronte** e non a livello come i latch.==

==Possiamo creare un registro ad n bit mettendo in sequenza n flip-flop== e unendo i loro segnali di clock e unendoli a corrispettivi pin di input e output oltre al segnale CLR che li resetta.

==Mettendo in linee e colonne i vari D-latch possiamo creare un memoria effettiva,== con 8 input: tre per i dati, deu per indirizzo e tre per i controlli **CS (Chip Select)**, **RD(Distinguere scrittura e lettura)** e **OE(abilita l'input)**, le tre linee di outpu sono dedicate ai dati.
Proprietà fondamentale di questo chip è che ==è facilmente espandibile.==

Tutte le memorie lette fino ad ora sono dette RAM e posso essere sia lette che scritte, ne esistono di due tipi:

- **Statiche**: Costruite usando circuiti simili ai flip-flop D, ==veloci e molto diffuse, mantengono il contenuto fintanto che vi è alimentazione==

- **Dinamiche**: Sono ==composte da array di celle==, composte da un ==transistor e un condesatore== che può essere caricato o scaricato per memorizzare i valori, ==hanno bisogno di refresh costante,== questo svantaggio e ripagato da una velocità maggiore.

Ci sono diversi tipi di RAM dinamiche:

- **DRAM FPM(Fast Page Mode)**: Usate in calcolatori vecchi, sostituite da DRAM EDO(Extended Data Output) un cui il riferimento in memoria inizia prima della fine di quello precedente.

- **SDRAM(Synchronus DRAM, DRAM sincrona)**: E' una ==RAM ibrida==, il numero di cicli per cui deve funzionare gli vengono detti dalla CPU, il passaggio successivo è stata la **SDRAM DDR(Double Data Rate)** in queste ==memorie il chip produce un output sul fronte di salita del segnale di clock e uno sul fronte di discesa, raddoppiando così il tasso di trasferimento dati.==

Esistono anche chip di memoria non volatile, cioè le **ROM,** l’unico modo per cambiare il programma ==consiste nella sostituzione dell’intero chip.== Ne esistono di diversi tipi:

- **PROM:** Può essere programmata una volta sul posto 
- **EPROM:** I campi possono essere programmato e cancellati 
- **EEPROM:** Possono essere cancellate tramite impulsi elettrici inceve di dover inserire il chip in una camera speciale per l'esposizione alla luce ultravioletta.
- **Flash**: E' cancellabile a blocchi e riscivibile

## Chip della CPU e bus

I pin delle CPU possono essere di indirizzi, dati e controlli, i pin permettono le relative comunicazioni con il mondo esterno, i pin sono collegati ad analoghi pin sulla memoria attraverso dei bus.
La CPU comunica con memoria e I/O tramite i propri pin e in nessun altro modo.
Due dei principali parametri che determinano le prestazioni di una CPU sono il numero di pin d’indirizzo e il numero di pin di dati, oltre ai pin d’indirizzo e dei dati, le CPU sono dotate anche di alcuni pin di controllo.
I pin di controllo regolano il flusso e la temporizzazione dei dati da e verso la CPU.
I pin di controllo possono essere ragruppati nelle seguenti categorie:

1) controllo del bus;
2) interrupt;
3) arbitraggio del bus;
4) comunicazione con il coprocessore;
5) stato;
6) altro.