/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.jdbc;

import dao.DAOItems;
import dao.DAOPurchases;
import dao.impl.files.DaoPurchasesFilesImpl;
import model.Item;
import model.Purchase;
import services.ServicesItems;
import producers.annotations.JDBC;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@JDBC
public class ServicesItemsJDBCImpl implements ServicesItems {

    private DAOItems daoItems;
    private DAOPurchases daoPurchases;

    @Inject
    public ServicesItemsJDBCImpl(@JDBC DAOItems daoItems, @JDBC DAOPurchases daoPurchases) {
        this.daoItems = daoItems;
        this.daoPurchases = daoPurchases;
    }

    public boolean add(Item item) {
        return daoItems.add(item);
    }

    public boolean delete(Item item) {
        return daoItems.delete(item);
    }

    @Override
    public boolean deleteAllPurchasesFromAnItem(Item item) {
        return daoItems.deleteAllPurchasesFromAnItem(item);
    }

    @Override
    public boolean update(Item item) {
        return false;
    }

    @Override
    public Item get(int id) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        return daoItems.getAll();
    }

    @Override
    public boolean checkItemPurchases(Item item) {
        List<Purchase> purchaseList = daoPurchases.getAll();
        return purchaseList.stream()
                .map(purchase -> purchase.getItem().getId())
                .anyMatch(itemID -> item.getId() == itemID);
    }

}
