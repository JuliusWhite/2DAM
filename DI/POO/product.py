class Product:
    def __init__(self, cod, stock, price):
        self.cod = cod
        self.stock = stock
        self.price = price

    def __str__(self) -> str:
        return "Cod: " + str(self.cod) + "\nStock: " + str(self.stock) + "\nPrice: " + str(self.price)
