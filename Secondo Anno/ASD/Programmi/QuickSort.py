def partition(A,i,f): #O(n)

    perno = A[i] ##perno è il primo elemento della lista
    inf = i+1
    sup = f

    while True:
        while inf <= sup and A[inf] <= perno: #Scorre la lista finchè non trova un elemento maggiore del perno
            inf += 1
        while inf <= sup and A[sup] >= perno: #Scorre la lista finchè non trova un elemento minore del perno
            sup -= 1
        if inf <= sup:
            A[inf],A[sup] = A[sup],A[inf] #Scambio
        else:
            break

    A[i],A[sup] = A[sup],A[i] #Scambio del perno con l'elemento in posizione sup

    return sup

def quicksort(A,i,f): #O(nlogn)
    if i < f:
        p = partition(A,i,f)
        quicksort(A,i,p-1)
        quicksort(A,p+1,f)
    return A

A = [5,4,3,2,1]
print(quicksort(A,0,len(A)-1)) # [1, 2, 3, 4, 5]