import sys
from PyQt6.QtWidgets import QApplication, QMainWindow, QVBoxLayout, QWidget
from Colors import Color


class FiestraPrincipal(QMainWindow):

    def __init__(self):
        super().__init__()
        self.setWindowTitle("Exemplo QVBoxLayout")

        caixaV = QVBoxLayout()

        caixaV.addWidget(Color("red"))
        caixaV.addWidget(Color("blue"))
        caixaV.addWidget(Color("black"))

        widget = QWidget()
        widget.setLayout(caixaV)
        self.setCentralWidget(widget)
        self.show()

if __name__ == "__main__":
    aplicacion = QApplication(sys.argv)
    fiestra = FiestraPrincipal()
    aplicacion.exec()