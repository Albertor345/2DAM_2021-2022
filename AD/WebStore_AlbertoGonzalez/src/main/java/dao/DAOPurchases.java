/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Item;
import model.Purchase;

import java.util.List;

public interface DAOPurchases {

    Purchase get(Purchase purchase);
     
    List<Purchase> getAll();
     
    boolean add(Purchase t);
     
    boolean update(Purchase t);
     
    boolean delete(Purchase t);
}
