public class MyThread extends Thread {

    private static double cant;
    private static boolean bussy;


    public MyThread(String name) {
        setName(name);
    }

    public MyThread(String name, double cant) {
        setName(name);
        this.cant = cant;
        this.bussy = false;
    }

    @Override
    public synchronized void run() {
        System.out.println(Main.getCapital());
//        Realiza un programa en java que execute tres fíos de forma concurrente. Un de eles debe sumar os
//        números pares del 1 ao 1000, outro os impares, e outro, os que terminen en dous ou en tres

//        int sum = 0;
//        if (getName() == "even") {
//            for (int i = 0; i < 1000; i++) {
//                if (i % 2 == 0) {
//                    sum += i;
//                }
//            }
//            System.out.println("Even numbers sum: " + sum);
//        } else if (getName() == "odd") {
//            for (int i = 0; i < 1000; i++) {
//                if (i % 2 != 0) {
//                    sum += i;
//                }
//            }
//            System.out.println("Odd numbers sum: " + sum);
//        } else if (getName() == "23") {
//            sum = 5; //2+3, due to the algorithm does not add them
//            for (int i = 0; i < 1000; i++) {
//                if (i % 10 == 2 || i % 10 == 3) {
//                    sum += i;
//                }
//            }
//            System.out.println("Numbers ended in 2 or 3 sum: " + sum);

//        Escribe unha clase que cree dous fíos e force que a escritura do segundo sexa sempre anterior á
//        escritura por pantalla do primeiro.
//        Exemplo de ejecución:
//        Ola, son o fío número 2
//        Ola, son o fío número 1
//        a) faino con join
//        b )faino con prioridades

//        System.out.println("Hi, im the thread: " + getName());

//        Simular a caixa dunha empresa con dous fíos. Un simulará o ingreso, ca compra de artigos por parte
//        de clientes e o outro a extracción de cartos da caixa co pago a proveedores. A conta terá un capital
//        inicial. Realizar 10 ingresos e 5 extraccións


        if (!bussy) {
            bussy = true;
            if (getName() == "In") {
                Main.setCapital(Main.getCapital() + cant);
                System.out.println(Main.getCapital());
            } else {
                Main.setCapital(Main.getCapital() - cant);
                System.out.println(Main.getCapital());
            }
            bussy = false;
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


//        Programa que simule un buzón de correo (recurso compartido), de xeito que se
//        poida leer unha mensaje ou envialo. O buzón soamente pode almacear unha
//        mensaxe, de xeito que para poder escribir débese atopar baleiro e para poder leer
//        debe de estar cheo. Crear varios fíos lectores e escritores que manexen o buzón
//        de xeito sincronizado


    }
}

