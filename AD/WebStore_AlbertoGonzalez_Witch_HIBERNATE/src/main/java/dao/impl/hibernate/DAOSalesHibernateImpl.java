package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOSales;
import lombok.extern.log4j.Log4j2;
import model.Sale;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
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
        List<Sale> reviews = new ArrayList<>();
        String orderBy = order ? "Item" : "Customer";
        String query = "getAllSalesOrderBy" + orderBy;
        try (Session session = hibernateConfig.getSession()) {
            reviews = session.createNamedQuery(query, Sale.class)
                    .getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return reviews;
    }

    @Override
    public List<Sale> getAllOrderedByDate(Date initialDate, Date finalDate) {
        return null;
    }

    @Override
    public boolean add(Sale sale) {
        /*Constantes.INSERT_PURCHASE_QUERY*/
        return false;
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
