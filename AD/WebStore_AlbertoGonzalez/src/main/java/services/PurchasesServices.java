/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOPurchases;
import model.Item;
import model.Purchase;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;


public class PurchasesServices {

    private DAOPurchases daoPurchases;

    @Inject
    public PurchasesServices(DAOPurchases daoPurchases) {
        this.daoPurchases = daoPurchases;
    }

    public List<Purchase> getAllPurchases() {
        return daoPurchases.getAll();
    }

    public List<Purchase> getPurchaseByDate(LocalDate date) {
        return null;
    }

    public List<Purchase> getPurchasesByClientId(int id) {
        return null;
    }

    public boolean deletePurchase(Purchase purchase) {
        return daoPurchases.delete(purchase);
    }

    public boolean deleteAllPurchasesFromAnItem(Item item) {
        return daoPurchases.deleteAllPurchasesFromAnItem(item);
    }


    public boolean addPurchase(Purchase purchase) {
        return daoPurchases.add(purchase);
    }

}
