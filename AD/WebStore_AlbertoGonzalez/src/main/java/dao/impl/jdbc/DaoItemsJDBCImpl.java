package dao.impl.jdbc;

import dao.DAOItems;
import model.Item;

import java.util.List;

public class DaoItemsJDBCImpl implements DAOItems {
    @Override
    public void update(Item item) {

    }

    @Override
    public boolean add(Item item) {
        return false;
    }

    @Override
    public boolean delete(Item item) {
        return false;
    }

    @Override
    public Item get(Item item) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }
}
