#Definire una funzione che riceva come argomenti due liste che si assumono avere la stessa lunghezza,
#contenenti i voti (numeri interi compresi tra 0 e 30) conseguiti da un certo insieme di studenti nelle
#due prove di un esame. Posizioni corrispondenti nelle due liste si riferiscono allo stesso studente.
#La funzione dovrà restituire il valore True se tutti gli studenti hanno superato entrambe le prove;
#in caso contrario dovrà restituire il valore False.
#Per esempio, se le liste fossero [21, 28, 26] e [23, 27, 28], la funzione dovrebbe restituire True,
#mentre se le liste fossero [21, 28, 26] e [23, 12, 28] la funzione dovrebbe restituire False.

def esame(lista1, lista2):

    for voto in lista1 and lista2:
        if voto < 18:
            return False
        
    return True
       

list = [30,23,21]
list2 = [18,23,24]

print(esame(list,list2))

#La complessità è di O(n)

