import subprocess
import sys

from reportlab.lib.pagesizes import A4
from reportlab.lib import colors
from reportlab.platypus import SimpleDocTemplate, Table, TableStyle
import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

from conexionBD import ConexionBD


class MainWindow(Gtk.Window):
    def __init__(self):
        super().__init__()
        self.set_title("exa2")

        v_box = Gtk.Box(orientation=Gtk.Orientation.VERTICAL)

        model = Gtk.ListStore(str, str, str, str, bool)
        model_total = Gtk.ListStore(str, str, str, str, bool)

        self.trv_usarios_activos = Gtk.TreeView(model=model)

        crt = Gtk.CellRendererText()

        crc = Gtk.CellRendererCombo()
        crc.set_property("editable", True)
        crc.set_property("model", model_total)
        crc.set_property("text-column", 0)
        crc.set_property("has-entry", False)
        crc.connect("changed", self.on_crc_changed, model, model_total)

        col = Gtk.TreeViewColumn("Nome", crc, text=0)
        col2 = Gtk.TreeViewColumn("DNI", crt, text=1)
        col3 = Gtk.TreeViewColumn("Nome Perfil", crt, text=2)
        col4 = Gtk.TreeViewColumn("Desc", crt, text=3)

        crt = Gtk.CellRendererToggle()
        col5 = Gtk.TreeViewColumn("Activo", crt, active=4)
        crt.connect("toggled", self.on_toogled)

        self.trv_usarios_activos.append_column(col)
        self.trv_usarios_activos.append_column(col2)
        self.trv_usarios_activos.append_column(col3)
        self.trv_usarios_activos.append_column(col4)
        self.trv_usarios_activos.append_column(col5)

        v_box.pack_start(self.trv_usarios_activos, True, True, 0)

        db = ConexionBD("perfisUsuarios.bd")
        conexionBD = db.conectaBD()
        cursor = db.creaCursor()

        query_usuarios = "select nome, dni from usuarios"
        query_usuarios_perfis = "select idPerfil from perfisUsuario where dniUsuario=?"
        query_perfis_usuarios = "select nomePerfil, descricion from perfis where idPefil=?"
        query_usuario_activo = "select activo from usuarios where dni=?"

        lista_usuarios = db.consultaSenParametros(query_usuarios)

        for usuario in lista_usuarios[:2]:
            idPefil = db.consultaConParametros(query_usuarios_perfis, usuario[1])
            perfil = db.consultaConParametros(query_perfis_usuarios, idPefil[0][0])
            activo = db.consultaConParametros(query_usuario_activo, usuario[1])
            element = list(usuario)
            element.extend([perfil[0][0], perfil[0][1], activo[0][0]])
            model.append(element)

        for usuario in lista_usuarios:
            idPefil = db.consultaConParametros(query_usuarios_perfis, usuario[1])
            perfil = db.consultaConParametros(query_perfis_usuarios, idPefil[0][0])
            activo = db.consultaConParametros(query_usuario_activo, usuario[1])
            element = list(usuario)
            element.extend([perfil[0][0], perfil[0][1], activo[0][0]])
            model_total.append(element)

        pdf_btn = Gtk.Button()
        pdf_btn.set_label("Create PDF with selected rows")
        pdf_btn.connect("clicked", self.on_clicked)
        v_box.add(pdf_btn)

        self.add(v_box)

        self.show_all()

    def on_toogled(self, cellRendererCombo, row):
        model = self.trv_usarios_activos.get_model()
        value = not model[row][4]
        model[row][4] = value

    def on_clicked(self, model):
        doc = SimpleDocTemplate("MyExa2.pdf", pagesize=A4)

        data = []

        for row in self.trv_usarios_activos.get_model():
            if row[4]:
                data.append([row[0], row[1], row[2], row[3]])

        table = Table([("Nome", "DNI", "Nome Perfil", "Descripcion",)] + data)

        table_style = ([("BACKGROUND", (0, 0), (-1, 0), colors.rosybrown),
                        ("TEXTCOLOR", (0, 0), (-1, 0), colors.whitesmoke),
                        ("ALIGN", (0, 0), (-1, -1), "CENTER"),
                        ("VALIGN", (0, 0), (-1, -1), "MIDDLE"),
                        ])
        table.setStyle(table_style)

        doc.build([table])

        subprocess.run(["xdg-open", "MyExa2.pdf"])

        sys.exit()

    def on_crc_changed(self, crc, row, element, model, modelcrc):
        model[row][0] = modelcrc[element][0]
        model[row][1] = modelcrc[element][1]
        model[row][2] = modelcrc[element][2]
        model[row][3] = modelcrc[element][3]
        model[row][4] = modelcrc[element][4]



if __name__ == '__main__':
    MainWindow()
    Gtk.main()
