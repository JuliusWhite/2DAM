from reportlab.pdfgen import canvas
from reportlab.lib.pagesizes import A4

aux = canvas.Canvas("CanvasText.pdf", A4)

string1 = ["Example string", "Example string 2", "Example string 3", "Example string 4"]

text = aux.beginText()
text.setTextOrigin(75, 770)

text.setFont("Helvetica", 12)

for line in string1:
    text.textLine(line)

text.setFillGray(0.6)

continuous_text = """"This is the exaple for multiline text
uses in marcation for python docs.
This way to write comments also allow us to 
write a several lines"""

text.textLines(continuous_text)
text.textLine("")

for font in aux.getAvailableFonts():
    text.setFont(font, 14)
    text.textLine(font + ": Example")
    text.moveCursor(10, 10)

text.setFillColorRGB(0, 0, 1)
text.setFont("Courier", 14)

for i in range(5):
    text.textLine("Example")
    text.setFillColorRGB(1 + i, i / 5, 1 / (i + 1))
    text.setStrokeColorRGB(1 / ((i + 1) * 0.1), i / 2, 1 / (i + 1))
    text.moveCursor(40, 20)

aux.drawText(text)
aux.showPage()
aux.save()
