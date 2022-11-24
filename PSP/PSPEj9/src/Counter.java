import static java.lang.Thread.sleep;

public class Counter {

    private int aux;

    public boolean busy = false;

    public Counter() {
        this.aux = 3;
    }

    public synchronized void countNums(String name, int cont) {
//        System.out.println("aux " + aux);
//        System.out.println("cont " + cont);
        while (aux != cont) {
            try {
//                System.out.println("Cannot execute thread");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        busy = true;
        System.out.println("Executing thread " + name);
        for (int i = 0; i < 10; i++) {
            System.out.println("\tHi! I'm thread " + name + " and my i is " + i);
            try {
                sleep((int) (Math.random() * (1000 - 500)) + 500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
        aux--;
//        System.out.println("aux " + aux);
//        System.out.println("cont " + cont);
        busy = false;
        notify();
    }

}
