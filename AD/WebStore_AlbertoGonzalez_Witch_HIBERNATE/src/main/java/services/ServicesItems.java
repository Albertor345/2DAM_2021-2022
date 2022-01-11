package services;

import model.Item;

import java.util.List;

public interface ServicesItems {
    boolean add(Item item);

    boolean delete(Item item);

    public boolean deleteAllPurchasesFromAnItem(Item item);

    boolean update(Item item);

    List<Item> getAll();

    Item get(Item item);

    String getItemData(Item item);

    boolean checkItemPurchases(Item item);
}
