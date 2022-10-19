import sys
from PyQt6.QtWidgets import QApplication, QMainWindow, QPushButton

class MainWindow (QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("My first window with PyQt6")

        self.show()

aplication = QApplication(sys.argv)
window = MainWindow()
aplication.exec()
