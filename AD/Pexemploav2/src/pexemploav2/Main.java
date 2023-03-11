package pexemploav2;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Connecting to postgres
        DBConnection.conexion();


        // Creating a Mongo client
        MongoClient mongo = new MongoClient("localhost", 27017);

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("test");

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("empretodos");
        System.out.println("Collection empretodos selected successfully");
        System.out.println();

        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();

        // Getting the iterator
        Iterator<Document> it = iterDoc.iterator();


        // Connecting to horasextratodos.odb
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("horasextratodos.odb");
        EntityManager em = emf.createEntityManager();

        // Retriving vehiculos into horasextratodos.java
        System.out.println("\nobxectos tipo Horasextra.java\n");
        TypedQuery<Horasextra> query = em.createQuery("SELECT h FROM Horasextra h", Horasextra.class);
        List<Horasextra> resulthe = query.getResultList();


        Statement s = DBConnection.conexion().createStatement();
        ResultSet rs = s.executeQuery("select cinf ,dniinf, (fillos).homes, (fillos).mulleres from informaticos");
        while (rs.next()) {
            String dni = rs.getString("dniinf");
            int cinf = rs.getInt("cinf");
            int salarioBase = 0;
            int phe = 0;
            int nhe = 0;

            int totalHijos = rs.getInt(3) + rs.getInt(4);
            if (totalHijos > 0) {
                System.out.println("Informatico:" +
                        "\n\tCInf: " + cinf + "" + "\n\tDni: " + dni + "" + "\n\tFillos totais: " + totalHijos);

                System.out.println();

                Document filter = new Document("dnie", dni);
                iterDoc = collection.find(filter);
                it = iterDoc.iterator();
                while (it.hasNext()) {
                    Document aux = it.next();
                    String dnie = (String) aux.get("dnie");
                    phe = (int) aux.get("phe");
                    salarioBase = (int) aux.get("sb");
                    System.out.println("\tDnie: " + dnie + "\n\tSalario base: " + salarioBase + "\n\tPrecio hora extra: " + phe + "\n\tCodigo hora extra: " + aux.get("che"));

                    System.out.println();

                    for (Horasextra he : resulthe) {
                        if (he.getChe().equals(aux.get("che"))) {
                            nhe = he.getNhe();
                            System.out.println("\tCodigo horas extras: " + he.getChe() + "\n\tNumero hpras extras: " + nhe);
                        }
                    }
                }

                int salarioTotal = salarioBase + phe * nhe + totalHijos * 100;

                System.out.println("SALARIO TOTAL: "+salarioTotal);

                Statement s2 = DBConnection.conexion().createStatement();
                String inset = "insert into finalinf(cinf, salariototal)" + " values (" + cinf + ", " + salarioTotal + ")";
                try {
                    s2.executeUpdate(inset);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                System.out.println();
            }
        }

    }
}