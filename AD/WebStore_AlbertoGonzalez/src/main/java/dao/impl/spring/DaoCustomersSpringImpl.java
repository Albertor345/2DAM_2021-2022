package dao.impl.spring;

import dao.DAOCustomers;
import dao.DBConnection;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import producers.annotations.JDBC;
import utils.Constantes;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@JDBC
public class DaoCustomersSpringImpl implements DAOCustomers {
    private final DBConnection dbConnection;
    private JdbcTemplate jtm;

    @Inject
    public DaoCustomersSpringImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        jtm = new JdbcTemplate(dbConnection.getDataSource());
    }

    @Override
    public Customer get(Customer customer) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.SELECT_CUSTOMER_QUERY)) {
            preparedStatement.setInt(1, customer.getIdCustomer());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhone(resultSet.getString("phone"));
            }

            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dbConnection.getDataSource());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(usuario);
            usuario = jdbcTemplate.queryForObject(Constantes.QUERY_LOGIN, namedParameters, BeanPropertyRowMapper.newInstance(UsuarioServer.class));


        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.SELECT_ALL_CUSTOMERS_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(Customer.builder()
                        .idCustomer(resultSet.getInt("id_customer"))
                        .name(resultSet.getString("name"))
                        .phone(resultSet.getString("phone"))
                        .address(resultSet.getString("address"))
                        .build());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return customers;
    }

    @Override
    public boolean add(Customer customer) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.INSERT_CUSTOMER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                customer.setIdCustomer(resultSet.getInt("id_customer"));
            }
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean update(Customer customer) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.UPDATE_CUSTOMER_QUERY)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setInt(4, customer.getIdCustomer());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.DELETE_CUSTOMER_QUERY)) {
            preparedStatement.setInt(1, customer.getIdCustomer());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            return false;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }
}
