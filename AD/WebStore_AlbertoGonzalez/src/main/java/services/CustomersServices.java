/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOCustomers;
import model.Customer;
import model.Customers;

import javax.inject.Inject;
import java.util.List;


public class CustomersServices {

    private DAOCustomers daoCustomers;

    @Inject
    public CustomersServices(DAOCustomers daoCustomers) {
        this.daoCustomers = daoCustomers;
    }

    public Customers getAllCustomers() {
        return daoCustomers.getAll();
    }

    public List<Customer> get(int id) {
        return null;
    }

    public boolean deleteCustomer(Customer customer) {
        return daoCustomers.delete(customer);
    }

    public boolean addCustomer(Customer customer) {
        Customers customers = getAllCustomers();
        customer.setIdCustomer(customers.getCustomers().size());
        customers.getCustomers().add(customer);
        return daoCustomers.add(customers);
    }

}
