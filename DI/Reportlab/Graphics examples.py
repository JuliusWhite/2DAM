from reportlab.graphics.charts.barcharts import VerticalBarChart
from reportlab.graphics.charts.piecharts import Pie
from reportlab.graphics.shapes import Drawing
from reportlab.graphics.charts.legends import Legend
from reportlab.lib import colors
from reportlab.lib.pagesizes import A4
from reportlab.platypus import SimpleDocTemplate

doc = SimpleDocTemplate("Grapich info.pdf", pagesize=A4)
guion = []

draw = Drawing(400, 200)
data = [
    (13, 14, 23, 4, 5, 42, 19, 10, 44, 39, 18, 31),
    (12, 23, 25, 19, 32, 39, 12, 34, 37, 21, 29, 42)
]

g_bar = VerticalBarChart()
g_bar.x = 80
g_bar.y = 50
g_bar.height = 125
g_bar.width = 300
g_bar.data = data
g_bar.strokeColor = colors.black

g_bar.valueAxis.valueMin = 0
g_bar.valueAxis.valueMax = 50
g_bar.valueAxis.valueStep = 10
g_bar.categoryAxis.labels.boxAnchor = 'nw'
g_bar.categoryAxis.labels.dx = 8
g_bar.categoryAxis.labels.dy = -2
g_bar.categoryAxis.labels.angle = -30
g_bar.categoryAxis.categoryNames = ["Jan", "Feb", "Mar", "April", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov",
                                    "Dec"]
g_bar.groupSpacing = 10
g_bar.barSpacing = 2

draw.add(g_bar)

guion.append(draw)

draw2 = Drawing(300, 200)

g_pie = Pie()
draw2.add(g_pie)
g_pie.x = 145
g_pie.y = 0
g_pie.width = 170
g_pie.height = 170
g_pie.data = [5, 24, 21, 40, 32]
g_pie.labels = ["Edge", "Brave", "Safari", "Firefox", "Chrome"]

g_pie.slices.strokeWidth = 0.5
g_pie.slices[3].popout = 10
g_pie.slices[3].strokeWidth = 2
g_pie.slices[3].strokeDashArray = [2, 2]
g_pie.slices[3].labelRadius = 1.75
g_pie.slices[3].fontColor = colors.red
g_pie.sideLabels = 1

legend = Legend()
legend.x = 390
legend.y = 0
legend.dx = 8
legend.dy = 8
legend.fontName = "Helvetica"
legend.fontSize = 8
legend.boxAnchor = "n"
legend.columnMaximum = 10
legend.strokeWidth = 1
legend.strokeColor = colors.black
legend.deltax = 75
legend.deltay = 12
legend.autoXPadding = 5
legend.yGap = 0
legend.dxTextSpace = 5
legend.alignment = "right"
legend.dividerLines = 1 | 2 | 4
legend.dividerOffsY = 4.5
legend.subCols.rpad = 30
colores = [colors.grey, colors.orangered, colors.springgreen, colors.antiquewhite, colors.hotpink]

for i, color in enumerate(colores):
    g_pie.slices[i].fillColor = color

legend.colorNamePairs = [(
    g_pie.slices[i].fillColor,
    (g_pie.labels[i][0:20], "%0.2f" % g_pie.data[i])
) for i in range(len(g_pie.data))
]

print(legend.colorNamePairs)

draw2.add(legend)

guion.append(draw2)

doc.build(guion)
