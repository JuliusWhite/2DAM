import sys
from PyQt6.QtWidgets import *
from PyQt6.QtCore import Qt


class ExInterfazSwing(QMainWindow):

    def __init__(self):
        super().__init__()

        self.setWindowTitle("WindowTitle")

        caixaP = QVBoxLayout()
        caixaH1 = QHBoxLayout()
        boton = QPushButton("Boton")
        caixaH1.addWidget(boton)
        checkbox = QCheckBox("Caixa de checkeo")
        caixaH1.addWidget(checkbox)
        caixaP.addLayout(caixaH1)

        caixaH2 = QHBoxLayout()
        caixaP.addLayout(caixaH2)
        caixaV1 = QVBoxLayout()
        caixaH2.addLayout(caixaV1)

        grid = QGridLayout()

        grid.addWidget(QListView(), 0, 0, 7, 1)
        grid.addWidget(QCheckBox("Opción 1"), 0, 1)
        grid.addWidget(QCheckBox("Opción 2"), 1, 1)
        grid.addWidget(QCheckBox("Opción 3"), 2, 1)
        grid.addWidget(QCheckBox("Opción 4"), 3, 1)
        grid.addWidget(QPushButton("Boton"), 6, 1)

        caixaG = QGroupBox()
        caixaG.setTitle("QGroupBox")
        caixaV1.addWidget(caixaG)
        caixaG.setLayout(grid)
        caixaV1.addWidget(caixaG)

        caixaP.addWidget(caixaG)

        textField = QLineEdit("TextField")
        caixaP.addWidget(textField)

        textPassword = QLineEdit("·········")
        caixaP.addWidget(textPassword)

        combobox = QComboBox()
        combobox.addItem("Item 1")
        combobox.addItem("Item 2")
        caixaP.addWidget(combobox)

        caixaV2 = QVBoxLayout()
        caixaH1.addLayout(caixaV2)

        grid = QGridLayout()

        casinha = QCheckBox("Opción 1")
        casinha2 = QCheckBox("Opción 2")
        casinha3 = QCheckBox("Opción 3")
        deslizador = QSlider(Qt.Orientation.Horizontal)
        grid.addWidget(casinha, 0, 0)
        grid.addWidget(casinha2, 1, 0)
        grid.addWidget(casinha3, 2, 0)
        grid.addWidget(QWidget())
        grid.addWidget(QWidget())
        grid.addWidget(deslizador, 5, 0, 1, 2)
        caixaV2.addLayout(grid)

        contedorP = QWidget()
        contedorP.setLayout(caixaP)
        tab = QTabWidget()
        tab.addTab(contedorP, "Primeira solapa")
        tab.addTab(QLabel("Texto da segunda solapa"), "Segunda Solapa")
        caixaV2.addWidget(tab)
        areaTexto = QTextEdit()
        caixaV2.addWidget(areaTexto)

        self.setCentralWidget(contedorP)

        self.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = ExInterfazSwing()
    sys.exit(app.exec())