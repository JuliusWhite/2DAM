package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Iterator;

public class ConnectToDB {

    public static void main(String[] args) {

        // Creating a Mongo client
        MongoClient mongo = new MongoClient("localhost", 27017);

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("test");

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("datos");
        System.out.println("Collection myCollection selected successfully");
        System.out.println();

        try {
            // Adding data
            Document document = new Document("_id", 11)
                    .append("puntuacion", 8)
                    .append("exame", "test")
                    .append("estudiante", 30);

            //Inserting document into the collection
            collection.insertOne(document);
            System.out.println("Document inserted successfully");
            System.out.println();
        } catch (Exception e) {
            System.out.println("\t_id: 11 already added");
        }

        collection.updateOne(Filters.eq("_id", 11), Updates.set("puntuacion", 10));
        System.out.println("Document update successfully...");
        System.out.println();

        // Deleting the documents'
//        collection.deleteOne(Filters.eq("_id", 12));
//        System.out.println("Document deleted successfully...");

        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();

        // Getting the iterator
        System.out.println("·Showing all documents");
        Iterator<Document> it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();

        // Showing databases
        System.out.println("·Showing all collectins");
        for (String name : database.listCollectionNames()) {
            System.out.println("\t" + name);
        }
        System.out.println();

        System.out.println("·Showing all examns where 'exam' equals 'teoria'");
        BasicDBObject whereQuery = new BasicDBObject();
        BasicDBObject fields = new BasicDBObject();
        whereQuery.put("exame", "teoria");
        fields.put("_id", 0);
        iterDoc = collection.find(whereQuery).projection(fields);
        it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}