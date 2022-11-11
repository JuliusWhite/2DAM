public class Main {

    private static double capital = 1000.00;

    public static double getCapital() {
        return capital;
    }

    public static void setCapital(double capital) {
        Main.capital = capital;
    }

    public static void main(String[] args) throws InterruptedException {

//        EJ4
//        MyThread mt1 = new MyThread("even");
//        mt1.start();
//        mt1.join();
//        MyThread mt2 = new MyThread("odd");
//        mt2.start();
//        mt2.join();
//        MyThread mt3 = new MyThread("23");
//        mt3.start();
//        mt3.join();

//        EJ5

//        MyThread mt1 = new MyThread("1");
//        MyThread mt2 = new MyThread("2");

//        mt2.start();
//        mt2.join();
//        mt1.start();

//        mt2.setPriority(10);
//        mt2.start();
//        mt1.setPriority(1);
//        mt1.start();

//        EJ6

        MyThread mt1 = new MyThread("In", 10);
        mt1.start();
        MyThread mt2 = new MyThread("", 10);
        mt2.start();
        System.out.println("C: " + capital);

//        EJ 7



    }

}