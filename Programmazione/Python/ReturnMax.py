#Funziona che torna il massimo nella lista p

def rmax( a, p ):
        
    if p == len(a)-1:
        return a[p]
    else:
        m = rmax(a, p+1)
        if m > a[p]:
            return m
        else:
            return a[p]      
    
print(rmax([3,2,6, 14, 1, 9, 0], 0))

# Complessità temporale Theta(n)
# Complessità spaziale Theta(n)