import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.*;

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

    public static void confitmation(double id) {

    }

    public static void main(String[] args) throws SQLException {

        // Starting the postgres connection from the class DBConnection
        DBConnection.conexion();

        // Creating a Mongo client
        MongoClient mongo = new MongoClient("localhost", 27017);

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("reservas");

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("resreva");
        System.out.println("\nCollection myCollection selected successfully");

        flightReservation(1, "361a", 1, 2, 0, collection);

    }

}
