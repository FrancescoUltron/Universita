# 2 - macchine di Turing

Nella lezione precedente abbiamo visto informalemente cosa è una macchina di Turing a tre nastri - sui primi due ci sta scritto l'input - sul terzo la viene scritta la somma dei numeri.

---
### Definizione di Macchina di Turing

Formalizziamo questo concetto considerando inizialmente macchine di Turing ad un solo nastro. La possiamo definire come:

- Un'unità di controllo che - ad ogni istante - può trovarsi in uno stato interno appartenente ad un certo insieme $Q$ che contiene - fra gli altri - lo stato particolare $q_0$ che fa partire la computazione e un sottoinsieme $Q_F$ di stati che fanno terminare la computazione.

- Un nastro suddiviso in un infinito numero di celle - ciascuna delle quali - ad ogni istante - può essere vuota o contenere un simbolo appartenente ad un alfabeto $\Sigma$ - e sul quale nastro si muove una testina di lettura/scrittura.

- Ad ogni istante - dipendentemente dallo stato interno e da ciò che è letto dalla testina - viene eseguita una quintupla scelta in un insieme $P$ di quintuple.

All'inizio la testina legge il simbolo contenuto nella cella che sta scandendo e la macchina cerca una quintupla i cui primi due elementi sono: **Lo stato in cui si trova** e **il simbolo letto dalla testina.** Se trova tale quintupla la esegue - altrimenti non compie alcuna azione e termina la computazione.

Eseguire una quintupla significa eseguire - nel seguente ordine - tre azioni:

1) **Sovrascrivere** il simbolo nella cella scandita dalla testina con il simbolo indicato dalla quintupla.

2) **Cambiare** - eventualmente - stato interno.

3) **Muovere** - eventualmente - la testina.

Eseguita la prima quintupla - si cerca un'altra quintupla da eseguire e così via - fino a quando nessuna quintupla può essere eseguita.

Ovviamente - quando scriviamo su un nastro - possiamo scrivere qualsiasi cosa. Potrebbe capitare che sul nastro ci siano dei simboli a cui non è associata alcuna quintupla. Discuteremeo del problema nelle prossime lezioni.

---
### Ricapitoliamo

Riassumendo - una macchina di Turing ad un nastro è completamente caratterizzata dai cinque elementi:

- $\Sigma$ - Un insieme *finito* di caratteri che prende il nome di **alfabeto.**
- $Q$ - Un insieme *finito* di **stati interni.**
- Uno stato interno particolare $q_0$ che è lo **stato iniziale.**
- Un sottoinsieme $Q_F$ di $Q$ di **stati finali.**
- Un insieme $P\subset Q\times \Sigma \times \Sigma \times Q \times [sinistra, fermo, destra]$ di **quintuple.**

> Le quintuple non devono essere *ambigue* - ossia non ci devono essere coppie di quintuple che hanno uguali i primi due elementi.

$P$ è una funzione - $P:Q\times \Sigma \to \Sigma \times \Sigma \times Q \times [sinistra, fermo, destra]$.

Possiamo quindi dire che una macchina di Turing - *ad un nastro* - è una **quintupla** $<\Sigma,Q,q_0,Q_F,P>$ dando per assodata l'esistenza di unità di controllo e nastro.

Quando parliamo di macchine ad $k$ nastri valgono le stesse definizioni dette precedentemente ma - in questo caso - le quintuple avranno la seguente forma: $$<q_1,(a_1,a_2, ...,a_k), (b_1,b_2,...b_k), q_2, (m_1,m_2,...m_k)>$$
Dove:

- $(a_1,a_2,...,a_k)$ sono i caratteri che devono essere letti sui relativi nastri.
- $(b_1,b_2,...,b_k)$ sono i caratteri che devono essere scritti sui relativi nastri.
- $(m_1,m_2,...,m_k)$ sono i movimenti che devono essere eseguiti dalle $k$ testine.

Per capire quanti nastri ha una macchina di Turing ci basta vedere l'insieme delle quintuple della macchina.

