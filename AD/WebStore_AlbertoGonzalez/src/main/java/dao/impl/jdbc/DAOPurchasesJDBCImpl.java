package dao.impl.jdbc;

import dao.DAOPurchases;
import model.Item;
import model.Purchase;
import producers.annotations.JDBC;

import javax.inject.Named;
import java.util.List;
@JDBC
public class DAOPurchasesJDBCImpl implements DAOPurchases {
    @Override
    public Purchase get(int id) {
        return null;
    }

    @Override
    public List<Purchase> getAll() {
        return null;
    }

    @Override
    public boolean add(Purchase t) {
        return false;
    }

    @Override
    public void update(Purchase t) {

    }

    @Override
    public boolean delete(Purchase t) {
        return false;
    }

    @Override
    public boolean deleteAllPurchasesFromAnItem(Item item) {
        return false;
    }
}
