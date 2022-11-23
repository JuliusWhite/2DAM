public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Started thread " + getName());
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread " + getName() + ", i = " + i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Finished thread " +getName() + "\n");
    }
}
