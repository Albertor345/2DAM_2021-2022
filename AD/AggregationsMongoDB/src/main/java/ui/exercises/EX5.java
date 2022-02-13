package ui.exercises;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Field;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

public class EX5 {
    public void exercise() {
        MongoClient mongo = MongoClients.create("mongodb://dam2.tomcat.iesquevedo.es:2323");
        MongoDatabase db = mongo.getDatabase("AlbertoWebStore");
        MongoCollection<Document> col = db.getCollection("Events");

//This one is better, in my opinion, than the one below since it counts the events per location grouping by the final part of each location
/*[{
    $project: {
        _id: 1,
        location: {
            $last: {
                $split: [
                    '$event-location',
                    '('
                ]
            }
        }
    }
}, {
    $group: {
        _id: '$location',
        nEvents: {
            $sum: 1
        }
    }
 col.aggregate(Arrays.asList(
 addFields(
 new Field("location", new Document("$last", new Document("$split", Arrays.asList("$event-location", "("))))),
 project(fields(include("_id", "location"))),
 group("$location", sum("nEvents", 1))))
 .into(new ArrayList<>()).forEach(System.out::println);*/


//This is the good one regarding the exercise shown in the pdf
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
}]*/

        col.aggregate(Arrays.asList(
                        addFields(new Field("_id", new Document("_id", "$event-location"))),
                        project(fields(include("_id"))),
                        group("$_id", sum("nEvents", 1))))
                .into(new ArrayList<>()).forEach(System.out::println);
    }
}
