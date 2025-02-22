def IntegerSort(X, k):
    y = [0] * k  # Inizializza un array di k elementi a 0 O(1)

    for i in range(len(X)): # O(n)
        y[X[i]] += 1  # Incrementa il counter array

    j = 0  # Inizializza l'indice per l'array ordinato

    for i in range(k): # O(k)
        while y[i] > 0: # O(n+k)
            X[j] = i
            j += 1
            y[i] -= 1

    return X

# Esempio di utilizzo
X = [3, 4, 2, 5, 1]
k = 6  # Valore massimo in X + 1
print(IntegerSort(X, k))
    
