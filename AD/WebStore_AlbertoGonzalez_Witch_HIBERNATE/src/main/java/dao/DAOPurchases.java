/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Sale;

import java.util.List;

public interface DAOPurchases {

    Sale get(Sale sale);
     
    List<Sale> getAll();
     
    boolean add(Sale t);
     
    boolean update(Sale t);
     
    boolean delete(Sale t);
}
