from reportlab.platypus import Paragraph, Image, SimpleDocTemplate, \
    Spacer, Table, TableStyle, PageBreak

from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.pagesizes import A4
from reportlab.lib import colors

doc = SimpleDocTemplate("Tables.pdf", pagesize=A4)

op = []

data = [('', 'Sales', 'Bougths'),
        ('January', 300, 500),
        ('February', 400, 500),
        ('March', 30, 200)]

table = Table(data, colWidths=100, rowHeights=30)
table.setStyle([('GRID', (0, 0), (-1, -1), 0.5, colors.black)])

op.append(table)
doc.build(op)
