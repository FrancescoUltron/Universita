#Funzione che sorta una lista in base alle volte che un elemento è presente nella lista da quello meno frequente a quello più frequente
#Esempio L = [4, 'a', 2, 'b', 2, 'b', 'b'] deve mutare L nel modo seguente ['a', 4, 2, 2, 'b', 'b', 'b']

def sort_by_freq(L):

    dizionario = {e: 0 for e in L}

    for e in L:
        if(e in dizionario):
            dizionario[e] += 1

    L= sorted(L, key=lambda x: (dizionario[x], L.index(x)))

    return L


L = ['a', 4, 2, 2, 'b', 'b', 'b']
print(sort_by_freq(L))
