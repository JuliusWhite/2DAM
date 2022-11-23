public class Writer extends Thread{

    private final Mail mail;

    private final String text;

    public Writer(Mail mail, String text) {
        this.mail = mail;
        this.text = text;
    }

    @Override
    public void run() {
        mail.write(text);
    }
}
