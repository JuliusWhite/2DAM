import java.util.ArrayList;

public class ThreadRide extends Thread{

    private final Elevator elevator;

    public ThreadRide (String name, Elevator evlevator) {
        setName(name);
        this.elevator = new Elevator();
    }

    @Override
    public void run() {
        if (elevator.weith > 0) {

        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
