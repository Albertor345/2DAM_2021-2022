package dao.impl.hibernate;

import dao.DAOPurchases;
import dao.DBConnection;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Item;
import model.Sale;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import utils.Constantes;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
public class DAOPurchasesSpringImpl implements DAOPurchases {
    private final DBConnection dbConnection;

    @Inject
    public DAOPurchasesSpringImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Sale get(Sale sale) {
        try {

            sale = template.queryForObject(Constantes.SELECT_PURCHASE_QUERY, namedParameters, new PurchaseMapper());

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return sale;
    }

    @Override
    public List<Sale> getAll() {
        try {

            return new ArrayList<>(template.query(Constantes.SELECT_ALL_PURCHASES_QUERY, new PurchaseMapper()));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }

    @Override
    public boolean add(Sale sale) {
        try {
            jdbcTemplate.update(Constantes.INSERT_PURCHASE_QUERY, namedParameters, keyHolder);
            sale.setId(keyHolder.getKey().intValue());

            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean update(Sale sale) {
        try {

            template.update(Constantes.UPDATE_PURCHASE_QUERY, namedParameters);

            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean delete(Sale sale) {
        try {

            jdbcTemplate.update(Constantes.DELETE_PURCHASE_QUERY, namedParameters);

            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }
}
