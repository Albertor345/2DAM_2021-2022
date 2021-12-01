package dao.impl.jdbc;

import dao.DAOReviews;
import model.Item;
import model.Review;
import producers.annotations.JDBC;

import java.util.List;

@JDBC
public class DAOReviewsJDBCImpl implements DAOReviews {
    @Override
    public Review get(Review review) {
        return null;
    }

    @Override
    public List<Review> getAll(boolean isAdmin) {
        return null;
    }

    @Override
    public List<Review> getReviewsByItem(Item item) {
        return null;
    }

    @Override
    public Review add(Review t) {
        return null;
    }

    @Override
    public boolean update(Review t) {
        return false;
    }

    @Override
    public boolean delete(Review t) {
        return false;
    }
}
