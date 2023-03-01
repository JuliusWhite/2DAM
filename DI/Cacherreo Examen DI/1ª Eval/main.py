import sys
from PyQt6.QtCore import QSize, Qt
from PyQt6.QtGui import QPixmap
from PyQt6.QtWidgets import (QApplication, QMainWindow,
                             QLabel, QCheckBox, QTextEdit, QPushButton, QComboBox, QSlider,
                             QGroupBox, QDial, QWidget, QVBoxLayout, QHBoxLayout, QGridLayout)


class FiestraPrincipal (QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Exame 7-12_2022")

        self.tedCadroTexto = QTextEdit()

        btnAbrirFich = QPushButton("Abrir ficheiro")
        btnReproducir = QPushButton("Reproducir ficheiro")
        btnGardar = QPushButton("Gardar")
        btnEliminar = QPushButton("Eliminar")

        self.dilVolume = QDial()
        self.dilVolume.valueChanged.connect(self.on_sliderChanged)

        chkAnimado = QCheckBox("Animado")

        btnSaltar = QPushButton("Saltar")
        btnSaltar.clicked.connect(self.on_saltarClicked)
        self.cmbSaltar = QComboBox()
        self.cmbSaltar.addItems(("0", "1", "2", "3", "4", "5", "6", "7", "8"))

        opcionsReproduccion = QGroupBox("Opcións de reproducción")

        self.chkFiltrar = QCheckBox("Filtrar antes de reproducir")
        self.chkFiltrar.clicked.connect(self.on_chkFiltrar_selected)
        self.chkEXML = QCheckBox("É XML")
        self.chkEXML.clicked.connect(self.on_chkEXML_selected)
        self.chkRepNPL = QCheckBox("Reproducción NPL")
        self.chkRepNPL.clicked.connect(self.on_chkRepNPL_selected)

        lblVolume = QLabel("volume:")
        lblFormato = QLabel("Formato:")
        lblSaidaAudio = QLabel("SaidaAudio")

        self.sldVolume = QSlider(Qt.Orientation.Horizontal)
        self.sldVolume.setMinimum(0)
        self.sldVolume.setMaximum(8)

        self.cmbFormato = QComboBox()
        self.cmbFormato.addItems(("mp3", "wav", "wma", "ogg"))
        self.cmbFormato.currentTextChanged.connect(self.on_formatoChanged)

        cmbSaidaAudio = QComboBox()

        hBox1 = QHBoxLayout()
        hBox1.addWidget(self.tedCadroTexto)
        vBox11 = QVBoxLayout()
        vBox11.addWidget(self.dilVolume)
        vBox11.addWidget(chkAnimado)
        hBox11 = QHBoxLayout()
        hBox11.addWidget(btnSaltar)
        hBox11.addWidget(self.cmbSaltar)
        w0 = QWidget()
        w0.setLayout(hBox11)
        vBox11.addWidget(w0)
        w = QWidget()
        w.setLayout(vBox11)
        hBox1.addWidget(w)

        hBox2 = QHBoxLayout()
        hBox2.addWidget(btnAbrirFich)
        hBox2.addWidget(btnReproducir)
        hBox2.addWidget(btnGardar)
        hBox2.addWidget(btnEliminar)

        hBox3 = QHBoxLayout()
        vBox31 = QVBoxLayout()
        vBox31.addWidget(self.chkFiltrar)
        vBox31.addWidget(self.chkEXML)
        vBox31.addWidget(self.chkRepNPL)
        opcionsReproduccion.setLayout(vBox31)
        hBox3.addWidget(opcionsReproduccion)
        grid32 = QGridLayout()
        grid32.addWidget(lblVolume, 0, 0)
        grid32.addWidget(self.sldVolume, 0, 1)
        grid32.addWidget(lblFormato, 1, 0)
        grid32.addWidget(self.cmbFormato, 1, 1)
        grid32.addWidget(lblSaidaAudio, 2, 0)
        grid32.addWidget(cmbSaidaAudio, 2, 1)
        ww = QWidget()
        ww.setLayout(grid32)
        hBox3.addWidget(ww)

        vBoxPrincipal = QVBoxLayout()
        w1 = QWidget()
        w1.setLayout(hBox1)
        w2 = QWidget()
        w2.setLayout(hBox2)
        w3 = QWidget()
        w3.setLayout(hBox3)
        vBoxPrincipal.addWidget(w1)
        vBoxPrincipal.addWidget(w2)
        vBoxPrincipal.addWidget(w3)

        widget = QWidget()
        widget.setLayout(vBoxPrincipal)
        self.setCentralWidget(widget)
        self.show()

    def on_sliderChanged(self):
        print("volumen del dial: " + str(self.dilVolume.sliderPosition()))

    def on_formatoChanged(self):
        print("formato: " + self.cmbFormato.currentText())

    def on_saltarClicked(self):
        self.sldVolume.setValue(int(self.cmbSaltar.currentText()))

    def on_chkFiltrar_selected(self):
        if self.chkFiltrar.isChecked():
            self.tedCadroTexto.append(self.chkFiltrar.text())
        else:
            self.tedCadroTexto.clear()
            if self.chkEXML.isChecked():
                self.tedCadroTexto.append(self.chkEXML.text())
            if self.chkRepNPL.isChecked():
                self.tedCadroTexto.append(self.chkRepNPL.text())

    def on_chkEXML_selected(self):
        if self.chkEXML.isChecked():
            self.tedCadroTexto.append(self.chkEXML.text())
        else:
            self.tedCadroTexto.clear()
            if self.chkFiltrar.isChecked():
                self.tedCadroTexto.append(self.chkFiltrar.text())
            if self.chkRepNPL.isChecked():
                self.tedCadroTexto.append(self.chkRepNPL.text())

    def on_chkRepNPL_selected(self):
        if self.chkRepNPL.isChecked():
            self.tedCadroTexto.append(self.chkRepNPL.text())
        else:
            self.tedCadroTexto.clear()
            if self.chkEXML.isChecked():
                self.tedCadroTexto.append(self.chkEXML.text())
            if self.chkFiltrar.isChecked():
                self.tedCadroTexto.append(self.chkFiltrar.text())


if __name__ == "__main__":
    aplicacion = QApplication(sys.argv)
    fiestra = FiestraPrincipal()

    aplicacion.exec()
