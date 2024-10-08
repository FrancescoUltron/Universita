#Test sul funzionamento dell'aliasing in Python

def fun(nome):
    print(f"Ciao {nome}, benvenuto in questo test!")
 
 
cheer = fun

print(f'Il ID di () : {id(fun)}')
print(f'The id of cheer() : {id(cheer)}')
 
fun('Geeks')
cheer('Geeks')
