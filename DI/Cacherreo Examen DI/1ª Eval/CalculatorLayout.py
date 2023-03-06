import sys
from PyQt6.QtWidgets import *


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Calculator")

        grid = QGridLayout()

        btn1 = QPushButton("1")
        btn2 = QPushButton("2")
        btn3 = QPushButton("3")
        btn4 = QPushButton("4")
        btn5 = QPushButton("5")
        btn6 = QPushButton("6")
        btn7 = QPushButton("7")
        btn8 = QPushButton("8")
        btn9 = QPushButton("9")
        btn0 = QPushButton("0")

        btn_sum = QPushButton("+")
        btn_sub = QPushButton("-")
        btn_mul = QPushButton("X")
        btn_div = QPushButton("%")
        btn_eq = QPushButton("=")

        # v_box1 = QVBoxLayout()
        # v_box1.setContentsMargins(2, 2, 2, 2)
        # v_box1.setSpacing(0)
        #
        # v_box2 = QVBoxLayout()
        # v_box2.setContentsMargins(2, 2, 2, 2)
        # v_box2.setSpacing(0)
        #
        # v_box3 = QVBoxLayout()
        # v_box3.setContentsMargins(2, 2, 2, 2)
        # v_box3.setSpacing(0)
        #
        # v_box4 = QVBoxLayout()
        # v_box4.setContentsMargins(2, 0, 2, 2)
        # v_box4.setSpacing(0)
        #
        # h_box2 = QHBoxLayout()
        # h_box2.setContentsMargins(2, 2, 2, 2)
        # h_box2.setSpacing(0)

        # v_box1.addWidget(btn1)
        # v_box1.addWidget(btn2)
        # v_box1.addWidget(btn3)
        #
        # v_box2.addWidget(btn4)
        # v_box2.addWidget(btn5)
        # v_box2.addWidget(btn6)
        #
        # v_box3.addWidget(btn7)
        # v_box3.addWidget(btn8)
        # v_box3.addWidget(btn9)
        #
        # h_box2.addWidget(btn0)
        #
        # v_box4.addWidget(btn_sum)
        # v_box4.addWidget(btn_sub)
        # v_box4.addWidget(btn_mul)
        # v_box4.addWidget(btn_eq)
        # v_box4.addWidget(btn_div)

        grid.addWidget(btn1, 0, 0)
        grid.addWidget(btn2, 0, 1)
        grid.addWidget(btn3, 0, 2)
        grid.addWidget(btn4)
        grid.addWidget(btn5)
        grid.addWidget(btn6)
        grid.addWidget(btn7)
        grid.addWidget(btn8)
        grid.addWidget(btn9)
        grid.addWidget(btn0, 3, 0, 1, 3)

        # grid.addWidget(QLineEdit(), 0, 0, 1, 3)
        # grid.addLayout(v_box1, 1, 0)
        # grid.addLayout(v_box2, 1, 1)
        # grid.addLayout(v_box3, 1, 2)
        # grid.addLayout(v_box4, 0, 3, 4, 1)
        # grid.addLayout(h_box2, 2, 0, 1, 3)
        # grid.setContentsMargins(0, 0, 0, 0)

        container = QWidget()
        container.setLayout(grid)
        self.setCentralWidget(container)

        self.show()


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MainWindow()
    app.exec()
