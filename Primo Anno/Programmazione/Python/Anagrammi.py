#L'algoritmo visto a lezione che verifica se due stringhe sono anagrammi richiede tempo O(n log n) 
#dove n è la lunghezza della stringa. Si progetti una funzione che abbia costo lineare.

def Anagramma(parola1, parola2):

    if len(parola1) != len(parola2):
        return False

    dizionario1 = {"a": 0, "b": 0, "c": 0, "d": 0, "e": 0, "f": 0, "g": 0, "h": 0, "i": 0, "l": 0, "m": 0, "n": 0, "o": 0, "p": 0, "q": 0, "r": 0, "s": 0, "t": 0, "u": 0, "v": 0, "z": 0, "x": 0, "y": 0, "w": 0}
    dizionario2 = {"a": 0, "b": 0, "c": 0, "d": 0, "e": 0, "f": 0, "g": 0, "h": 0, "i": 0, "l": 0, "m": 0, "n": 0, "o": 0, "p": 0, "q": 0, "r": 0, "s": 0, "t": 0, "u": 0, "v": 0, "z": 0, "x": 0, "y": 0, "w": 0}

    for char in parola1:
        dizionario1[char] += 1

    for char in parola2:
        dizionario2[char] += 1

    if dizionario2 == dizionario1:
        return True
    else:
        return False

#Complessità temporale di O(n):
#L'inizializzazione delle stringhe da confrontare e del dizionario è O(n)
#Il riempimento dei dizionari ha come complessità O(n) dove n è la lunghezza della stringhe
#Il confronto tra due dizionari è costante O(1)
#Complessità spaziale O(n) dove n è la lunghezza della nostra stringa

#RICORDA: La comparazione fra due dizionari costa O(k + j) dove k e j sono le lunghezze del nostro dizionario
#In questo caso avremo O(k) + O(k) = O(2k), che si può semplificare in O(k)
#Ma k in questo programma è costante (sarà sempre 26, il numero di lettere nell'alfabeto)
#Quindi k = 26 che è un valore costante e allora possiamo dire che la complessità è costante nella comparazione dei dizionari O(1)
