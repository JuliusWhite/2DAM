import sys

from PyQt6.QtWidgets import QApplication, QMainWindow, QPushButton, QVBoxLayout, QWidget, QLabel
from PyQt6.QtGui import QPixmap
from PyQt6.QtCore import Qt

class FiestraPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("A miña primeira fiestra con PyQt6")


        boton = QPushButton("Púlsame!!")
        boton.clicked.connect(self.on_button_clicked)

        self.etiqueta = QLabel("Pulsa o boton")
        fonte = self.etiqueta.font()
        fonte.setPointSize(30)
        self.etiqueta.setFont(fonte)
        self.etiqueta.setAlignment(Qt.AlignmentFlag.AlignHCenter|Qt.AlignmentFlag.AlignVCenter)

        etiqueta2 = QLabel()
        etiqueta2.setPixmap(QPixmap("/home/dam2a/Descargas/comunist_pingu.jpg"))

        caixaV = QVBoxLayout()
        caixaV.addWidget(boton)
        caixaV.addWidget(self.etiqueta)
        caixaV.addWidget(etiqueta2)

        contedor = QWidget()
        contedor.setLayout(caixaV)


        self.setCentralWidget(contedor)
        self.show()

    def on_button_clicked(self):
        print("O boton foi pulsado")


applicacion = QApplication(sys.argv)
fiestra = FiestraPrincipal()
applicacion.exec()

"""Instalar el paquete PyGObject y el paquete PyGObject-stubs en el interprete de python"""


