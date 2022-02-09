/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Purchase;

import java.time.LocalDate;
import java.util.List;

public interface DAOSales {

    Purchase get(Purchase purchase);
     
    List<Purchase> getAll();

    List<Purchase> getAllOrderedBy(boolean orderBy);

    List<Purchase> getAllOrderedByDate(LocalDate initialDate, LocalDate finalDate);
     
    boolean add(Purchase t);
     
    boolean update(Purchase t);
     
    boolean delete(Purchase t);
}
