from reportlab.pdfgen import canvas

string1 = ["Example string", "Example string 2", "Example string 3", "Example string 4"]

aux = canvas.Canvas("Canvas Text.pdf")

text = aux.beginText()
text.setTextOrigin(100, 750)
text.setFont("Courier", 12)

for line in string1:
    text.textLine(line)

text.setFillGray(0.5)

continuous_text = """"This is the exaple for multiline text
uses in marcation for python docs.
This way to write comments also allow us to 
write a several lines"""

text.textLines(continuous_text)

text.textLine("")

for font in aux.getAvailableFonts():
    text.setFont(font, 14)
    text.textLine(font + ": Example")
    text.moveCursor(10, 20)

text.setFillColorRGB(0, 0, 1)
text.setFont("Helvetica", 12)
for i in range(5):
    text.textLine("Blue text")
    text.setFillColorRGB(1 + i, i / 5, 1 / (i + 1))
    text.moveCursor(5, 1.5)

aux.drawText(text)
aux.showPage()
aux.save()
