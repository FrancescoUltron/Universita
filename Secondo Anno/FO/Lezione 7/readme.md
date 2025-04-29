# 7 - Maeehine - Linguaggi e Funzioni

Partendo dal concetto di operazione elementare - Turing ha introdotto un modello di calcolo: Machina di Turing che è essenzialmente un linguaggio usato per descrivere degli algoritmi.

> Ogni macchina di Turing è un algoritmo.

Adesso poniamo due domande:

1) Utilizzando la Macchina di Turing - possiamo risolvere **tutti** i problemi?

2) Se esiste un qualche problema che non è risolubile con la Macchina di Turing - non sarà forse possibile risolvere quel problema con un altro modello di calcolo?

Rispondiamo alla seconda domanda per prima - ma per farlo dobbiamo introdurre dei nuovi concetti e formalismi.

---
### Linguaggi e macchine di Turing

Sappiamo che una macchina di Turing di *tipo riconoscitore* calcola una **funzione booleana** - dato un input $x\in \Sigma^*$ andrà a verificare se $x$ soddisfa una certa proprietà o *predicato* $\pi(.)$.

Indichiamo con $o_{T(x)}$ l'esito della comuptazione $T(x)$ - ossia lo stato raggiunto dalla computazione se essa termina - possiamo quindi dire che:$$o_{T(x)} = q_A \iff \pi(x)$$
oppure: $$\{x\in \Sigma^* : o_{T(x)} = q_A\} = \{x\in \Sigma^*:\pi(x)\}$$
Questo è **l'insieme delle parole accettate da una macchina di Turing di tipo riconoscitore.**
La nostra macchina di Turing sa riconoscere le parole che appartengono a tale insieme - ossia che soddisfano una certa proprietà.

---
### Decidere un Linguaggio

> **Definizione:** Un linguaggio $L$ è un sottoinsieme di $\Sigma^*:L\subseteq \Sigma^*$.

> **Definizione:** Il linguaggio complemento $L^c$ di un linguaggio $L\subseteq \Sigma^*$ è l'insieme delle parole non contenute in $L:L^c=\Sigma^*-L$.

Quindi dato un alfabeto $\Sigma$ - un linguaggio $L$ è un insieme di parole costituite da $\Sigma$.

Un linguaggio $L$ è **decidibile** se esiste una macchina di Turing che *termina per ogni input* $x\in \Sigma^*$ e tale che: $$\forall x\in \Sigma^*[o_{T(x)}=q_A \iff x\in L]$$
> La macchina $T$ è detta **decidere** $L$.

Le computazioni della macchina $T$ che decide $L$ **terminano sempre**. Ciò significa che $T$ è in grado di distinguere fra le parole di $L$ e le parole che non appartengono ad $L$.

> Nota che $o_{T(x)}=q_R$ se $x\in L^c$.

---
### Accetare un Linguaggio

Un linguaggio $L$ è **accettabile** se esiste una macchina di Turing tale che: $$\forall x\in\Sigma^*[o_{T(x)}=q_A \iff x\in L]$$
Ossia se:

- Per ogni $x\in L$, la computazione $T(x)$ termina in $q_A$.
- Per ogni $x\notin L$, la computazione $T(x)$ non termina in $q_A$.

> La macchina $T$ è detta **accettare** $L$.

Al contrario dell'altra macchina l'accettabilità non fornisce indicazioni circa l'esito della computazione $T(x)$ nel caso in cui $x\notin L$ - infatti potrebbe accadere che $o_{T(x)} = q_R$ oppure anche che non termina mai.

> La accettabilità di un linguaggio $L$ non dà indicazioni circa l'accettabilità di $L^c$.

Dato un qualsiasi linguaggio $L$ non è banale progettare una macchina di Turing che accetti tutte e sole le parole di $L$. In generale **non è detto** che una tale macchina di Turing esista.

$T$ è solo in grado di dirci se una parola appartiene ad $L$.

---
### Linguaggio Complemento

> Quando un linguaggio $L$ è deciso da una macchina $T$ scriviamo: $L=L(T)$.

*Ogni linguaggio decidibile è anche accettabile* - ma non viceversa.

Una macchina che **decide** un linguaggio su un alfabeto $\Sigma$ sa come comportarsi con tutte le parole presenti in $\Sigma^*$ - ossia *per ogni parola sa accettare o rigettare*.

Una macchina che **accetta** un linguaggio su un alfabeto $\Sigma$ non sa come comportasi sulle parole in $\Sigma^*$ che non sono in $L$.

