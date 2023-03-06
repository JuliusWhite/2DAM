import os.path
from sys import platform

from reportlab.platypus import Paragraph
from reportlab.platypus import Image
from reportlab.platypus import SimpleDocTemplate
from reportlab.platypus import Spacer

from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.pagesizes import A4
from reportlab.lib import colors


class MyParagraphStyle(ParagraphStyle):
    def __init__(self):
        super().__init__(self, fontSize=14,
                         font='Helvetica',
                         textColor='Red',
                         leftIndent=105)


style_sheet = getSampleStyleSheet()

op = []

header = style_sheet["Heading4"]

header.pageBreakBefore = 0
header.keepWithNext = 0
header.backColor = colors.paleturquoise

paragarph = Paragraph("Docs header", header)
op.append(paragarph)

string_ = """Paragraph content text to repeat. """
regular_text = style_sheet['BodyText']
paragraph2 = Paragraph(string_ * 100, regular_text)
op.append(paragraph2)

op.append(Spacer(0, 20))

img_path = ""
if platform == "linux" or platform == "linux2":
    img_path = "/home/dam2a/Imaxes/Wallpapers/comunist_pingu.jpg"
    img_width = 225
    img_height = 225
elif platform == "win32":
    img_path = "C:\\Users\\julia\\Downloads\\king_bugs_bunny.jpg"
    img_width=260
    img_height=200
img = Image(os.path.realpath(img_path), width=img_width, height=img_height)
op.append(img)

paragraph3 = Paragraph(string_ * 5, MyParagraphStyle())
op.append(paragraph3)

doc = SimpleDocTemplate("Platypus.pdf", pagesize=A4, showBoundary=1)
doc.build(op)
