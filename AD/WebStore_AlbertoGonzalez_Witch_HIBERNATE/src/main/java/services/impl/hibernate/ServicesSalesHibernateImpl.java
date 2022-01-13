/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.hibernate;

import dao.DAOPurchases;
import model.Sale;
import services.ServicesSales;

import javax.inject.Inject;
import java.util.List;

public class ServicesSalesHibernateImpl implements ServicesSales {

    private DAOPurchases daoPurchases;

    @Inject
    public ServicesSalesHibernateImpl(DAOPurchases daoPurchases) {
        this.daoPurchases = daoPurchases;
    }

    public boolean add(Sale purchase) {
        return daoPurchases.add(purchase);
    }

    @Override
    public boolean delete(Sale purchase) {
        return daoPurchases.delete(purchase);
    }

    @Override
    public boolean update(Sale purchase) {
        return false;
    }

    @Override
    public List<Sale> getAll() {
        return daoPurchases.getAll();
    }

    @Override
    public Sale get(int id) {
        return null;
    }

}
