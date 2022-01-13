/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.hibernate;

import dao.DAOItems;
import dao.DAOSales;
import model.Item;
import model.Sale;
import services.ServicesItems;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ServicesItemsHibernateImpl implements ServicesItems {

    private DAOItems daoItems;
    private DAOSales daoSales;

    @Inject
    public ServicesItemsHibernateImpl(DAOItems daoItems, DAOSales daoSales) {
        this.daoItems = daoItems;
        this.daoSales = daoSales;
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
        return daoItems.update(item);
    }

    @Override
    public Item get(Item item) {
        return daoItems.get(item);
    }

    @Override
    public List<Item> getAll() {
        return daoItems.getAll();
    }

    @Override
    public String getItemData(Item item) {
        AtomicReference<String> result = new AtomicReference<>("");
        List<Object[]> list = daoItems.getItemData(item);
        if (Arrays.stream(list.get(0)).anyMatch(o -> o == null)) {
            result.set("This item has not been sold in the last month");
        } else {
            list.forEach(o -> result.set("{Price: " + o[0] + "} -- {Number of Purchases: " + o[1] + "} -- {Average rating " + o[2] + "}"));
        }

        return result.get();
    }

    @Override
    public boolean checkItemPurchases(Item item) {
        List<Sale> purchaseList = daoSales.getAll();
        return purchaseList.stream()
                .map(purchase -> purchase.getItem().getId())
                .anyMatch(itemID -> item.getId() == itemID);
    }

}
