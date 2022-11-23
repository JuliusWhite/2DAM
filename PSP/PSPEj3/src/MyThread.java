public class MyThread extends Thread {

    protected static int cont = 0;

    public MyThread(String name, int cont) {
        super(name);
        this.cont = cont;
    }

    @Override
    public void run() {
        if (cont < 5) {
            cont++;
            System.out.println("Started thread " + getName());
            MyThread aux = new MyThread(Integer.toString(cont), cont);
            aux.start();
            try {
                aux.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i=0; i < 10; i++) {
                System.out.println("I am thread " + getName() + " i: " + i);
                try {
                    sleep((int) Math.random() * (600 - 100) + 100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Ended thread " + getName());
        }
    }
}
