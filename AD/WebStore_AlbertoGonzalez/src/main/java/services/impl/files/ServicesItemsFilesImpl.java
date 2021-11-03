/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.files;

import dao.DAOItems;
import dao.DAOPurchases;
import dao.impl.files.DaoItemsFilesImpl;
import dao.impl.files.DaoPurchasesFilesImpl;
import model.Item;
import model.Purchase;
import services.ServicesItems;
import producers.annotations.FILES;

import javax.inject.Inject;
import java.util.List;

@FILES
public class ServicesItemsFilesImpl implements ServicesItems {

    private DAOItems daoItems;
    private DAOPurchases daoPurchases;

    @Inject
    public ServicesItemsFilesImpl(@FILES DAOItems daoItems, @FILES DAOPurchases daoPurchases) {
        this.daoItems = daoItems;
        this.daoPurchases = daoPurchases;
    }

    public boolean add(Item item) {
        if (!checkItemID(getAll(), item)) {
            return daoItems.add(item);
        } else {
            return false;
        }
    }

    public boolean delete(Item item) {
        if (daoItems.deleteAllPurchasesFromAnItem(item)) {
            return daoItems.delete(item);
        }
        return false;
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
    public Item get(Item item) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        return daoItems.getAll();
    }

    private boolean checkItemID(List<Item> allItems, Item item) {
        return allItems.stream().anyMatch(checkingItem -> checkingItem.getId() == item.getId());
    }

    public boolean checkItemPurchases(Item item) {
        List<Purchase> purchaseList = daoPurchases.getAll();
        return purchaseList.stream()
                .map(purchase -> purchase.getItem().getId())
                .anyMatch(itemID -> item.getId() == itemID);
    }

}
