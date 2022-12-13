package exa15brevep;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, XMLStreamException {

        File f = new File("./platoss");

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

        XMLOutputFactory output = XMLOutputFactory.newInstance();

        XMLStreamWriter writer = output.createXMLStreamWriter(new FileOutputStream("./platos.xml"));

        writer.writeStartDocument("1.0");

        writer.writeStartElement("platos");

        Platos aux;

        while ((aux = (Platos) ois.readObject()) != null) {
            writer.writeStartElement("plato");

            System.out.println(aux.getCodigop() + "\n" + aux.getNomep());
            writer.writeAttribute("codigo", aux.getCodigop());

            writer.writeStartElement("nomep");
            writer.writeCharacters(aux.getNomep());
            writer.writeEndElement();

            int fat = 0;

            Statement s = DBConnection.conexion().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM composicion JOIN componentes ON composicion.codc = componentes.codc WHERE codp = '" + aux.getCodigop() + "'");
            while (rs.next()) {
                    fat += (rs.getInt("peso") / 100) * rs.getInt("graxa");
            }
            rs.close();

            System.out.println(fat);

            writer.writeStartElement("graxatotal");
            writer.writeCharacters(String.valueOf(fat));
            writer.writeEndElement();

            writer.writeEndElement();
        }

        writer.writeEndDocument();

        writer.flush();

        writer.close();

    }

}
