package dao.impl.files;

import configuration.Config;
import dao.DAOItems;
import dao.DAOPurchases;
import model.Customer;
import model.Item;
import model.Purchase;
import producers.annotations.FILES;

import java.io.*;
import java.nio.file.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
@FILES
public class DaoPurchasesFilesImpl implements DAOPurchases {

    @Override
    public boolean add(Purchase purchase) {
        Path file = Paths.get(Config.getProperties().getProperty("purchases"));

        try (BufferedWriter bw = Files.newBufferedWriter(file, new OpenOption[]{StandardOpenOption.APPEND, StandardOpenOption.WRITE})) {
            String content = purchase.getId() + " " + purchase.getCustomer().getId() + " " + purchase.getCustomer().getName() + " " + purchase.getItem().getId() + " " + purchase.getItem().getName() + " " + purchase.getDate() + "\n";
            bw.write(content);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Purchase purchase) {
    return true;
    }

    @Override
    public boolean delete(Purchase purchase) {
        List<Purchase> purchaseList = getAll();
        Path file = Paths.get(Config.getProperties().getProperty("purchases"));
        try (BufferedWriter bw = Files.newBufferedWriter(file, new OpenOption[]{StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING})) {
            bw.write("");
            purchaseList.remove(purchase);
            purchaseList.forEach(purchase1 -> add(purchase1));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Purchase get(Purchase purchase) {
        return null;
    }

    @Override
    public List<Purchase> getAll() {
        List<Purchase> purchases = new ArrayList<>();
        File file = new File(Config.getProperties().getProperty("purchases"));
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;
            List<String> lista;
            while ((st = br.readLine()) != null) {
                lista = Arrays.stream(st.split(" ")).collect(Collectors.toList());
                purchases.add(Purchase.builder()
                        .id(Integer.parseInt(lista.get(0)))
                        .customer(Customer.builder()
                                .id(Integer.parseInt(lista.get(1)))
                                .name(lista.get(2))
                                .build())
                        .item(Item.builder()
                                .id(Integer.parseInt(lista.get(3)))
                                .name(lista.get(4))
                                .build())
                        .date(Date.valueOf(lista.get(5)).toLocalDate())
                        .build());
            }
            return purchases;
        } catch (IOException ex) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
}
