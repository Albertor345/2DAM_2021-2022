/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOItems;
import dao.DAOPurchases;
import model.Item;
import model.Purchase;

import javax.inject.Inject;
import java.util.List;

/**
 * @author dam2
 */
public class ItemsServices {

    private DAOItems daoItems;
    private DAOPurchases daoPurchases;

    @Inject
    public ItemsServices(DAOItems daoItems, DAOPurchases daoPurchases) {
        this.daoItems = daoItems;
        this.daoPurchases = daoPurchases;
    }

    public boolean addItem(Item item) {
        if (!checkItemID(getAllItems(), item)) {
            return daoItems.add(item);
        } else {
            return false;
        }
    }

    public boolean deleteItem(Item item) {
        if (daoPurchases.deleteAllPurchasesFromAnItem(item)) {
            return daoItems.delete(item);
        }
        return false;
    }


    public List<Item> getAllItems() {
        return daoItems.getAll();
    }

    private boolean checkItemID(List<Item> allItems, Item item) {
        return allItems.stream().anyMatch(checkingItem -> checkingItem.getIdItem() == item.getIdItem());
    }

    public boolean checkItemPurchases(Item item) {
        List<Purchase> purchaseList = daoPurchases.getAll();
        return purchaseList.stream()
                .map(purchase -> purchase.getItemID())
                .anyMatch(itemID -> item.getIdItem() == itemID);
    }

}
