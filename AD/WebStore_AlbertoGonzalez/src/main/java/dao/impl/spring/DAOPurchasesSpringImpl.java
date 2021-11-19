package dao.impl.spring;

import dao.DAOPurchases;
import dao.DBConnection;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Item;
import model.Purchase;
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

@Log4j2
@SPRING
public class DAOPurchasesSpringImpl implements DAOPurchases {
    private final DBConnection dbConnection;

    @Inject
    public DAOPurchasesSpringImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Purchase get(Purchase purchase) {
        try {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(purchase);
            purchase = template.queryForObject(Constantes.SELECT_PURCHASE_QUERY, namedParameters, new PurchaseMapper());

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return purchase;
    }

    @Override
    public List<Purchase> getAll() {
        try {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            return new ArrayList<>(template.query(Constantes.SELECT_ALL_PURCHASES_QUERY, new PurchaseMapper()));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }

    @Override
    public boolean add(Purchase purchase) {
        try {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(purchase);
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(Constantes.INSERT_PURCHASE_QUERY, namedParameters, keyHolder);
            purchase.setId(keyHolder.getKey().intValue());

            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean update(Purchase purchase) {
        try {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(purchase);
            template.update(Constantes.UPDATE_PURCHASE_QUERY, namedParameters);

            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean delete(Purchase purchase) {
        try {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(purchase);
            jdbcTemplate.update(Constantes.DELETE_PURCHASE_QUERY, namedParameters);

            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    public static final class PurchaseMapper implements RowMapper<Purchase> {

        @Override
        public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Purchase.builder()
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
                    .build();
        }
    }
}