> Una **macchina di Turing** è la descrizione di un procedimento di calcolo - ossia un *algoritmo* descritto utilizzanfo le regole introdotte da Alan Turing. Le regole introdotte da Turing per descrivere i calcoli costituiscono un *modello di calcolo.* Il modello di calcolo usato prende il nome di **Macchina di Turing.**

---
### Modello Macchina di Turing

Nel modello **Macchina di Turing** la cardinalità dell'alfabeto e dell'insieme degli stati deve essere **finita.** Lo stesso vale per il numero dei nastri.

Ma perché abbiamo bisogno di cardinalità finite?

Facciamo un esempio con l'esercizio della somma di due numeri in riga - stanno sulla dispensa e i lucidi della professoressa. Basterà usare l'alfabeto $\Sigma = N\cup [+]$ e $Q=[q_x:x\in N] \cup [q_i,q_F]$ e le quintuple:

- Per ogni $n\in N$ usiamo $<q_i,(n,\square),(n,\square),q_n,(d,F)>$ - che legge il primo numero entra nello stato corrispondente e muove la testina del primo nastro a destra per andare a cercare il secondo numero.

- Per ogni $n\in N$ usiamo $<q_n,(+,\square),(+,\square),q_n,(d,F)>$ che scavalca il $+$.

- Per ogni $n,m \in N$ usiamo $<q_n,(m,\square),(m,h),q_F,(d,F)>$ dove $h=m+n$.

Molto semplice - peccato che non funziona. Il punto principale è che questa macchina **non possiamo costruirla.** Perché avremmo bisogno di tanti stati interni quanti sono i numeri naturali.

Quindi è necessario - all'interno di questo modello - che il numero di stati - simboli dell'alfabeto e di quintuple sia *costante* - ossia **indipendente dall'input.**

---
### Alcune definizioni

Queste definizioni devono essere **sempre presenti**.

> Osservazione: Viene usata la parola determinisca - significa che $P$ è una funzione.

Inanzitutto dato un alfabeto $\Sigma$ - indichiamo con $\Sigma^*$ l'insieme delle *parole* - quindi l'insieme delle sequenze costituito da un numero finito di elementi di $\Sigma$: $$\Sigma^*=[<x_1x_2...x_n>:n\in \mathbb{N}\land\forall 1\leq i\leq n\;\;\;con\;[x_i\in\Sigma]].$$
> Una **parola** su $\Sigma$ è una sequenza finita di caratteri di $\Sigma$.

Uno **stato globale** $SG$ di una macchina di turing $T$ è una 'forografia' della macchina in un certo istante. $SG$ contiene una descrizione della porzione non blank del nastro di $T$, della posizione della testina e dello stato interno.

> Possiamo definire anche lo stato globale di una macchina con $k$ nastri.

Una **transizione** da uno stato globale $SG_1$ a $SG_2$ avviene quando esiste una quintupla $<q_1,\sigma_1,\sigma_2,q_2,m>\in P$ tale che:

- $q_1$ e $\sigma_1$ sono lo stato interno e il simbolo letto dalla testina in $SG_1$

- $q_2$ e $\sigma_2$ sono lo stato interno e il simbolo che sostituisce $\sigma_1$ nello stato $SG_2$ e la testina si è spostata in accordo ad $m$ nella passaggio da $SG_1$ a $SG_2$.

Una **computazione deterministica** $T(x)$ della macchina $T$ su input $x=<x_1x_2...x_n>\in\Sigma^*$ è una successione: $$SG_0(x),SG_1(x),.....,SG_k(x),.....$$ di stati globali tali che:

- $SG_0$ è lo *stato globale iniziale* di $T(x)$ - la testina si trova nella cella dove è scritto $x_1$ e lo stato interno di $SG_0$ è lo stato iniziale della macchina.

- Se per qualche $k\geq0$ lo stato interno di $SG_k(x)$ è uno stato finale - allora -$\forall i>k$ $SG_i$ non è definito - è quindi $SG_k$ è uno stato finale di $T(x)$.

- Per ogni $i\geq0$ tale che $SG_i(x)$ non è uno stato finale - esiste una transizione da $SG_i(x)$ a $SG_{i+1}(x)$.

> Informalmente una macchina di Turing deterministica ad un nastro è l'esecuzione delle quintuple di $T$ su una sequenza di caratteri scritti sul suo nastro.

