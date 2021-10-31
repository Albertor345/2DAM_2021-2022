/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.files;

import dao.DAOCustomers;
import dao.impl.files.DaoCustomersFilesImpl;
import model.Customer;
import services.ServicesCustomers;
import producers.annotations.FILES;

import javax.inject.Inject;
import java.util.List;

@FILES
public class ServicesCustomersFilesImpl implements ServicesCustomers {

    private DAOCustomers daoCustomers;

    @Inject
    public ServicesCustomersFilesImpl(@FILES DAOCustomers daoCustomers) {
        this.daoCustomers = daoCustomers;
    }

    @Override
    public List<Customer> getAll() {
        return daoCustomers.getAll();
    }

    @Override
    public Customer get(int id) {
        return null;
    }

    @Override
    public boolean delete(Customer customer) {
        return daoCustomers.delete(customer);
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean add(Customer customer) {
        List<Customer> customers = getAll();
        customer.setId(customers.size());
        return daoCustomers.add(customer);
    }

}
