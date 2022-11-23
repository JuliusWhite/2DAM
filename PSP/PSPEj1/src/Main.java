public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThread mt1 = new MyThread("Huan");
        MyThread mt2 = new MyThread("Juan");

        mt1.start();
        mt1.join();

        mt2.start();
        mt2.join();
    }
}