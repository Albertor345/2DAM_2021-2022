package services;

import model.Item;
import model.Review;

import java.util.List;

public interface ServicesReviews {
    Review add(Review review);

    List<Review> getReviewsByItem(Item item);

    List<Review> getAll(boolean isAdmin);

    boolean delete(Review review);

    Review get(Review review);

    boolean update(Review review);
}
