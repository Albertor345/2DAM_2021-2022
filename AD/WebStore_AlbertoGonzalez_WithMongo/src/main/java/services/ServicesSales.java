package services;

import model.Purchase;

import java.time.LocalDate;
import java.util.List;

public interface ServicesSales {

    boolean add(Purchase purchase);

    boolean delete(Purchase purchase);

    boolean update(Purchase purchase);

    List<Purchase> getAll();

    List<Purchase> getAllOrderedBy(boolean orderBy);

    List<Purchase> getAllOrderedByDate(LocalDate initialDate, LocalDate finalDate);

    Purchase get(int id);
}
