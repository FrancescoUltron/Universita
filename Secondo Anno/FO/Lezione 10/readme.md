# 10 - Modelli di calcolo e Tesi di Church-Turing

### Breve ricapitolazione

Abbiamo studiato il modello di calcolo Macchina di Turing che è un linguaggio per descrivere algoritmi.

> Ogni macchina di Turing è un algoritmo.

Abbiamo introdotto i concetti di linguaggi decidibili e accettabili - e di funzioni calcolabili - che corrispondono informalmente ai problemi che sappiamo risolvere con una macchina di Turing.

> Possono esistere linguaggi non decidibili o non accettabili - e funzioni non calcolabili. Quindi possono esistere problemi irrisolvibili con la Macchina di Turing.

> Abbiamo visto per esempio l'**Halting Problem.**

La domanda che ci poniamo adesso è: **Non sarà forse possibile decidere/accettare quel linguaggio con un altro modello di calcolo?**

> Ossia esistono dei modelli di calcolo più 'forti' della macchina di Turing?

---
### Nuovi modelli di calcolo e la tesi di Church-Turing

Esistono molti modelli di calcolo - per esempio:

- Le **grammatiche** di Tipo $0$.
- Il modello di Kleene.
- Il $\lambda-$calcolo di Church.
- La logica combinatoria.
- Gli algoritmi normali di Markov.
- I sistemi combinatori di Post.
- Le macchina a registri elementari.
- I comuni **linguaggi di programmazione**.

Tutti questi modelli di calcolo si basano sull'idea di **operazione elementare** - e tutti questi modelli sanno 'risolvere' *tutti e soli* i problemi che possono essere *risolti* tramite le macchine di Turing.

> Ossia tutti i modelli di calcolo sono **Turing-equivalenti**.

Questa osservazione ha condotto *Alonzo Church* e *Alan Turing* ad enunciare la seguente tesi: *Se la soluzione di un problema può essere descritta da una serie finita di passi elementari - allora esiste una macchina di Turing in grado di calcolarlo.* Oppure:

**E' calcolabile tutto e solo ciò che può essere calcolato da una macchina di Turing.**

La tesi afferma che non ci sono formalisimi più potenti delle macchine di Turing in temini computazionali. La tesi di Church-Turing è generalemente accettata - ma non esiste per essa una dimostrazione formale.

> Ovviamente tutti i modelli a cui facciamo riferimento sono 'ragionevoli' e basati sul conetti di operazione elementare.

Noi vedremo un paio di esempi di validità di questa tesi:

- **Un linguaggio di programmazione**
- **Le grammatiche formali**

> Esistono anche modelli di calcolo meno potenti come *grammatiche regolari* - *automi a stati finiti* e le *macchine che terminano sempre*.

---
### Linguaggio di programmazione: PascalMinimo

Il linguaggio di programmazione che prenderemo in considerazione sarà il **PascalMinimo.** 

> E' una versione semplificata dal Pascal.

Questo linguaggio utlizza - oltre al concetto di *variabile* e di *collezione di variabile (array)* - le istruzioni:

- *Assegnazione* - "$x\leftarrow y$".

> $y$ può essere una variabile - valore costante - oppure una espressione contenente variabili e valori costanti.

- *Condizionale* **If** (condizione) **then begin** $<Sequenza\;di\;istruzioni>$ **end** - **else begin** $<Sequenza\;di\;istruzioni>$ **end**.

> Else può essere assente.

- *Loop* **while** (condizione) **do** $<Sequenza\;di\;istruzioni>$ **end** e **for** (...).

- *funzioni*.

- *Input e output*.

> L'istruzione di output deve essere l'ultima istruzione di un programma che consente di comunicare all'esterno il valore di una varibiale o costante.

Assumiamo che se le sequenze di istruzioni sono istruzioni singole si possa omettere l'istruzione **begin-end**. E assumiamo che le istruzioni del programma abbiano inizio mediante una sorta di istruzione denotata dalla parola **input**.

Questo linguaggio lo possiamo usare in sostituzione delle macchine di Turing - ma prima dobbiamo dimostrare che il potere computazionale **PascalMinimo** non differisce da quello delle **macchine di Turing.**

**Teorema 3.5**: *Per ogni programma scritto in accordo con il linguaggio di programmazione* **PascalMinimo** - *esiste una macchina di Turing $T$ di tipo trasduttore che scrive sul nastro di output lo stesso valore fornito in output dal programma*.





