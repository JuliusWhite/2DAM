import subprocess
import sys

import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

from conexionBD import ConexionBD

from reportlab.platypus import SimpleDocTemplate, Table
from reportlab.lib import colors
from reportlab.lib.pagesizes import A4


class App(Gtk.Window):

    def __init__(self):
        super().__init__(title="Example Treeview CellRendererCombo")

        v_box = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=4)

        model = Gtk.ListStore(str, str, int, str)
        perfil_model = Gtk.ListStore(str, int)

        self.trv_perfil_usuarios = Gtk.TreeView(model=model)

        cell = Gtk.CellRendererText()
        col = Gtk.TreeViewColumn("DNI", cell, text=0)
        self.trv_perfil_usuarios.append_column(col)

        # cell2 = Gtk.CellRendererText()
        col2 = Gtk.TreeViewColumn("Nome", cell, text=1)
        self.trv_perfil_usuarios.append_column(col2)

        cell3 = Gtk.CellRendererCombo()
        cell3.set_property("editable", True)
        cell3.set_property("model", perfil_model)
        cell3.set_property("text-column", 0)
        cell3.set_property("has-entry", False)
        cell3.connect("changed", self.on_cell3_changed, model, perfil_model)
        col3 = Gtk.TreeViewColumn("Descripcion", cell3, text=3)
        self.trv_perfil_usuarios.append_column(col3)

        # cell4 = Gtk.CellRendererText()
        col4 = Gtk.TreeViewColumn("Perfil", cell, text=2)
        self.trv_perfil_usuarios.append_column(col4)

        db = ConexionBD("perfisUsuarios.bd")
        conexionBD = db.conectaBD()
        cursor = db.creaCursor()

        # query = "SELECT u.dni, u.nome, pu.idPerfil FROM usuarios u join perfisUsuario pu on u.dni = pu.dniUsuario"

        query_m = "select dni, nome from usuarios"
        query_m2 = "select idPerfil from perfisUsuario where dniUsuario=?"
        query_m3 = "select descricion from perfis where idPefil=?"
        query_m4 = "select descricion, idPefil from perfis"

        query_aux = "select nome, activo from usuarios"

        lista_usuarios = db.consultaSenParametros(query_m)
        usuarios_perfil = list()

        for usuario in lista_usuarios:
            id_pefil = db.consultaConParametros(query_m2, usuario[0])  # usuario[0] is dni
            desc = db.consultaConParametros(query_m3, id_pefil[0][0])  # id_pefil[0] is descricion
            element = list(usuario)
            element.append(id_pefil[0][0])
            element.append(desc[0][0])
            model.append(element)

        lista_perfis = db.consultaSenParametros(query_m4)
        for perfil in lista_perfis:
            perfil_model.append(perfil)

        v_box.pack_start(self.trv_perfil_usuarios, True, True, 0)

        pdf_btn = Gtk.Button()
        pdf_btn.set_label("PDF file")
        pdf_btn.connect("clicked", self.on_clicked_pdf)

        v_box.add(pdf_btn)

        self.add(v_box)
        self.connect("destroy", Gtk.main_quit)
        self.show_all()

    def on_cell3_changed(self, cellRendererCombo, fila, element, model_trv, model_cmb_perfis):
        model_trv[fila][3] = model_cmb_perfis[element][0]
        model_trv[fila][2] = model_cmb_perfis[element][1]

    def on_clicked_pdf(self, model):
        doc = SimpleDocTemplate("Manuel2.pdf", pagesize=A4)

        data = []

        for row in self.trv_perfil_usuarios.get_model():
            data.append([row[0], row[1], row[2], row[3]])

        table = Table([("DNI", "Nome", "Desc", "Perfil",)] + data)
        table_style = ([("BACKGROUND", (0, 0), (-1, 0), colors.rosybrown),
                        ("TEXTCOLOR", (0, 0), (-1, 0), colors.whitesmoke),
                        ("ALIGN", (0, 0), (-1, -1), "CENTER"),
                        ("VALIGN", (0, 0), (-1, -1), "MIDDLE"),
                        ])
        table.setStyle(table_style)

        doc.build([table])

        subprocess.run(["xdg-open", "Manuel2.pdf"])

        sys.exit()


if __name__ == "__main__":
    App()
    Gtk.main()
