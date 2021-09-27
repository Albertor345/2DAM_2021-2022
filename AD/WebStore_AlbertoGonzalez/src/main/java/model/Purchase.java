/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Laura
 */
public class Purchase {

    private int idPurchase;
    private int idCustomer;
    private String item;
    private String date;

    public Purchase() {
    }

    public Purchase(int idPurchase, int idCustomer, String item, String date) {
        this.idPurchase = idPurchase;
        this.idCustomer = idCustomer;
        this.item = item;
        this.date = date;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ID: " + idPurchase + "  Customer: " + idCustomer + "  Item: " + item + "  Date: " + date;
    }
    
    public String toStringForClientInfo() {
        return "ID: " + idPurchase + "  Item: " + item + "  Date: " + date + "\n";
    }

    public String toStringTexto() {
        return idPurchase + ";" + idCustomer + ";" + item + ";" + date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idPurchase;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Purchase other = (Purchase) obj;
        if (this.idPurchase != other.idPurchase) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
