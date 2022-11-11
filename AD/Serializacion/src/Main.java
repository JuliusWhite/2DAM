import javax.swing.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        File f = new File("./serial");
        mClase o1 = new mClase("ola", -4234, 2.7E10);
        mClase o2 = new mClase();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            oos.writeObject(o1);
            oos.close();
            mClase aux = (mClase) ois.readObject();
            o2.setName(aux.getName());
            o2.setNum1(aux.getNum1());
            o2.setNum2(aux.getNum2());
            System.out.println(o2.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}