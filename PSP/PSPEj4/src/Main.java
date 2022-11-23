public class Main {
    public static void main(String[] args) {
        MyThread pares = new MyThread("Pares");
        MyThread impares = new MyThread("Impares");
        MyThread _2_3 = new MyThread("2/3");

        pares.start();
        impares.start();
        _2_3.start();
    }
}