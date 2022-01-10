package services;

import model.Item;
import model.Review;

import java.util.List;

public interface ServicesReviews {
    Review add(Review review);

    List<Review> getReviewsByItemAndMonth(Item item, String month);

    List<Review> getAll(boolean isAdmin);

    boolean delete(Review review);

    Review get(Review review);

    boolean update(Review review);
}
