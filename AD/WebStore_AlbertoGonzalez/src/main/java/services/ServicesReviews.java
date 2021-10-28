package services;

import model.Customer;
import model.Review;

import java.util.Collections;
import java.util.List;

public interface ServicesReviews {
    boolean add(Review review);

    List<Review> getAll();

    boolean delete(Review review);

    Review get(int id);

    boolean update(Review review);
}
