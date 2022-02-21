/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl.mongo;

import dao.DAO_objects;
import dao.impl.mongo.DaoLocationsMongoImpl;
import model.ObjetoEntity;
import model.PermisosUbicacionesEntity;
import model.UbicacionEntity;
import services.ServicesLocations;
import services.ServicesObjects;

import java.util.List;

public class ServicesLocationsMongoImpl implements ServicesLocations {

    private DAO_objects daoObjects;

    public ServicesLocationsMongoImpl() {
        this.daoObjects = new DaoLocationsMongoImpl();
    }


    @Override
    public UbicacionEntity add(UbicacionEntity u) {
        return null;
    }

    @Override
    public boolean delete(UbicacionEntity u) {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public List<UbicacionEntity> getAll() {
        return null;
    }

    @Override
    public UbicacionEntity get() {
        return null;
    }

    @Override
    public List<PermisosUbicacionesEntity> getAllPermisosLocations() {
        return null;
    }
}
