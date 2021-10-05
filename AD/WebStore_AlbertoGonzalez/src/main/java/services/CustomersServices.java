/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOCustomers;
import model.Customer;

import javax.inject.Inject;
import java.util.List;


public class CustomersServices {

    DAOCustomers daoCustomers;

    @Inject
    public CustomersServices(DAOCustomers daoCustomers) {
        this.daoCustomers = daoCustomers;
    }

    public List<Customer> getAllCustomers() {
        return daoCustomers.getAll();
    }

    public List<Customer> get(int id) {
        return null;
    }

    public void deleteCustomer(Customer customer) {

    }

    public boolean addCustomer(Customer customer) {
        return false;
    }

}
