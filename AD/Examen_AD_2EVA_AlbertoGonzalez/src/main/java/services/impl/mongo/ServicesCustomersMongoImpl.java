/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.mongo;

import dao.DAOCustomers;
import dao.impl.mongo.DaoCustomersMongoImpl;
import services.ServicesCustomers;

import java.util.List;

public class ServicesCustomersMongoImpl implements ServicesCustomers {

    private DAOCustomers daoCustomers;

    public ServicesCustomersMongoImpl() {
        this.daoCustomers = new DaoCustomersMongoImpl();
    }

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
        return daoCustomers.getAll();
    }

    @Override
    public void get() {
        daoCustomers.get();
    }
}
