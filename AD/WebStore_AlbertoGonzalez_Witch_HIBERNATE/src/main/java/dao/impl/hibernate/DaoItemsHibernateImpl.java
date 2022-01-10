package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOItems;
import lombok.extern.log4j.Log4j2;
import model.Item;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Log4j2
public class DaoItemsHibernateImpl implements DAOItems {

    private HibernateConfig hibernateConfig;

    @Inject
    public DaoItemsHibernateImpl(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    @Override
    public boolean update(Item item) {
        /*Constantes.UPDATE_ITEM_QUERY*/
        return false;
    }

    @Override
    public boolean add(Item item) {

        /*Constantes.INSERT_ITEM_QUERY*/
        return false;
    }

    @Override
    public boolean delete(Item item) {
        /*Constantes.DELETE_ITEM_QUERY*/
        return true;
    }

    @Override
    public boolean deleteAllPurchasesFromAnItem(Item item) {
        /*Constantes.DELETE_SALES_FROM_ITEM
        Constantes.DELETE_ITEM_QUERY*/
        return true;
    }

    @Override
    public Item get(Item item) {
        /*Constantes.SELECT_ITEM_QUERY*/
        return item;
    }

    @Override
    public List<Item> getAll() {
        /*Constantes.SELECT_ALL_ITEMS_QUERY*/
        return hibernateConfig.getSession().createNamedQuery("getAllItems", Item.class).getResultList();
    }
}
