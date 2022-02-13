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
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Projections.*;

public class EX4 {
    public void exercise() {
        MongoClient mongo = MongoClients.create("mongodb://dam2.tomcat.iesquevedo.es:2323");
        MongoDatabase db = mongo.getDatabase("AlbertoWebStore");
        MongoCollection<Document> col = db.getCollection("Events");

        /*[{
            $match: {
                'event-location': {
                    $regex: '(Latina)'
                }
            }
        }, {
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
        }, {
            $count: 'Number of events at (La Latina)'
            }
         ]*/

        col.aggregate(Arrays.asList(
                        match(regex("event-location", "(Latina)")),
                        addFields(new Field("mes", new Document("$month", new Document("$toDate", "$dtstart")))),
                        project(fields(include("title", "mes"), exclude("_id"))),
                        match(eq("mes", 1)),
                        count("Number of events at (La Latina)")))
                .into(new ArrayList<>()).forEach(System.out::println);

    }
}
