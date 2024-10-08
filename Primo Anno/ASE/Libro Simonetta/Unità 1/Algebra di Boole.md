E' un tipo di algebra che vede come protagonista tutte le possibili operazioni con i numeri 0 e 1.

Una funzione booleana di _n_ variabili può essere completamente descritta mediante una tabella di $2^n$ righe; dato che esistono $2^n$ possibili combinazioni dei valori di input, ciascuna riga può quindi indicare il valore della funzione per una data combinazione degli input. Una tabella di questo tipo è chiamata tabella di verità.

**Come implementare un circuito che realizzi una qualsiasi funzione booleana**

1. Si scrive la tabella di verità della funzione.

2. Ci si munisce di invertitori per generare la negazione di ciascun input.

3. Si utilizza una porta AND per ciascun termine il cui valore nella colonna risultato è 1.

4. Si collegano le porte AND agli input appropriati.

5. Si connettono tutti gli output delle porte AND nella porta OR.

Anche se abbiamo dimostrato la possibile implementazione di una qualsiasi funzione booleana mediante le porte NOT, AND e OR, spesso è più vantaggioso implementare circuiti utilizzando un solo tipo di porta logica.
Sia le porte NAND sia le porte NOR costituiscono un insieme di connettivi funzionalmente completo, nel senso che una qualsiasi funzione booleana può essere calcolata usando soltanto uno di questi due tipi di porte. Nessun’altra porta logica gode di questa proprietà; questo è un ulteriore motivo per il quale NAND e NOR sono spesso utilizzate come elementi base nella costruzione dei circuiti.

**Operazioni**: 

- **NOT**  ----> Se A = 1 allora è 0, altrimenti, se A = 0 allora è 1
- **AND** ----> A AND B = 1 solo se A = B = 1
- **OR** ----> A OR B = 0 solo se A = B = 0
- **XOR** ----> E' uguale a 1 solo se ci sono un numero dispari di operandi con valore 1


==*NB: A XOR B = NOT A * B + A * NOT B**==

Esercizio:

Scrivere la versione semplificata di questa funzione booleana:

L = {A,B,C} = (A + NOT B)(A+B)(A+B+C)

1) Tabella di verità:

|  A  |  B  |  C  |     |  L  |
| :-: | :-: | :-: | :-: | :-: |
|  0  |  0  |  0  |     |  0  |
|  0  |  0  |  1  |     |  0  |
|  0  |  1  |  0  |     |  0  |
|  0  |  1  |  1  |     |  0  |
|  1  |  0  |  0  |     |  0  |
|  1  |  0  |  1  |     |  1  |
|  1  |  1  |  0  |     |  1  |
|  1  |  1  |  1  |     |  1  |

2) Mappa di Karnaugh:

| A\BC  | 00  | 01  | 11  | 10  |
| :---: | :-: | :-: | :-: | :-: |
| **0** |  0  |  0  |  0  |  0  |
| **1** |  0  |  ==1==  |  ==1==  |  ==1==  |

3) Semplificazione tramite la mappa di Karnaugh

   **L = AC + AB**
