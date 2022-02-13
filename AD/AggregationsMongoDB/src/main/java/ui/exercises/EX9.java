package ui.exercises;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Field;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Aggregates.addFields;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Projections.*;

public class EX9 {
    public void exercise() {
        MongoClient mongo = MongoClients.create("mongodb://dam2.tomcat.iesquevedo.es:2323");
        MongoDatabase db = mongo.getDatabase("AlbertoWebStore");
        MongoCollection<Document> col = db.getCollection("Customers");

        /*[{
            $project: {
                nCompras: {
                    $size: '$purchases'
                }
            }
        }]*/

        col.aggregate(Arrays.asList(
                        addFields(new Field("nCompras", new Document("$size", "$purchases"))),
                        project(fields(include("nCompras")))))
                .into(new ArrayList<>()).forEach(System.out::println);
    }
}
