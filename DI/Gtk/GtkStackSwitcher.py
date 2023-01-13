import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class MainWindow(Gtk.Window):
    def __init__(self):
        super().__init__()
        self.set_title("Gtk Stack Switch Ex")

        vBox = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=6)

        stack = Gtk.Stack()
        stack.set_transition_type(Gtk.StackTransitionType.SLIDE_LEFT_RIGHT)
        stack.set_transition_duration(300)

        combo = Gtk.ComboBoxText()
        combo.append_text("Push me")
        combo.append_text("Tag")
        combo.connect("changed", self.on_combo_changed, stack)

        checkBtn = Gtk.CheckButton(label="Push me")
        stack.add_titled(checkBtn, "Button", "Push me!")

        tag = Gtk.Label(label="A pretty tag")
        stack.add_titled(tag, "Tag", "A tag")

        conmutador = Gtk.StackSwitcher()
        conmutador.set_stack(stack)

        vBox.pack_start(combo, True, True, 2)
        vBox.pack_start(conmutador, True, True, 2)
        vBox.pack_start
        vBox.pack_start(stack, True, True, 0)

        self.add(vBox)

        self.connect("delete-event", Gtk.main_quit)
        self.show_all()

    def on_combo_changed (self, combo, stack):
        text = combo.get_active_text()

        if text == "Push me":
            stack.set_visible_child_name("Button")
        else:
            stack.set_visible_child_name("Tag")


if __name__ == "__main__":
    MainWindow()
    Gtk.main()
