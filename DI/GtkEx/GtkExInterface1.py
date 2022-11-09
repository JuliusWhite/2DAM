import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class MainWindow(Gtk.Window):
    def __init__(self):
        super().__init__()
        self.set_title("Gtk ex window")

        label1 = Gtk.Label("Visible sheets")
        hideBtn = Gtk.Button(label="Hide >>")
        showBtn = Gtk.Button(label="<< Show")
        label2 = Gtk.Label("Hiden sheets")
        closeLabel = Gtk.Button(label="Close")
        panel1 = Gtk.Entry()
        panel2 = Gtk.Entry()


        grid = Gtk.Grid()

        # with attach

        grid.attach(label1, 0, 0, 1, 1)
        grid.attach(hideBtn, 2, 1, 1, 1)
        grid.attach(showBtn, 2, 2, 1, 1)
        grid.attach(label2, 3, 0, 1, 1)
        grid.attach(closeLabel, 4, 4, 1, 1)
        grid.attach(panel1, 0, 1, 2, 3)
        grid.attach(panel2, 3, 1, 2, 3)
        grid.set_column_homogeneous(True)
        grid.set_row_homogeneous(True)
        grid.set_column_spacing(5)
        grid.set_row_spacing(10)
        grid.set_margin_top(5)
        grid.set_margin_bottom(5)
        grid.set_margin_left(5)

        self.add(grid)

        self.connect("delete-event", Gtk.main_quit)
        self.show_all()


if __name__ == "__main__":
    MainWindow()
    Gtk.main()