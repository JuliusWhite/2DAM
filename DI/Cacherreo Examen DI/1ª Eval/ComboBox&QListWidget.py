import sys

from PyQt6.QtWidgets import *


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("ComboBox + List")

        v_box = QVBoxLayout()
        v_box.setContentsMargins(2, 2, 2, 2)
        v_box.setSpacing(0)

        self.combo_box = QComboBox()
        self.combo_box.addItems(["Male", "Female", "Other"])
        # self.combo_box.currentIndexChanged.connect(self.on_combo_box_currentIndexChanged)
        # self.combo_box.currentTextChanged.connect(self.on_combo_box_currentTextChanged)
        self.combo_box.setEditable(False)
        self.combo_box.setInsertPolicy(QComboBox.InsertPolicy.InsertAlphabetically)

        self.text_line = QLineEdit()
        self.text_line.setPlaceholderText("Insert text here and press ENTER")
        self.text_line.returnPressed.connect(self.on_text_card_pressed)

        self.list = QListWidget()
        self.list.addItems(["Busy", "Unemployeed", "Other"])
        # self.list.currentItemChanged.connect(self.on_list_currentItemChanged)
        # self.list.currentTextChanged.connect(self.on_list_cirrentTextChanged)

        v_box.addWidget(self.text_line)
        v_box.addWidget(self.combo_box)
        v_box.addWidget(self.list)

        widget = QWidget()
        widget.setLayout(v_box)
        self.setCentralWidget(widget)
        self.show()

    def on_combo_box_currentIndexChanged(self, index):
        print(index)

    def on_combo_box_currentTextChanged(self, text):
        print(text)

    def on_list_currentItemChanged(self, item):
        print(item.text())

    def on_list_cirrentTextChanged(self, text):
        print(text)

    def on_text_card_pressed(self):
        self.list.addItem(self.text_line.text())
        self.text_line.setText("")


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MainWindow()
    app.exec()