import sqlite3
from reportlab.lib.pagesizes import A4
from reportlab.lib import colors
from reportlab.platypus import SimpleDocTemplate, Table, TableStyle

# Connect to the database and retrieve the data
conn = sqlite3.connect('perfisUsuarios.bd')
c = conn.cursor()

c.execute('SELECT idPefil, nomePerfil, descricion FROM perfis')
data1 = c.fetchall()

c.execute('SELECT dniUsuario, idPerfil, permiso FROM perfisUsuario')
data2 = c.fetchall()

c.execute('SELECT nome, dni, departamento, correoe, activo FROM usuarios')
data3 = c.fetchall()

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

table1 = Table([('idPefil', 'nomePerfil', 'descricion')] + data1, style=table_style)
table2 = Table([('dniUsuario', 'idPefil', 'permiso')] + data2, style=table_style)
table3 = Table([('Nome', 'DNI', 'Departamento', 'Correoe', 'Activo')] + data3, style=table_style)

op = []

op.append(table1)
op.append(table2)
op.append(table3)
# Create a PDF document and add a table with the data
pdf_doc = SimpleDocTemplate('SQLite.pdf', pagesize=A4)

# Add the table to the PDF document and save it
pdf_doc.build(op)

# Closing db connection
conn.close()
