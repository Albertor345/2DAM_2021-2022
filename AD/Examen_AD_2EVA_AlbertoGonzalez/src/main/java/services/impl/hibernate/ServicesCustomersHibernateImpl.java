/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.hibernate;

import dao.DAOCustomers;
import dao.impl.hibernate.DaoCustomersHibernateImpl;
import services.ServicesCustomers;

import java.util.List;

public class ServicesCustomersHibernateImpl implements ServicesCustomers {


    private DAOCustomers daoCustomers;

    public ServicesCustomersHibernateImpl() {
        this.daoCustomers = new DaoCustomersHibernateImpl();
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
        daoCustomers.getAll();
        return null;
    }

    @Override
    public void get() {
        daoCustomers.get();
    }
}
