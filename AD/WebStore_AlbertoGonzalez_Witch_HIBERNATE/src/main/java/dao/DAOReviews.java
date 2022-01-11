/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Item;
import model.Review;

import java.util.List;

/**
 *
 */
public interface DAOReviews {

    Review get(Review review);

    List<Review> getAll(boolean isAdmin);

    List<Review> getReviewsByItem(Item item);

    Review add(Review t);

    boolean update(Review t);

    boolean delete(Review t);
}
