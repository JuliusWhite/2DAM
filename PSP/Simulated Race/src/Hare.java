public class Hare extends Thread {

    private int pos;

    public Hare(String name) {
        super(name);
        this.pos = 0;
    }

    private static int adjustPos(int pos) {
        if (pos >= 70) return 70;
        else return pos;
    }

    @Override
    public void run() {
        while (pos < 70) {
            int n = (int) (Math.random() * (100 - 1)) + 1;
//            System.out.println(n);
            if (n <= 20) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(getName() + " sleep, pos: " + pos);
            } else if (n <= 40) {
                pos += 9;
                System.out.println(getName() + " big step, pos: " + adjustPos(pos));
            } else if (n <= 50) {
                if (pos > 12) {
                    pos -= 12;
                    System.out.println(getName() + " big back, pos: " + pos);
                } else pos = 1;
                System.out.println(getName() + " big back, pos: " + pos);
            } else if (n < 80) {
                pos += 1;
                System.out.println(getName() + " little step, pos: " + adjustPos(pos));
            } else {
                if (pos > 2) {
                    pos -= 2;
                    System.out.println(getName() + " little back, pos: " + pos);
                } else pos = 1;
                System.out.println(getName() + " little back, pos: " + pos);
            }
        }
        System.out.println("\t*Hare ended win at " + java.time.LocalTime.now());
    }
}
