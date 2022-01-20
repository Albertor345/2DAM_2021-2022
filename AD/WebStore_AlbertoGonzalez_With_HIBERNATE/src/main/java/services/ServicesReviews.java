package services;

import model.Customer;
import model.Item;
import model.Review;

import java.util.List;

public interface ServicesReviews {
    List<Review> orderBy(Item item, boolean order, boolean column);

    boolean add(Review review);

    List<Review> getReviewsByItem(Item item);

    List<Review> getAll();

    boolean delete(Review review);

    Review get(Review review);

    boolean update(Review review);

    List<Review> getReviewsByCustomer(Customer customer);
}
