import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory output = XMLOutputFactory.newInstance();

		XMLStreamWriter writer = output.createXMLStreamWriter(new FileOutputStream("./test.xml"));

		writer.writeStartDocument("1.0");

		writer.writeStartElement("autores");

		writer.writeStartElement("autor");
		writer.writeAttribute("codigo", "a1");
		
		writer.writeStartElement("nome");
        writer.writeCharacters("Alexandre Dumas");
        writer.writeEndElement();
        
        writer.writeStartElement("titulo");
        writer.writeCharacters("El conde de montecristo");
        writer.writeEndElement();
        
        writer.writeStartElement("titulo");
        writer.writeCharacters("Los miserables");
        writer.writeEndElement();
        
        writer.writeEndElement();
        
        writer.writeStartElement("autor");
		writer.writeAttribute("codigo", "a2");
		
		writer.writeStartElement("nome");
        writer.writeCharacters("Fiodor Dostoyevski");
        writer.writeEndElement();
        
        writer.writeStartElement("titulo");
        writer.writeCharacters("El idiota");
        writer.writeEndElement();
        
        writer.writeStartElement("titulo");
        writer.writeCharacters("Noches blancas");
        writer.writeEndElement();
		
		writer.writeEndDocument();

		writer.flush();

		writer.close();
	}
}