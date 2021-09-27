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
        return daoItems.add(item);
    }

    public List<Item> getAllItems() {
        return daoItems.getAll();
    }
}
