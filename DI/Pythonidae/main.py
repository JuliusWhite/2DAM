import math
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

nums = [1, 3, 6, 7]

# 5.- Dada una lista de números enteros, escribir una función que:

# a) Devuelva una lista con todos los que sean primos.
def prime(n):
    if (n == 1):
        return True
    for i in range(2, n):
        if (n % i == 0):
            return False
        else:
            return True

l_pri = [n for n in nums if prime(n)]
print(l_pri)

def primeNum(nums):
    toret = []
    for n in nums:
        if (n == 1):
            toret.append(n)
        for i in range (2, n):
            if(n % i == 0):
                break
            else:
                toret.append(n)
                break
    return toret

print(primeNum(nums))

# b) Devuelva la sumatoria y el promedio de los valores.

def sumAndMed(num):
    sum = 0
    for n in nums:
        sum += n
    print("The sum of the list is:", sum, "and its average is:", (sum/len(nums)))

sumAndMed(nums)

# c) Devuelva una lista con el factorial de cada uno de esos números.

l_fact = [math.factorial(n) for n in nums]
print(l_fact)

def fact(nums):
    toret = []
    for n in nums:
        nFact = math.factorial(n)
        toret.append(nFact)
    print(toret)

fact(nums)


# 6.- Dada una lista de números enteros y un entero k, escribir una función que:

# a) Devuelva tres listas, una con los menores, otra con los mayores y otra con los iguales a k.

def retLists(nums, k):
    lt = []
    eq = []
    gt = []
    for i in nums:
        if(i < k):
            lt.append(i)
        elif(i > k):
            gt.append(i)
        else:
            eq.append(k)
    print("Numbers little than", k, ":",lt)
    print("Numbers equals to", k, ":", eq)
    print("Numbers greater than", k, ":",gt)

retLists(nums, 3)

# b) Devuelva una lista con aquellos que son múltiplos de k.

def retMulList(nums, k):
    toret = []
    for i in nums:
        if(k % i == 0):
            toret.append(i)
    print("The multiples of", k, "in the list are:", toret)

retMulList(nums, 6)


def saludar (lingua):
    def saludar_gl():
       print("Ola")
    def saludar_es():
        print("Hola")
    def saludar_en():
       print("Hello")
    lingua_funcion = {"es" : saludar_es,
                      "gl" : saludar_gl,
                      "en" : saludar_en
    }
    return lingua_funcion [lingua]

f = saludar ("en")
f()
saludar("gl")()

def numero_par (n):
    return n % 2 == 0

print(numero_par(5))

l = [1, 2 ,3, 4, 5]

l2 = filter (numero_par, l)
for item in l2:
    print(item)

l3 = filter(lambda n : n % 2 == 0, l)

l5 = [n**2 for n in l]

l6 = [n for n in l if n % 2 == 0]
