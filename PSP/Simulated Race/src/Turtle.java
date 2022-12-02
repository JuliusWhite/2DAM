public class Turtle extends Thread{

    private int pos;

    public Turtle(String name) {
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
            if (n <= 50) {
                pos += 3;
                System.out.println(getName() + " big step, pos: " + adjustPos(pos));
            } else if (n <= 70) {
                if (pos > 6) {
                    pos -= 6;
                    System.out.println(getName() + " back, pos: " + pos);
                } else pos = 1;
                System.out.println(getName() + " back, pos: " + pos);
            } else {
                pos += 1;
                System.out.println(getName() + " little step, pos: " + adjustPos(pos));

            }
        }
        System.out.println("\t*Turtle has win at " + java.time.LocalTime.now());
    }

}
