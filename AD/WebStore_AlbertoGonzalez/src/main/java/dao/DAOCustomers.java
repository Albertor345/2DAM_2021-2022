/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Customer;
import model.Customers;

/**
 *
 */
public interface DAOCustomers {

    Customer get(Customer customer);

    Customers getAll();

    boolean add(Customers t);

    void update(Customer t);

    boolean delete(Customer t);
}
