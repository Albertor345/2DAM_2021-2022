package dao;

import configuration.ConfigProperties;
import model.Item;
import model.Purchase;

import java.io.*;
import java.nio.file.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DaoPurchasesFiles implements DAOPurchases {

    @Override
    public boolean add(Purchase purchase) {
        Path file = Paths.get(ConfigProperties.getInstance().getProperty("purchases"));

        try (BufferedWriter bw = Files.newBufferedWriter(file, new OpenOption[]{StandardOpenOption.APPEND, StandardOpenOption.WRITE})) {
            String content = purchase.getIdPurchase() + " " + purchase.getCustomerID() + " " + purchase.getCustomerName() + " " + purchase.getItemID() + " " + purchase.getItemName() + " " + purchase.getDate() + "\n";
            bw.write(content);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void update(Purchase purchase) {

    }

    @Override
    public boolean delete(Purchase purchase) {
        List<Purchase> purchaseList = getAll();
        Path file = Paths.get(ConfigProperties.getInstance().getProperty("purchases"));
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
    public boolean deleteAllPurchasesFromAnItem(Item item) {
        List<Purchase> purchaseList = getAll();
        Path file = Paths.get(ConfigProperties.getInstance().getProperty("purchases"));

        try (BufferedWriter bw = Files.newBufferedWriter(file, new OpenOption[]{StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING})) {
            bw.write("");
            purchaseList.stream()
                    .filter(purchase -> purchase.getItemID() != item.getIdItem())
                    .collect(Collectors.toList())
                    .forEach(p -> add(p));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DAOItems.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Purchase get(int id) {
        return null;
    }

    @Override
    public List<Purchase> getAll() {
        List<Purchase> purchases = new ArrayList<>();
        File file = new File(ConfigProperties.getInstance().getProperty("purchases"));
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;
            List<String> lista;
            while ((st = br.readLine()) != null) {
                lista = Arrays.stream(st.split(" ")).collect(Collectors.toList());
                purchases.add(Purchase.builder()
                        .idPurchase(Integer.parseInt(lista.get(0)))
                        .customerID(Integer.parseInt(lista.get(1)))
                        .customerName(lista.get(2))
                        .itemID(Integer.parseInt(lista.get(3)))
                        .itemName(lista.get(4))
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
