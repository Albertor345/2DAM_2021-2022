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
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class EX11 {
    public void exercise() {
        MongoClient mongo = MongoClients.create("mongodb://dam2.tomcat.iesquevedo.es:2323");
        MongoDatabase db = mongo.getDatabase("AlbertoWebStore");
        MongoCollection<Document> col = db.getCollection("Customers");


        /*[{
            $project: {
                name: 1,
                nCompras: {
                    $size: '$purchases'
                }
            }
        }, {
            $sort: {
                nCompras: -1
            }
        }, {
            $limit: 1
        }, {
            $project: {
                _id: 0,
                name: 1
            }
        }]*/

        col.aggregate(Arrays.asList(
                        addFields(new Field("nComprass", new Document("$size", "$purchases"))),
                        project(fields(include("nComprass", "name"))),
                        sort(new Document("nComprass", -1)),
                        limit(1),
                        project(fields(include( "name"), exclude("_id")))))
                .into(new ArrayList<>()).forEach(System.out::println);
    }
}
