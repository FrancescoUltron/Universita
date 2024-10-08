def deep_mean1(lista):

    lunghezza = len(lista) 

    if(lunghezza == 0):
        return 0
    
    media = 0

    for e in lista:
        media = media + e

    return media/lunghezza

#La complessità temporale è O(n), poiché: il costo della funzione len è lineare O(n), il costo dell'assegnazione delle variabili è costante O(1) e
#Il costo del ciclo for è di O(n) con n la lunghezza della lista.
#Nel caso migliore la complessità è di O(1), cioè nel caso in cui lunghezza = 0

#La complessità spaziale è O(1), perchè non vengono utilizzate strutture dati aggiuntive, ma solo variabili temporanee.