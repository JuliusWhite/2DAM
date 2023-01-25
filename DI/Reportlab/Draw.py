from sys import platform
from reportlab.graphics.shapes import Image, Drawing
from reportlab.graphics import renderPDF
from reportlab.lib.pagesizes import A4

op = []

if platform == "linux" or platform == "linux2":
    img = Image(250, 0, 400, 400, "/home/dam2a/Imaxes/Wallpapers/comunist_pingu.jpg")
elif platform == "win32":
    img = Image(200, 0, 400, 300, "C:\\Users\\julia\\Downloads\\king_bugs_bunny.jpg")

draw = Drawing(30, 30)
draw.add(img)
draw.translate(0, 400)

op.append(draw)

draw2 = Drawing(30, 30)
draw2.add(img)
draw2.rotate(30)
draw2.scale(0.5, 0.5)
op.append(draw2)

draw3 = Drawing(A4[0], A4[1])
for img in op:
    draw3.add(img)
renderPDF.drawToFile(draw3, "Draw.pdf")
