import sys
from PyQt6.QtWidgets import *


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("CheckBox")

        v_box = QVBoxLayout()

        self.chech_box1 = QCheckBox("Male")
        self.chech_box1.stateChanged.connect(self.ch1_on_state_changed)

        self.chech_box2 = QCheckBox("Female")
        self.chech_box2.stateChanged.connect(self.ch2_on_state_changed)

        self.chech_box1.setChecked(True)
        self.chech_box2.setChecked(False)

        v_box.addWidget(self.chech_box1)
        v_box.addWidget(self.chech_box2)

        widget = QWidget()
        widget.setLayout(v_box)
        self.setCentralWidget(widget)
        self.show()

    def ch1_on_state_changed(self):
        if self.chech_box2.isChecked():
            self.chech_box2.setChecked(False)
            self.chech_box1.setChecked(True)

    def ch2_on_state_changed(self):
        if self.chech_box1.isChecked():
            self.chech_box1.setChecked(False)
            self.chech_box2.setChecked(True)


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MainWindow()
    sys.exit(app.exec())