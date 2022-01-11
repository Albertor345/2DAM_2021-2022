package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOReviews;
import lombok.extern.log4j.Log4j2;
import model.Item;
import model.Review;
import org.hibernate.Session;
import utils.Constantes;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
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
    public List<Review> getAll(boolean isAdmin) {
        String query = isAdmin ? Constantes.SELECT_ALL_REVIEWS_QUERY : Constantes.SELECT_REVIEWS_FROM_CUSTOMER;
        return Collections.emptyList();

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
    public Review add(Review review) {
        /*Constantes.INSERT_REVIEW_QUERY*/
        return review;
    }

    @Override
    public boolean update(Review review) {
        /*Constantes.UPDATE_REVIEW_QUERY*/
        return false;

    }

    @Override
    public boolean delete(Review review) {
        /* Constantes.DELETE_REVIEW_QUERY*/
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
