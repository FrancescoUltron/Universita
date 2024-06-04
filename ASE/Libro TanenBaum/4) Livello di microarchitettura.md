
- Implementa le funzioni del **livello ISA (Instruction Set Architecture).**

- La progettazione ==dipende dall'ISA che si vuole implementare.==

- Alcuni livelli ISA hanno semplici istruzioni eseguibili in un solo ciclo di clock (in particolare le piattaforme RISC -- Architettura per processori).

- Altri livelli ISA, come quello di Core i7, richiedono più cicli per esecuzione.

- ==L'esecuzione di un operazione richiede di norma l'identificazione di operandi in memoria, la loro lettura, l'esecuzione, la memorizzazione dei risultati in memoria.==

**Livello ISA**: Caratterizzato da un'assenza di schema generale, pertanto tratteremo un esempio particolare **IJVM (Integer Java Virtual Machine)**

==La microarchitettura conterrà un microprogramma il cui compito è di eseguire le operazioni di Fetch-Decode-Execute.==
Il microprogramma ha diverse variabili che definiscono lo **stato** del computer sono accessibili da tutte le funzioni: ==ogni funzione modifica almeno una di queste variabili.==
==Il microprogramma è un insieme di istruzioni elementari, di basso livello, memorizzate in una memoria di sola lettura (ROM) all'interno di un processore.==

## Datapath

==Parte della CPU che contiene la ALU, i suoi input e i suoi output, contiene alcuni registri a 32 bit.
Alcuni possono inviare i loro contenuti sul BUS B, altri ricevono dati sul BUS C.
I dati presenti sul BUS C possono essere scritti su più registri contemporaneamente.==

![[Percorso dati 1.png]]


## Lista segnali di controllo ALU e SHIFTER

Segnali di controllo della ALU:

- **F0 e F1:** Servono a selezionare la funzione operativa che l'ALU deve eseguire.

- **ENA:** Abilita l'input A dell'ALU quando ENA è attivo, l'ALU utilizza il valore presente sul BUS A come uno dei suoi operandi.

- **ENB:** Abilita l'input B dell'ALU quando ENA è attivo, l'ALU utilizza il valore presente sul BUS B come uno dei suoi operandi.

- **ENVA:** Abilita il multiplexer che seleziona l'input A della ALU. Se ENVA  è attivo il MUX instrada il valore dal registro o dal bus appropriato verso l'input A dell'ALU.

- **INC:** Esegue un operazione di incremento, se attivo somma 1 al valore presente sul suo input, senza considerare l'altro input ![[ALU.png]]

Segnali di controllo dello shifter:

- **SLL8**:==Trasla il valore a sinistra di un byte, impostando gli 8 bit meno significativi a 0.== 

- **SRA1**:==Trasla invece il valore di 1 bit a destra, lasciando inalterato il bit più significativo.==


## Temporizzazione del percorso dati

 ==Serve a svolgere le operazioni all'interno del datapath.==
 La temporizzazione dei percorsi dati è un concetto fondamentale nel design dei circuiti digitali.==Si tratta di garantire che i segnali all'interno di un circuito raggiungano le destinazioni desiderate in modo sincronizzato e senza errori temporali.== Questo viene gestito considerando i ritardi di propagazione attraverso le varie porte logiche e i percorsi che i segnali devono percorrere.

Possiamo pensare al ciclo di clock del percorso dati come un sottoinsieme di cicli il cui inizio è guidato dal fronte di discesa del clock:

1) Impostazione dei segnali per guidare il datapath (∆w)
2) I registri sono caricati nel bus B (∆x) 
3) Esecuzione delle operazioni nella ALU e nello shifter (∆y) 
4) Propagazione dei segnali dallo shifter ai registri (∆z)

## Datapath: operazioni della memoria

La macchina ha due modi per comunicare con la memoria:

- Tramite una ==porta a 32 bit== (Controllata dai registri **MAR** e **MDR**)
- Tramite una ==porta a 8 bit== (Controllata dal registro **PC** che legge il byte meno significativo di **MBR**)

In generale:

