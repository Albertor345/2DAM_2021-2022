package services;

import model.Purchase;
import model.Review;

import java.util.List;

public interface ServicesPurchases {

    boolean add(Purchase purchase);

    boolean delete(Purchase purchase);

    boolean update(Purchase purchase);

    List<Purchase> getAll();

    Purchase get(int id);
}
