import sys
from PyQt6.QtWidgets import *


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("ExcelEx1")

        grid = QGridLayout()

        h_box = QHBoxLayout()
        # h_box.setContentsMargins(2, 2, 2, 2)
        # h_box.setSpacing(0)

        v_box_aux = QVBoxLayout()
        # v_box_aux.setContentsMargins(2, 2, 2, 2)
        # v_box_aux.setSpacing(0)

        v_box1 = QVBoxLayout()
        # v_box1.setContentsMargins(10, 2, 5, 2)
        # v_box1.setSpacing(0)

        v_box2 = QVBoxLayout()
        # v_box2.setContentsMargins(2, 2, 2, 2)
        # v_box2.setSpacing(0)

        v_box3 = QVBoxLayout()
        # v_box3.setContentsMargins(2, 2, 2, 2)
        # v_box3.setSpacing(0)

        tag1 = QLabel("Visible sheets")
        # tag1.setContentsMargins(0, 5, 0, 10)

        list1 = QListWidget()
        list1.addItems(["Sheet 1", "Sheet 2", "Sheet 3"])

        v_box1.addWidget(tag1)
        v_box1.addWidget(list1)

        btn1 = QPushButton("Hide >>")

        btn2 = QPushButton("<< Show")

        v_box2.addWidget(btn1)
        v_box2.addWidget(btn2)

        tag1 = QLabel("Hiden sheets")
        # tag1.setContentsMargins(0, 5, 0, 10)

        list1 = QListWidget()
        list1.addItems(["Sheet 3", "Sheet 4", "Sheet 5"])

        v_box3.addWidget(tag1)
        v_box3.addWidget(list1)

        btn3 = QPushButton("Close")
        # btn3.setMaximumSize(80, 20)
        # btn3.setContentsMargins(5, 5, 5, 10)

        # h_box.addLayout(v_box1)
        # h_box.addLayout(v_box2)
        # h_box.addLayout(v_box3)
        # v_box_aux.addLayout(h_box)
        # v_box_aux.addWidget(btn3)

        grid.addLayout(v_box1, 0, 0, 2, 2)
        grid.addLayout(v_box2, 0, 2, 2, 1)
        grid.addLayout(v_box3, 0, 4, 2, 2)
        grid.addWidget(btn3, 3, 5)


        widget = QWidget()
        widget.setLayout(grid)
        self.setCentralWidget(widget)
        self.show()


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MainWindow()
    sys.exit(app.exec())