- ==Registri MAR/MDR: Servono per la lettura/scrittura di parole di dati.==
- ==Registri PC/MBR: Servono per la lettura del programma eseguibile.==

**Il registro MAR contiene indirizzi espressi in parole, mentre il registro PC contiene indirizzi espressi in byte.** (Sono due cose diverse):

- Assegnare il valore 2 al registro MAR e avviare una lettura in memoria significa leggere i byte 8-11 (la parola 2) e scriverli sul registro MDR.

- Assegnare il valore 2 al registro PC e avviare una lettura in memoria significa leggere il byte 2 dalla memoria e scriverlo sugli 8 bit meno significativi di MBR

==La differenza è dovuta dal fatto che i due registri accedono a parti (semanticamente) differenti della memoria.==

==La combinazione MAR/MDR viene usata per leggere/scrivere parole di dati del livello ISA.

==La combinazione PC/MBR viene usata per leggere il programma eseguibile al livello ISA, che consiste di un flusso di byte.==

Tutti gli altri registri che contengono indirizzi si comportano come MAR.

![[MAR.png]]
Nelle implementazioni reali esiste un solo tipo di memoria (orientata al byte).

Per consentire al registro MAR di contare in parole si opera come in figura:

- ==Si scartano i due bit più significativi di MAR==
- ==Si effettua uno shift verso sinistra di due bit==
- ==Si inseriscono due zeri nei bit meno significativi di MAR==

I dati letti dalla memoria mediante la port di memoria a 8 bit vengono scritti sul registro MBR, il quale contenuto può essere ==copiato sul registro B con segno o senza segno==:

- **Con segno**: Gli 8 bit di MBR possono essere considerati come un numero compreso tra -128 e +127. Il segno (bit più significativo tra gli 8 di MBR) viene esteso duplicandolo 24 volte.

- **Senza segno**: I 32 bit scritti sul bus B si compongono degli 8 bit letti da MBR (meno significativi) e di zeri nei restanti bit più significativi.

==La scelta se convertire gli 8 bit di MBR in un valore a 32 bit con o senza segno viene determinato da quale dei due segnali di controllo viene settato==

## Microistruzioni

==Implementano le istruzioni ISA, una sequenza di queste microistruzioni crea appunto un'istruzione ISA o microprogramma.==
Il controllo del datapath richiede 29 segnali:

- 9 per controllare la scrittura dei dati dal bus C ai registri
- 9 per controllare la scrittura dei dati dai registri al bus B
- 8 per il controllo delle funzioni di ALU e shifter
- 2 per indicare gli accessi alla memoria mediante i registri MAR e MDR
- 1 per indicare il fetch della memoria mediante i registri PC/MBR

Questi valori specificano le operazioni di ciclo del datapath, un ciclo consiste nel:

- ==Copiare i valori dai registri sul bus B==
- ==Propagare i segnali lungo la ALU e lo shifter
- ==Dirigerli sul bus C
- ==Scrivere i risultati sul registro appropriato

Se viene asserito in segnale di lettura dati dalla memoria, l'operazione di lettura viene avviata soltanto al termine del ciclo del datapath, quando l'indirizzio è stato caricato sul registro MAR.

I dati della memoria sono disponibili al termine del ciclo successivo nel registro MBR o MDR e possono essere usati nel ciclo ancora successivo

==Lettura dati nel ciclo k saranno disponibili nel ciclo k+2==.

Il formato di una microistruzione è composto da:

- **Addr**:  Contiene l'indirizzo di una potenziale prossima istruzione 
- **JAM**:  Modalità di selezione della prossima istruzione 
- **ALU**:  Selezione della funzione della ALU e dello shifter 
- **C**: Selezione dei registri sui quali copiare il contenuto del bus C 
- **Mem**: Selezione della funzione della memoria 
- **B**: Selezione del registro dal quale scrivere sul bus B

## Controllo delle microistruzioni

Il **Sequencer** stabilisce i segnali di controllo da eseguire ad ogni ciclo di clock, si tratta di un dispositivo incaricato di far ==avanzare passo-passo la sequenza di istruzioni per l'esecuzione di ogni singola istruzione ISA.==

