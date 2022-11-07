import sys

from PyQt6.QtWidgets import (QApplication, QWidget,
                             QMainWindow, QStackedLayout, QVBoxLayout, QHBoxLayout, QPushButton)
from Colors import Color


class Example(QMainWindow):

    def __init__(self):
        super().__init__()

        self.setWindowTitle("EX Stacked Layout")

        vBox = QVBoxLayout()
        hBox = QHBoxLayout()

        redBtn = QPushButton("Red")
        redBtn.pressed.connect(self.activateCard1)
        hBox.addWidget(redBtn)

        blueBtn = QPushButton("Blue")
        blueBtn.pressed.connect(self.activateCard2)
        hBox.addWidget(blueBtn)

        greenBtn = QPushButton("Green")
        greenBtn.pressed.connect(self.activateCard3)
        hBox.addWidget(greenBtn)

        scheme = QStackedLayout()
        vBox.addLayout(hBox)
        vBox.addLayout(scheme)

        scheme.addWidget(Color("red"))
        scheme.addWidget(Color("blue"))
        scheme.addWidget(Color("green"))

        scheme.setCurrentIndex(0)

        container = QWidget()
        container.setLayout(vBox)
        self.setCentralWidget(container)

        self.show()

    def activateCard1(self):
        self.schema.setCurrentIndex(0)

    def activateCard2(self):
        self.schema.setCurrentIndex(1)

    def activateCard3(self):
        self.schema.setCurrentIndex(2)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec())
