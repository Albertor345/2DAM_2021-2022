package ui.main;

import model.UbicacionEntity;
import org.bson.Document;
import services.ServicesLocations;
import services.ServicesObjects;
import services.ServicesUsers;
import services.impl.hibernate.ServicesLocationsHibernateImpl;
import services.impl.hibernate.ServicesObjectsHibernateImpl;
import services.impl.hibernate.ServicesUsersHibernateImpl;
import services.impl.mongo.ServicesLocationsMongoImpl;

import java.util.List;
import java.util.stream.Collectors;

public class Ex6 {
//Generate a new collection Locations using MySQL data. Each location will have:
//a. Name and building of the location
//b. An array of users, with the username and the permissions in that location for each user
//c. An array of the objects in the location, registering for each object the object name and
//an array of users with the name of user and the permissions on that object.


    public static void main(String[] args) {
        ServicesUsers servicesUsers = new ServicesUsersHibernateImpl();
        ServicesObjects servicesObjects = new ServicesObjectsHibernateImpl();
        ServicesLocations servicesLocations = new ServicesLocationsHibernateImpl();
        ServicesLocations servicesLocationsMongo = new ServicesLocationsMongoImpl();


        List<UbicacionEntity> locations = servicesLocations.getAll();

        for (int i = 0; i < locations.size(); i++) {
            servicesLocationsMongo.add(mapperLocation(locations.get(i)));
        }

    }

    public static Document mapperLocation(UbicacionEntity u) {
        Document d = new Document()
                .append("_id", u.getId())
                .append("name", u.getNombre())
                .append("company", u.getEdificio())
                .append("users", "")
                .append("objects", u.getObjetosById().stream()
                        .map(objetoEntity -> new Document().append("Object_name", objetoEntity.getNombre()))
                        .collect(Collectors.toList()));

        return d;
    }
}