Produce due tipi di informazione ad ogni ciclo:

- ==Lo stato di ogni segnale di controllo nel sistema.==
- ==L'indirizzo della prossima microistruzione da eseguire.==

## Memoria di controllo 

La **memoria di controllo** ==contiene l'intero microprogramma==, è un dispositivo da 512 parole, ognuna consistenti di una microistruzione di 36 bit.

Ha bisogno di un registro di indirizzo e un registro dati:

- **MPC**(MicroProgram Counter): Non si tratta di un contatore perché le microistruzioni non si possono disporre in modo sequenziale.

- **MIR**(MicroInstruction Register): Contiene la microistruzione corrente i cui bit determinano i segnali di controllo che pilotano il datapath.

**Ciclo delle operazioni:**

1) Sul fronte di discesa del clock, MIR viene incaricata dalla parola nella memoria di controllo puntata da MPC (tempo ∆w), dopo che la ==microistruzione caricata in MIR==  i segnali si propagano sul datapath: ==il contenuto di un registro è copiato sul bus B, la ALU sa che operazione svolgere.==

2) Il tempo necessario ad eseguire queste operazioni è indicato da ∆x; ==dopo un tempo ∆w + ∆x gli input della ALU sono stabili==, ==dopo un intervallo ∆y sono stabili gli output della ALU, dello shifter e dei valori di N e Z.

3) N e Z sono memorizzati in due memorie ad un bit, dopo l'intervallo ∆z, ==l'output dello shifter raggiunge i registri lungo il bus C.==

4) Nel frattempo il ==microprogramma determina la microistruzione successiva,== il calcolo di questo indirizzo ==avviene dopo che MIR è stato caricato ed è stabile==, il campo ==NEXT_ADDRESS viene copiato in MPC==, poi viene ==esaminato il campo JAM: se contiene il valore 0 non è richiesto alcun intervento== perché la microistruzione successiva sarà quella che segue la microistruzione corrente.

5) Se invece uno o più dei bit di ==JAM sono settati a 1== si possono verificare ==diverse azioni==: Se ==**JAMN è settato**==, viene calcolato l=='AND logico con il flip-flop N== Analogamente, se **==JAMZ è settato==**, viene calcolato ==l'AND logico con il flip-flop Z== Se risultano ==settati entrambi, si calcola l'AND rispetto ad entrambi==.

6) I componenti che effettuano l'elaborazione sono chiamati in figura come "**Bit alto**" e la funzione booleana che calcola questo bit è: 

   ==F = ( JAMZ AND Z ) OR ( JAMN AND N ) OR NEXT_ADDRESS [8]==

   Z = il risultato è 0
   N = il risultato è negativo
   
   MPC può assumere uno solo dei due possibili valori: 
   NEXT_ADDRESS oppure NEXT_ADDRESS con il bit più significativo calcolato in OR con 1.

7) Quando tutti i bit di **JAM valgono zero**, ==l'indirizzo della microistruzione successiva è semplicemente il numero a 9 bit nel campo NEXT_ADDRESS==, quando invece J**AMN o JAMZ valgono 1**, ci sono due potenziali microistruzioni successive: ==NEXT_ADDRESS oppure NEXT_ADDRESS in OR con 0x100==

8) ==Il terzo bit del campo JAM è JMPC se è settato, gli 8 bit di MBR vengono posti in OR (bit a bit) con gli 8 bit meno significativi di NEXT_ADDRESS prelevati dalla microistruzione corrente;== ==il risultato viene inviato a MPC== Il dispositivo indicato con "O" effettua un OR di MBR con NEXT_ADDRESS se JMPC vale 1, ma invia NEXT_ADDRESS a MPC se JMPC vale zero Questo permette di implementare una diramazione, specificando uno dei 256 possibili indirizzi.![[microarchitetturaMIC1.png]]


## Stack

Tutti i linguaggi di programmazione supportano il concetto di metodo/funzione.
==La gestione delle variabili locali di un metodo viene effettuata mediante l'utilizzo di una parte di memoria chiamata **stack**, riservata alle variabili.==

