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

        self.scheme = QStackedLayout()
        vBox.addLayout(hBox)
        vBox.addLayout(self.scheme)

        self.scheme.addWidget(Color("red"))
        self.scheme.addWidget(Color("blue"))
        self.scheme.addWidget(Color("green"))

        self.scheme.setCurrentIndex(0)

        container = QWidget()
        container.setLayout(vBox)
        self.setCentralWidget(container)

        self.show()

    def activateCard1(self):
        self.scheme.setCurrentIndex(0)

    def activateCard2(self):
        self.scheme.setCurrentIndex(1)

    def activateCard3(self):
        self.scheme.setCurrentIndex(2)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec())
