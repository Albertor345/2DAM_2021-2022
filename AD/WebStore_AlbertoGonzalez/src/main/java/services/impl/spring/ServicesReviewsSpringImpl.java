/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.spring;

import dao.DAOReviews;
import model.Review;
import producers.annotations.SPRING;
import services.ServicesReviews;

import javax.inject.Inject;
import java.util.List;

@SPRING
public class ServicesReviewsSpringImpl implements ServicesReviews {

    DAOReviews daoReviews;

    @Inject
    public ServicesReviewsSpringImpl(@SPRING DAOReviews daoReviews) {
        this.daoReviews = daoReviews;
    }

    @Override
    public Review add(Review review) {
        return daoReviews.add(review);
    }

    public List<Review> getAll(boolean isAdmin) {
        return daoReviews.getAll(isAdmin);
    }

    public boolean delete(Review review) {
        return daoReviews.delete(review);
    }

    public Review get(Review review) {
        return daoReviews.get(review);
    }

    public boolean update(Review review) {
        return daoReviews.update(review);
    }
}
