a = [3, 5, 7, 9, 10, 21]
b = [1, 2, 6, 10, 12, 14, 16, 23]

# siano a e b due liste ordinate
# scrivere una funzione che ordini gli elementi 
# di a e b in una nuova lista

def merge( a, b ):
    n, m = len(a), len(b)
    
    c = []
    i, j = 0, 0
    
    while i < n and j < m: # O(n+m)
        if a[i] < b[j]:
            c.append(a[i])
            i += 1
        else:
            c.append(b[j])
            j += 1
    
    if i == n: # O(n+m)
        c.extend(b[j:])
    else:
        c.extend(a[i:])
    
    return c

# Costo è Theta(n+m) in quanto l'ottimale è almeno
# lineare in n+m

print(merge(a,b))