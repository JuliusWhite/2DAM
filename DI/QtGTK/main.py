import sys
from PyQt6.QtWidgets import QApplication, QMainWindow, QVBoxLayout, QHBoxLayout, QWidget, QGroupBox
from Colors import Color


class FiestraPrincipal(QMainWindow):

    def __init__(self):
        super().__init__()
        self.setWindowTitle("Exemplo QVBoxLayout")

        caixaV = QVBoxLayout()
        caixaV.setContentsMargins(0, 0, 0, 0)
        caixaV.setSpacing(0)

        caixaV.addWidget(Color("purple"))
        caixaV.addWidget(Color("black"))

        caixaH = QHBoxLayout()
        caixaH.setContentsMargins(0, 0, 0, 0)
        caixaH.setSpacing(0)
        caixaH.addWidget(Color("green"))
        caixaH.addWidget(Color("red"))
        widget = QWidget()
        widget.setLayout(caixaH)
        caixaV.addWidget(widget)

        widget = QWidget()
        widget.setLayout(caixaV)
        self.setCentralWidget(widget)
        self.show()

import gi
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

if __name__ == "__main__":
    aplicacion = QApplication(sys.argv)
    fiestra = FiestraPrincipal()
    aplicacion.exec()