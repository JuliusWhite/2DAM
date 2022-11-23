public class Mail {

    private String msg;

    public Mail() {
        this.msg = "";
    }

    public synchronized void write(String text) {
        while (!msg.equals("")){
            try {
//                System.out.println("Can´t write, mail full");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Writing " + text);
        msg = text;
        notifyAll();
    }

    public synchronized void read() {
        while (msg.equals("")){
            try {
//                System.out.println("Can´t read, mail empty");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Reading " + msg);
        msg = "";
        notifyAll();
    }


}
