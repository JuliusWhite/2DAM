import gi
gi.require_version("Gtk", "3.0")
from gi.repository import Gtk
class Glade:

    def __init__(self):

        builder = Gtk.Builder()
        builder.add_from_file("/home/dam2a/Escritorio/ENDERMAITER/DI/EjemploGlade.glade")
        fiestraPrincipal = builder.get_object("FiestraPrincipal")

        self.txtNome = builder.get_object("txtNome")
        self.labelSaudo = builder.get_object("txtSaudo")
        self.botonSaudo = builder.get_object("btn")
        # fiestraPrincipal.show_all()
        # Esta linea es necesaria a no ser que pongamos Visible = true (marcar la casilla
        # de visible) en el apartado de Common de la ventana principal en glade

        sinais = {"on_txtNome_activate" : self.on_txtNome_activate,
                  "on_botonSaudo_clicked" : self.on_btnSaudo_clicked,
                  "on_wndFiestraPrincipal_destroy" : Gtk.main_quit
                  }
        builder.connect_signals(sinais)
    def on_txtNome_activate(self, control):
        self.labelSaudo.set_text("Ola " + self.txtNome.get_text() + "!")

    def on_btnSaudo_clicked(self, control):
        self.labelSaudo.set_text("Ola " + self.txtNome.get_text())


if __name__ == "__main__":
    Glade()
    Gtk.main()
