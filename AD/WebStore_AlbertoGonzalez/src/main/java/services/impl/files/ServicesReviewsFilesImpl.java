/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.files;

import model.Item;
import model.Review;
import services.ServicesReviews;
import producers.annotations.FILES;

import java.util.Collections;
import java.util.List;

@FILES
public class ServicesReviewsFilesImpl implements ServicesReviews {

    @Override
    public Review add(Review review) {
        return null;
    }

    @Override
    public List<Review> getReviewsByItem(Item item) {
        return null;
    }

    public List<Review> getAll(boolean isAdmin) {
        return Collections.emptyList();
    }

    public boolean delete(Review review) {
        return false;
    }

    public Review get(Review review) {
        return null;
    }

    public boolean update(Review review) {
        return false;
    }
}
