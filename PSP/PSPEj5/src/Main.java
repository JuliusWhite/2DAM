public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThread mt1 = new MyThread("1");
        MyThread mt2 = new MyThread("2");

        mt2.start();
        mt2.join();
        mt1.start();
    }
}