import sys
from PyQt6.QtWidgets import QMainWindow, QHBoxLayout, QVBoxLayout, QWidget, QApplication

from Colors import Color


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Draft")
        self.resize(1000, 750)

        h_box = QHBoxLayout()
        h_box.setContentsMargins(2, 2, 2, 2)

        h_box.addWidget(Color("pink"))

        v_box1 = QVBoxLayout()
        v_box1.setContentsMargins(2, 2, 2, 2)

        v_box1.addWidget(Color("red"))
        v_box1.addWidget(Color("green"))
        v_box1.addWidget(Color("yellow"))

        v_box2 = QVBoxLayout()
        v_box2.setContentsMargins(2, 2, 2, 2)

        v_box2.addWidget(Color("blue"))
        v_box2.addWidget(Color("brown"))

        h_box.addLayout(v_box1)
        h_box.addLayout(v_box2)

        widget = QWidget()
        widget.setLayout(h_box)
        self.setCentralWidget(widget)
        self.show()


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MainWindow()
    app.exec()
