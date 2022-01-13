package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOItems;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Item;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.ArrayList;
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
        try (Session session = hibernateConfig.getSession()) {
            session.beginTransaction();
            Item itemFromDatabase = get(item);
            itemFromDatabase.setCompany(item.getCompany());
            itemFromDatabase.setName(item.getName());
            itemFromDatabase.setPrice(item.getPrice());
            session.update(itemFromDatabase);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean add(Item item) {
        try (Session session = hibernateConfig.getSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean delete(Item item) {
        /*Constantes.DELETE_ITEM_QUERY*/
        return true;
    }

    @Override
    public boolean deleteAllPurchasesFromAnItem(Item item) {
        try (Session session = hibernateConfig.getSession()) {
            session.beginTransaction();

            item = session.createNamedQuery("getItem", Item.class).setParameter("id", item.getId()).getSingleResult();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return item;
    }

    @Override
    public Item get(Item item) {
        try (Session session = hibernateConfig.getSession()) {
            item = session.createNamedQuery("getItem", Item.class).setParameter("id", item.getId()).getSingleResult();
        } catch (NoResultException ex) {
            item = null;
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            items = session.createNamedQuery("getAllItems", Item.class).getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return items;
    }

    @Override
    public List<Object[]> getItemData(Item item) {
        List<Object[]> list = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            list = session.createNamedQuery("select_Price_AvgRating_NSales_FromItem_LastMonth")
                    .setParameter("id", item.getId())
                    .getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return list;
    }
}
