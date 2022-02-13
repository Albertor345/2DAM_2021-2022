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
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

public class EX10 {
    public void exercise() {
        MongoClient mongo = MongoClients.create("mongodb://dam2.tomcat.iesquevedo.es:2323");
        MongoDatabase db = mongo.getDatabase("AlbertoWebStore");
        MongoCollection<Document> col = db.getCollection("Customers");

        /*[{
            $unwind: {
                path: '$purchases'
            }
        }, {
            $match: {
                $and: [{
                    'purchases.review': {
                        $exists: true
                    }
                },
                {
                    'purchases.review': {
                    $ne: null
                    }
                }
        ]
            }
        }, {
            $group: {
                _id: '$_id',
                        nReviews: {
                    $sum: 1
                }
            }
        }]*/

        col.aggregate(Arrays.asList(
                        unwind("$purchases"),
                        match(and(exists("purchases.review",true), ne("purchases.review", null))),
                        group("$_id", sum("nReviews", 1))))
                .into(new ArrayList<>()).forEach(System.out::println);
    }
}
