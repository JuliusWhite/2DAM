from reportlab.graphics.charts.barcharts import VerticalBarChart
from reportlab.graphics.charts.piecharts import Pie
from reportlab.graphics.shapes import Drawing
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
g_bar.x = 50
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
g_pie.x = 65
g_pie.y = 15
g_pie.width = 170
g_pie.height = 170
g_pie.data = [10, 20, 30, 40, 50]
g_pie.labels = ["Edge", "Brave", "Safari", "Firefox", "Chrome"]

g_pie.slices.strokeWidth = 0.5
g_pie.slices[3].popout = 10
g_pie.slices[3].strokeWidth = 2
g_pie.slices[3].strokeDashArray = [2, 2]
g_pie.slices[3].labelRadius = 1.75
g_pie.slices[3].fontColor = colors.red
g_pie.sideLabels = 1

guion.append(draw2)

doc.build(guion)
