package exa17p;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, SQLException {

        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new FileInputStream("./pedidos.xml"));

        int eventType = reader.getEventType();

        String codCli = "";
        String codPro = "";
        String strCant;
        int cant = 0;
        String date = "";

        Pedido p1 = new Pedido();

        while (reader.hasNext()) {

            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {

                switch (reader.getLocalName().toLowerCase()) {

                    case "pedido":
                        codCli = reader.getAttributeValue(0);
                        codPro = reader.getAttributeValue(1);
                        break;

                    case "cantidade":
                        strCant = reader.getElementText();
                        cant = Integer.parseInt(strCant);
                        break;

                    case "data":
                        date = reader.getElementText();
                        p1 = new Pedido(codCli, codPro, cant, date);
                        pedidos.add(p1);
                        break;

                    default:
                        break;
                }
            }
        }
        reader.close();

        for (Pedido p : pedidos) {

            System.out.println(p.toString());

            Statement s = DBConnection.conexion().createStatement();
            ResultSet rs = s.executeQuery("select * from produtos where codigop = '" + p.getCodpro() + "'");

            rs.next();
            int stock = rs.getInt("stock");
            int prezo = rs.getInt("prezo");
            rs.close();

            s.executeUpdate("UPDATE produtos set stock = " + (stock - p.getCantidade()) + " where codigop = '" + p.getCodpro() + "'");

            rs = s.executeQuery("select gasto from clientes where codigoc = '" + p.getCodcli() + "'");

            rs.next();
            int gasto = rs.getInt("gasto");
            rs.close();

            s.executeUpdate("UPDATE clientes set gasto = " + (gasto + (prezo * p.getCantidade())) + " where codigoc = '" + p.getCodcli() + "'");

            int total = p.getCantidade() * prezo;

            PreparedStatement ps = DBConnection.conexion().prepareStatement("insert into vendas(codigoc, codigop, data, total) values (?, ?, ?, ?)");
            ps.setString(1, p.getCodcli());
            ps.setString(2, p.getCodpro());
            ps.setString(3, p.getData());
            ps.setInt(4, total);
            ps.executeUpdate();
            ps.close();

        }

    }

}
