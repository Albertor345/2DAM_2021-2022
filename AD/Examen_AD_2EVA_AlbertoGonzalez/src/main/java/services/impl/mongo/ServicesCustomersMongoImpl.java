/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.mongo;

import dao.DAOCustomers;
import services.ServicesCustomers;

import javax.inject.Inject;
import java.util.List;

public class ServicesCustomersMongoImpl implements ServicesCustomers {

    @Inject
    private DAOCustomers daoCustomers;


    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public List<String> getAll() {
        return null;
    }

    @Override
    public void get() {

    }
}
