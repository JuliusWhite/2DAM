import java.sql.*;

public class Main {

    public static void showProdcts() {
        try {
            Statement s = DBConnection.conexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM PRODUTOS;";
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

    public static void modifyProductDesc(String cod, String desc) {
        try {
            Statement s = DBConnection.conexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM PRODUTOS;";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("codigo").equals(cod)) {
                    rs.updateString(2, desc);
                    rs.updateRow();
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void modifyProductPrice(String cod, double price) {
        try {
            Statement s = DBConnection.conexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM PRODUTOS;";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("codigo").equals(cod)) {
                    rs.updateDouble(3, price);
                    rs.updateRow();
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void addNewProduct(String cod, String desc, double price) {
        try {
            Statement s = DBConnection.conexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM PRODUTOS;";
            ResultSet rs = s.executeQuery(query);
            rs.moveToInsertRow();
            rs.updateString(1, cod);
            rs.updateString(2, desc);
            rs.updateDouble(3, price);
            rs.insertRow();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deleteProduct(String cod) {
        try {
            Statement s = DBConnection.conexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM PRODUTOS;";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()){
                if (rs.getString("codigo").equals(cod)) {
                    rs.deleteRow();
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void main(String[] args) throws SQLException {

        DBConnection.conexion();

        showProdcts();
        modifyProductDesc("p2", "test desc");
        modifyProductPrice("p2", 1.95);
        showProdcts();
        addNewProduct("p4", "martelo", 20);
        showProdcts();
        deleteProduct("p4");
        showProdcts();

    }

}
