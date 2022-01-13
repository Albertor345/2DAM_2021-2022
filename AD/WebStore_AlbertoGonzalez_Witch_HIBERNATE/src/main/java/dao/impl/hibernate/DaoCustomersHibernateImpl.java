package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOCustomers;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.User;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.NoResultException;
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
            customer = session.createNamedQuery("getCustomer", Customer.class).setParameter("id", customer.getId()).getSingleResult();
        } catch (NoResultException ex) {
            customer = null;
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
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
        try (Session session = hibernateConfig.getSession()) {
            session.beginTransaction();
            User user = User.builder().password(customer.getName()).name(customer.getName()).customer(customer).build();
            customer.setUser(user);

            int id = (Integer) session.save(user);
            user.setId(id);
            customer.setId(id);
            session.save(customer);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
       /*Constantes.DELETE_CUSTOMER_QUERY
    Constantes.DELETE_USER_QUERY*/
        return false;
    }
}
