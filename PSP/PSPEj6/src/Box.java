public class Box {

    protected static int capital;

    private boolean busy = false;

    public Box() {
        capital = 1000;
    }

    public synchronized void movement(int cant) {
        while (busy) {
            try {
                System.out.println("Cannot execute thread");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        busy = true;
        System.out.println("Capital = " + capital +  " + " + cant + " = " + (capital + cant));
        capital += cant;
        busy = false;
        notify();
    }

}