> Potrebbe esistere una parola sulla quale la macchina non riesce a prendere nessuna decisione.

**La differenza fra decisione e accettazione di un linguaggio è il comportamento della macchina sul linguaggio complemento.**

**Teorema:** Un linguaggio $L\subseteq \Sigma^*$ è decidibile se e soltanto se $L$ e $L^c$ sono accettabili.

**Dimostrazione:** Se $L$ è decidibile allora esiste una macchina di Turing $T$ con input $x$ tale che termina in $q_A$ se $x\in L$ o $q_R$ se $x\in L^c$ - quindi $o_{T(x)}=q_A$ se e soltanto se $x\in L$ - ossia $T$ accetta $L$.
Descriviamo la macchina $T'$ partendo da $T$ - questa avrà gli stessi stati interni più due nuovi stati - ossia $q_{A}^{'}$ e $q_{R}^{'}$ che saranno anche gli stati finali di $T'$ - inoltre avrà le stesse quintuple tranne per due aggiuntive: $$<q_A,u,u,q_{R}^{'},ferma>\;\;\;<q_R,u,u,q_{A}^{'},ferma>\;\;\;\forall u\in \Sigma \cup\{\square\}$$ Quindi le computazioni $T'(x)$ e $T(x)$ conincidono tranne per l'ultima istruzione eseguita da $T'(x)$. Quando $T(x)$ termina in $q_A$ allora $T'(x)$ eseguirà un ultima istruzione e terminerà in $q_R$ - è analogo nel caso opposto. Allora $T'(x)$ accetta $x$ se e soltanto se $T$ rigetta $x$ -ossia se e soltanto se $x\in L^c$. $T'$ accetta $L^c$.
**Viceversa** se $L$ è accettabile e $L^c$ è accettabile allora esistono due macchine di Turing $T_1$ e $T_2$ tali che per ogni $x\in\Sigma^*$ - $T_1(x)$ accetta se e soltanto se $x\in L$ e $T_2(x)$ accetta se e soltanto se $x\in L^c$. I casi opposti non sono specificati - ossia non sappiamo cosa fa la prima macchina con le parole che non apartengono ad $L^c$ e non sappiamo cosa fa la seconda con le parole che non appartengono ad $L$. 
Componendo le due macchine definiamo una macchina $T$ che simulando le computazioni di $T_1$ e $T_2$ su input $x\subseteq\Sigma^*$ decide $L$.

> Osservazione: **Non possiamo simulare la prima e la seconda macchina in ordine** perché non ci sarebbe garanzia di terminazione.

Quindi $T$ dispone di due nastri - su ciascuno è scritta la parola $x$ - la computazione $T(x)$ avviene alternando sui i due nastri singole istruzioni di $T_1$ e $T_2$ nel seguente modo:

1) Esegui una *singola istruzione* di $T_1$ sul nastro $1$: se $T_1$ entra nello stato di accettazione allora $T$ accetta - altrimenti esegui il passo successivo.

2) Esegui una *singola istruzione* di $T_2$ sul nastro $2$: se $T_2$ entra nello stato di accettazione allora $T$ rigetta - altrimenti esegui il passo precedente.

Se $x\in L$ allora prima o poi al primo passo $T_1$ entrerà nello stato di accettazione portando $T$ ad accettare - viceversa se $x\in L^c$ allora prima o poi al secondo passo $T_2$ entrerà nello stato di accettazione portando $T$ a rigettare. Quindi $T$ decide $L\;\;\;\;\square$. 

---
### Funzioni calcolabili e linguaggi decidibili

Torniamo a parlare di macchine di Turing di tipo trasduttore - quindi macchine che hanno un nastro di output. Queste macchine sanno **calcolare** il valore di una funzione generica.

> Scrivono tale valore sul nastro di ouput e poi entrano in $q_F$.

Una **funzione parziale** da un dato insieme $A$ a un insieme $B$ è una qualunque funzione: $$f:A\to B$$
Una funzione è **totale** se il dominio di $f$ concide con $A$.

> Essenzialmente è una funzione definita per **tutti** gli elementi del dominio.

Ci limitiamo a considerare funzioni '*discrete*' - dati due alfabeti finiti $\Sigma_1$ e $\Sigma_2$ - noi consideriamo funzioni $f:\Sigma_1^* \to \Sigma_2^*$.

> Funzioni che trasformano parole in altre parole - inoltre le vogliamo calcolare solo dove sono **definite**.

