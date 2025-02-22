def ProdottoMatrici(A, B):
    # Assumiamo che A e B siano matrici quadrate di dimensione 2x2
    return [
        [
            A[0][0] * B[0][0] + A[0][1] * B[1][0],
            A[0][0] * B[0][1] + A[0][1] * B[1][1]
        ],
        [
            A[1][0] * B[0][0] + A[1][1] * B[1][0],
            A[1][0] * B[0][1] + A[1][1] * B[1][1]
        ]
    ]

def PotenzaMatrici(M, n):
    if n == 0:
        return [[1, 0], [0, 1]] #Ritorna la matrice identità
    else:
        M = PotenzaMatrici(M, n//2)
        M = ProdottoMatrici(M, M)
        if n % 2 == 1:
            M = ProdottoMatrici(M, [[1, 1], [1, 0]])
        return M


def fibonacci6(MatriceFibonacci, n):
    if n == 0:
        return [[1, 0], [0, 1]] #Ritorna la matrice identità
    else:
        M = PotenzaMatrici(MatriceFibonacci, n//2)
        M = ProdottoMatrici(M, M)
        if n % 2 == 1:
            M = ProdottoMatrici(M, [[1, 1], [1, 0]])
        return M

MatriceFibonacci = [[1, 1], [1, 0]]
n = int(input("Inserisci un numero: "))

M = fibonacci6(MatriceFibonacci, n-1)
print(M[0][0])

#Complessità temporale: O(log(n))



