/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.PermisoEntity;
import model.PermisosObjetosEntity;

import java.util.List;

/**
 *
 */
public interface DAOPermisos {

    PermisoEntity getPermiso(PermisoEntity p);

    List<PermisoEntity> getAll();

    PermisosObjetosEntity add(PermisosObjetosEntity p);

    boolean update();

    boolean delete();
}
