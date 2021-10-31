package dao.impl.jdbc;

import dao.DAOCustomers;
import model.Customer;
import producers.annotations.JDBC;

import java.util.List;
@JDBC
public class DaoCustomersJDBCImpl implements DAOCustomers {

    @Override
    public Customer get(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public boolean add(Customer customer) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        return true;
    }

    @Override
    public boolean delete(Customer customer) {
        return false;
    }
}
