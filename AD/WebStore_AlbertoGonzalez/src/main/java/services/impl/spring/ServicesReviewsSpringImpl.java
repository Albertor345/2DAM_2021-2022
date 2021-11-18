/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.spring;

import dao.DAOReviews;
import model.Review;
import producers.annotations.JDBC;
import producers.annotations.SPRING;
import services.ServicesReviews;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@SPRING
public class ServicesReviewsSpringImpl implements ServicesReviews {

    DAOReviews daoReviews;

    @Inject
    public ServicesReviewsSpringImpl(@SPRING DAOReviews daoReviews) {
        this.daoReviews = daoReviews;
    }

    @Override
    public boolean add(Review review) {
        return false;
    }

    public List<Review> getAll() {
        return Collections.emptyList();
    }

    public boolean delete(Review review) {
        return false;
    }

    public Review get(int id) {
        return null;
    }

    public boolean update(Review review) {
        return false;
    }
}
