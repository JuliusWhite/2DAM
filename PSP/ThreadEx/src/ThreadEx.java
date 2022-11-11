public class ThreadEx extends Thread{

    public ThreadEx(String a){
        super(a);
    }

    public void run(){
        for (int i = 0; i < 10; i++){
            System.out.println(i + " "+ getName());
        }
        try {
            ThreadEx.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Termina thread "+ getName());
    }

    public static void sleepMethod(){
        try {
            ThreadEx.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {

//        new ThreadEx("a1").start();
//        new ThreadEx("a2").start();
//        System.out.println("Termina thread main");

        //CON EL METODO sleep()

//        new ThreadEx("Fiesta").start();
//        ThreadEx.sleepMethod();
//        new ThreadEx("Maquiabelico").start();
//        ThreadEx.sleepMethod();
//        new ThreadEx("Sintron").start();
//        ThreadEx.sleepMethod();
//        new ThreadEx("Roncero").start();
//        ThreadEx.sleepMethod();
//        System.out.println("Termina thread main");

        //CON EL MÉTODO join()

        Thread fiesta = new Thread(new ThreadEx("Fiesta"));
        Thread maquiabelico = new Thread(new ThreadEx("Maquiabelico"));
        Thread sintron = new Thread(new ThreadEx("Sintron"));
        Thread roncero = new Thread(new ThreadEx("Roncero"));

        fiesta.start();
        fiesta.join();

        maquiabelico.start();
        maquiabelico.join();

        sintron.start();
        sintron.join();

        roncero.start();
        roncero.join();

    }
}
