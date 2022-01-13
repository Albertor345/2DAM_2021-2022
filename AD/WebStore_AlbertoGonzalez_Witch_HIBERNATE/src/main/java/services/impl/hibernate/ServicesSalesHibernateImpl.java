/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.hibernate;

import dao.DAOSales;
import model.Sale;
import services.ServicesSales;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class ServicesSalesHibernateImpl implements ServicesSales {

    private DAOSales daoSales;

    @Inject
    public ServicesSalesHibernateImpl(DAOSales daoSales) {
        this.daoSales = daoSales;
    }

    public boolean add(Sale purchase) {
        return daoSales.add(purchase);
    }

    @Override
    public boolean delete(Sale purchase) {
        return daoSales.delete(purchase);
    }

    @Override
    public boolean update(Sale purchase) {
        return false;
    }

    @Override
    public List<Sale> getAll() {
        return daoSales.getAll();
    }

    @Override
    public List<Sale> getAllOrderedBy(boolean orderBy) {
        return daoSales.getAllOrderedBy(orderBy);
    }

    @Override
    public List<Sale> getAllOrderedByDate(Date initialDate, Date finalDate) {
        return daoSales.getAllOrderedByDate(initialDate, finalDate);
    }

    @Override
    public Sale get(int id) {
        return null;
    }

}
