import gi
from reportlab.lib.pagesizes import A4
from reportlab.platypus import SimpleDocTemplate, Table

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

from conexionBD import ConexionBD


class MainWindow(Gtk.Window):
    def __init__(self):
        super().__init__()
        self.set_title("exa1")

        db = ConexionBD("perfisUsuarios.bd")
        db.conectaBD()
        db.creaCursor()

        v_box = Gtk.Box(orientation=Gtk.Orientation.VERTICAL)

        model = Gtk.ListStore(str, str, str, str, bool)
        self.trv_usarios_activos = Gtk.TreeView(model=model)

        crt = Gtk.CellRendererText()
        col1 = Gtk.TreeViewColumn("Nome", crt, text=0)
        col2 = Gtk.TreeViewColumn("DNI", crt, text=1)
        col3 = Gtk.TreeViewColumn("Nome Perfil", crt, text=2)
        col4 = Gtk.TreeViewColumn("Descripcion", crt, text=3)

        crtg = Gtk.CellRendererToggle()
        col5 = Gtk.TreeViewColumn("Activo", crtg, active=4)
        crtg.connect("toggled", self.on_toogled)

        self.trv_usarios_activos.append_column(col1)
        self.trv_usarios_activos.append_column(col2)
        self.trv_usarios_activos.append_column(col3)
        self.trv_usarios_activos.append_column(col4)
        self.trv_usarios_activos.append_column(col5)

        v_box.add(self.trv_usarios_activos)

        query_usuario = "select dni, nome from usuarios"
        query_intermedia = "select idPerfil from perfisUsuario where dniUsuario=?"
        query_perfil = "select nomePerfil, descricion from perfis where idPefil=?"
        query_activo = "select activo from usuarios where dni=?"

        lista_usuarios = db.consultaSenParametros(query_usuario)

        for row in lista_usuarios:
            id_perfil = db.consultaConParametros(query_intermedia, row[0])
            datos_perfil = db.consultaConParametros(query_perfil, id_perfil[0][0])
            activo = db.consultaConParametros(query_activo, row[0])
            model.append([row[0], row[1], datos_perfil[0][0], datos_perfil[0][1], activo[0][0]])

        pdf_btn = Gtk.Button(label="PDF")
        pdf_btn.connect("clicked", self.on_pdf_button)

        v_box.add(pdf_btn)

        self.add(v_box)

        self.show_all()

    def on_toogled(self, whatever, row):
        model = self.trv_usarios_activos.get_model()
        value = not model[row][4]
        model[row][4] = value

    def on_pdf_button(self, whatever):
        doc = SimpleDocTemplate("MyExam1.1.pdf", pagesize=A4)

        data = ([("Nome", "Dni", "Nome perfil", "Desc",)])

        model = self.trv_usarios_activos.get_model()

        for row in model:
            if row[4]:
                data.append([row[0], row[1], row[2], row[3]])

        table = Table(data)

        doc.build([table])



if __name__ == '__main__':
    MainWindow()
    Gtk.main()
