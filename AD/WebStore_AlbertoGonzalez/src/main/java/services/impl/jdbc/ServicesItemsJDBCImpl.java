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
import services.ServicesItems;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


public class ServicesItemsJDBCImpl implements ServicesItems {

    private DAOItems daoItems;
    private DAOPurchases daoPurchases;

    @Inject
    public ServicesItemsJDBCImpl(@Named("ItemsJDBC") DAOItems daoItems, DaoPurchasesFilesImpl daoPurchases) {
        this.daoItems = daoItems;
        this.daoPurchases = daoPurchases;
    }

    public boolean add(Item item) {
        return daoItems.add(item);
    }

    public boolean delete(Item item) {
        if (daoPurchases.deleteAllPurchasesFromAnItem(item)) {
            return daoItems.delete(item);
        }
        return false;
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

}
