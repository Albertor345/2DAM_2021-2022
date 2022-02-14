package ui.main;

import dao.DAOCustomers;
import dao.impl.hibernate.DaoCustomersHibernateImpl;
import dao.impl.mongo.DaoCustomersMongoImpl;
import services.ServicesCustomers;
import services.impl.hibernate.ServicesCustomersHibernateImpl;
import services.impl.mongo.ServicesCustomersMongoImpl;

import javax.inject.Inject;

public class Main {

    public static void main(String[] args) {
        ServicesCustomers servicesCustomersMongo = new ServicesCustomersMongoImpl();
        servicesCustomersMongo.get();
        servicesCustomersMongo.getAll();

        ServicesCustomers servicesCustomersHibernate = new ServicesCustomersHibernateImpl();
        servicesCustomersHibernate.get();
        servicesCustomersHibernate.getAll();

    }
}
