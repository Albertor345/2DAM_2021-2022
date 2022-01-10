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
    public Review add(Review review) {
        return daoReviews.add(review);
    }

    @Override
    public List<Review> getReviewsByItemAndMonth(Item item, String month) {
        return daoReviews.getReviewsByItem(item, mapMonth(month));
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

    private int mapMonth(String month) {
        int number = 0;
        switch (month) {
            case "January":
                number = 1;
                break;
            case "February":
                number = 2;
                break;
            case "March":
                number = 3;
                break;
            case "April":
                number = 4;
                break;
            case "May":
                number = 5;
                break;
            case "June":
                number = 6;
                break;
            case "July":
                number = 7;
                break;
            case "August":
                number = 8;
                break;
            case "September":
                number = 9;
                break;
            case "October":
                number = 10;
                break;
            case "November":
                number = 11;
                break;
            case "December":
                number = 12;
                break;
        }
        return number;
    }
}
