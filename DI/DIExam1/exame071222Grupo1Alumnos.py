import sys
from PyQt6.QtCore import QSize, Qt
from PyQt6.QtGui import QPixmap
from PyQt6.QtWidgets import (QApplication, QMainWindow,
                             QLabel, QCheckBox, QVBoxLayout, QHBoxLayout, QGridLayout, QListWidget, QPushButton, QComboBox, QSlider,
                             QGroupBox, QWidget)

# Alumno: Julián Lago González #

class FiestraPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Exame 7-12_2022")

        grid = QGridLayout()

        v_box1 = QVBoxLayout()
        v_box2 = QVBoxLayout()
        v_box3 = QVBoxLayout()
        v_box4 = QVBoxLayout()
        v_box5 = QVBoxLayout()

        h_box1 = QHBoxLayout()

        lblCede = QLabel()
        lblCede.setPixmap(QPixmap("CDico.jpeg"))
        chkAnimado = QCheckBox ("Animado")

        v_box1.addWidget(lblCede)
        v_box1.addWidget(chkAnimado)

        self.tedCadroTexto = QListWidget()

        btnEngadirPista = QPushButton("Engadir pista a reproducir")
        self.btnSubirLista = QPushButton("Subir na lista")
        self.btnSubirLista.pressed.connect(self.on_up_pressed)
        self.btnBaixarLista = QPushButton("Baixar na lista")
        self.btnBaixarLista.pressed.connect(self.on_down_pressed)

        btnSaltar = QPushButton("Saltar")
        cmbSaltar = QComboBox()

        h_box1.addWidget(btnSaltar)
        h_box1.addWidget(cmbSaltar)

        v_box2.addWidget(btnEngadirPista)
        v_box2.addWidget(self.btnSubirLista)
        v_box2.addWidget(self.btnBaixarLista)
        v_box2.addLayout(h_box1)

        lblSon = QLabel("Son:")
        lblRitmo = QLabel("Ritmo:")
        lblVolume = QLabel("volume:")

        v_box3.addWidget(lblSon)
        v_box3.addWidget(lblRitmo)
        v_box3.addWidget(lblVolume)

        self.cmbSon = QComboBox()
        self.cmbSon.addItems(["Maracas", "Marimba", "Triangulo", "Timbales"])
        self.cmbSon.currentIndexChanged.connect(self.on_cmbSon_item_selected)

        sldRitmo = QSlider(Qt.Orientation.Horizontal)
        self.sldVolume = QSlider(Qt.Orientation.Horizontal)
        self.sldVolume.sliderReleased.connect(self.on_vlm_slider_moved)

        v_box4.addWidget(self.cmbSon)
        v_box4.addWidget(sldRitmo)
        v_box4.addWidget(self.sldVolume)

        opcionsReproduccion = QGroupBox ("Opcións de reproducción")
        self.chkAsincrono = QCheckBox("Asíncrono")
        self.chkAsincrono.stateChanged.connect(self.on_chkas_checked)
        self.chkENome = QCheckBox("É nome de ficheiro")
        self.chkENome.stateChanged.connect(self.on_chknm_checked)
        self.chkXMlPersistente = QCheckBox("XML Persistente")
        self.chkXMlPersistente.stateChanged.connect(self.on_chkxml_checked)

        v_box5.addWidget(opcionsReproduccion)
        v_box5.addWidget(self.chkAsincrono)
        v_box5.addWidget(self.chkENome)
        v_box5.addWidget(self.chkXMlPersistente)

        grid.addLayout(v_box1, 0, 0, 4, 2)
        grid.addWidget(self.tedCadroTexto, 0, 2, 4, 2)
        grid.addLayout(v_box2, 0, 5, 4, 2)
        grid.addLayout(v_box3, 4, 0, 3, 1)
        grid.addLayout(v_box4, 4, 1, 3, 4)
        grid.addLayout(v_box5, 4, 5, 3, 1)

        widget = QWidget()
        widget.setLayout(grid)
        self.setCentralWidget(widget)
        self.show()

    def on_vlm_slider_moved(self):
        print(self.sldVolume.value())

    def on_cmbSon_item_selected(self, index):
        print(self.cmbSon.itemText(index))

    def on_chkas_checked(self):
        if self.chkAsincrono.isChecked():
            self.tedCadroTexto.addItem(self.chkAsincrono.text())

    def on_chknm_checked(self):
        if self.chkENome.isChecked():
            self.tedCadroTexto.addItem(self.chkENome.text())

    def on_chkxml_checked(self):
        if self.chkXMlPersistente.isChecked():
            self.tedCadroTexto.addItem(self.chkXMlPersistente.text())

    def on_up_pressed(self):
        print(self.tedCadroTexto.row(self.tedCadroTexto.item(self.tedCadroTexto.currentRow())))
        print(self.tedCadroTexto.currentItem())

        item = self.tedCadroTexto.currentItem()
        aux = self.tedCadroTexto.row(self.tedCadroTexto.item(self.tedCadroTexto.currentRow()))

    def on_down_pressed(self):
        print(self.tedCadroTexto.row(self.tedCadroTexto.item(self.tedCadroTexto.currentRow())))
        print(self.tedCadroTexto.currentItem())

        item = self.tedCadroTexto.currentItem()
        aux = self.tedCadroTexto.row(self.tedCadroTexto.item(self.tedCadroTexto.currentRow()))


if __name__ == "__main__":
    aplicacion = QApplication(sys.argv)
    fiestra = FiestraPrincipal()
    aplicacion.exec()