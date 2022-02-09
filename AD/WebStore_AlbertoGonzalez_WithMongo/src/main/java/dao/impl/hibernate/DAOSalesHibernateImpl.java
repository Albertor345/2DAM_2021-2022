package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOSales;
import lombok.extern.log4j.Log4j2;
import model.Purchase;
import org.hibernate.Session;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOSalesHibernateImpl implements DAOSales {
    private final HibernateConfig hibernateConfig;

    @Inject
    public DAOSalesHibernateImpl(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    @Override
    public Purchase get(Purchase purchase) {
        /*Constantes.SELECT_PURCHASE_QUERY*/
        return purchase;
    }

    @Override
    public List<Purchase> getAll() {
        List<Purchase> purchases = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            purchases = session.createNamedQuery("getAllSales", Purchase.class).getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return purchases;
    }

    @Override
    public List<Purchase> getAllOrderedBy(boolean order) {
        List<Purchase> purchases = new ArrayList<>();
        String orderBy = order ? "Item" : "Customer";
        String query = "getAllSalesOrderBy" + orderBy;
        try (Session session = hibernateConfig.getSession()) {
            purchases = session.createNamedQuery(query, Purchase.class)
                    .getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return purchases;
    }

    @Override
    public List<Purchase> getAllOrderedByDate(LocalDate initialDate, LocalDate finalDate) {
        List<Purchase> purchases = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            purchases = session.createNamedQuery("getAllSalesOrderByDate", Purchase.class)
                    .setParameter("initDate", initialDate)
                    .setParameter("finalDate", finalDate)
                    .getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return purchases;
    }

    @Override
    public boolean add(Purchase purchase) {
        try (Session session = hibernateConfig.getSession()) {
            session.beginTransaction();
            session.save(purchase);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean update(Purchase purchase) {
        /*Constantes.UPDATE_PURCHASE_QUERY*/
        return false;

    }

    @Override
    public boolean delete(Purchase purchase) {
        /*Constantes.DELETE_PURCHASE_QUERY*/
        return false;

    }
}
