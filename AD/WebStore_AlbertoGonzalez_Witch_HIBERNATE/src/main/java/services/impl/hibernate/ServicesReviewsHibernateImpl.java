/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.hibernate;

import dao.DAOReviews;
import model.Item;
import model.Review;
import services.ServicesReviews;

import javax.inject.Inject;
import java.util.List;

public class ServicesReviewsHibernateImpl implements ServicesReviews {

    DAOReviews daoReviews;

    @Inject
    public ServicesReviewsHibernateImpl(DAOReviews daoReviews) {
        this.daoReviews = daoReviews;
    }

    @Override
    public List<Review> getReviewsByItem(Item item) {
        return daoReviews.getReviewsByItem(item);
    }

    @Override
    public List<Review> getAll(boolean isAdmin) {
        return daoReviews.getAll(isAdmin);
    }

    @Override
    public Review get(Review review) {
        return daoReviews.get(review);
    }

    @Override
    public List<Review> orderBy(Item item, boolean order, boolean column) {
        return daoReviews.orderBy(item, order, column);
    }

    @Override
    public Review add(Review review) {
        return daoReviews.add(review);
    }

    public boolean delete(Review review) {
        return daoReviews.delete(review);
    }

    public boolean update(Review review) {
        return daoReviews.update(review);
    }

}
