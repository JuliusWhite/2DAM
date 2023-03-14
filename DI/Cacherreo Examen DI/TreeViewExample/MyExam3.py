import subprocess
import sys

from reportlab.lib.pagesizes import A4
from reportlab.lib import colors
from reportlab.lib.utils import ImageReader
from reportlab.platypus import SimpleDocTemplate, Table, TableStyle, Image
import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk, GdkPixbuf

from conexionBD import ConexionBD


class MainWindow(Gtk.Window):
    def __init__(self):
        super().__init__()
        self.set_title("exa3")

        v_box = Gtk.Box(orientation=Gtk.Orientation.VERTICAL)

        model = Gtk.ListStore(str, str, str, str, GdkPixbuf.Pixbuf)
        self.trv_usarios_activos = Gtk.TreeView(model=model)

        crt = Gtk.CellRendererText()

        col = Gtk.TreeViewColumn("Nome", crt, text=0)
        col2 = Gtk.TreeViewColumn("DNI", crt, text=1)
        col3 = Gtk.TreeViewColumn("Nome Perfil", crt, text=2)
        col4 = Gtk.TreeViewColumn("Desc", crt, text=3)

        crp = Gtk.CellRendererPixbuf()
        col5 = Gtk.TreeViewColumn("Imagen", crp, pixbuf=4)

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

        print(lista_usuarios)
        for usuario in lista_usuarios:
            idPefil = db.consultaConParametros(query_usuarios_perfis, usuario[1])
            perfil = db.consultaConParametros(query_perfis_usuarios, idPefil[0][0])
            activo = db.consultaConParametros(query_usuario_activo, usuario[1])
            element = list(usuario)
            element.extend([perfil[0][0], perfil[0][1], GdkPixbuf.Pixbuf.new_from_file("/home/julius/Descargas/memejuan.jpeg")])
            model.append(element)

        pdf_btn = Gtk.Button()
        pdf_btn.set_label("Create PDF with selected rows")
        pdf_btn.connect("clicked", self.on_clicked)
        v_box.add(pdf_btn)

        self.add(v_box)

        self.show_all()

    def on_clicked(self, model):
        doc = SimpleDocTemplate("MyExa3.pdf", pagesize=A4)

        data = []

        for row in self.trv_usarios_activos.get_model():
            data.append([row[0], row[1], row[2], row[3]])

        table = Table([("Nome", "DNI", "Nome Perfil", "Descripcion",)] + data)

        table_style = ([("BACKGROUND", (0, 0), (-1, 0), colors.rosybrown),
                        ("TEXTCOLOR", (0, 0), (-1, 0), colors.whitesmoke),
                        ("ALIGN", (0, 0), (-1, -1), "CENTER"),
                        ("VALIGN", (0, 0), (-1, -1), "MIDDLE"),
                        ])
        table.setStyle(table_style)

        doc.build([table])

        subprocess.run(["xdg-open", "MyExa3.pdf"])

        sys.exit()


if __name__ == '__main__':
    MainWindow()
    Gtk.main()
