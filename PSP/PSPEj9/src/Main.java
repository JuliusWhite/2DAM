public class Main {

    public static void main(String[] args) throws InterruptedException {

        Counter c = new Counter();

        MyThread mt1 = new MyThread("1", 1, c);
        MyThread mt2 = new MyThread("2", 2, c);
        MyThread mt3 = new MyThread("3", 3, c);

        mt3.start();
        mt2.start();
        mt1.start();

    }

}
