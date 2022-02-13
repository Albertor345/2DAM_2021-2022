package ui.exercises;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Field;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.*;

public class EX8 {
    public void exercise() {
        MongoClient mongo = MongoClients.create("mongodb://dam2.tomcat.iesquevedo.es:2323");
        MongoDatabase db = mongo.getDatabase("AlbertoWebStore");
        MongoCollection<Document> col = db.getCollection("Events");


        /*[{
            $project: {
                _id: '$event-location'
            }
        }, {
            $group: {
                _id: '$_id',
                        nEvents: {
                    $sum: 1
                }
            }
        }, {
            $group: {
                _id: null,
                        avg: {
                    $avg: '$nEvents'
                }
            }
        }]*/

        col.aggregate(Arrays.asList(
                        addFields(new Field("_id", new Document("_id", "$event-location"))),
                        project(fields(include("_id"))),
                        group("$_id", sum("nEvents", 1)),
                        group(null, avg("all_events_avg", "$nEvents"))))
                .into(new ArrayList<>()).forEach(System.out::println);
    }
}
