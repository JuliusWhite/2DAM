import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

class App:
    def __init__(self):

        builder = Gtk.Builder()
        builder.add_from_file("FirstGlade.glade")

        self.lbl_greeting = builder.get_object("greeting_label")
        self.txt_name = builder.get_object("entry")
        self.btn = builder.get_object("btn")

        signals = {
            "on_txt_name_activate" : self.on_txt_name_activate,
            "on_btn_clicked" : self.on_btn_clicked,
            "on_wnd_destroy" : Gtk.main_quit
        }

        builder.connect_signals(signals)

    def on_txt_name_activate(self, control):
        self.lbl_greeting.set_text(self.txt_name.get_text())

    def on_btn_clicked(self, control):
        self.lbl_greeting.set_text(self.txt_name.get_text())

if __name__ == "__main__":
    App()
    Gtk.main()
