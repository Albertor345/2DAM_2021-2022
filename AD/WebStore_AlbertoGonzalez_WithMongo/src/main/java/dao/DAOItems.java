/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Item;
import model.Purchase;
import model.Review;

import java.util.List;

public interface DAOItems {

    boolean update(Item item);

    boolean add(Item item);

    boolean delete(Item item);

    boolean deleteAllPurchasesFromAnItem(Item item);

    Item get(Item item);

    List<Item> getAll();

    String getItemData(Item item);

    boolean checkIfItemHasReviews(Item item);

    boolean checkIfItemHasPurchases(Item item);

    boolean addPurchase(Purchase purchase);

    boolean addReview(Review review, Item item);

    boolean deletePurchase(Purchase purchase);

    boolean deleteReview(Review review);
}

