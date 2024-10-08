def deep_copy( a ):

    b = []
    
    for e in a:
        if type(e) == list:
            b.append( deep_copy(e) )
        else:
            b.append( e )
    
    return b

a = [3, [4, 5, 6, [10, 11]], 7, 8, [12, 13, [2,3]]]

b = deep_copy(a)

print(b)

#Funzione di Deep Copy, Serve a copiare una lista in modo tale da creare una lista
#Che non sia influenzata da a 

#b = a sarebbe un alias e quindi la modifica di a influenzerebbe b

# -------------------------------- ALIASING ------------------------------------------

list1 = [1, 2, 3]
list2 = list1 # Aliasing

print("La lista 2 contiene prima della modifica di lista 1: ", list2)
list1[0] = 0
print("La lista 2 contiene dopo la modifica di lista 1: ", list2)# Output: [0, 2, 3]

#Con l'alias creaimo un nuovo riferimento alla lista esistente
#2 o più variabili puntano allo stesso oggetto nella lista
#Se faccio un cambiamento ad una variabile modificherò anche tutte le altre variabili che puntano alla stessa lista


# -------------------------------- COPIA ------------------------------------------

list1 = [1, 2, 3]
list2 = list1[:] # Cloning using slicing

list1[0] = 0
print(list2) # Output: [1, 2, 3]

#Con la copiatura possibile tramite slicing o la funzione copy() possiamo
#copiare gli elementi da una lista all'altra 
#Quando facciamo cambiamenti sulla lista clonata, questi non modificano la lista originale