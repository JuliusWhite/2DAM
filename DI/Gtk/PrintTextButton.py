import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class FiestraPrincipal2(Gtk.Window):
    def __init__(self):
        super().__init__()

        self.set_title("A miña primeira fiestra gtk")

        # box = Gtk.Box(spacing=6)
        # self.add(box)

        caixaV = Gtk.Box(orientation=Gtk.Orientation.VERTICAL)

        boton = Gtk.Button(label="Pulsame!!!")
        boton.connect("clicked", self.on_button_clicked)
        caixaV.pack_start(boton, False, False, 4)

        self.etiqueta = Gtk.Label(label="Pulsa o boton")
        caixaV.pack_start(self.etiqueta, False, False, 4)

        imaxe = Gtk.Image.new_from_file("/home/dam2a/Descargas/comunist_pingu.jpg")
        caixaV.pack_start(imaxe, True, True, 4)

        self.add(caixaV)

        self.connect("delete-event", Gtk.main_quit)
        self.show_all()

    def on_button_clicked(self, referenciaBoton):
        print("Boton " + referenciaBoton.get_label() + " foi pulsado")
        self.etiqueta.set_text("Boton " + referenciaBoton.get_label() + " foi pulsado")


if __name__ == "__main__":
    FiestraPrincipal2()
    Gtk.main()