Vi si accede mediante il registro **LV** che ==punta alla base delle variabili locali della funzione corrente, mentre== il registro **SP** ==punta alla parola in cima allo stack.==
![[stackA.png]]
==Oltre ad ospitare le variabili locali, lo stack contiene gli operandi delle espressioni aritmetiche==
![[stackB.png]]
## Modello di memoria

La memoria della macchina IJVM è di 4GB, cioè un array di:

- 4.294.967.296 byte
- 1.073.741.824 parole di 4 byte

Sono definite le seguenti aree di memoria:

- **Constant pool**: ==Contiene costanti, stringhe, puntatori e non è modificabile.==
- **Local Variable Frame**: ==Contiene variabili locali allocate al momento dell'invocazione di un metodo.==
- **Operand stack**: Lo stack degli operandi.
- **Method Area**: ==Contiene il programma, il PC prende le istruzioni da qui.==

## Insieme istruzioni IJVM

![[istruzioni.png]]
Inserimento nello stack di una parola proveniente da:

- Porzione costante di memoria (**LDC_W)
- Blocco variabili locali (**ILOAD**)
- Istruzione stessa (**BIPUSH**)

Inserimento nel blocco delle variabili locali di una parola proveniente da:

- stack(**ISTORE**)

Operazioni logiche e aritmetiche (sugli operandi in cima allo stack):

- **IADD, ISUB**
- **IAND, IOR**

Operazione per i salti:

- **GOTO** (salto condizionato)
- **IFEQ, IFLT, IF_ICMPEQ**

Operazioni sugli operandi in cima allo stack:

- Scambio di due parole (**SWAP**)
- Duplicazione di una parola (**DUP**)
- Rimozione di una parola (**POP**)

Invocazione di un altro metodo:

- **INVOKERVIRTUAL 

Terminazione di un metodo:

- **IRETURN**


## Invocazione di una procedura

==La procedura chiamante carica sullo stack un riferimento ==(puntatore **OBFREF**) ==alla procedura chiamata,== vengono poi caricati sullo stack i parametri del metodo e viene eseguita l'istruzione **INVOKEVIRTUAL**..

Questa istruzione richiede un argomento, disp (displacment) che indica la posizione nel **constant pool** che ==contiene l'indirizzo di inizio dell'area dei metodi per la procedura chiamata.==

**NB: l'indirizzo iniziale nell'area dei metodi non coincide con l'indirizzo del primo opcode della procedura.**

4 byte nell'area di memoria contengono dati speciali cosi composti:

- I primi ==2 byte indicano il numero di parametri nella procedura==.
- altri ==due byte indicano la dimensione dell'area delle variabili locali== alla procedura chiamata.

Il ==quinto byte contiene il primo opcode da eseguire.==

Ci sono due informazioni fondamentali per il funzionamento di una procedura che sono contenute nei registri PC e LV, quindi bisogna salvarne gli stati allo scopo di ripristinare l'esecuzione della procedura chiamante.

I contenuti dei registri vengono salvati in questo modo:

- ==Parola precedente usata per allocare OBJREF è usata per salvare un puntatore alla parola che contiene il PC della procedura chiamante, il suo indirizzo è salvato in LV.==

- Il valore di ==SP viene aggiornato alla parola dello stack che contiene il registro LV della procedura chiamante.==

- il valore di ==PC viene aggiornato al quinto byte dell'area dei metodi.==

## Ritorno di una procedura

==**IRETURN** inverte le operazioni di **INVOKEVIRTUAL:**==

- Dealloca lo spazio usato dalla procedura in rientro.
- Ripristina lo stack allo stato precedente l'invocazione della procedura 
- Rispristina i puntatori LV e PC
- Il valore di ritorno viene copiato dalla cima dello stack al puntatore link
- SP è aggiornato e punta a questa locazione
- Una volta ripristinato il valore originario di PC, l'esecuzione prosegue con la prima istruzione successiva a INVOKEVIRTUAL

