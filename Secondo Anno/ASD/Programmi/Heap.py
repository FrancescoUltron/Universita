def FixHeap(i, A): # indice di inizio e array
    s = 2 * i + 1 # Figlio sinistro del nodo
    d = 2 * i + 2 # Figlio destro del nodo
    massimo = i

    if s < len(A) and A[s] > A[massimo]: # Se s si trova in A ed è maggiore del padre
        massimo = s

    if d < len(A) and A[d] > A[massimo]: # Se d si trova in A ed è maggiore del padre
        massimo = d

    if massimo != i:
        A[i], A[massimo] = A[massimo], A[i] # Scambio il massimo con i
        FixHeap(massimo, A) # Richiamo funzione su istanza minore

    return A #Complessità O(log n)

def EstraiMax(A):
    max_val = A[0] # Salvo il massimo
    A[0] = A[len(A) - 1] # Sostituisco il massimo con l'ultimo elemento
    A.pop() # Rimuovo l'ultimo elemento
    FixHeap(0, A) # Richiamo la funzione di correzione
    return max_val #Complessità O(log n)

def heapify(H):
    # Partiamo dall'ultimo nodo non foglia e applichiamo FixHeap
    for i in range(len(H) // 2 - 1, -1, -1):
        FixHeap(i, H)
    return H #Complessità O(n)

def HeapSort(H):
    heapify(H) # Creo l'heap
    A = []
    for i in range(len(H)):
        A.append(EstraiMax(H)) # Estraggo il massimo e lo appendo
    return A #Complessità O(n log n)


H = [3,4,2,5,1]
print(HeapSort(H))
