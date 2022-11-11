import java.io.*;

public class Main {

    public static void primitiveWriteUTF(File f) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
             DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            String aux = "o tempo está xélido";
            for (int i = 0; i < 3; i++) {
                dos.writeUTF(aux);
                System.out.println("Writting the string: " + dis.readUTF());
                System.out.println("File size: " + dos.size() + " bytes");
            }
            System.out.println("Final size of the file: " + dos.size() + "\n");
            DataInputStream dis2 = new DataInputStream(new FileInputStream(f));
            while(true) {
                System.out.println("Left: " + dos.size() + " bytes");
                System.out.println("String: " + dis2.readUTF());
            }
        } catch (EOFException e) {
            System.out.println("End of file");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        primitiveWriteUTF(new File("./text3.txt"));
    }

}
