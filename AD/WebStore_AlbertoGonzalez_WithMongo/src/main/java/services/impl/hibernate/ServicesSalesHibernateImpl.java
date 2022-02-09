/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.hibernate;

import dao.DAOSales;
import model.Purchase;
import services.ServicesSales;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class ServicesSalesHibernateImpl implements ServicesSales {

    private DAOSales daoSales;

    @Inject
    public ServicesSalesHibernateImpl(DAOSales daoSales) {
        this.daoSales = daoSales;
    }

    public boolean add(Purchase purchase) {
        return daoSales.add(purchase);
    }

    @Override
    public boolean delete(Purchase purchase) {
        return daoSales.delete(purchase);
    }

    @Override
    public boolean update(Purchase purchase) {
        return false;
    }

    @Override
    public List<Purchase> getAll() {
        return daoSales.getAll();
    }

    @Override
    public List<Purchase> getAllOrderedBy(boolean orderBy) {
        return daoSales.getAllOrderedBy(orderBy);
    }

    @Override
    public List<Purchase> getAllOrderedByDate(LocalDate initialDate, LocalDate finalDate) {
        return daoSales.getAllOrderedByDate(initialDate, finalDate);
    }

    @Override
    public Purchase get(int id) {
        return null;
    }

}
