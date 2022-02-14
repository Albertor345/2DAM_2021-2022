package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOCustomers;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.List;

@Log4j2
public class DaoCustomersHibernateImpl implements DAOCustomers {
    @Inject
    private HibernateConfig hibernateConfig;


    @Override
    public void get() {
        hibernateConfig.getSession();
    }

    @Override
    public List<String> getAll() {
        return null;
    }

    @Override
    public boolean add() {
        return false;
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
        try (Session session = hibernateConfig.getSession()) {
            user = session.createNamedQuery("login", User.class)
                    .setParameter("name", user.getName())
                    .setParameter("password", user.getPassword()).getSingleResult();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return user;
    }*/

    /*@Override
    public Customer get(Customer customer) {
        try (Session session = hibernateConfig.getSession()) {
            customer = session.createNamedQuery("getCustomer", Customer.class).setParameter("id", customer.getId()).getSingleResult();
        } catch (NoResultException ex) {
            customer = null;
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return customer;
    }*/

    /*@Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            customers = session.createNamedQuery("getAllCustomers", Customer.class).getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return customers;
    }*/

    /*@Override
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
    }*/

    /* @Override
    public boolean update(Customer customer) {
        return false;
    }*/

    /*@Override
    public boolean delete(Customer customer) {
        try (Session session = hibernateConfig.getSession()) {
            session.beginTransaction();
            User user = User.builder().id(customer.getId()).build();
            session.remove(customer);
            session.remove(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }*/
}
