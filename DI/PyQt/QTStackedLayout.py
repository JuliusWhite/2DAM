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
        scheme = QStackedLayout()
        vBox.addLayout(hBox)
        vBox.addLayout(scheme)

        scheme.addWidget(Color("red"))
        scheme.addWidget(Color("blue"))
        scheme.addWidget(Color("green"))
        scheme.addWidget(Color("purple"))

        scheme.setCurrentIndex(0)

        container = QWidget()
        container.setLayout(vBox)
        self.setCentralWidget(container)

        self.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec())
