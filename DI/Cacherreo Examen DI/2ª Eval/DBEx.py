import sqlite3
from reportlab.lib.pagesizes import A4
from reportlab.lib import colors
from reportlab.platypus import SimpleDocTemplate, Table, TableStyle

# Connect to the database and retrieve the data
conn = sqlite3.connect('bbdd.dat')
c = conn.cursor()
c.execute('SELECT dni, nome, direccion FROM usuarios')
data = c.fetchall()

# Create a PDF document and add a table with the data
pdf_doc = SimpleDocTemplate('SQLite.pdf', pagesize=A4)
table = Table([('DNI', 'Nome', 'Dirección')] + data)

# Set the table style
table_style = TableStyle([
    ('BACKGROUND', (0, 0), (-1, 0), colors.gray),
    ('TEXTCOLOR', (0, 0), (-1, 0), colors.whitesmoke),
    ('ALIGN', (0, 0), (-1, 0), 'CENTER'),
    ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
    ('FONTSIZE', (0, 0), (-1, 0), 14),
    ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
    ('BACKGROUND', (0, 1), (-1, -1), colors.beige),
    ('TEXTCOLOR', (0, 1), (-1, -1), colors.black),
    ('ALIGN', (0, 1), (-1, -1), 'CENTER'),
    ('FONTNAME', (0, 1), (-1, -1), 'Helvetica'),
    ('FONTSIZE', (0, 1), (-1, -1), 12),
    ('BOTTOMPADDING', (0, 1), (-1, -1), 8),
])

table.setStyle(table_style)

# Add the table to the PDF document and save it
pdf_doc.build([table])
