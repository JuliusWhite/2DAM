package pinvasoras;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
        MongoCollection<Document> collection = database.getCollection("especiesinvasoras");
        System.out.println("Collection especiesinvasoras selected successfully");
        System.out.println();

        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();

        // Getting the iterator
        Iterator<Document> it = iterDoc.iterator();

        // Connecting to horasextratodos.odb
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("encontradasezonas.odb");
        EntityManager em = emf.createEntityManager();

        // Retriving encontradas into Encontradas.java
        System.out.println("\nobxectos tipo Encontradas.java\n");
        TypedQuery<Encontradas> query = em.createQuery("SELECT e FROM Encontradas e", Encontradas.class);
        List<Encontradas> resulte = query.getResultList();

        // Retriving zonas into Zonas.java
        System.out.println("\nobxectos tipo Zonas.java\n");
        TypedQuery<Zonas> queryz = em.createQuery("SELECT z FROM Zonas z", Zonas.class);
        List<Zonas> resultz = queryz.getResultList();


        // (a) Showing encontradas
        for (Encontradas e : resulte) {
            System.out.println(e);
        }
        System.out.println();

        for (Encontradas e : resulte) {
            // Getting the data to use
            int codZona = e.getCodzona();
            int codEsp = e.getCodespecie();
            String nomeZona = "";
            int tempeMedia = 0;
            int tempBarrera = 0;
            double superficie = 0;
            String nomeInv = "";
            double extension = e.getExtension();

            for (Zonas z : resultz) {
                if (codZona == z.getCodz()) {
                    nomeZona = z.getNomz();
                    tempeMedia = z.getTempmedia();
                    superficie = z.getSuperficie();

                    // (b) Showing data from zona
                    System.out.println("Encontrada numero " + e.getNumero() +
                            "\n\tNome da zona: " + nomeZona +
                            "\n\tTemperatura media: " + tempeMedia +
                            "\n\tSuperficie: " + superficie +
                            "\n\tNumero invasoras: " + z.getNumeroinvasoras());

                    // (d) Setting the new value to numero invasoras
                    z.setNumeroinvasoras(z.getNumeroinvasoras() + 1);
                    System.out.println("\tNuevo numero de invasoras: " + z.getNumeroinvasoras());

                    System.out.println();
                }
            }

            // Restarting the iteator
            it = iterDoc.iterator();

            while (it.hasNext()) {
                Document aux = it.next();
                int id = (int) aux.get("_id");
                if (id == codEsp) {
                    nomeInv = (String) aux.get("nomei");
                    tempBarrera = (int) aux.get("tempbarrera");

                    // (c) Showing data from invasora
                    System.out.println("\tId invasora: " + id +
                            "\n\tNome de invasora: " + nomeInv +
                            "\n\tTemperatura barrera: " + tempBarrera);
                    System.out.println();
                }
            }

            if (tempeMedia > tempBarrera) {
                // (e) Calculating poscentaje Danos
                double porDanos =  extension * 100 / superficie;

                System.out.println("CodZona: " + codZona + " nomeZ: " + nomeZona + " nomeI: " + nomeInv + " extension dañada: " + extension + " porcentaje daños: " + porDanos);

                // (f) Inserting rows to resumo
                Statement s = DBConnection.conexion().createStatement();
                String insert = "insert into resumo(codz, nomez, nomei, danos) values ("
                        + codZona + ",'"
                        + nomeZona + "','"
                        + nomeInv + "',("
                        + extension + ","
                        + porDanos + "))";
                s.executeUpdate(insert);
            }


        }
    }

}
