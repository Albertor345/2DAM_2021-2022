package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOReviews;
import lombok.extern.log4j.Log4j2;
import model.Item;
import model.Review;
import utils.Constantes;

import javax.inject.Inject;
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
        /*Constantes.SELECT_REVIEWS_FROM_ITEM*/
        return Collections.emptyList();

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


}
