package services;

import model.Item;
import model.Purchase;
import model.Review;

import java.util.List;

public interface ServicesItems {
    boolean add(Item item);

    boolean delete(Item item);

    boolean deleteAllPurchasesFromAnItem(Item item);

    boolean update(Item item);

    List<Item> getAll();

    Item get(Item item);

    String getItemData(Item item);

    boolean checkIfItemHasReviews(Item item);

    boolean checkIfItemHasPurchases(Item item);

    boolean addPurchase(Purchase purchase);

    boolean addReview(Review review, Item item);

    boolean deletePurchase(Purchase purchase);

    boolean deleteReview(Review review);
}
