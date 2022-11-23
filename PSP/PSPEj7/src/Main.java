public class Main {
    public static void main(String[] args) {

        Mail mail = new Mail();

        Writer w1 = new Writer(mail, "hola");
        Writer w2 = new Writer(mail, "klk");
        Writer w3 = new Writer(mail, "hi");
        Writer w4 = new Writer(mail, "wassaaaa");

        Reader r1 = new Reader(mail);
        Reader r2 = new Reader(mail);
        Reader r3 = new Reader(mail);
        Reader r4 = new Reader(mail);

        w1.start();
        w2.start();
        w3.start();
        w4.start();

        r1.start();
        r2.start();
        r3.start();
        r4.start();

    }
}