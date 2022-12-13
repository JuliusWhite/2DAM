package pasaxeirosvoosserializadooracle_3;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        File f = new File("./reservas");

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

        Reserva aux;

        while ((aux = (Reserva) ois.readObject()) != null) {

            System.out.println(aux.toString());

            Statement s = DBConnection.conexion().createStatement();
            ResultSet rs = s.executeQuery("select nreservas from pasaxeiros where dni = '" + aux.getDni() + "'");

            rs.next();
            int nReservas = rs.getInt("nreservas");
            rs.close();

            s.executeUpdate("UPDATE pasaxeiros set nreservas = " + (nReservas + 1) + " where dni = '" + aux.getDni() + "'");

            rs = s.executeQuery("select nome from pasaxeiros where dni = '" + aux.getDni() + "'");

            rs.next();
            String nomeReseva = rs.getString("nome");
            rs.close();

            rs = s.executeQuery("select prezo from voos where voo = '" + aux.getIdvooida() + "'");

            rs.next();
            int prezoIda = rs.getInt("prezo");
            rs.close();

            rs = s.executeQuery("select prezo from voos where voo = '" + aux.getIdvoovolta() + "'");

            rs.next();
            int prezoVolta = rs.getInt("prezo");
            rs.close();

            PreparedStatement ps = DBConnection.conexion().prepareStatement("insert into reservasfeitas(codr, dni, nome, prezoreserva) values (?, ?, ?, ?)");
            ps.setInt(1, aux.getCodr());
            ps.setString(2, aux.getDni());
            ps.setString(3, nomeReseva);
            ps.setInt(4, (prezoIda + prezoVolta));
            ps.executeUpdate();
            ps.close();

        }

    }

}
