# 11 - Modelli di calcolo 

Guardiamo ora il teorema inverso della lezione precedente.

**Teorema 3.6:** *Per ogni macchina di Turing deterministica* $T$ *di tipo riconoscitore ad un nastro esiste un programma* $P$ *scritto in accordo alle regole del linguaggio PascalMinimo tale che, per ogni stringa* $x$*, se* $T(x)$ *termina nello stato finale* $q_F \in \{q_A,q_R\}$ *allora P con input x restituisce* $q_F$ *in output.*

**Idea della dimostrazione:** Progettiamo un programma $u$ scritto in PascalMinimo che si comporta come la macchina di Turing Universale - quindi avrà come input la descrizione di una macchina $T$ - il suoi stato iniziale - i suoi stati finali ed un suo input $x$.

L'esecuzione di $u$ sul suo input restituisce un output che corrisponde allo stato in cui terminerebbe la computazione $T(x)$ o che **non termina** qualora $T(x)$ non terminasse.

Per memorizzare le quintuple della macchina $T$ usiamo $5$ array: $Q1,S1,S2,Q2,M$ - inoltre usiamo i valori $-1,0,1$ per rappresentare i *movimenti della testina* - rispettivamente *sinistra, ferma, destra*.

Se la $i-esima$ quintupla di $T$ è $<q,a,b,q^{1},sinistra>$ allora avremo: $$Q1[i]=q\;\;S1[i]=a\;\;S2[i]=b\;\;Q2[i]=q^1\;\;M[i]=-1$$
Quindi:

- $Q1[i]$ memorizza lo stato in cui si deve trovare la macchina per eseguire la quintupla $i$.

- $Q2[i]$ memorizza lo stato in cui deve entrare la macchina dopo aver eseguito la quintupla $i$.

- Analogo per i restanti array.

Rappresentiamo il nastro di $T$ mediante l'array $N$ - che per semplicità - ammette anche valori negativi.

> Il PascalMinimo è un linguaggio inventato - quindi possiamo farlo.

---
### Un programma che simula U

**Input:** 
- Stringa $x_1x_2...x_n$ memorizzata nell'array $N$ - con $N[i]=x_i$ per $i=1,...,n$. Array 
- $Q,\Sigma,Q_1,S_1,S_2,Q_2,M$ descritti nel testo.
- $q_0,q_A,q_R$

``` PascalMinimo
1) q <-- q0;
2) t <-- 1;
3) primaCella <-- 1;
4) ultimaCella <-- n;
5) while(q != q_A AND q != Q_R) do begin
6)	j <-- 1;
7)	trovata <-- falso;
8)	while(j <= k AND trovata = falso) do
9)		if(q=Q1[j] AND N[t]=S1[j]) then trovata <-- vero;
10)		else j <-- j+1;
11)	if(trovata = vero) then begin
12)		N[t] <-- S2[j];
13)		q <-- Q2[j];
14)		t <-- t + M[j];
15)		if(t < primaCella) then begin
16)			primaCella <-- t;
17)			N[t] <-- blank;
18)		end
19)		if(t>ultimaCella) then begin
20)			ultimaCella <-- t;
21)			N[t] <-- blanck;
22)		end
23)	end
24)	else q <-- q_R;
25 end 
26 Output q
```

> $q$ è la variabile dove memorizziamo lo stato interno di $T$ e $t$ è la variabile in cui memorizziamo la posizione della testina di $T$ ad ogni passo della computazione.

> Le variabili $primaCella$ e $ultimaCella$ delimitano l'area dell'array $N$ usata ad ogni istante. $primaCella$ memorizza l'indirizzo della cella del nastro più a sinistra usata fino ad allora dalla computazione $T(x)$ - mentre $ultimaCella$ quella più a destra.

Il programma non è altro che un cloop **while** il cui corpo è una sequenza di istruzioni condizionali - fino a quando l'istruzione corrispondente alla quintupla $i$ i cui primi simboli letti sono $q$ e $N[t]$. Ogni volta che viene eseguita una quintupla - se la testina viene spostata - allora vengono aggiornati i valori di $primaCella$ e $ultimaCella$ e viene inizializzato a $\square$ il valore di $N[t]$. $\;\;\;\;\square$

6 - 10) Vengono esaminate ordinatamente tutte le quintuple di $T$ dalla prima fino alla $k-esima$ fino a quando:

- Ne viene trovata una che può essere eseguita - $trovata = true$.

- Non ne viene trovata alcuna allora $j=k+1$.

12 - 13) Se la quintupla da eseguire è stata trovata la si esegue.

15 -21) Aggiornamento della porzione di nastro usata.

24 - Se non viene trovata alcuna quintupla allora la macchina entra in $q_R$.

---
### Non determinismo

Cosa dobbiamo fare se vogliamo simulare una macchina non deterministica? Semplicemente implementiamo la coda di rondine in PascalMinimo:

- Inizializziamo un contatore $i$ a $1$.

- Simula tutte le computazioni deterministiche di $i$ istruzioni.

- Se una accetta allora accetta.

- Se tutte rigettano allora rigetta.

Se al passo precedente non hai terminato - allora incrementa il valoer di $i$ e ripeti il passo precedente.

Tradotto in PascalMinimo abbiamo:
```PascalMinimo
1 q <- q_0; 
2 i <- 1;
3 primaCella <- 1;
4 ultimaCella <- n;
5 while(q != q_A AND q != q_R) do begin
6    q <- simulaRicorsivo(q_0,1,N,i);
7    i <- i + 1;
8 end
9 Output: q
```

La simulazione di tutte le computazioni deterministiche è eseguita dall'invocazione delle funzione ricorsiva *simulaRicorsivo ($q_0,1,N,i$)* con parametri: Stato interno - posizione della testina - conenuto del nastro - lunghezza delle computazioni da simulare.

Di seguito lo schema della funzione ricorsiva:

//Immagine slide 12.

Il programma al completo:

//Immagine slide 13.

Abbiamo finalmente fatto vedere che il linguaggio PascalMinimo è **Turing-equivalente.**
