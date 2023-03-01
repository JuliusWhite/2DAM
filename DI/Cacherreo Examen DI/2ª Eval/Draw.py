from reportlab.graphics.shapes import Drawing, Image
from reportlab.graphics import renderPDF
from reportlab.lib.pagesizes import A4

op = []

img = Image(200, 0, 400, 300, "C:\\Users\\julia\\Downloads\\king_bugs_bunny.jpg")

draw = Drawing(30, 35)
draw.add(img)
draw.translate(0, 400)
op.append(draw)

draw2 = Drawing(30, 30)
draw2.add(img)
draw2.rotate(30)
draw2.scale(0.5, 0.5)
op.append(draw2)

draw3 = Drawing(A4[0], A4[1])
for item in op:
    draw3.add(item)

renderPDF.drawToFile(draw3, "Drawing.pdf")
