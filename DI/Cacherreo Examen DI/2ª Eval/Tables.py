from reportlab.platypus import SimpleDocTemplate, Table
from reportlab.lib.pagesizes import A4
from reportlab.lib import colors

doc = SimpleDocTemplate("Tables.pdf", pagesize=A4)

op = []

data = [("", "Sales", "Boughts"),
        ('January', 300, 500),
        ('February', -400, 500),
        ('March', 30, -200)]

style = [('GRID', (0, 0), (-1, -1), 0.5, colors.black),
         ("FONT", (0, 0), (-1, -1), "Courier")]


for r, line in enumerate(data):
    for c, value in enumerate(line):
        if type(value) == int:
            if value < 0:
                style.append(("TEXTCOLOR", (c, r), (c, r), colors.indianred))
            else:
                style.append(("TEXTCOLOR", (c, r), (c, r), colors.darkolivegreen))

table = Table(data, colWidths=100, rowHeights=30, style=style)

op.append(table)

doc.build(op)
