package dao;

import configuration.ConfigProperties;
import model.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DaoItemsFiles implements DAOItems {

    @Override
    public boolean add(Item item) {
        File file = new File(ConfigProperties.getInstance().getProperty("items"));

        try (FileWriter writer = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            String content = item.getIdItem() + " " + item.getName() + " " + item.getCompany() + " " + item.getPrice() + "\n";
            bw.write(content);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void update(Item item) {

    }

    @Override
    public boolean delete(Item item) {
        List<Item> listItems = getAll();
        return listItems.remove(item);
    }

    @Override
    public Item get(Item item) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        File file = new File(ConfigProperties.getInstance().getProperty("items"));
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String st;
            List<String> lista;
            while ((st = br.readLine()) != null) {
                lista = Arrays.stream(st.split(" ")).collect(Collectors.toList());
                items.add(Item.builder()
                        .idItem(Integer.parseInt(lista.get(0)))
                        .name(lista.get(1))
                        .company(lista.get(2))
                        .price(Double.parseDouble(lista.get(3)))
                        .build());
            }
            return items;
        } catch (IOException ex) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
}
