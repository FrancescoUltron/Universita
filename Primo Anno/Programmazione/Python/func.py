#Opertaore di spacchettamento *, prende una lista o tupla e tira fuori i valori singoli

lista = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
print(*lista)                               #output: 1 2 3 4 5 6 7 8 9 10 

#Prende due liste e crea delle tuple con gli elementi nella stessa posizione delle liste
list1 = [1, 2, 3,4]
list2 = ['a', 'b', 'c', 'd']

zipped = zip(list1, list2)

print(list(zipped))  # Output: [(1, 'a'), (2, 'b'), (3, 'c')]

#La funzione sum() calcola la somma degli elementi di un iterabile (come una lista o una tupla).

numbers = [1, 2, 3, 4, 5]
print(sum(numbers))  # Output: 15

# Con valore iniziale
print(sum(numbers, 10))  # Output: 25
