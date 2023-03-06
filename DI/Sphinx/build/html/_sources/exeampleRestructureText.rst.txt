My first pyDoc
==============

A paragraph in Restrucrture text is teh most basic block and doest have to be marked in any specia way

We only have to respect tabs. To use *italica*, **black**, ``code``

* This is item 1. A blank line before the first and last items is required.
* This is item 2

1. List items should be NOT sequentially numbered, but need not start at 1 (although not all formatters will honour the first index).
2. This item is non-auto-enumerated

3. List items should be sequentially numbered, but need not start at 1 (although not all formatters will honour the first index).
#. This item is auto-enumerated

Therm
    Definition

    Multiple paragraohs

Anorther Therm
    Definition of the seconf therm


Code example::

    class Product:
    def __init__(self, cod, stock, price):
        self.cod = cod
        self.stock = stock
        self.price = price


Stop of code.
Restart of code:

>>>    def __str__(self) -> str:
        return "Cod: " + str(self.cod) + "\nStock: " + str(self.stock) + "\nPrice: " + str(self.price)

End od code.

Table

=====  =====  ======
   Inputs     Output
------------  ------
  A      B    A or B
=====  =====  ======
False  False  False
True   False  True
False  True   True
True   True   True
=====  =====  ======

Another table example:

+------------+------------+-----------+
| Header 1   | Header 2   | Header 3  |
+============+============+===========+
| body row 1 | column 2   | column 3  |
+------------+------------+-----------+
| body row 2 | Cells may span columns.|
+------------+------------+-----------+
| body row 3 | Cells may  | - Cells   |
+------------+ span rows. | - contain |
| body row 4 |            | - blocks. |
+------------+------------+-----------+