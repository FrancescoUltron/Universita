#Sia a una lista che contiene k elementi di tipo int, k elementi di tipo float e k elementi di tipo str. 
#Si scriva un frammento di codice Python che stampi k righe tali che 
#la prima contienga il primo int seguito dalla prima str seguita dal primo float; 
#la seconda riga mostri il secondo int, la seconda str e il secondo float… 
#e così via fino alla k-esima riga che elenchi l’ultimo int, l’ultima str e l’ultimo float.


def print_list(a):
    k = len(a) // 3
    for i in range(k):
        print(a[i], a[k + i], a[2 * k + i])
    

a = [1, 2, 3, 1.1, 2.2, 3.3, 'a', 'b', 'c']
print_list(a)

#La complessità computazionale della funzione è O(n) in quanto il ciclo for viene eseguito n/3 volte, dove n è la lunghezza della lista a.