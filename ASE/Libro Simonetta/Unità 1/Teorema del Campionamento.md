
I sistemi digitali hanno velocemente soppiantato quelli analogici.

E' quindi chiaro che se vogliamo usare sistemi digitali ==dobbiamo prima riuscire a tradurre grandezze analogiche in digitale (Analog to Digital / ADC) e viceversa (Digital to Analog / DAC).==

Qui entra in gioco il **Teorema del Campionamento di Shannon** che ==ci permette di trasformare un segnale analogico in digitale senza perdita di informazioni importanti.==

Per eseguire questa trasformazione servono tre fasi distinte:

 - **Campionamento**: Si discretizza l'asse temporale del segnale analogico.
 - **Quantizzazione**: Si rendono discreti i valori del segnale analogico.
 - **Codifica**: Si converte la sequenza numerica quantizzata nella sua codifica binaria.

**IMPORTANTE:** 

==Il teorema del campionamento assicura che l'approssimazione discreta di un segnale analogico a banda limitata è senza perdita di informazione utile se la frequenza di campionamento è almeno il doppio rispetto alla massima frequenza del segnale analogico, ovvero se rispetta il criterio di Nyquist-Shannon.==