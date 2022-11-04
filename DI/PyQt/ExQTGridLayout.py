import sys
from PyQt6.QtWidgets import (QApplication, QWidget,
                             QPushButton, QGridLayout, QMainWindow, QVBoxLayout, QLineEdit, QHBoxLayout)


class Example(QMainWindow):

    def __init__(self):
        super().__init__()

        self.setWindowTitle("ExGridLayout")

        grid = QGridLayout()

        grid.addWidget(QPushButton('1'), 0, 0)
        grid.addWidget(QPushButton('2'), 0, 1)
        grid.addWidget(QPushButton('3'), 0, 2)
        grid.addWidget(QPushButton('4'), 1, 0)
        grid.addWidget(QPushButton('5'), 1, 1)
        grid.addWidget(QPushButton('6'), 1, 2)
        grid.addWidget(QPushButton('7'), 2, 0)
        grid.addWidget(QPushButton('8'), 2, 1)
        grid.addWidget(QPushButton('9'), 2, 2)
        grid.setContentsMargins(0, 6, 0, 6)

        gridContainer = QWidget()
        gridContainer.setLayout(grid)
        self.setCentralWidget(gridContainer)

        boxV = QVBoxLayout()
        boxV.setSpacing(0)
        boxV.addWidget(QLineEdit())
        boxV.addWidget(gridContainer)
        boxV.addWidget(QPushButton('0'))

        operatorKeyboard = QGridLayout()
        operatorKeyboard.addWidget(QPushButton('+'), 0, 0)
        operatorKeyboard.addWidget(QPushButton('-'), 1, 0)
        operatorKeyboard.addWidget(QPushButton('x'), 2, 0)
        operatorKeyboard.addWidget(QPushButton('%'), 3, 0)
        operatorKeyboard.addWidget(QPushButton('='), 4, 0)
        operatorKeyboard.setContentsMargins(0, 6, 0, 6)

        boxH = QHBoxLayout()
        control = QWidget()
        control.setLayout(boxV)
        boxH.addWidget(control)
        control = QWidget()
        control.setLayout(operatorKeyboard)
        boxH.addWidget(control)

        container = QWidget()
        container.setLayout(boxH)
        self.setCentralWidget(container)

        self.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec())
