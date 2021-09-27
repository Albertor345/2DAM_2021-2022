/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Item;

import java.util.List;

/**
 *
 */
public interface DAOItems {
    void update(Item item);

    void add(Item item);

    void delete(Item item);

    Item get(Item item);

    List<Item> getAll();
}
