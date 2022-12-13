package pasaxeirosvossdelimitado;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        File f = new File("./reservas.txt");

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line;
        while ((line = br.readLine()) != null) {
            int codr;
            String dni;
            String vooIda;
            String vooVolta;

            String[] aux = line.split(",");

            codr = Integer.parseInt(aux[0]);
            dni = aux[1];
            vooIda = aux[2];
            vooVolta = aux[3];

            System.out.println("Codr: " + codr);
            System.out.println("Dni: " + dni);
            System.out.println("Voo ida: " + vooIda);
            System.out.println("Voo volta: " + vooVolta + "\n");

            Statement s = DBConnection.conexion().createStatement();
            ResultSet rs = s.executeQuery("select nreservas from pasaxeiros where dni = '" + dni + "'");

            rs.next();
            int nReservas = rs.getInt("nreservas");
            rs.close();

            s.executeUpdate("UPDATE pasaxeiros set nreservas = " + (nReservas + 1) + " where dni = '" + dni + "'");

            rs = s.executeQuery("select nome from pasaxeiros where dni = '" + dni + "'");

            rs.next();
            String nomeReseva = rs.getString("nome");
            rs.close();

            rs = s.executeQuery("select prezo from voos where voo = '" + vooIda + "'");

            rs.next();
            int prezoIda = rs.getInt("prezo");
            rs.close();

            rs = s.executeQuery("select prezo from voos where voo = '" + vooVolta + "'");

            rs.next();
            int prezoVolta = rs.getInt("prezo");
            rs.close();

            PreparedStatement ps = DBConnection.conexion().prepareStatement("insert into reservasfeitas(codr, dni, nome, prezoreserva) values (?, ?, ?, ?)");
            ps.setInt(1, codr);
            ps.setString(2, dni);
            ps.setString(3, nomeReseva);
            ps.setInt(4, (prezoIda + prezoVolta));
            ps.executeUpdate();
            ps.close();

        }


    }

}
