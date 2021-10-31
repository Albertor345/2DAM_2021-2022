/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.files;

import model.Review;
import services.ServicesReviews;
import producers.annotations.FILES;

import java.util.Collections;
import java.util.List;

@FILES
public class ServicesReviewsFilesImpl implements ServicesReviews {

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
