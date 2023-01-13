import sys

from PyQt6.QtCore import Qt
from PyQt6.QtWidgets import (QApplication, QWidget,
                             QPushButton, QGridLayout, QMainWindow, QVBoxLayout, QLineEdit, QHBoxLayout, QCheckBox)


class Example(QMainWindow):

    def __init__(self):
        super().__init__()

        self.setWindowTitle("CheckBox")

        vBox = QHBoxLayout()

        self.chckMale = QCheckBox("Male")
        self.chckMale.setCheckState(Qt.CheckState.Checked)
        self.chckMale.clicked.connect(self.on_chckMale_statedChanged)

        vBox.addWidget(self.chckMale)

        self.chckFemale = QCheckBox("Female")
        self.chckFemale.setCheckState(Qt.CheckState.Unchecked)
        self.chckFemale.clicked.connect(self.on_chckFemale_statedChanged)

        vBox.addWidget(self.chckFemale)

        container = QWidget()
        container.setLayout(vBox)

        self.setCentralWidget(container)
        self.show()

    def on_chckMale_statedChanged(self, estado):
        if self.chckFemale.isChecked():
            self.chckFemale.setCheckState(Qt.CheckState.Unchecked)

    def on_chckFemale_statedChanged(self, estado):
        if self.chckMale.isChecked():
            self.chckMale.setCheckState(Qt.CheckState.Unchecked)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec())
