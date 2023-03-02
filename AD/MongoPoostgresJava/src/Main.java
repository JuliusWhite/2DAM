import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.*;
import java.util.Iterator;

public class Main {

    public static void flightReservation(double id, String dni, double outwardFlight, double arrivalFlight, double reserved, MongoCollection<Document> collection) {
        try {
            // Adding data flight reservation for reservas database and reserva collection
            Document document = new Document("_id", id)
                    .append("dni", dni)
                    .append("idvooida", outwardFlight)
                    .append("idvoovolta", arrivalFlight)
                    .append("prezoreserva", 0)
                    .append("confirmado", reserved);

            // Adding flight reservation to mongodb
            collection.insertOne(document);
            System.out.println("Document inserted successfully");
            System.out.println();
        } catch (Exception e) {
            System.out.println("\n\t_id: " + id + " already added");
        }
    }

    public static void confitmation(double id, MongoCollection<Document> collection) {
        Document filter = new Document("_id", 1);
        Document update = new Document("$set", new Document("confirmado", 1));
        collection.updateOne(filter, update);
        System.out.println("\nSeted filght 1 as confirmado");

        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();

        // Getting the iterator
        Iterator<Document> it = iterDoc.iterator();
        iterDoc = collection.find(filter);

        // Getting outward flight id
        it = iterDoc.iterator();
        double outwardFlight = (double) it.next().get("idvooida");
        System.out.println("Outward flight: " + outwardFlight);

        // Getting arrival flight id
        it = iterDoc.iterator(); // here im restarting the iterator
        double arrivalFlight = (double) it.next().get("idvoovolta");
        System.out.println("Arrival flight: " + arrivalFlight);

        // Insatiation of the variables that are going to be used to save postgres data
        int outwardFlightPrize = 0;
        int arrivalFlightPrize = 0;
        int total = 0;

        try {
            // Starting the postgres connection from the class DBConnection
            DBConnection.conexion();

            PreparedStatement ps = DBConnection.conexion().prepareStatement("SELECT * FROM voos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // getting prices from voos postgres
                if (rs.getInt("voo") == outwardFlight) outwardFlightPrize = rs.getInt("prezo");
                else if (rs.getInt("voo") == arrivalFlight) arrivalFlightPrize = rs.getInt("prezo");
            }
            total = outwardFlightPrize + arrivalFlightPrize;
            System.out.println("prezo ida: " + outwardFlightPrize + ", prezo volta: " + arrivalFlightPrize + ", total: " + total);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        update = new Document("$set", new Document("prezoreserva", total));
        collection.updateOne(filter, update);
        System.out.println("\nSeted prezoreserva as " + total);

    }

    public static void main(String[] args) {

        // Creating a Mongo client
        MongoClient mongo = new MongoClient("localhost", 27017);

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("reservas");

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("resreva");
        System.out.println("\nCollection myCollection selected successfully");

        flightReservation(1, "361a", 1, 2, 0, collection);
        confitmation(1, collection);
    }

}
