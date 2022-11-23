public class MyThread extends Thread {

    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Empezado hilo " + getName());
        for (int i = 0; i < 10; i++) {
            System.out.println("Hilo " + getName() + " i: " + i);
        }
        System.out.println("Finalizado hilo " + getName());
    }
}
