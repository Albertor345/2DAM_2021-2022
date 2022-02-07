package services;

import model.Sale;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ServicesSales {

    boolean add(Sale purchase);

    boolean delete(Sale purchase);

    boolean update(Sale purchase);

    List<Sale> getAll();

    List<Sale> getAllOrderedBy(boolean orderBy);

    List<Sale> getAllOrderedByDate(LocalDate initialDate, LocalDate finalDate);

    Sale get(int id);
}
