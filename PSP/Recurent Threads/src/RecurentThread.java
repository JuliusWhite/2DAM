public class RecurentThread extends Thread {

    private int cont = 0;

    public RecurentThread(String a, int cont) {
        super(a);
        this.cont = cont;
    }

    public void run() {
        System.out.println("Thread " + cont);
        cont++;
        if (this.cont < 2) {
            Thread aux = new Thread(new RecurentThread("aux", this.cont++));
            aux.start();
        }
    }

    public static void main(String[] args) {
        Thread th1 = new Thread(new RecurentThread("th1", 0));
        th1.start();
    }

}