Una funzione $f:\Sigma_1^* \to \Sigma_2^*$ è **calcolabile** se esiste una macchina di Turing di tipo trasduttore $T$ tale che - per ogni $x\in\Sigma_1^*$ - tale che $f(x)$ è definita - abbiamo che $T(x)=f(x)$.

> Se $f(x)$ è definita - la computazione termina con la parola $f(x)$ sul nastro di output.

Possiamo notare come la definizione sopra **non chiarisce il comportamento** della macchina nel caso in cui l'input non appartenga al dominio della funzione. Questa ambiguità è simile a quella che abbiamo nella definizione di accettabilità di linguaggi.

- Quando scegliamo un $x$ tale che $f(x)$ è definita allora tutto va bene.

- Quando scegliamo un $x$ tale che $f(x)$ non è definita allora può succedere di tutto.

> Il **concetto di calcolabitlità** di una funzione è molto simile al **concetto di accettabilità** di un linguaggio.

Studiamo la relazione tra accettabilità e dedicibilità di linguaggi e calcolabilità di funzioni - quindi associamo una oppurtuna funzione a ciascun linguaggio e viceversa. Sia $\Sigma$ un alfabeto finito ed $L\subseteq \Sigma^*$ un linguaggio. La *funzione caratteristica* $\chi_L:\Sigma^*\to\{0,1\}$ tale che - per ogni $x\in \Sigma^*$:

- $\chi_L(x) = 1$ se $x\in L$
- $\chi_L(x) = 0$ se $x\notin L$

> Qualunque sia $L$ - $\chi_L$ è sempre una funzione totale.

---
**Teorema 3.1:** *Un linguaggio $L$ è decidibile se e soltanto se la funzione $\chi_L$ è calcolabile.*

**Dimostrazione:** Supponiamo che $L$ è decidibile - allora esiste una macchina di tipo riconoscitore $T$ con stato di accettazione $q_A$ e di rigetto $q_R$ tale che:

- $o_{T}(x)=q_A$ se $x\in L$
- $o_{T}(x)=q_R$ se $x\notin L$

*Senza perdità di generalità* supponiamo che $T$ usi un solo nastro. Partendo da $T$ possiamo definire una macchina di tipo *trasduttore* $T'$ a due nastri - che con input $x\in\Sigma^*$ - opera nella maniera seguente:

1) Sul primo nastro - dove sta scritto l'input - esegue la computazione $T(x)$.

2) Se $T(x)$ termina nello stato $q_A$ allora scrive sul nastro di output $1$ - altrimenti $0$ - poi termina.

Siccome $L$ è decidibile il primo passo termina sempre - quindi entrerà sempre in $q_A$ o $q_R$ - il passo due avverrà sempre. Questo dimostra che $\chi_L$ è calcolabile.

**Viceversa** sia $\chi_L$ è calcolabile. Per costruzione $\chi_L$ è totale. Allora esiste una macchina di Turing $T$ di tipo trasduttore che - per ogni $x\in\Sigma^*$ - calcola $\chi_L(x)$. Partendo da $T$ definiamo una macchina di Turing di tipo riconoscitore $T'$ a due nastri che - con input $x\in\Sigma^*$ - opera nel seguente modo:

1) Sul primo nastro esegue la computazione $T(x)$ - scrivendo il risultato nel secondo.

2) Se sul secondo nastro è stato scritto $1$ allora la computazione $T'(x)$ termina in $q_A$ altrimenti in $q_R$.

Siccome la funzione è totale - il passo 1) termina per ogni input. Se $\chi_L(x)=1$ allora il passo 1) termina scrivendo 1 sul secondo nastro - quindi al passo due la computazione termina in $q_A$. Analogo se $\chi_L(x)=0$ allora $q_R$. Questo dimostra che $L$ è decidibile $\square$.

---
Il teorema sopra mostra come ogni linguaggio decidibile possa essere associata una funzione totale e calcolabile.

> Tale funzione è **la funzione caratteristica del linguaggio**.

Possiamo fare l'inverso? - ossia data una funzione $f$ - possiamo associare ad $f$ un linguaggio che sia decidibile se e soltanto se $f$ è calcolabile. Per rispondere alla domanda associamo ad ogni funzione $f:\Sigma^*\to \Sigma_1^*$ il linguaggio: $$L_f=\{<x,y>:x\in\Sigma^* \;e\;y\in\Sigma^*_1\;e\;y=f(x)\}$$
> I concetti di decidibilità di un linguaggi e di calcolabilità di una funzione sono **assimetrici**.

