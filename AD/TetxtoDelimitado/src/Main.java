import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos", "tachas"};
        int[] prezo = {3, 4, 5};
        NumberFormat nf =NumberFormat
                .getCurrencyInstance(
                        Locale.GERMANY);
        ;

        File f = new File("./prod.txt");

        try (PrintWriter pw = new PrintWriter(f); BufferedReader br = new BufferedReader(new FileReader(f))) {
            for (int i = 0; i < cod.length; i++) {
                pw.println(cod[i] + "\t" + desc[i] + "\t" + prezo[i]);
            }
            pw.flush();
            String line;
            while ((line = br.readLine()) != null) {
                String cod2;
                String desc2;
                double prezo2;
                String[] aux = line.split("\t");
                cod2 = aux[0];
                desc2 = aux[1];
                prezo2 = Double.parseDouble(aux[2]);
                System.out.println("Cod: " + cod2);
                System.out.println("Desc: " + desc2);
                System.out.println("Price: " + nf.format(prezo2));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}