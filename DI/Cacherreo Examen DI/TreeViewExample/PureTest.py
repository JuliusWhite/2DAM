import subprocess
import sys

import gi
from reportlab.lib.pagesizes import A4

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

from reportlab.platypus import SimpleDocTemplate, Table, TableStyle
from reportlab.lib import colors


class MainWindow(Gtk.Window):
    def __init__(self):
        super().__init__()
        self.set_title("Testing")

        caixaV = Gtk.Box(orientation=Gtk.Orientation.VERTICAL)

        self.entry = Gtk.Entry()
        self.entry.set_placeholder_text("Enter any text")

        self.entry.connect("activate", self.on_entry_entered)

        boton = Gtk.Button(label="Push me to write the text into a pdf file")
        boton.connect("clicked", self.on_button_clicked)

        caixaV.pack_start(self.entry, False, False, 4)
        caixaV.pack_start(boton, False, False, 4)

        self.add(caixaV)

        self.connect("delete-event", Gtk.main_quit)
        self.show_all()

    def on_button_clicked(self, referenciaBoton):
        self.write_into_pdf()

    def on_entry_entered(self, whatever):
        self.write_into_pdf()

    def write_into_pdf(self):
        doc = SimpleDocTemplate("Testing.pdf", pagesize=A4)

        data = []
        for i in range(10):
            if self.entry.get_text() != "":
                data.append([self.entry.get_text()])
            else:
                data.append("null")

        table = Table([("Title",)] + data)
        table_style = ([("FONTSIZE", (0, 0), (0, 0), 14),
                        ("BACKGROUND", (0, 0), (0, 0), colors.rosybrown),
                        ("TEXTCOLOR", (0, 0), (-1, 0), colors.floralwhite),
                        ("ALIGN", (0, 0), (-1, 0), "CENTER"),
                        ("VALIGN", (0, 0), (-1, -1), "MIDDLE"),
                        ("FONTNAME", (0, 0), (-1, -1), "Courier"),
                        ])
        table.setStyle(table_style)

        doc.build([table])

        subprocess.run(["xdg-open", "Testing.pdf"])

        sys.exit()


if __name__ == '__main__':
    MainWindow()
    Gtk.main()
