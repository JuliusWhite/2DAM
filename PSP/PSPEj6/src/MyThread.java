public class MyThread extends Thread {

    private final Box box;

    private final int cant;

    public MyThread(String name, int cant, Box box) {
        super(name);
        this.cant = cant;
        this.box = box;
    }

    @Override
    public void run() {
        box.movement(cant);
    }
}
