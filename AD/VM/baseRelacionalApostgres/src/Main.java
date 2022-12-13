import java.sql.*;
import java.util.Date;

public class Main {

    public static void insireProduct(String code, String desc, int price, java.sql.Date datec) {
        try {
            PreparedStatement ps = DBConnection.conexion().prepareStatement("INSERT INTO produtos (CODIGO, DESCRICION, PREZO, DATAC) VALUES (?,?,?,?)");
            ps.setString(1, code);
            ps.setString(2, desc);
            ps.setInt(3, price);
            ps.setDate(4, datec);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void listarProdutos() throws SQLException {
        Statement s = DBConnection.conexion().createStatement();
        ResultSet rs = s.executeQuery("select * from produtos");
        System.out.print("Codigo");
        System.out.print("\tDesc");
        System.out.print("\t\tPrezo");
        System.out.print("\t\tDatac");
        System.out.println();
        while (rs.next()) {
            System.out.print(rs.getString("codigo"));
            System.out.print("\t\t" + rs.getString("descricion"));
            System.out.print("\t\t" + rs.getDouble("prezo"));
            System.out.print("\t\t" + rs.getDate("datac"));
            System.out.println();
        }
        rs.close();
    }

    public static void actualizarPre(String cod, double newPrice){
        try {
            Statement s = DBConnection.conexion().createStatement();
            s.executeUpdate("update produtos set prezo = " + newPrice + " where codigo = " + cod);
            s.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarProduto(String cod){
        try {
            Statement s = DBConnection.conexion().createStatement();
            s.executeUpdate("delete from produtos where codigo = " + cod);
            s.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {

        DBConnection.conexion();

        java.util.Date aux1 = new Date(120, 12, 27);
        java.sql.Date d1 = new java.sql.Date(aux1.getTime());

        java.util.Date aux2 = new Date(98, 4, 6);
        java.sql.Date d2 = new java.sql.Date(aux2.getTime());

        java.util.Date aux3 = new Date(98, 3, 7);
        java.sql.Date d3 = new java.sql.Date(aux3.getTime());

        insireProduct("p1","parafusos", 3, d1);
        insireProduct("p2","cravos", 4, d2);
        insireProduct("p3","tachas", 6, d3);

        listarProdutos();
        actualizarPre("'p2'", 2.29);
        listarProdutos();
        eliminarProduto("'p2'");

    }

}
