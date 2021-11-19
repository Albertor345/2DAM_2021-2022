package dao.impl.spring;

import dao.DAOReviews;
import dao.DBConnection;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Item;
import model.Purchase;
import model.Review;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import producers.annotations.SPRING;
import utils.Constantes;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SPRING
@Log4j2
public class DAOReviewsSpringImpl implements DAOReviews {

    private final DBConnection dbConnection;

    @Inject
    public DAOReviewsSpringImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Review get(Review review) {
        try {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(review);
            return template.queryForObject(Constantes.SELECT_PURCHASE_QUERY, namedParameters, new ReviewRowMapper());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public List<Review> getAll(boolean isAdmin) {
        String query = isAdmin ? Constantes.SELECT_ALL_REVIEWS_QUERY : Constantes.SELECT_REVIEWS_FROM_CUSTOMER;
        try {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            return new ArrayList<>(template.query(query, new ReviewRowMapper()));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Review add(Review review) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(review);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(Constantes.INSERT_REVIEW_QUERY, namedParameters, keyHolder);
        review.setId(keyHolder.getKey().intValue());
        return review;
    }

    @Override
    public boolean update(Review review) {
        try {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(review);
            return template.update(Constantes.UPDATE_REVIEW_QUERY, namedParameters) > 0;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean delete(Review review) {
        try {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(review);
            return template.update(Constantes.DELETE_REVIEW_QUERY, namedParameters) > 0;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    public static final class ReviewRowMapper implements RowMapper<Review> {
        @Override
        public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Review.builder()
                    .id(rs.getInt("id"))
                    .purchase(Purchase.builder()
                            .id(rs.getInt("id"))
                            .customer(Customer.builder()
                                    .id(rs.getInt("sales.id"))
                                    .name(rs.getString("c.name"))
                                    .phone(rs.getString("phone"))
                                    .address(rs.getString("address"))
                                    .build())
                            .item(Item.builder()
                                    .id(rs.getInt("sales.id"))
                                    .name(rs.getString("i.name"))
                                    .company(rs.getString("company"))
                                    .price(rs.getDouble("price"))
                                    .build())
                            .date(rs.getDate("date").toLocalDate())
                            .build())
                    .rating(rs.getInt("rating"))
                    .title(rs.getString("title"))
                    .review(rs.getString("review"))
                    .date(rs.getDate("date").toLocalDate()).build();
        }
    }

}
