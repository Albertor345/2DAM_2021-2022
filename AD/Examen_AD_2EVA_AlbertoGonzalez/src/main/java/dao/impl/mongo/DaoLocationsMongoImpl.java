package dao.impl.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import configuration.Config;
import dao.DAO_objects;
import lombok.extern.log4j.Log4j2;
import model.ObjetoEntity;
import model.UbicacionEntity;
import org.bson.Document;

import java.util.List;

@Log4j2
public class DaoLocationsMongoImpl implements DAO_objects {
    private Config config;

    public DaoLocationsMongoImpl() {
        config = new Config();
    }

    @Override
    public void get() {
        System.out.println(config);
    }

    @Override
    public List<ObjetoEntity> getAll() {
        System.out.println(config);
        return null;
    }

    @Override
    public boolean add(Document d) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> locationsCollection = db.getCollection("Locations");
            locationsCollection.insertOne(d);
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public void login() {

    }

    /*public User login(User user) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> usersCollection = db.getCollection("Users");
            user = User.documentToUser(usersCollection.find(and(eq("name", user.getName()), eq("password", user.getPassword()))).first());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            user = null;
        }
        return user;
    }*/

    /*   @Override
    public Customer get(Customer customer) {
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> customersCollection = db.getCollection("Customers");
            customer = Customer.documentToCustomer(customersCollection.find(eq("_id", customer.get_id())).first());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return customer;
    }*/

    /*@Override
    public List<Customer> getAll() {
        List<Document> customers = new ArrayList<>();
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> customersCollection = db.getCollection("Customers");
            customers = customersCollection.find().into(customers);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return customers.stream().map(Customer::documentToCustomer).collect(Collectors.toList());
    }*/

    /* @Override
    public boolean add(Customer customer) {
        User user = User.builder()._id(customer.get_id()).user_type("customer").password(customer.getName()).name(customer.getName()).build();

        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> customersCollection = db.getCollection("Customers");
            MongoCollection<Document> usersCollection = db.getCollection("Users");

            usersCollection.insertOne(User.userToDocument(user));
            customersCollection.insertOne(Customer.customerToDocument(customer));
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }*/

    /*@Override
    public boolean update(Customer customer) {
        return false;
    }*/

    /* @Override
    public boolean delete(Customer customer) {
        long deletedCount = 0;
        try (MongoClient mongo = MongoClients.create(Config.getProperties().getProperty("urlDB"))) {
            MongoDatabase db = mongo.getDatabase(Config.getProperties().getProperty("dbName"));
            MongoCollection<Document> customersCollection = db.getCollection("Customers");
            MongoCollection<Document> usersCollection = db.getCollection("Users");
            deletedCount = customersCollection.deleteOne(eq("_id", customer.get_id())).getDeletedCount();
            if (deletedCount != 0) {
                deletedCount = usersCollection.deleteOne(eq("_id", customer.get_id())).getDeletedCount();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return deletedCount != 0;
    }*/
}
