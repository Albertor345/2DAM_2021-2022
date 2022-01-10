/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.hibernate;

import dao.DAOCustomers;
import model.Customer;
import model.User;
import services.ServicesCustomers;

import javax.inject.Inject;
import java.util.List;

public class ServicesCustomersHibernateImpl implements ServicesCustomers {

    private DAOCustomers daoCustomers;

    @Inject
    public ServicesCustomersHibernateImpl(DAOCustomers daoCustomers) {
        this.daoCustomers = daoCustomers;
    }

    @Override
    public List<Customer> getAll() {
        return daoCustomers.getAll();
    }

    public User login(User user) {
        return daoCustomers.login(user);
    }

    @Override
    public Customer get(Customer customer) {
        return daoCustomers.get(customer);
    }

    @Override
    public boolean delete(Customer customer) {
        return daoCustomers.delete(customer);
    }

    @Override
    public boolean update(Customer customer) {
        return daoCustomers.update(customer);
    }

    @Override
    public boolean add(Customer customer) {
        List<Customer> customers = getAll();
        customer.setId(customers.size());
        return daoCustomers.add(customer);
    }
}
