/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;

import dao.DAOItems;
import dao.DaoItemsFiles;
import model.Item;

import javax.inject.Inject;

/**
 * @author dam2
 */
public class ItemsServices {

    DAOItems daoItems;

    @Inject
    public ItemsServices(DAOItems daoItems) {
        this.daoItems = daoItems;
    }

    public boolean addItem(Item item) {
        if (!checkItemID(getAllItems(), item)){
            return daoItems.add(item);
        }else{
            return false;
        }
    }



    public List<Item> getAllItems() {
        return daoItems.getAll();
    }

    private boolean checkItemID(List<Item> allItems, Item item){
        return allItems.stream().anyMatch(item1 -> item1.getIdItem() == item.getIdItem());
    }
}
