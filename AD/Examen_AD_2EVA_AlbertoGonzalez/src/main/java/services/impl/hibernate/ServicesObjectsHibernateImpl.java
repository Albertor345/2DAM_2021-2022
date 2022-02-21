/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.hibernate;

import dao.DAO_objects;
import dao.impl.hibernate.DaoObjectsHibernateImpl;
import model.ObjetoEntity;
import services.ServicesObjects;

import java.util.List;

public class ServicesObjectsHibernateImpl implements ServicesObjects {


    private DAO_objects daoObjects;

    public ServicesObjectsHibernateImpl() {
        this.daoObjects = new DaoObjectsHibernateImpl();
    }

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public List<ObjetoEntity> getAll() {
       return daoObjects.getAll();
    }

    @Override
    public void get() {
        daoObjects.get();
    }
}
