public class Reader extends Thread {

    private final Mail mail;

    public Reader(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        mail.read();
    }
}
