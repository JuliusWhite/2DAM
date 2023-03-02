import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Creating statement
        Statement s = DBConnection.conexion().createStatement();

        // Adding data for the following querys
        String code = "p2";
        String desc = "piso";
        String city = "Lugo";
        int prize = 14;
        int cp = 36452;

        // First query, insert a row in produtos, next there's a try catch in case the row was already added
        String query = "insert into produtos(codigo,descricion,prezo,ci)" + " values ('"
                + code + "','"
                + desc + "',"
                + prize + ",('"
                + city + "',"
                + cp + ") )";
        try {
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Next, an upadte query
        query = "update produtos set ci.cp =(ci).cp+1 where (ci).cidade='" + city + "'";
        s.executeUpdate(query);

        // Finally, show all the rows in the table
        ResultSet rs = s.executeQuery("select produtos, (ci).cidade, (ci).cp from produtos");
        while (rs.next()) {
            System.out.println("Produto:" + rs.getString(1) + ", cidade:" + rs.getString(2) + ", cp:" + rs.getInt(3));
        }
        rs.close();

        s.close();
    }
}