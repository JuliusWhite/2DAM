
tupla1 = (1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10)
tupla2 = (1, 2, 2, 7, 4, 6, 5, 8, 9, 10)
tupla3 = ()
tupla4 = (11,)


# 1.- Escribir una función que reciba una tupla de elementos e indique si se encuentran ordenados de menor a mayor o no.

def test_sorted(tupla):

    if tupla == ():
        return "Empty tuple"
    previous = tupla[0]
    for item in tupla:
        if previous <= item:
            previous = item
        else:
            return False
    return True

print(test_sorted(tupla1))
print(test_sorted(tupla2))
print(test_sorted(tupla3))
print(test_sorted(tupla4))


# 2.- Dominó.

# a) Escribir una función que indique si dos fichas de dominó encajan o no. Las fichas son recibidas en dos tuplas, por ejemplo: (3,4) y (5,4).

def domino_a(token1, token2):

    return (token1[0] == token2[0] or token1[0] == token2[1] or token1[1] == token2[0] or token1[1] == token2[1])

print(domino_a((1,3),(3,4)))


# b) Escribir una función que indique si dos fichas de dominó encajan o no.
# Las fichas son recibidas en una cadena, por ejemplo: 3-4 2-5.
# Nota: utilizar la función split de las cadenas.

def domino_b(token):

    token = token.split()
    if len(token) != 2:
        return False
    else:
        token1 = token[0].split("-")
        token2 = token[1].split("-")
        return (token1[0] == token2[0] or token1[0] == token2[1] or token1[1] == token2[0] or token1[1] == token2[1])

print(domino_b("1-3 4-4 3-2"))


# 3.- Campaña electoral

# a) Escribir una función que reciba una tupla con nombres, y para cada nombre imprima el mensaje "Estimado [nombre], vote por mí".

names = ("Tamara", "Julián", "Pedro")
def campanha_a(names):

    for i in names:
        print("Estimado/a %s, vote pot mí" % i)


campanha_a(names)


# b) Escribir una función que reciba una tupla con nombres, una posición de origen p y una cantidad n, e imprima el mensaje anterior para los n nombres que se encuentran a partir de la posición p.

def campanha_b(names, pos, n):

     for i in range(pos, pos+n):
         print("Estimado %s, vote por mí" % names[i])

campanha_b(names, 1, 2)

# c) Modificar las funciones anteriores para que tengan en cuenta el género del destinatario, para ello, deberán recibir una tupla de tuplas, conteniendo el nombre y el género.

def campanha_c(names):

    for i in names:
        if i[0] == "M":                     #(M)ale/(F)emale
            print("Estimado %s, vote por mí" % i[1])
        else:
            print("Estimada %s, vote por mí" % i[1])


def campanha_d(names, pos):

    for i in range(pos, len(names)):
        if names[i][0]=="M":                #(M)ale/(F)emale
            print("Estimado %s, vote por mí" % names[i][1])
        else:
            print("Estimada %s, vote por mí" % names[i][1])

campanha_c((("F", "Tamara"), ("M", "Julián"), ("M", "Pedro")))
campanha_d((("F", "Tamara"), ("M", "Julián"), ("M", "Pedro")),2)