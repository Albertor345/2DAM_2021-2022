package ui.exercises;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;

public class EX1 {

    public void exercise() {
        MongoClient mongo = MongoClients.create("mongodb://dam2.tomcat.iesquevedo.es:2323");
        MongoDatabase db = mongo.getDatabase("AlbertoWebStore");
        MongoCollection<Document> col = db.getCollection("Events");

        /*[{
            $match: {
                'event-location':'Auditorio y sala de exposiciones Paco de Lucía (Latina)'
            }
        },{
            $project: {
                _id: 0,
                '@type':1,
                 title:1
            }
        }]*/

        col.aggregate(Arrays.asList(
                match(eq("event-location", "Auditorio y sala de exposiciones Paco de Lucía (Latina)")),
                project(fields(include("@type", "title"), exclude("_id")))))
                .into(new ArrayList<>()).forEach(System.out::println);

    }
}
