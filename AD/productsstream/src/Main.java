import java.io.*;

public class Main {

    // Write attributes in the file product.txt. You also can read the lines you have wrote if you decomment the commented lines

    public static void writeAttributes(String code, String des, double price, File f) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(f, true));
             DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            dos.writeUTF(code);
            dos.writeUTF(des);
            dos.writeDouble(price);
//            while(true) {
//                System.out.println(dis.readUTF());
//                System.out.println(dis.readUTF());
//                System.out.println(dis.readDouble());
//            }
        } catch (EOFException e) {
            System.out.println("End of file" + e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        File f = new File("./produtos.txt");

        f.delete();

        // 1)crear un  obxecto da clase Product  cos argumentos: "cod1", "parafusos", 3
        Product p1 = new Product("cod1", "parafusos", 3);

        //2) gardar os valores do producto creado nun ficheiro denominado produtos.txt mediante os metodos adecuados de DataOutputStream (amosados ao final deste texto)

        writeAttributes(p1.getCode(), p1.getDesc(), p1.getPrice(), f);

        //3) crear outro  obxecto da clase Product cos argumentos: "cod2","cravos",4

        Product p2 = new Product("cod2", "cravos", 4);

        //4) gardar os valores do producto creado no ficheiro produtos.txt

        writeAttributes(p2.getCode(), p2.getDesc(), p2.getPrice(), f);

        //5) Despois crear un obxecto tipo “producto” novo chamado po3 sen argumentos e cargar os seus atributos a partires dos datos dos productos que estan gardados no ficheiro produtos.txt mediante DataInputSream, imprimindo os valores de ditas productos (enténdese que primeiro cargo os datos de primeiro producto lido no obxecto po3, e imprimo os valores e despois cargo os datos do seguinte producto lido no mesmo obxecto po3 e volto imprimir os seus valores)

        Product po3 = new Product();

        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            while (true) {
                po3.setCode(dis.readUTF());
                po3.setDesc(dis.readUTF());
                po3.setPrice(dis.readDouble());

                System.out.println(po3.getCode());
                System.out.println(po3.getDesc());
                System.out.println(po3.getPrice());
            }
        } catch (EOFException e) {
            System.out.println("End of file");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}