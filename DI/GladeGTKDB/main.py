import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

class MainWindow(Gtk.Window):
    def __init__(self):
        super().__init__()
        self.set_title("Gtk ex window")

if __name__ == "__main__":
    MainWindow()
    Gtk.main()
