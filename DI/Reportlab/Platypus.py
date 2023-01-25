from reportlab.platypus import Paragraph
from reportlab.platypus import Image
from reportlab.platypus import SimpleDocTemplate
from reportlab.platypus import Spacer

from reportlab.lib.styles import getSampleStyleSheet
from reportlab.lib.pagesizes import A4
from reportlab.lib import colors

style_sheet = getSampleStyleSheet()

op = []

header = style_sheet["Heading4"]

header.pageBreakBefore = 0
header.keepWithNext = 0
header.backColor = colors.paleturquoise

paragarph = Paragraph("Docs header", header)
op.append(paragarph)

doc = SimpleDocTemplate("Platypus.pdf", pagesize=A4, showBoundary=1)
doc.build(op)
