import java.io.*;

public class Main {

    public static void primitiveUTFChars(File f) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
             DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            String aux = "Está en casa";

            //Writting
            System.out.println("Writting the string with writeUTF: " + aux);
            dos.writeUTF(aux);
            System.out.println("File size: " + dos.size() + " bytes");
            System.out.println("Writting the string with writeChars: " + aux);
            dos.writeChars(aux);
            System.out.println("File size: " + dos.size() + " bytes");
            System.out.println("Writting the string: with writeUTF " + aux);
            dos.writeUTF(aux);
            System.out.println("File size: " + dos.size() + " bytes");
            System.out.println("Final size of the file: " + dos.size() + "\n");

            //Reading
            System.out.println("Read the first string with readUTF: " + dis.readUTF());
            System.out.println("Left: " + dis.available() + " bytes");

            String str = "";
            for (int i = 0; i < aux.length(); i++) {
                str += dis.readChar();
            }
            System.out.println("Read the second string with readChar: " + str);
            System.out.println("Left: " + dis.available() + " bytes");

            System.out.println("Read the third string with readUTF: " + dis.readUTF());
            System.out.println("Left: " + dis.available() + " bytes");

        } catch (EOFException e) {
            System.out.println("End of file");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        primitiveUTFChars(new File("./text6.dat"));
    }
}