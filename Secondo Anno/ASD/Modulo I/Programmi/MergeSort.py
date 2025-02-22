def mergesort(arr): # O(nlogn) funzione che divide la lista in due parti e le ordina

    if len(arr) <= 1:
        return arr
    
    mid = len(arr) // 2

    left = mergesort(arr[:mid]) 
    right = mergesort(arr[mid:])

    return merge(left, right)

def merge(left, right): # O(n) funzione che unisce le due liste ordinate
    result = []
    i = j = 0

    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            result.append(left[i]) ## Se l'elemento nella lista di sinistra è minore di quello nella lista di destra, lo aggiungo alla lista risultato
            i += 1
        else:
            result.append(right[j]) ## Altrimenti aggiungo l'elemento della lista di destra
            j += 1

    result.extend(left[i:])
    result.extend(right[j:])

    return result

A = [5,4,3,2,1]
print(mergesort(A)) # [1, 2, 3, 4, 5]