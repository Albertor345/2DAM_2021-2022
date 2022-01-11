package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOItems;
import lombok.extern.log4j.Log4j2;
import model.Item;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
        List<Item> items = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            items = session.createNamedQuery("getAllItems", Item.class).getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return items;
    }

    @Override
    public String getItemData(Item item) {
        AtomicReference<String> result = new AtomicReference<>("");
        List<Object[]> list;
        try (Session session = hibernateConfig.getSession()) {
            list = session.createNamedQuery("select_Price_AvgRating_NSales_FromItem_LastMonth")
                    .setParameter("id", item.getId())
                    .getResultList();
            list.forEach(o -> result.set("{Price: " + o[0] + "} -- {Number of Purchases: " + o[1] + "} -- {Average rating " + o[2] + "}"));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return result.get();
    }
}
