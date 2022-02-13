package dao.impl.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Field;
import configuration.Config;
import dao.DAOItems;
import lombok.extern.log4j.Log4j2;
import model.Item;
import model.Purchase;
import model.Review;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Aggregates.addFields;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

@Log4j2
public class DaoItemsMongoImpl implements DAOItems {

    @Override
    public boolean update(Item item) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");

            itemsCollection.updateOne(eq("_id", item.get_id()),
                    set("name", item.getName()));
            itemsCollection.updateOne(eq("_id", item.get_id()),
                    set("company", item.getCompany()));
            itemsCollection.updateOne(eq("_id", item.get_id()),
                    set("price", item.getPrice()));

            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean add(Item item) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            itemsCollection.insertOne(Item.ItemToDocument(item));
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean delete(Item item) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            itemsCollection.deleteOne(eq("_id", item.get_id()));
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean deleteAllPurchasesFromAnItem(Item item) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            MongoCollection<Document> customersCollection = db.getCollection("Customers");

            Document d = itemsCollection.find(and(eq("_id", item.get_id()), exists("reviews", false))).first();
            item = Item.documentToItem(d);
            item.getPurchases().forEach(purchase ->
                    customersCollection.updateMany(
                            eq("_id", purchase.getCustomerID()),
                            pull("purchases", eq("id_purchase", purchase.get_id()))
                    ));
            delete(item);
            return true;
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return false;
    }

    @Override
    public Item get(Item item) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            item = Item.documentToItem(itemsCollection.find(eq("_id", item.get_id())).first());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Document> items = new ArrayList<>();
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            items = itemsCollection.find().into(items);
            return new ArrayList<>(items.stream().map(Item::documentToItem).collect(Collectors.toList()));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return new ArrayList<>();
    }

    @Override
    public String getItemData(Item item) {
        List<Document> list = new ArrayList<>();
        AtomicLong nPurchases = new AtomicLong();
        AtomicReference<Double> avgRating = new AtomicReference<>();

        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            itemsCollection.aggregate(
                    List.of(match(eq("_id", item.get_id())), addFields(new Field("avgRating", new Document("avgRating",
                            new Document("$avg", "$reviews.rate")))))).into(list);


            list.forEach(document -> {
                Item i = Item.documentToItem(document);
                nPurchases.set(i.getPurchases().stream().filter(
                        purchase -> purchase.getDate().isBefore(LocalDate.now()) &&
                                purchase.getDate().getMonth().equals(LocalDate.now().getMonth()) &&
                                purchase.getDate().getYear() == LocalDate.now().getYear()).count());
                if (document.get("avgRating", Document.class).getDouble("avgRating") != null)
                    avgRating.set(document.get("avgRating", Document.class).getDouble("avgRating"));
                else
                    avgRating.set(0.0);
            });

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return "Nºpurchases this month: " + nPurchases.get() + ", Avg rating:  " + avgRating.get() + ", Price: " + item.getPrice();
    }

    @Override
    public boolean checkIfItemHasReviews(Item item) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            return itemsCollection.find(and(eq("_id", item.get_id()), exists("reviews", false))).first() != null;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean checkIfItemHasPurchases(Item item) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            return itemsCollection.find(and(eq("_id", item.get_id()), exists("purchases", false))).first() != null;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean addPurchase(Purchase purchase) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            MongoCollection<Document> customersCollection = db.getCollection("Customers");

            customersCollection.updateOne(eq("_id", purchase.getCustomerID()), push("purchases", Purchase.purchaseToDocument(purchase)));
            itemsCollection.updateOne(eq("_id", new ObjectId(purchase.getItemID())), push("purchases", Purchase.purchaseToDocument(purchase)));
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean addReview(Review review, Item item) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            MongoCollection<Document> customersCollection = db.getCollection("Customers");

            customersCollection.updateOne(eq("purchases.id_purchase", review.getPurchaseID()), set("purchases.$.review", Review.reviewToDocument(review)));
            itemsCollection.updateOne(eq("_id", item.get_id()), push("reviews", Review.reviewToDocument(review)));
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean deletePurchase(Purchase purchase) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            MongoCollection<Document> customersCollection = db.getCollection("Customers");

            customersCollection.updateOne(eq("_id", purchase.getCustomerID()), pull("purchases", Purchase.purchaseToDocument(purchase)));
            itemsCollection.updateOne(eq("_id", new ObjectId(purchase.getItemID())), pull("purchases", Purchase.purchaseToDocument(purchase)));
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean deleteReview(Review review) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> itemsCollection = db.getCollection("Items");
            MongoCollection<Document> customersCollection = db.getCollection("Customers");

            customersCollection.updateOne(eq("purchases.id_purchase", review.getPurchaseID()), set("purchases.$.review", null));
            itemsCollection.updateOne(eq("reviews.id_purchase", review.getPurchaseID()), pull("reviews", eq("_id", review.get_id())));
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

}
