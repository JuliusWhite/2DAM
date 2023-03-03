import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException {
        // Creating statement
        Statement s = DBConnection.conexion().createStatement();

    }

}
