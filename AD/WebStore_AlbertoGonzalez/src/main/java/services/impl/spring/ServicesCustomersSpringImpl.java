/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.spring;

import dao.DAOCustomers;
import dao.impl.spring.DaoCustomersSpringImpl;
import model.Customer;
import model.User;
import producers.annotations.SPRING;
import services.ServicesCustomers;

import javax.inject.Inject;
import java.util.List;

@SPRING
public class ServicesCustomersSpringImpl implements ServicesCustomers {

    private DAOCustomers daoCustomers;

    @Inject
    public ServicesCustomersSpringImpl(@SPRING DAOCustomers daoCustomers) {
        this.daoCustomers = daoCustomers;
    }

    @Override
    public List<Customer> getAll() {
        return daoCustomers.getAll();
    }

    public User login(User user) {
        return ((DaoCustomersSpringImpl) daoCustomers).login(user);
    }

    public int checkUserStatus(User user) {
        return ((DaoCustomersSpringImpl) daoCustomers).checkUserStatus(user);
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
        customer.setIdCustomer(customers.size());
        return daoCustomers.add(customer);
    }
}
