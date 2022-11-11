import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        File f = new File("./prod");

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

            XMLOutputFactory output = XMLOutputFactory.newInstance();

            XMLStreamWriter writer = output.createXMLStreamWriter(new FileOutputStream("./test.xml"));

            writer.writeStartDocument("1.0");

            writer.writeStartElement("produtos");

            Product aux;
            while ((aux = (Product) ois.readObject()) != null) {
                System.out.println(aux.toString());
                writer.writeStartElement("produto");

                writer.writeAttribute("codigo", aux.getCode());

                writer.writeStartElement("desc");
                writer.writeCharacters(aux.getDesc());
                writer.writeEndElement();

                writer.writeStartElement("prezo");
                writer.writeCharacters(String.valueOf(aux.getPrice()));
                writer.writeEndElement();

                writer.writeEndElement();
            }

            writer.writeEndDocument();

            writer.flush();

            writer.close();
        } catch (EOFException e) {
            System.out.println("Fin del fichero");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }

    }
}