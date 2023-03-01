# str = "Hola que tal"

# print(str[:2])
# print(str[-3:])
# print(str[::-1])
# print(str.replace("", ", ")[1:-1])
# print(str.replace(" ", "\_"))

tupla1 = (1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10)
tupla2 = (1, 2, 2, 7, 4, 6, 5, 8, 9, 10)
tupla3 = ()
tupla4 = (11,)

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

names = ("Tamara", "Julián", "Pedro")
def campanha_a(names):

    for i in names:
        print("Estimado/a %s, vote pot mí" % i)


campanha_a(names)


def campanha_b(names, pos, n):
    for i in range(pos, pos + n):
        print("Estimado %s, vote por mí" % names[i])


campanha_b(names, 0, 2)

def prime(n):
    if (n == 1):
        return True
    for i in range(2, n):
        if (n % i == 0):
            return False
        else:
            return True

print(prime(7))

nums = [1, 3, 6, 7]

print(type(nums))

