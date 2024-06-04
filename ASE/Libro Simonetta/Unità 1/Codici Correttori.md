
Le memorie dei calcolatori possono commettere degli errori per via di picchi di tensione sulle linee di alimentazione o per altre cause, per proteggersi da tali errori, alcune memorie utilizzano dei codici di rilevazione e/o di correzione degli errori. 
==Quando si impiegano questi codici vengono aggiunti dei bit extra a ogni parola di memoria secondo una modalità particolare. Quando una parola viene letta dalla memoria, si controllano questi bit aggiuntivi per vedere se si è verificato un errore.==

m = bit della parola da trasmettere 
r = bit di controllo
n = m + r ==> **Codeword**

**Distanza di Hamming**: numeri di bit per il quale due parole distano, si calcola facendo lo XOR tra tutti i bit delle due parole e contando i risultati uguali a 1.

==Se fra due parole di codice vi è una distanza di Hamming pari a _d_ allora saranno necessari _d_ errori singoli per trasformare una parola nell’altra.==

###### Esempio:

|  **Prima parola**  |  1  |  0  |  0  |  0  |  1  |  0  |  0  |  1  |
| :----------------: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| **Seconda parola** |  1  |  0  |  1  |  1  |  0  |  0  |  0  |  1  |
|      **XOR**       |  0  |  0  |  1  |  1  |  1  |  0  |  0  |  0  |
Ci sono tre "1" nel risultato, ciò significa che la distanza di Hamming è uguale a 3.





### Funzionamento del codice di Hamming

Abbiamo un parola da trasmette lunga n bit, il numero di bit di controllo deve essere scleto tramite questa disuguaglianza: ==(n + 1) 2^m <= 2^n==.
Ma si può pensare anche in maniera più semplice:

 - Prendi la lunghezza della parola e riscrivila come potenza di 2, ==l'esponente + 1 della potenza è il numeri di bit di controllo==

I bit di controllo controllano tutti i bit che possono rappresentare il bit di controllo e il loro valore è uguale allo XOR di tutti i bit che sono controllati.

Esempio:

il bit di controllo 4 (0011 in binario) controllerà tutte le posizioni in cui ci sta un 1 in ultima e in penultima posizione.

###### Esempio del funzionamento:

Vogliamo trasmettere "F5".

1) Trasformiamo "F5" in binario: "11110101"
2) Calcoliamo il numero di bit di controllo: lunghezza messaggio = 8 = 2^3 -----> 3+1 = 4
3) Lunghezza totale = 8 + 4 = 12


| Posizione | Valore bit di controllo | 12  | 11  | 10  |  9  |  8  |  7  |  6  |  5  |  4  |  3  |  2  |  1  |
| :-------: | :---------------------: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
|           |                         |  1  |  1  |  1  |  1  | b3  |  0  |  1  |  0  | b2  |  1  | b1  | b0  |
|           |                         |     |     |     |     |     |     |     |     |     |     |     |     |
|    b0     |            1            |     |  *  |     |  *  |     |  *  |     |  *  |     |  *  |     |     |
|    b1     |            0            |     |  *  |  *  |     |     |  *  |  *  |     |     |  *  |     |     |
|    b2     |            0            |  *  |     |     |     |     |  *  |  *  |  *  |     |     |     |     |
|    b3     |            0            |  *  |  *  |  *  |  *  |     |     |     |     |     |     |     |     |

Adesso per dimostrare che il codice di Hamming funziona possiamo fare la stessa cosa, ma questa volta:

- Facciamo lo XOR anche con i valori trovati per i bit di controllo

Modifichiamo il messaggio originale:

da 11110101 a 10110101, quindi ci sta un errore di trasmissione nella posizione 11:

| Posizione | Valore bit di controllo | 12  | 11  | 10  |  9  |  8  |  7  |  6  |  5  |  4  |  3  |  2  |  1  |
| :-------: | :---------------------: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
|           |                         |  1  |  0  |  1  |  1  | b3  |  0  |  1  |  0  | b2  |  1  | b1  | b0  |
|           |                         |     |     |     |     |     |     |     |     |     |     |     |     |
|    b0     |            1            |     |  *  |     |  *  |     |  *  |     |  *  |     |  *  |     |  *  |
|    b1     |            1            |     |  *  |  *  |     |     |  *  |  *  |     |     |  *  |  *  |     |
|    b2     |            0            |  *  |     |     |     |     |  *  |  *  |  *  |  *  |     |     |     |
|    b3     |            1            |  *  |  *  |  *  |  *  |  *  |     |     |     |     |     |     |     |

Il valore dei bit di controllo è 1101, cioè 11, proprio la posizione in cui si è verificato l'errore di trasmissione.

==RICORDA: Il codice di Hamming funziona solo su errori singoli==