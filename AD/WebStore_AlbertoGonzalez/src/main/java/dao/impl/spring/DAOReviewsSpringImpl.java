package dao.impl.spring;

import dao.DAOReviews;
import model.Review;
import producers.annotations.JDBC;

import java.util.List;

@JDBC
public class DAOReviewsSpringImpl implements DAOReviews {
    @Override
    public Review get(int id) {
        return null;
    }

    @Override
    public List<Review> getAll() {
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
