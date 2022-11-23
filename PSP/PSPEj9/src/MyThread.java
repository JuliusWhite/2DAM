public class MyThread extends Thread {

    private int cont;
    private Counter counter;

    public MyThread(String name, int cont, Counter counter) {
        super(name);
        this.cont = cont;
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.countNums(getName(), cont);
    }
}
