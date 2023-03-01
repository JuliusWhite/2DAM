from reportlab.pdfgen import canvas

doc = canvas.Canvas("Docs.pdf")

doc.drawString(0, 0, "Origin position (x, y) = (0, 0)")
doc.drawString(200, 400, "Origin position (x, y) = (200, 400)")
doc.drawString(350, 800, "Origin position (x, y) = (350, 800)")

doc.drawImage("C:\\Users\\julia\\Downloads\\king_bugs_bunny.jpg", 260, 480, 260, 200)

doc.showPage()
doc.save()
