/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Sale;

import java.util.Date;
import java.util.List;

public interface DAOSales {

    Sale get(Sale sale);
     
    List<Sale> getAll();

    List<Sale> getAllOrderedBy(boolean orderBy);

    List<Sale> getAllOrderedByDate(Date initialDate, Date finalDate);
     
    boolean add(Sale t);
     
    boolean update(Sale t);
     
    boolean delete(Sale t);
}
