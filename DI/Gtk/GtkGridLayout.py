import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class MainWindow(Gtk.Window):
    def __init__(self):
        super().__init__()
        self.set_title("Gtk ex window")

        panel = Gtk.Entry()

        button1 = Gtk.Button(label="1")
        button2 = Gtk.Button(label="2")
        button3 = Gtk.Button(label="3")
        button4 = Gtk.Button(label="4")
        button5 = Gtk.Button(label="5")
        button6 = Gtk.Button(label="6")
        button7 = Gtk.Button(label="7")
        button8 = Gtk.Button(label="8")
        button9 = Gtk.Button(label="9")
        button0 = Gtk.Button(label="0")

        buttonSum = Gtk.Button(label="+")
        buttonSub = Gtk.Button(label="-")
        buttonMul = Gtk.Button(label="x")
        buttonDiv = Gtk.Button(label="/")
        buttonEq = Gtk.Button(label="=")

        grid = Gtk.Grid()

        # with attach

        grid.attach(panel, 0, 0, 3, 1)
        grid.attach(button1, 0, 1, 1, 1)
        grid.attach(button2, 1, 1, 1, 1)
        grid.attach(button3, 2, 1, 1, 1)
        grid.attach(button4, 0, 2, 1, 1)
        grid.attach(button5, 1, 2, 1, 1)
        grid.attach(button6, 2, 2, 1, 1)
        grid.attach(button7, 0, 3, 1, 1)
        grid.attach(button8, 1, 3, 1, 1)
        grid.attach(button9, 2, 3, 1, 1)
        grid.attach(button0, 0, 4, 3, 1)

        grid.attach(buttonSum, 3, 0, 1, 1)
        grid.attach(buttonSub, 3, 1, 1, 1)
        grid.attach(buttonMul, 3, 2, 1, 1)
        grid.attach(buttonDiv, 3, 3, 1, 1)
        grid.attach(buttonEq, 3, 4, 1, 1)

        self.add(grid)

        self.connect("delete-event", Gtk.main_quit)
        self.show_all()


if __name__ == "__main__":
    MainWindow()
    Gtk.main()