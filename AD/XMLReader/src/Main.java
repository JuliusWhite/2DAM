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

            String toret;
            double price;

            Product p1 = new Product();

            while (reader.hasNext()) {

                eventType = reader.next();

                if (eventType == XMLEvent.START_ELEMENT) {

                    switch (reader.getLocalName()) {

                        case "produto":
                            toret = reader.getAttributeValue(0);
                            p1.setCode(toret);
                            break;
                        case "desc":
                            toret = reader.getElementText();
                            p1.setDesc(toret);
                            break;
                        case "prezo":
                            toret = reader.getElementText();
                            double d = Double.parseDouble(toret);
                            p1.setPrice(d);
                            break;
                        default:
                            break;
                    }

                }
            }
            reader.close();
            produtos.add(p1);

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