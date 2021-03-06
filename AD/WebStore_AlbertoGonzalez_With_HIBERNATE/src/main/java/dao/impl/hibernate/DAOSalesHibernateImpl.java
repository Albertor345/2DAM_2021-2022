package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOSales;
import lombok.extern.log4j.Log4j2;
import model.Sale;
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
    public Sale get(Sale sale) {
        /*Constantes.SELECT_PURCHASE_QUERY*/
        return sale;
    }

    @Override
    public List<Sale> getAll() {
        List<Sale> sales = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            sales = session.createNamedQuery("getAllSales", Sale.class).getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return sales;
    }

    @Override
    public List<Sale> getAllOrderedBy(boolean order) {
        List<Sale> sales = new ArrayList<>();
        String orderBy = order ? "Item" : "Customer";
        String query = "getAllSalesOrderBy" + orderBy;
        try (Session session = hibernateConfig.getSession()) {
            sales = session.createNamedQuery(query, Sale.class)
                    .getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return sales;
    }

    @Override
    public List<Sale> getAllOrderedByDate(LocalDate initialDate, LocalDate finalDate) {
        List<Sale> sales = new ArrayList<>();
        try (Session session = hibernateConfig.getSession()) {
            sales = session.createNamedQuery("getAllSalesOrderByDate", Sale.class)
                    .setParameter("initDate", initialDate)
                    .setParameter("finalDate", finalDate)
                    .getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return sales;
    }

    @Override
    public boolean add(Sale sale) {
        try (Session session = hibernateConfig.getSession()) {
            session.beginTransaction();
            session.save(sale);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean update(Sale sale) {
        /*Constantes.UPDATE_PURCHASE_QUERY*/
        return false;

    }

    @Override
    public boolean delete(Sale sale) {
        /*Constantes.DELETE_PURCHASE_QUERY*/
        return false;

    }
}
