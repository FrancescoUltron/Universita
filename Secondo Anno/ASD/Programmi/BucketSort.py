def bucket_sort(X, k):
    # 1. Sia Y un array di dimensione k
    Y = [[] for _ in range(k)]

    # 2. for i=1 to k do Y[i]=lista vuota
    # Già fatto nella riga precedente

    # 3. for i=1 to n do
    for i in range(len(X)):
        # 4. appendi il record X[i] alla lista Y[chiave(X[i])]
        index = int(X[i] * k)  # chiave(X[i]) calcolata in base al valore di X[i]
        Y[index].append(X[i])

    # 5. for i=1 to k do
    sorted_array = []
    for i in range(k):
        # 6. copia ordinatamente in X gli elementi della lista Y[i]
        sorted_array.extend(sorted(Y[i]))

    return sorted_array

# Esempio di utilizzo
X = [0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68]
k = len(X)  # Numero di bucket
sorted_X = bucket_sort(X, k)
print(sorted_X)