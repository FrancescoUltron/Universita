#Selection Sort: Complessità O(n^2)

def SelectionSort(A):
    for i in range(len(A)): #i è l'indice dell'elemento da sostituire
        min = i 
        for j in range(i+1,len(A)): #j è l'indice del minimo elemento
            if A[j] < A[min]: 
                min = j  
        A[i],A[min] = A[min],A[i] #Scambio
    return A

A = [5,4,3,2,1]
print(SelectionSort(A))

## Come funziona: 
# 1. Si cerca il minimo elemento nella lista A
# 2. Si scambia il minimo con l'elemento in posizione i
# 3. Si incrementa i e si ripete il processo fino a quando i è minore della lunghezza della lista
