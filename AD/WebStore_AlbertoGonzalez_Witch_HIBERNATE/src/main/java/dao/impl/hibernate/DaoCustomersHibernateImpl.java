package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOCustomers;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.User;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoCustomersHibernateImpl implements DAOCustomers {
    private final HibernateConfig hibernateConfig;

    @Inject
    public DaoCustomersHibernateImpl(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    public User login(User user) {
        try (Session session = hibernateConfig.getSession()) {
            user = session.createNamedQuery("login", User.class)
                    .setParameter("name", user.getName())
                    .setParameter("password", user.getPassword()).getSingleResult();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return user;
    }

    @Override
    public Customer get(Customer customer) {
        try (Session session = hibernateConfig.getSession()) {
            customer = session.createNamedQuery("getCustomer", Customer.class).getSingleResult();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            customers = session.createNamedQuery("getAllCustomers", Customer.class).getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return customers;
    }

    @Override
    public boolean add(Customer customer) {
      /* Constantes.INSERT_USER_QUERY
    Constantes.INSERT_CUSTOMER_QUERY*/
        return true;
    }

    @Override
    public boolean update(Customer customer) {
        /*Constantes.UPDATE_CUSTOMER_QUERY*/
        return true;
    }

    @Override
    public boolean delete(Customer customer) {
       /*Constantes.DELETE_CUSTOMER_QUERY
    Constantes.DELETE_USER_QUERY*/
        return false;
    }
}
