package dao.impl.jdbc;

import dao.DAOReviews;
import model.Review;
import producers.annotations.JDBC;

import java.util.List;

@JDBC
public class DAOReviewsJDBCImpl implements DAOReviews {
    @Override
    public Review get(int id) {
        return null;
    }

    @Override
    public List<Review> getAll(boolean isAdmin) {
        return null;
    }

    @Override
    public void save(Review t) {

    }

    @Override
    public void update(Review t) {

    }

    @Override
    public void delete(Review t) {

    }
}
