import java.io.*;

public class Main {

    public static void primitiveWriteChars(File f) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
             DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            String aux = "o tempo está xélido";
            for (int i = 0; i < 2; i++) {
                dos.writeChars(aux);
                System.out.println("Writting : " + aux);
                System.out.println("File size: " + dos.size() + " bytes");
                System.out.println("The string lenght is: " + aux.length());
            }
            System.out.println("Final size of the file: " + dos.size() + "\n");
            while (dis.available() != 0) {
                String str = "";
                System.out.print("String: ");
                for (int i = 0; i < aux.length(); i++){
                    str += dis.readChar();
                }
                System.out.println(str);
                System.out.println("Left: " + dis.available() + " bytes");
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
        primitiveWriteChars(new File("./text5.dat"));
    }
}