from reportlab.platypus import Paragraph, Image, SimpleDocTemplate, \
    Spacer, Table, TableStyle, PageBreak

from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.pagesizes import A4
from reportlab.lib import colors

doc = SimpleDocTemplate("Tables.pdf", pagesize=A4)

op = []

data = [('', 'Sales', 'Bougths'),
        ('January', 300, 500),
        ('February', -400, 500),
        ('March', 30, -200)]

table = Table(data, colWidths=100, rowHeights=30)
style = [('GRID', (0, 0), (-1, -1), 0.5, colors.black),
        ('TEXTCOLOR', (1, 1), (-1, -1), colors.springgreen)]
table.setStyle(style)

for f, line in enumerate (data):
    for c, value in enumerate(line):
        if type(value) == int and value < 0:
            style.append(('BACKGROUND', (c, f), (c, f), colors.indianred))
table.setStyle(style)

op.append(table)
doc.build(op)
