import java.io.*;
import java.util.RandomAccess;

public class Main {

    public static void main(String[] args) {
        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos", "tachas"};
        int[] prezo = {3, 4, 5};

        try (RandomAccessFile raf = new RandomAccessFile("ejemplo_raf.dat", "rw")) {
            for (int i = 0; i < cod.length; i++) {
                String sf1;
                sf1 = String.format("%" + 3 + "s", cod[i]);
                raf.writeChars(sf1);
//                System.out.println(sf1);
                String sf2;
                sf2 = String.format("%" + 10 + "s", desc[i]);
                raf.writeChars(sf2);
//                System.out.println(sf2);
                raf.writeInt(prezo[i]);
            }

            raf.seek(30);
            String codAux = "";
            for (int i = 0; i < 3; i++){
                char aux = raf.readChar();
                if (aux != ' '){
                    codAux += aux;
                }
            }
            System.out.println(codAux);
            String descAux = "";
            for (int i = 0; i < 10; i++){
                char aux = raf.readChar();
                if (aux != ' '){
                    descAux += aux;
                }
            }
            System.out.println(descAux);
            System.out.println(raf.readInt());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}