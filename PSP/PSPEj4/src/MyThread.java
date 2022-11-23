public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        int sum;
        switch (getName().toLowerCase()) {
            case "pares":
                sum = 0;
                for (int i = 0; i < 1000; i++) {
                    if (i % 2 == 0) {
                        sum += i;
                    }
                }
                System.out.println("Los " + getName().toLowerCase() + " suman " + sum);
                break;

            case "impares":
                sum = 0;
                for (int i = 0; i < 1000; i++) {
                    if (i % 2 != 0) {
                        sum += i;
                    }
                }
                System.out.println("Los " + getName().toLowerCase() + " suman " + sum);
                break;

            case "2/3":
                sum = 5; // due to the alorithm wont add 2 and 3
                for (int i = 0; i < 1000; i++) {
                    if (i % 10 == 2 || i % 10 == 3) {
                        sum += i;
                    }
                }
                System.out.println("La suma de los terminados en 2/3 es " + sum);
                break;

            default:
                System.out.println("Default");
        }
    }
}
