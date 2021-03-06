/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.files;

import dao.DAOPurchases;
import dao.impl.files.DaoPurchasesFilesImpl;
import model.Purchase;
import services.ServicesPurchases;
import producers.annotations.FILES;

import javax.inject.Inject;
import java.util.List;


@FILES
public class ServicesPurchasesFilesImpl implements ServicesPurchases {

    private DAOPurchases daoPurchases;

    @Inject
    public ServicesPurchasesFilesImpl(@FILES DAOPurchases daoPurchases) {
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
