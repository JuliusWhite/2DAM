import sys
from PyQt6.QtWidgets import *
from Colors import Color


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Stacked Check")

        self.stacked_layout = QStackedLayout()

        h_box = QHBoxLayout()

        v_box = QVBoxLayout()

        combo_box = QComboBox()
        combo_box.addItems(["Male", "Female", "Other"])
        combo_box.setEditable(False)
        combo_box.setInsertPolicy(QComboBox.InsertPolicy.InsertAlphabetically)
        combo_box.setMaximumSize(QWIDGETSIZE_MAX, 20)

        list = QListWidget()
        list.addItems(["1", "2", "3"])

        btn1 = QPushButton("1")
        btn1.pressed.connect(self.activate_card1)
        h_box.addWidget(btn1)

        btn2 = QPushButton("2")
        btn2.pressed.connect(self.activate_card2)
        h_box.addWidget(btn2)

        btn3 = QPushButton("3")
        btn3.pressed.connect(self.activate_card3)
        h_box.addWidget(btn3)

        self.stacked_layout.addWidget(combo_box)
        self.stacked_layout.addWidget(list)
        self.stacked_layout.addWidget(Color("red"))

        self.stacked_layout.setCurrentIndex(0)

        v_box.addLayout(h_box)
        v_box.addLayout(self.stacked_layout)

        widget = QWidget()
        widget.setLayout(v_box)
        self.setCentralWidget(widget)
        self.show()

    def activate_card1(self):
        self.stacked_layout.setCurrentIndex(0)

    def activate_card2(self):
        self.stacked_layout.setCurrentIndex(1)

    def activate_card3(self):
        self.stacked_layout.setCurrentIndex(2)

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MainWindow()
    sys.exit(app.exec())
