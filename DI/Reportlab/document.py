from reportlab.pdfgen import canvas

aux = canvas.Canvas("document.pdf")

aux.drawString(0, 0, "Origin position (x, y) = (0, 0)")
aux.drawString(50, 100, "Position (x, y) = (50, 100)")
aux.drawString(150, 500, "Position (x, y) = (150, 500)")

aux.drawImage("/home/dam2a/Imaxes/Wallpapers/comunist_pingu.jpg", 200, 200, 250, 250)

aux.showPage()
aux.save()
