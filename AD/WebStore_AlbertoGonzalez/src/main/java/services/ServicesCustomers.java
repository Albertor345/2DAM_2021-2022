package services;

import model.Customer;
import model.Item;

import java.util.List;

public interface ServicesCustomers {
    boolean add(Customer customer);

    boolean delete(Customer customer);

    boolean update(Customer customer);

    List<Customer> getAll();

    Customer get(int id);
}