Se esiste un indice $h$ tale che $SG_h$ è uno stato globale dal quale non può avvenire alcuna transizione allora la computazione **termina.** Accade quando lo stato interno nel quale $T$ si trova è uno stato finale oppure $P$ non contiene una quintupla che possa essere eseguita da $SG_h$.

> Non tutte le computazioni terminano.

---
### Trasduttori e riconoscitori

Esistono due tipi diversi di macchine di Turing:

- **Trasduttori** - Hanno il compito di *calcolare* un valore. Quello che si calcola è il valore di una funzione - inoltre lo stato finale della macchina non dà alcuna informazione su come è terminata la computazione poiché è una sequenza di caratteri scritta su un nastro. Assumeremo che nell'insieme $Q$ avremo solo uno stato finale $q_F$ - ogni macchina disporrà di almeno due nastri - uno dei quali è il *nastro di output*. La testina che opera sull'output è di sola scrittura e si muove da sinistra a destra. Possiamo generalizzare l'idea per macchine di tipo trasduttore a $k$ nastri - nelle quali avremo $k$ nastri di *input* e *lavoro* e un nastro di output che svolgerà una funzione specifica.

> Il nastro di output è inzialmente vuoto e - una volta che scriviamo un carattere su di esso - questo non potrà essere cancellato.

- **Riconoscitori** - Hanno il compito di *decidere* se l'input appartiene ad un insieme. L'insieme degli stati finali di questo tipo di macchina è composto da due possibili stati $[q_A,q_R]$ - una computazione che termina nello stato di *accettazione* indica che l'input appartiene all'insieme altrimenti - se termina nello stato di *rigetto* - indica che l'input non appartiene all'insieme. 

> *l’output della seconda macchina e codificato nello stato interno ed è di tipo booleano.*

---
**Teorema:** Una macchina $R$ di tipo riconoscitore che utilizza $k$ nastri può essere trasformata in una macchina $T$ di tipo trasduttore che utilizza $k$ nastri più il nastro di output.

**Dimostrazione:** Indichiamo con:

- $\Sigma_R$ - L'alfabeto di $R$.
- $Q_R$ - L'insieme degli stati di $R$.
- $P_R$ - Le quintuple di $R$.

Allora $T$ avrà come:

- Alfabeto - $\Sigma_R \cup [q_A,q_r]$.
- Insieme degli stati - $Q_T = Q_R \cup [q_F]$ 
- Insieme delle quintuple - $P_T = P_R \cup P_{ACC} \cup P_{RIG}$.

> Nota che $q_A$ e $q_R$ in $T$ sono sia stati che simboli.

$P_R$ viene interpretata come una quintupla di $P_T$ che non scrive sul nastro di output - inoltre: $$P_{ACC} = [<q_A,(x_1,...,x_k,\square),(x_1,...,x_k,q_A),q_F,F>:,x_1,...,x_k \in \Sigma_T^K]$$ $$P_{RIG} = [<q_R,(x_1,...,x_k,\square),(x_1,...,x_k,q_R),q_F,F>:,x_1,...,x_k \in \Sigma_T^K]$$
Questi due insiemi di quintuple fanno in modo che una volta raggiunto lo stato $q_A$ o $q_R$ -qualunque cosa venga letta sui primi $k$ nastri - la macchina $T$ scriva sul nastro di output lo stato finale raggiunto dalla computazione di $R(x)$.

---
### Esito di una Computazione

Indichiamo con $o_T(x)$ **l'esito** della computazione $T(x)$ della macchina $T$ sull'input $x$.
Spiegamone il significato in base al tipo di macchina che abbiamo:

- *Trasduttori* - La funzione $o_T(x):\Sigma^* \to \Sigma^*$ è definita per i soli $x\in \Sigma^*$ tali che $T(x)$ termina e - per tali $x$ - il **valore della funzione è la parola calcolata** da tale computazione.

- *Riconoscitori* - La funzione $o_T(x):\Sigma^* \to [q_A,q_R]$ è definita per i soli $x\in \Sigma^*$ tali che $T(x)$ termina e - per tali $x$ - il **valore della funzione è lo stato finale di terminazione** della computazione.

