/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.ObjetoEntity;
import org.bson.Document;

import java.util.List;

/**
 *
 */
public interface DAO_objects {

    void get();

    List<ObjetoEntity> getAll();

    boolean add(Document d);

    boolean update();

    boolean delete();

    void login();
}
