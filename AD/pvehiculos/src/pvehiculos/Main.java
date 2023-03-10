package pvehiculos;

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
        // Creating a Mongo client
        MongoClient mongo = new MongoClient("localhost", 27017);

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("test");

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("vendas");
        System.out.println("Collection myCollection selected successfully");
        System.out.println();

        // Connecting to vehicli.odb
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vehicli.odb");
        EntityManager em = emf.createEntityManager();

        // Retriving vehiculos into vehiculos.java
        System.out.println("\nobxectos tipo Vehiculos.java\n");
        TypedQuery<Vehiculos> query =
                em.createQuery("SELECT v FROM Vehiculos v", Vehiculos.class);
        List<Vehiculos> resultv = query.getResultList();
        for (Vehiculos v: resultv) {
            System.out.println(v);
        }

        // Retriving clientes into clientes.java
        System.out.println("\nobxectos tipo Clientes.java\n");
        TypedQuery<Clientes> queryc =
                em.createQuery("SELECT c FROM Clientes c", Clientes.class);
        List<Clientes> resultc = queryc.getResultList();
        for (Clientes c: resultc) {
            System.out.println(c);
        }

        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();

        // Getting the iterator
        System.out.println("\n·Showing vendas");
        Iterator<Document> it = iterDoc.iterator();

        // Connecting to postgres
        DBConnection.conexion();

        // Showing vendas
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();

        // Restarting the iteator
        it = iterDoc.iterator();

        while (it.hasNext()) {
            Document aux = it.next();

            // Getting necessary data from the actual row
            int idventa = (int) aux.get("_id");
            String dni = (String) aux.get("dni");
            String codv = (String) aux.get("codveh");
            String nomec = "";
            String nomev = "";
            int prezofinal = 0;
            int ncompras = 0;

            System.out.println("Venda nº:" + idventa);
            for (Clientes c : resultc) {
                if (c.getDni().equals(dni)) {
                    ncompras = c.getNcompras();
                    nomec = c.getNomec();
                    System.out.println("\tCliente\n\t\tdni:" + c.getDni() + "\n\t\tnome:" + c.getNomec() + "\n\t\tacumula " + c.getNcompras() + " compra/s");
                }
            }

            for (Vehiculos v : resultv) {
                int desc = 0;
                if (ncompras != 0) desc = 500;
                if (v.getCodveh().equals(codv)) {
                    nomev = v.getNomveh();
                    prezofinal = v.getPrezoorixe() - ((2019 - v.getAnomatricula()) * 500) - desc;
                    System.out.println("\tVehiculo\n\t\tnome:" + v.getNomveh() + "\n\t\tprezo orixinal:" + v.getPrezoorixe() + "\n\t\tano matrícula " + v.getAnomatricula() + "\n\tPREZO FINAL:" + prezofinal);
                }
            }

            // Creating statement
            Statement s = DBConnection.conexion().createStatement();
            String inset = "insert into finalveh(id, dni, nomec, vehf)" + " values ("
                    + idventa + ",'"
                    + dni + "','"
                    + nomec + "',('"
                    + nomev + "',"
                    + prezofinal + ") )";
            try {
                s.executeUpdate(inset);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
        }

        // Close the database connection:
        em.close();
        emf.close();
    }
}