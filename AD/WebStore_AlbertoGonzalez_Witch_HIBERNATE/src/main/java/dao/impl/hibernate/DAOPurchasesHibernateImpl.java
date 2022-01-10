package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOPurchases;
import lombok.extern.log4j.Log4j2;
import model.Sale;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Log4j2
public class DAOPurchasesHibernateImpl implements DAOPurchases {
    private final HibernateConfig hibernateConfig;

    @Inject
    public DAOPurchasesHibernateImpl(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    @Override
    public Sale get(Sale sale) {
        /*Constantes.SELECT_PURCHASE_QUERY*/
        return sale;
    }

    @Override
    public List<Sale> getAll() {
        /*Constantes.SELECT_ALL_PURCHASES_QUERY*/
        return Collections.emptyList();
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
