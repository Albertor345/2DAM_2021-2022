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
        DAOCustomers daoCustomersMongo = new DaoCustomersMongoImpl();
        daoCustomersMongo.get();

        ServicesCustomers servicesCustomersHibernate = new ServicesCustomersHibernateImpl();
        DAOCustomers daoCustomersHibernate = new DaoCustomersHibernateImpl();
        daoCustomersHibernate.get();

    }
}