Infatti - Il comportamento di una macchina di Turing che decide un linguaggio decidibile è definito per ogni input. Mentre il comportamento di una macchina di Turing che calcola una funzione calcolabile non è definito ogni volta che l'input non appartiene al dominio.

> Se l'input non appartiene al dominio di $f$ non si sa se la macchina non termini oppure termina con un valore scritto sull'output che non ha relazione con $f$.

Al fine di garantire la dedicibilità di $L_f$ dobbiamo vincolare la funzione $f$ ad un ulteriore ipotesti - ossia deve essere una funzione totale.

**Teorema 3.2:** *Se la funzione $f:\Sigma^*\to\Sigma^*_1$ è totale e calcolabilea allora il linguaggio $L_f \subseteq \Sigma^* \times \Sigma_1^*$ è decidibile.

**Dimostrazione:** $f$ è calcolabile e totale - allora esiste una macchina di Turing di tipo trasduttore $T$ - che per ogni $x\in\Sigma^*$ - calcola $f(x)$. Partendo da $T$ definiamo $T'$ di tipo riconoscitore a due nastri - con input una coppia $<x,y>$ dove:

- $x\in\Sigma^*$
- $y\in\Sigma^*_1$

La macchina opera nella seguente maniera:

1) Sul primo nastro è scritto l'input $<x,y>$.

2) Sul secondo nastro eseguiamo la computazione $T(x)$ - scrivendo il risultato $z$.

3) Esegue un confronto tra $z$ e $y$ se uguali allora $T'(x)$ termina in $q_A$ - altrimenti $q_R$.

$f$ è totale quindi il passo 2) termina per ogni input $x$ - se $f(x)=y$ allora scrive $y$ sul secondo nastro e termina in $q_A$. Se invece $f(x)=z\neq y$ allora il passo 2) termina scrivendo $z$ sel secondo nastro - quindi termina in $q_R$. Questo dimostra che $L_f$ è decidibile. $\square$

---
Il teorema precedente può essere **parzialmente** invertito infatti:

**Teorema 3.4:** *Sia $f:\Sigma^*\to\Sigma^*_1$ una funzione. Se il linguaggio $L_f\subseteq\Sigma^*\times\Sigma^*_1$ è decidibile allora $f$ è calcolabile*.

**Dimostrazione:** Siccome $L_f$ è decidibile allora esiste una macchina di Turing di tipo riconoscitore $T$ con stati finali $Q_F=\{q_A,q_R\}$ - tale che - per ogni $x\in\Sigma^*$ e per ogni $y\in\Sigma^*_1$.

- $o_T(x,y)=q_A$ se $y=f(x)$.
- $o_T(x,y)=q_R$ altrimenti.

*Senza perdita di generalità* - supponiamo che $T$ abbiamo un unico nastro. Definiamo da $T$ - la macchina di tipo trasduttire $T'$ con 4 nastri e input $x\in\Sigma^*$ sul primo nastro. Opera nel seguente modo:

1) Scrive il valore $i=0$ sul primo nastro.

2) Enumera tutte le stringhe $y\in\Sigma^*_1$ la cui lunghezza è pari al valore scritto sul primo nastro - simulando per ciascuna di essere la computazione $T(x,y)$ quindi opera come segue:

- Sia $y$ la prima stringa di lunghezza $i$ non ancora enumerata; allora, scrive $y$ sul secondo nastro;

- Sul terzo nastro esegue la computazione $T(x,y)$;

- Se $T(x,y)$ termina in $q_a$ allora scriviamo sul nastro di output $y$ e terminiamo. altrimenti - eventualmente incrementando il valore di $i$ scritto sul primo nastro se $y$ era l'ultima stringa di lunghezza $i$ - torna al passo 2).

Il passo 2.2 termina sempre perchè $L_f$ è decidibile. Se $x$ appartiene al dominio di $f$ - allora esiste $y'\in\Sigma^*_1$ tale che $y'=f(x)$ e quindi $<x,y'>\in L_f$. Allora prima o poi la stringa $y'$ verrà scritta sul secondo nastro e la computazione $T(x,y')$ terminerà in $q_A$ - Al terzo passo $y'$ verrà scritta sull'output e terminerà. Quindi $f$ è calcolabile. $\square$

> Se $x$ non appartiene al dominio di $f$ allora nessuna stringa $y$ generata al passo 2.2 consente a $T(x,y)$ di terminare nello stato $q_A$ e quindi $T'(x)$ non termina. Quindi l'ipotesi non consente di affermare che $f$ sia totale.