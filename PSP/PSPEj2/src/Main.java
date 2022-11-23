public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThread mt1 = new MyThread("1");
        MyThread mt2 = new MyThread("2");
        MyThread mt3 = new MyThread("3");
        MyThread mt4 = new MyThread("4");

        mt1.start();
        mt1.join();

        mt2.start();
        mt2.join();

        mt3.start();
        mt3.join();

        mt4.start();
        mt4.join();
    }
}