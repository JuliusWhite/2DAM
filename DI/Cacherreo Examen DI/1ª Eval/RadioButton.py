import sys
from PyQt6.QtWidgets import *


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("RadioButton")

        v_box = QVBoxLayout()

        self.op1 = QRadioButton("Male")
        v_box.addWidget(self.op1)

        self.op2 = QRadioButton("Female")
        v_box.addWidget(self.op2)

        self.op3 = QRadioButton("Other")
        v_box.addWidget(self.op3)

        widget = QWidget()
        widget.setLayout(v_box)
        self.setCentralWidget(widget)
        self.show()


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MainWindow()
    sys.exit(app.exec())