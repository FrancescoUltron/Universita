#lamda è una funzione che svolge un unica operazione
#può prendere in input tutti gli argomenti che vuole

x = lambda a: a + 10 #Sintassi: lambda arguments : expression
print(x(5))

a = [ 3.14, 'python', 2, 'programma', 12, 0, 'corso' ]


#Utilizziamo la funzione sorted e gli passiamo come chiave una funzione lamda
#Che va a inserire in b gli elementi in base al valore intero o alla lunghezza delle stringhe degli elementi 
b = sorted(a, key= lambda e: len(e) if type(e) == str else e)
print(b)

