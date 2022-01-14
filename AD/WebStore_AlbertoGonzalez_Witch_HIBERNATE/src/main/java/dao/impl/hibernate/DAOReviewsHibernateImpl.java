package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOReviews;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Item;
import model.Review;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOReviewsHibernateImpl implements DAOReviews {

    private final HibernateConfig hibernateConfig;

    @Inject
    public DAOReviewsHibernateImpl(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    @Override
    public Review get(Review review) {
        /*Constantes.SELECT_PURCHASE_QUERY*/
        return null;
    }

    @Override
    public List<Review> getAll() {
        List<Review> reviews = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            reviews = session.createNamedQuery("getAllReviews", Review.class)
                    .getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return reviews;

    }

    @Override
    public List<Review> getReviewsByItem(Item item) {
        List<Review> reviews = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            reviews = session.createNamedQuery("getAllReviewsByItem", Review.class)
                    .setParameter("id", item.getId())
                    .getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return reviews;
    }

    @Override
    public List<Review> getReviewsByCustomer(Customer customer) {
        List<Review> reviews = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            reviews = session.createNamedQuery("getAllReviewsByCustomer", Review.class)
                    .setParameter("id", customer.getId())
                    .getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return reviews;
    }

    @Override
    public boolean add(Review review) {
        try (Session session = hibernateConfig.getSession()) {
            session.beginTransaction();
            session.save(review);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean update(Review review) {
        return false;
    }

    @Override
    public boolean delete(Review review) {
        try (Session session = hibernateConfig.getSession()) {
            session.beginTransaction();
            session.remove(review);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;

    }

    @Override
    public List<Review> orderBy(Item item, boolean order, boolean column) {
        List<Review> reviews = new ArrayList<>();
        String orderBy = order ? "Asc" : "Desc";
        String col = column ? "Rating" : "Date";
        String query = "orderReviews" + orderBy + col;
        try (Session session = hibernateConfig.getSession()) {
            reviews = session.createNamedQuery(query, Review.class)
                    .setParameter("id", item.getId())
                    .getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return reviews;
    }

}
