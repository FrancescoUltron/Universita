def RicercaBinaria(Lista, elemento,i,j):
    if i>j:
        return -1
    m = (i+j)//2
    if Lista[m] == elemento:
        return m
    if Lista[m] > elemento:
        return RicercaBinaria(Lista,elemento,i,m-1)
    else:
        return RicercaBinaria(Lista,elemento,m+1,j)
    

Lista = [1,2,3,4,5,6,7,8,9,10]
elemento = 5
print(RicercaBinaria(Lista,elemento,0,len(Lista)-1))
