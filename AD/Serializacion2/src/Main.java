import java.io.*;

public class Main {
    public static void main(String[] args) {

        File f = new File("./test.xml");
        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos", "tachas"};
        int[] prezo = {3, 4, 5};

        Product p1 = new Product(cod[0], desc[0], prezo[0]);
        Product p2 = new Product(cod[1], desc[1], prezo[1]);
        Product p3 = new Product(cod[2], desc[2], prezo[2]);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            oos.writeObject(p1);
            oos.writeObject(p2);
            oos.writeObject(p3);
            oos.writeObject(null);
            oos.close();
            Product aux;
            while ((aux = (Product) ois.readObject()) != null) {
                System.out.println(aux.toString());
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}