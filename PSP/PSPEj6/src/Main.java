public class Main {

    public static void main(String[] args) {

        Box b = new Box();

        MyThread mt1 = new MyThread("Ingreso1:",  200, b);
        MyThread mt2 = new MyThread("Ingreso2:",  100, b);
        MyThread mt3 = new MyThread("Ingreso3:",  50, b);
        MyThread mt4 = new MyThread("Ingreso4:",  50, b);
        MyThread mt5 = new MyThread("Retirada5:",  -110, b);

        mt1.start();
//        mt1.join();

        mt2.start();
//        mt2.join();

        mt3.start();
//        mt3.join();

        mt4.start();
//        mt4.join();

        mt5.start();
//        mt5.join();


    }
}