from reportlab.platypus import Paragraph, SimpleDocTemplate, Image, Spacer
from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import ParagraphStyle, getSampleStyleSheet
from reportlab.lib import colors


class MyParagraphStyle(ParagraphStyle):
    def __init__(self):
        super().__init__(self, font="Helvetica",
                         fontSize=12,
                         textColor=colors.darkolivegreen,
                         leftIndent=0)


style_sheet = getSampleStyleSheet()

op = []

header = style_sheet["h1"]
header.pageBreakBefore = 0
header.keepWithNext = 0
header.backColor = colors.indianred
header.textColor = colors.floralwhite

paragraph = Paragraph("Docs Header", header)
op.append(paragraph)

string_ = """Paragraph content text to repeat. """
regular_text = style_sheet["BodyText"]
paragraph2 = Paragraph(string_ * 40, regular_text)
op.append(paragraph2)

op.append(Spacer(0, 20))

img = Image("C:\\Users\\julia\\Downloads\\king_bugs_bunny.jpg", 260, 200)
op.append(img)

paragraph3 = Paragraph(string_ * 10, MyParagraphStyle())
op.append(paragraph3)

aux = SimpleDocTemplate("Platypus.pdf", pagesize=A4, showBoundary=0)
aux.build(op)
