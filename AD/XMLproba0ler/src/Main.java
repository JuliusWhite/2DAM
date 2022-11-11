import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new FileInputStream("./test.xml"));

        int eventType = reader.getEventType();

        while (reader.hasNext()) {

            String toret;

            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {

                switch (reader.getLocalName()) {
                    case "autor":
                        toret = reader.getAttributeValue(0);
                        System.out.println(toret);
                        break;
                    case "nome":
                        toret = reader.getElementText();
                        System.out.println(toret);
                        break;
                    case "titulo":
                        toret = reader.getElementText();
                        System.out.println(toret);
                        break;
                    default:
                        break;
                }

            }

        }
        reader.close();

    }

}