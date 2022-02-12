/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.mongo;

import dao.DAOItems;
import model.Item;
import model.Purchase;
import model.Review;
import services.ServicesItems;

import javax.inject.Inject;
import java.util.List;

public class ServicesItemsMongoImpl implements ServicesItems {

    private DAOItems daoItems;

    @Inject
    public ServicesItemsMongoImpl(DAOItems daoItems) {
        this.daoItems = daoItems;
    }

    public boolean add(Item item) {
        return daoItems.add(item);
    }

    public boolean delete(Item item) {
        return daoItems.delete(item);
    }

    @Override
    public boolean deleteAllPurchasesFromAnItem(Item item) {
        return daoItems.deleteAllPurchasesFromAnItem(item);
    }

    @Override
    public boolean update(Item item) {
        return daoItems.update(item);
    }

    @Override
    public Item get(Item item) {
        return daoItems.get(item);
    }

    @Override
    public List<Item> getAll() {
        return daoItems.getAll();
    }

    @Override
    public String getItemData(Item item) {
        return daoItems.getItemData(item);
    }

    @Override
    public boolean checkIfItemHasReviews(Item item) {
        return daoItems.checkIfItemHasReviews(item);
    }

    @Override
    public boolean checkIfItemHasPurchases(Item item) {
        return daoItems.checkIfItemHasPurchases(item);
    }

    @Override
    public boolean addPurchase(Purchase purchase) {
        return daoItems.addPurchase(purchase);
    }

    @Override
    public boolean addReview(Review review, Item item) {
        return daoItems.addReview(review, item);
    }

    @Override
    public boolean deletePurchase(Purchase purchase) {
        return daoItems.deletePurchase(purchase);
    }

    @Override
    public boolean deleteReview(Review review) {
        return daoItems.deleteReview(review);
    }

}
