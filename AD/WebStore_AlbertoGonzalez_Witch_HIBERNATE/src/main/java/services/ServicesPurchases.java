package services;

import model.Purchase;
import model.Review;
import model.Sale;

import java.util.List;

public interface ServicesPurchases {

    boolean add(Sale purchase);

    boolean delete(Sale purchase);

    boolean update(Sale purchase);

    List<Sale> getAll();

    Sale get(int id);
}
