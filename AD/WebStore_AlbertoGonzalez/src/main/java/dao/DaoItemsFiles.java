package dao;

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
        File file = new File("Data/items.txt");

        try (FileWriter writer = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            String content = "\n" + item.getIdItem() + " " + item.getName() + " " + item.getCompany() + " " + item.getPrice();
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
    public void delete(Item item) {

    }

    @Override
    public Item get(Item item) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        File file = new File("Data/items.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            List<String> lista;
            while ((st = br.readLine()) != null) {
                lista = Arrays.stream(st.split(" ")).collect(Collectors.toList());
                Item item = new Item();
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        item.setIdItem(Integer.parseInt(lista.get(0)));
                    } else if (i == 1) {
                        item.setName(lista.get(1));
                    } else if (i == 2) {
                        item.setCompany(lista.get(2));
                    } else {
                        item.setPrice(Double.parseDouble(lista.get(3)));
                        items.add(item);
                    }
                }
            }
            return items;
        } catch (IOException ex) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
}
