package dao.impl.spring;

import dao.DAOCustomers;
import dao.DBConnection;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
import producers.annotations.SPRING;
import utils.Constantes;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@SPRING
public class DaoCustomersSpringImpl implements DAOCustomers {
    private final DBConnection dbConnection;

    @Inject
    public DaoCustomersSpringImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public User login(User user) {
        try {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
            user = template.queryForObject(Constantes.QUERY_LOGIN, namedParameters, BeanPropertyRowMapper.newInstance(User.class));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return user;
    }

    public int checkUserStatus(User user) {
        try {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
            int isAdmin = template.queryForObject(Constantes.QUERY_CHECK_USER_IS_CUSTOMER, namedParameters, Integer.class);

            return isAdmin != 0 ? 1 : 0;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Customer get(Customer customer) {
        try {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(customer);
            customer = template.queryForObject(Constantes.SELECT_CUSTOMER_QUERY, namedParameters, BeanPropertyRowMapper.newInstance(Customer.class));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        try {
            JdbcTemplate template = new JdbcTemplate(dbConnection.getDataSource());
            return new ArrayList<>(template.query(Constantes.SELECT_ALL_CUSTOMERS_QUERY, BeanPropertyRowMapper.newInstance(Customer.class)));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }

    @Override
    public boolean add(Customer customer) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dbConnection.getDataSource());
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        try {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(transactionManager.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(User.builder().name(customer.getName()).password(customer.getName()).build());
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(Constantes.INSERT_USER_QUERY, namedParameters, keyHolder);
            customer.setIdCustomer(keyHolder.getKey().intValue());

            namedParameters = new BeanPropertySqlParameterSource(customer);
            jdbcTemplate.update(Constantes.INSERT_CUSTOMER_QUERY, namedParameters);

            transactionManager.commit(transactionStatus);
            return true;
        } catch (Exception ex) {
            transactionManager.rollback(transactionStatus);
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean update(Customer customer) {
        try {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(customer);
            jdbcTemplate.update(Constantes.UPDATE_CUSTOMER_QUERY, namedParameters);

            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean delete(Customer customer) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dbConnection.getDataSource());
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        try {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(transactionManager.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(customer);
            jdbcTemplate.update(Constantes.DELETE_CUSTOMER_QUERY, namedParameters);

            jdbcTemplate.update(Constantes.DELETE_USER_QUERY, namedParameters);

            transactionManager.commit(transactionStatus);
            return true;
        } catch (DataIntegrityViolationException ex) {
            transactionManager.rollback(transactionStatus);
        } catch (Exception ex) {
            transactionManager.rollback(transactionStatus);
            log.error(ex.getMessage(), ex);
        }
        return false;
    }
}
