a = [3, [4, 5, 6, [10, 11]], 7, 8, [12, 13, [2,3]]]

# Data una lista a,
# scrivere una funzione che ritorni il numero di
# interi contenuti in a ed in tutte le sue liste annidate

def NumElementi(a):

    contatore = 0

    for e in a:

        if type(e) == int:
            contatore += 1

        elif type(e) in [list, tuple]:
            contatore += NumElementi(e)

    return contatore

print(NumElementi(a))

#La complessità temporale è di O(n)


# Data una lista a,
# scrivere una funzione che ritorni la sommma degli
# interi contenuti in a ed in tutte le sue liste annidate


def SommaElementi(a):

    risultato = 0

    for e in a:

        if type(e) == int:
            risultato += e
        
        elif type(e) in [list, tuple]:
            risultato += SommaElementi(e)
    
    return risultato

print(SommaElementi(a))

#La complessità temporale è di O(n)