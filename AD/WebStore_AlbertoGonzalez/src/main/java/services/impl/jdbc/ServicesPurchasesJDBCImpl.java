/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.jdbc;

import dao.DAOPurchases;
import dao.impl.jdbc.DAOPurchasesJDBCImpl;
import model.Purchase;
import services.ServicesPurchases;

import javax.inject.Inject;
import java.util.List;


public class ServicesPurchasesJDBCImpl implements ServicesPurchases {

    private DAOPurchases daoPurchases;

    @Inject
    public ServicesPurchasesJDBCImpl(DAOPurchasesJDBCImpl daoPurchases) {
        this.daoPurchases = daoPurchases;
    }

    public boolean add(Purchase purchase) {
        return daoPurchases.add(purchase);
    }

    @Override
    public boolean delete(Purchase purchase) {
        return false;
    }

    @Override
    public boolean update(Purchase purchase) {
        return false;
    }

    @Override
    public List<Purchase> getAll() {
        return daoPurchases.getAll();
    }

    @Override
    public Purchase get(int id) {
        return null;
    }

}
