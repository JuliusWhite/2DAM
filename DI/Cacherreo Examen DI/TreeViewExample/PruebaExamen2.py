import gi
from reportlab.lib.pagesizes import A4
from reportlab.platypus import SimpleDocTemplate, Table

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk, Gdk

from conexionBD import ConexionBD


class Exame(Gtk.Window):
    def __init__(self):
        Gtk.Window.__init__(self, title="Exame 13-04-2023")
        self.set_border_width(10)

        builder = Gtk.Builder()
        builder.add_from_file("MyExam.glade")

        upper_h_box = Gtk.Box(orientation=Gtk.Orientation.HORIZONTAL)

        frmUsuarios = Gtk.Frame(label="Usuarios")
        lblNome = Gtk.Label(label="Nome")
        txtNome = Gtk.Entry()
        lblDni = Gtk.Label(label="Dni")
        self.txtDni = Gtk.Entry()
        lblDepartamento = Gtk.Label(label="Departamento")
        cmbDepartamento = Gtk.ComboBox()
        lblCorreoe = Gtk.Label(label="Correoe")
        txtCorreoe = Gtk.Entry()
        chkActivo = Gtk.CheckButton(label="Activo")

        upper_h_box.add(frmUsuarios)
        upper_h_box.add(lblNome)
        upper_h_box.add(txtNome)
        upper_h_box.add(lblDni)
        upper_h_box.add(self.txtDni)
        self.txtDni.set_text("111111k")
        upper_h_box.add(lblDepartamento)
        upper_h_box.add(cmbDepartamento)
        upper_h_box.add(lblCorreoe)
        upper_h_box.add(txtCorreoe)
        upper_h_box.add(chkActivo)

        middle_h_box = Gtk.Box(orientation=Gtk.Orientation.HORIZONTAL)
        middle_h_box.set_homogeneous(True)

        self.db = ConexionBD("perfisUsuarios.bd")
        self.db.conectaBD()
        self.db.creaCursor()

        frmPerfis = Gtk.Frame(label="Perfís aplicación")
        frmPerfis = builder.get_object("frm1")
        entry1 = builder.get_object("entry1")
        entry2 = builder.get_object("entry2")
        entry3 = builder.get_object("entry3")
        entry4 = builder.get_object("entry4")
        entry5 = builder.get_object("entry5")

        query_perfis = "select nomePerfil from perfis"
        lista_perfis = self.db.consultaSenParametros(query_perfis)

        entry1.set_text(lista_perfis[0][0])
        entry2.set_text(lista_perfis[1][0])
        entry3.set_text(lista_perfis[2][0])
        entry4.set_text(lista_perfis[3][0])

        middle_h_box.add(frmPerfis)

        ntbPerfis = Gtk.Notebook()

        trvPerfis = Gtk.TreeView()
        trvTreeView = Gtk.TreeView()
        trvOperaciones = Gtk.TreeView()

        ntbPerfis.add(trvPerfis)
        ntbPerfis.add(trvTreeView)
        ntbPerfis.add(trvOperaciones)

        middle_h_box.add(ntbPerfis)

        pdf_btn = Gtk.Button(label="Generate PDF")
        pdf_btn.connect("clicked", self.on_clicked_pdf)

        main_v_box = Gtk.Box(orientation=Gtk.Orientation.VERTICAL)

        main_v_box.add(upper_h_box)
        main_v_box.add(middle_h_box)
        main_v_box.add(pdf_btn)

        self.add(main_v_box)
        self.connect("delete-event", Gtk.main_quit)
        self.show_all()

    def on_clicked_pdf(self, row):
        doc = SimpleDocTemplate("PruebaExamen1.pdf", pagesize=A4)

        data = ([("DNI", "Nome", "Dept", "Correo", "Activo", "Perfil", "Permisos", )])

        dni = self.txtDni.get_text()

        query_usuarios = "select nome, departamento, correoe, activo from usuarios where dni=?"
        query_intermedia = "select idPerfil, permiso from perfisUsuario where dniUsuario=?"
        query_perfil = "select nomePerfil from perfis where idPefil=?"

        datos_usuario = self.db.consultaConParametros(query_usuarios, dni)
        datos_intermedios = self.db.consultaConParametros(query_intermedia, dni)
        nome_perfil = self.db.consultaConParametros(query_perfil, datos_intermedios[0][0])

        data.append([dni, datos_usuario[0][0], datos_usuario[0][1], datos_usuario[0][2], datos_usuario[0][3], nome_perfil[0][0], datos_intermedios[0][1]])

        table = Table(data)

        doc.build([table])



if __name__ == "__main__":
    Exame()
    Gtk.main()
