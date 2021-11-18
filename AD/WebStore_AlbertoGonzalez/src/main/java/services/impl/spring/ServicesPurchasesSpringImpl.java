/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.spring;

import dao.DAOPurchases;
import model.Purchase;
import producers.annotations.JDBC;
import producers.annotations.SPRING;
import services.ServicesPurchases;

import javax.inject.Inject;
import java.util.List;

@SPRING
public class ServicesPurchasesSpringImpl implements ServicesPurchases {

    private DAOPurchases daoPurchases;

    @Inject
    public ServicesPurchasesSpringImpl(@SPRING DAOPurchases daoPurchases) {
        this.daoPurchases = daoPurchases;
    }

    public boolean add(Purchase purchase) {
        return daoPurchases.add(purchase);
    }

    @Override
    public boolean delete(Purchase purchase) {
        return daoPurchases.delete(purchase);
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
