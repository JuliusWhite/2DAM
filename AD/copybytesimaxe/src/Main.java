import java.io.*;

public class Main {

    public static void copyBinaryFile(File f, File destiny) {
        try (FileInputStream fis = new FileInputStream(f); FileOutputStream fos = new FileOutputStream(destiny, true)) { //to append the text insetad of overwritting it you can write (, true after de destiny file)
            int valor;
            while ((valor = fis.read()) != -1) {
                fos.write(valor);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (EOFException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void copyBinaryFile2(File f, File destiny) {
        try {
            FileInputStream fis = new FileInputStream(f);
            FileOutputStream fos = new FileOutputStream(destiny, true);
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte[] bytes = new byte[fis.available()];
            int leidos = fis.read(bytes);
            while (leidos > 0) {
                fos.write(bytes, 0, leidos);
                leidos = fis.read(bytes);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
//        copyBinaryFile(new File("./foto.jpg"), new File("./foto2.jpg"));
        copyBinaryFile2(new File("./foto.jpg"), new File("./foto2.jpg"));
    }

}