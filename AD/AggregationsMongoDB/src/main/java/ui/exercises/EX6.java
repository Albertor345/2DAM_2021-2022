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
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

public class EX6 {
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
                        match(regex("event-location", "(Latina)")),
                        addFields(new Field("_id", new Document("_id", "$event-location"))),
                        project(fields(include("_id"))),
                        group("$_id", sum("nEvents", 1))))
                .into(new ArrayList<>()).forEach(System.out::println);
    }
}
