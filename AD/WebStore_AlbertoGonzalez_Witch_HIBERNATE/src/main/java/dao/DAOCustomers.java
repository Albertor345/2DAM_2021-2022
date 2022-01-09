/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Customer;
import model.User;

import java.util.List;

/**
 *
 */
public interface DAOCustomers {

    Customer get(Customer customer);

    List<Customer> getAll();

    boolean add(Customer t);

    boolean update(Customer t);

    boolean delete(Customer t);

}
