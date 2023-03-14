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
        self.perfil_model = Gtk.ListStore(str, int)

        self.trv_perfil_usuarios = Gtk.TreeView(model=model)

        crt = Gtk.CellRendererText()

        col1 = Gtk.TreeViewColumn("DNI", crt, text=0)
        col2 = Gtk.TreeViewColumn("Nome", crt, text=1)
        col3 = Gtk.TreeViewColumn("Id", crt, text=2)

        crc = Gtk.CellRendererCombo()
        crc.set_property("model", self.perfil_model)
        crc.set_property("editable", True)
        crc.set_property("text_column", 0)
        crc.set_property("has-entry", False)
        crc.connect("changed", self.on_crc_changed)
        col4 = Gtk.TreeViewColumn("Desc", crc, text=3)

        self.trv_perfil_usuarios.append_column(col1)
        self.trv_perfil_usuarios.append_column(col2)
        self.trv_perfil_usuarios.append_column(col3)
        self.trv_perfil_usuarios.append_column(col4)

        db = ConexionBD("perfisUsuarios.bd")
        db.conectaBD()
        db.creaCursor()

        query_m = "select dni, nome from usuarios"
        query_m2 = "select idPerfil from perfisUsuario where dniUsuario=?"
        query_m3 = "select descricion from perfis where idPefil=?"
        query_m4 = "select descricion, idPefil from perfis"

        query_aux = "select nome, activo from usuarios"

        lista_usuarios = db.consultaSenParametros(query_m)

        for row in lista_usuarios:
            idPerfil = db.consultaConParametros(query_m2, row[0])
            desc = db.consultaConParametros(query_m3, idPerfil[0][0])
            model.append([row[0], row[1], idPerfil[0][0], desc[0][0]])

        lista_perfis = db.consultaSenParametros(query_m4)
        for perfil in lista_perfis:
            self.perfil_model.append(perfil)

        v_box.add(self.trv_perfil_usuarios)

        self.add(v_box)
        self.connect("destroy", Gtk.main_quit)
        self.show_all()

    def on_crc_changed(self, whatever, row, x):
        trv_model = self.trv_perfil_usuarios.get_model()
        perfiles_model = self.perfil_model
        trv_model[row][2] = perfiles_model[row][1]
        trv_model[row][3] = perfiles_model[row][0]

if __name__ == "__main__":
    App()
    Gtk.main()
