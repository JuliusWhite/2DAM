import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File f = new File("./test.xml");

        ArrayList<Product> produtos = new ArrayList<Product>();

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

            XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new FileInputStream("./test.xml"));

            int eventType = reader.getEventType();

            String cod = "";
            String desc = "";
            String toPrase;
            double price;

            Product p1 = new Product();

            while (reader.hasNext()) {

                eventType = reader.next();

                if (eventType == XMLEvent.START_ELEMENT) {

                    switch (reader.getLocalName()) {

                        case "produto":
                            cod = reader.getAttributeValue(0);
                            break;
                        case "desc":
                            desc = reader.getElementText();
                            break;
                        case "prezo":
                            toPrase = reader.getElementText();
                            double d = Double.parseDouble(toPrase);
                            p1 = new Product(cod, desc, d);
                            produtos.add(p1);
                            break;
                        default:
                            break;
                    }
                }
            }
            reader.close();

            for (Product p : produtos) {
                System.out.println(p.toString());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}