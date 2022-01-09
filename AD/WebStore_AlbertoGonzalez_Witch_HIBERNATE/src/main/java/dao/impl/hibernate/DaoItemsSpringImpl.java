package dao.impl.hibernate;

import dao.DAOItems;
import dao.DBConnection;
import lombok.extern.log4j.Log4j2;
import model.Item;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import utils.Constantes;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
public class DaoItemsSpringImpl implements DAOItems {

    private DBConnection dbConnection;

    @Inject
    public DaoItemsSpringImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean update(Item item) {
        try {

            template.update(Constantes.UPDATE_ITEM_QUERY, namedParameters);

            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean add(Item item) {
        try {


            jdbcTemplate.update(Constantes.INSERT_ITEM_QUERY, namedParameters, keyHolder);
            item.setId(keyHolder.getKey().intValue());

            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean delete(Item item) {
        try {

            jdbcTemplate.update(Constantes.DELETE_ITEM_QUERY, namedParameters);

            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean deleteAllPurchasesFromAnItem(Item item) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dbConnection.getDataSource());
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        try {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(transactionManager.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(item);
            jdbcTemplate.update(Constantes.DELETE_SALES_FROM_ITEM, namedParameters);

            jdbcTemplate.update(Constantes.DELETE_ITEM_QUERY, namedParameters);

            transactionManager.commit(transactionStatus);
            return true;
        } catch (Exception ex) {
            transactionManager.rollback(transactionStatus);
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public Item get(Item item) {
        try {

            item = template.queryForObject(Constantes.SELECT_ITEM_QUERY, namedParameters, BeanPropertyRowMapper.newInstance(Item.class));

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        try {

            return new ArrayList<>(template.query(Constantes.SELECT_ALL_ITEMS_QUERY, BeanPropertyRowMapper.newInstance(Item.class)));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }
}
