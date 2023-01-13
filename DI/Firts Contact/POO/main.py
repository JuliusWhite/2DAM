from product import Product
from person import Person

# p2 = Product(1, 20, 9.99)
# print(p2)
#
# print()

p1 = Person("Manu", 53)
p3 = Person("Hugo", 19)

print(p3.__lt__(p1))
print(p3.__le__(p1))
print(p3.__eq__(p1))
print(p3.__ne__(p1))
print(p3.__gt__(p1))
print(p3.__ge__(p1))

print(p1>p3)
