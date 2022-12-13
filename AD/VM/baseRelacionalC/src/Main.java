import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void showProdcts() {
        try {
            Statement s = DBConnection.conexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM PRODUTOS";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" +
                        rs.getString(2) + "\t" + rs.getDouble(3));
            }
            System.out.println();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void inisreRows(String cod, String desc, double price) {
        try {
            PreparedStatement ps = DBConnection.conexion().prepareStatement("INSERT INTO Produtos(codigo, descricion, prezo) values (?, ?, ?)");
            ps.setString(1, cod);
            ps.setString(2, desc);
            ps.setDouble(3, price);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateDesc(String cod, String desc) {
        try {
            PreparedStatement ps = DBConnection.conexion().prepareStatement("update produtos set descricion=? where codigo=?");
            ps.setString(1, desc);
            ps.setString(2, cod);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updatePrice(String cod, double price) {
        try {
            PreparedStatement ps = DBConnection.conexion().prepareStatement("update produtos set prezo=? where codigo=?");
            ps.setDouble(1, price);
            ps.setString(2, cod);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void totalQuery() {
        try {
            PreparedStatement ps = DBConnection.conexion().prepareStatement("SELECT * FROM produtos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" +
                        rs.getString(2) + "\t" + rs.getDouble(3));
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {

        DBConnection.conexion();
        showProdcts();
//        inisreRows("p4", "martelo", 20);
//        showProdcts();
        updateDesc("p4", "PS Desc");
        updatePrice("p4", 4.90);
        showProdcts();
        totalQuery();

    }
}
