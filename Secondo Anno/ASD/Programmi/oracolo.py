def CostruisciOracolo(X, k):
    # 1. Sia Y un array di dimensione k
    Y = [0] * k
    # 2. for i=1 to k do Y[i]=0
    # fatto nella riga di prima

    # 3. for i=1 to n do incrementa Y[X[i]]
    for i in range(len(X)):
        Y[X[i]] += 1

    # 4. for i=2 to k do Y[i]=Y[i]+Y[i-1]
    for i in range(1, k):
        Y[i] += Y[i - 1]

    # 5. return Y
    return Y

def InterrogaOracolo(Y, k, a, b):
    # 1. if b > k then b=k
    if b > k:
        b = k

    # 2. if a <= 1 then return Y[b]
    if a <= 1:
        return Y[b]
    else:
        # else return (Y[b]-Y[a-1])
        return Y[b] - Y[a - 1]
    
# Esempio di utilizzo
X = [3, 4, 2, 5, 1]
k = 6  # Valore massimo in X + 1

# Costruisci l'oracolo
Y = CostruisciOracolo(X, k)
print("Oracolo Y:", Y)

# Interroga l'oracolo
a = 2
b = 4
risultato = InterrogaOracolo(Y, k, a, b)
print(f"InterrogaOracolo(Y, {k}, {a}, {b}) = {risultato}")

# Un altro esempio di interrogazione
a = 1
b = 5
risultato = InterrogaOracolo(Y, k, a, b)
print(f"InterrogaOracolo(Y, {k}, {a}, {b}) = {risultato}")

