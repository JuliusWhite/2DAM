import sys
from PyQt6.QtWidgets import *
from Colors import Color


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Stacked Layout")

        v_box = QVBoxLayout()
        h_box = QHBoxLayout()
        self.schema = QStackedLayout()

        r_btn = QPushButton("Red")
        r_btn.pressed.connect(self.activate_card1)
        h_box.addWidget(r_btn)

        g_btn = QPushButton("Green")
        g_btn.pressed.connect(self.activate_card2)
        h_box.addWidget(g_btn)

        b_btn = QPushButton("Blue")
        b_btn.pressed.connect(self.activate_card3)
        h_box.addWidget(b_btn)

        self.schema.addWidget(Color("red"))
        self.schema.addWidget(Color("green"))
        self.schema.addWidget(Color("blue"))

        self.schema.setCurrentIndex(0)

        v_box.addLayout(h_box)
        v_box.addLayout(self.schema)

        widget = QWidget()
        widget.setLayout(v_box)
        self.setCentralWidget(widget)

        self.show()

    def activate_card1(self):
        self.schema.setCurrentIndex(0)

    def activate_card2(self):
        self.schema.setCurrentIndex(1)

    def activate_card3(self):
        self.schema.setCurrentIndex(2)


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MainWindow()
    sys.exit(app.exec())
