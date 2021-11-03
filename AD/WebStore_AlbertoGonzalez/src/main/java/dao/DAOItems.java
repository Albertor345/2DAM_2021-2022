/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import configuration.Config;
import model.Item;
import model.Purchase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public interface DAOItems {

    boolean update(Item item);

    boolean add(Item item);

    boolean delete(Item item);

    boolean deleteAllPurchasesFromAnItem(Item item);

    Item get(Item item);

    List<Item> getAll();
}

