#Si scriva una funzione, denominata is_matrix, che riceva in input una lista di
#numeri e restituisca True se e solo se la lista può codificare una matrice quadrata.

def is_matrix(a):
    n = len(a)
    if n == 0:
        return False
    for i in range(1, n):
        if len(a[i]) != n:
            return False
    return True


a = [[1, 2, 3],[4, 5, 6], [7, 8, 9]]
print(is_matrix(a))  # True

#La complessità computazionale della funzione è O(n) in quanto il ciclo for viene eseguito n volte, dove n è la lunghezza della lista a.
