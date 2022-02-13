package ui.exercises;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Field;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;

public class EX3 {
    public void exercise() {
        MongoClient mongo = MongoClients.create("mongodb://dam2.tomcat.iesquevedo.es:2323");
        MongoDatabase db = mongo.getDatabase("AlbertoWebStore");
        MongoCollection<Document> col = db.getCollection("Events");

        /*[{
            $project: {
                title: 1,
                        mes: {
                    $month: {
                        $toDate: '$dtstart'
                    }
                }
            }
        }, {
            $match: {
                mes: 1
            }
        }]*/

        col.aggregate(Arrays.asList(
                        addFields(new Field("mes", new Document("$month", new Document("$toDate", "$dtstart")))),
                        project(fields(include("title", "mes"), exclude("_id"))),
                        match(eq("mes", 1))))
                .into(new ArrayList<>()).forEach(System.out::println);

    }
}
