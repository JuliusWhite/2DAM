import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class MainWindow(Gtk.Window):
    def __init__(self):
        super().__init__()
        self.set_title("Gtk ex window")

        vBox = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=10)
        vBox.add(Gtk.Button(label="Button 1"))
        vBox.add(Gtk.Button(label="Button 2"))

        hBox = Gtk.Box(orientation=Gtk.Orientation.HORIZONTAL, spacing=5)
        hBox.add(Gtk.Button(label="Button 3"))
        hBox.add(Gtk.Button(label="Button 4"))
        hBox.add(Gtk.Button(label="Button 5"))
        vBox.add(hBox)

        self.add(vBox)

        self.connect("delete-event", Gtk.main_quit)
        self.show_all()


if __name__ == "__main__":
    MainWindow()
    Gtk.main()