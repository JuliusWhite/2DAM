from reportlab.platypus import SimpleDocTemplate
from reportlab.lib.pagesizes import A4
from reportlab.lib import colors
from reportlab.graphics.charts.barcharts import VerticalBarChart3D
from reportlab.graphics.charts.piecharts import Pie
from reportlab.graphics.shapes import Drawing
from reportlab.graphics.charts.legends import Legend

doc = SimpleDocTemplate("Graphics.pdf", pagesize=A4)

op = []

draw = Drawing(400, 300)

data = [
    (13, 14, 23, 4, 5, 42, 19, 10, 49, 39, 18, 31),
    (12, 23, 25, 19, 32, 39, 12, 34, 37, 21, 29, 42)
]

b_chart = VerticalBarChart3D()
b_chart.data = data
b_chart.x = 50
b_chart.y = 50
b_chart.height = 150
b_chart.width = 300
b_chart.valueAxis.valueMin = 0
b_chart.valueAxis.valueMax = 50
b_chart.valueAxis.valueStep = 10
b_chart.categoryAxis.categoryNames = ["Jan", "Feb", "Mar", "April", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov",
                                      "Dec"]
b_chart.categoryAxis.labels.boxAnchor = 'ne'
b_chart.categoryAxis.labels.angle = 30
b_chart.categoryAxis.labels.dx = 4
b_chart.categoryAxis.labels.dy = -2

draw2 = Drawing(10, 150)

p_chart = Pie()
p_chart.x = 30
p_chart.y = -40
p_chart.width = 170
p_chart.height = 170
p_chart.data = [10, 20, 30, 40, 50, 60]
p_chart.labels = ["Edge", "Brave", "Safari", "Chrome", "Firefox", "Opera"]

p_chart.slices.strokeWidth = 1
p_chart.slices[3].popout = 10
p_chart.slices[3].strokeWidth = 2
p_chart.slices[3].strokeDashArray = [4, 4]
p_chart.slices[3].labelRadius = 1.5
p_chart.slices[3].fontColor = colors.cadetblue
p_chart.slices[3].fontSize = 14
p_chart.slices[0].fillColor = colors.darkcyan
p_chart.slices[1].fillColor = colors.blueviolet
p_chart.slices[2].fillColor = colors.deepskyblue
p_chart.slices[3].fillColor = colors.darkseagreen
p_chart.slices[4].fillColor = colors.darkorange
p_chart.slices[5].fillColor = colors.darkmagenta

legend = Legend()
legend.x = 310
legend.y = 0
legend.deltax = 70
legend.deltay = 10
legend.autoXPadding = 5
legend.autoYPadding = 2
legend.dx = 10
legend.dy = 10
legend.swdx = 0
legend.swdy = 0
legend.fontName = "Helvetica"
legend.fontSize = 8
legend.strokeWidth = 1
legend.strokeColor = colors.blueviolet
legend.yGap = 2
legend.dxTextSpace = 5
legend.alignment = "right"
legend.dividerLines = 1 | 2 | 4
legend.dividerOffsY = 7
colores = [colors.darkcyan, colors.blueviolet, colors.deepskyblue, colors.darkseagreen, colors.darkorange, colors.darkmagenta]

for i, color in enumerate(colores):
    p_chart.slices[i].fillColor = color

legend.colorNamePairs = [(
    p_chart.slices[i].fillColor,
    (p_chart.labels[i][0:20], "%0.2f" % p_chart.data[i])
) for i in range(len(p_chart.data))]

draw.add(b_chart)
draw2.add(p_chart)
draw2.add(legend)

op.append(draw)
op.append(draw2)

doc.build(op)
