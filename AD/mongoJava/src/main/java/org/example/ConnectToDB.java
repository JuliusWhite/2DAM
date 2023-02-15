package org.example;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import com.mongodb.MongoClient;

import java.util.Iterator;

public class ConnectToDB {

    public static void main(String args[]) {

        // Creating a Mongo client
        MongoClient mongo = new MongoClient("localhost", 27017);

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("test");

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("datos");
        System.out.println("Collection myCollection selected successfully");

        try {
            // Adding data
            Document document = new Document("_id", 11)
                    .append("puntuacion", 8)
                    .append("exame", "test")
                    .append("estudiante", 30);

            //Inserting document into the collection
            collection.insertOne(document);
            System.out.println("Document inserted successfully");
        } catch (Exception e) {
            System.out.println("\t_id: 11 already added");
        }

        collection.updateOne(Filters.eq("_id", 11), Updates.set("puntuacion", 10));
        System.out.println("Document update successfully...");

        // Deleting the documents
        collection.deleteOne(Filters.eq("_id", 12));
        System.out.println("Document deleted successfully...");

        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;

        // Getting the iterator
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }

        // Showing databases
        System.out.println("Collection created successfully");
        for (String name : database.listCollectionNames()) {
            System.out.println("\t" + name);
        }
    }
}