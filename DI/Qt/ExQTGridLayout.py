import sys
from PyQt6.QtWidgets import (QApplication, QWidget,
                             QPushButton, QGridLayout, QMainWindow, QVBoxLayout, QLineEdit, QHBoxLayout)


class Example(QMainWindow):

    def __init__(self):
        super().__init__()

        self.setWindowTitle("ExGridLayout")

        grid = QGridLayout()
        grid.addWidget(QLineEdit(), 0, 0, 1, 3)  # 0,0 is ppsition en 1,3 is expansion, 1 row, 3 cols

        grid.addWidget(QPushButton('1'), 1, 0)
        grid.addWidget(QPushButton('2'), 1, 1)
        grid.addWidget(QPushButton('3'), 1, 2)
        grid.addWidget(QPushButton('4'), 2, 0)
        grid.addWidget(QPushButton('5'), 2, 1)
        grid.addWidget(QPushButton('6'), 2, 2)
        grid.addWidget(QPushButton('7'), 3, 0)
        grid.addWidget(QPushButton('8'), 3, 1)
        grid.addWidget(QPushButton('9'), 3, 2)
        grid.addWidget(QPushButton('0'), 4, 0, 1, 3)

        grid.addWidget(QPushButton('+'), 0, 4)
        grid.addWidget(QPushButton('-'), 1, 4)
        grid.addWidget(QPushButton('x'), 2, 4)
        grid.addWidget(QPushButton('%'), 3, 4)
        grid.addWidget(QPushButton('='), 4, 4)
        grid.setContentsMargins(0, 6, 0, 6)

        # gridContainer = QWidget()
        # gridContainer.setLayout(grid)
        # self.setCentralWidget(gridContainer)
        #
        # boxV = QVBoxLayout()
        # boxV.setSpacing(0)
        # boxV.addWidget(QLineEdit())
        # boxV.addWidget(gridContainer)
        # boxV.addWidget(QPushButton('0'))
        #
        # operatorKeyboard = QGridLayout()
        # operatorKeyboard.addWidget(QPushButton('+'), 0, 0)
        # operatorKeyboard.addWidget(QPushButton('-'), 1, 0)
        # operatorKeyboard.addWidget(QPushButton('x'), 2, 0)
        # operatorKeyboard.addWidget(QPushButton('%'), 3, 0)
        # operatorKeyboard.addWidget(QPushButton('='), 4, 0)
        # operatorKeyboard.setContentsMargins(0, 6, 0, 6)
        #
        # boxH = QHBoxLayout()
        # boxH.addLayout(boxV)
        # boxH.addLayout(operatorKeyboard)
        # # control = QWidget()
        # # control.setLayout(boxV)
        # # boxH.addWidget(control)
        # # control = QWidget()
        # # control.setLayout(operatorKeyboard)
        # # boxH.addWidget(control)

        container = QWidget()
        container.setLayout(grid)  ## or (boxH)
        self.setCentralWidget(container)

        self.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec())
