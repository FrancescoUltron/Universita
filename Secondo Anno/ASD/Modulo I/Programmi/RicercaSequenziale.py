#Torna la posizione dell'elemento cercato, altrimenti -1
def RicercaSequenziale(Lista, elemento):
    n = len(Lista)
    i = 0
    for i in range(n):
        if Lista[i] == elemento:
            return i
    return -1

Lista = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
elemento = 5
print(RicercaSequenziale(Lista, elemento))