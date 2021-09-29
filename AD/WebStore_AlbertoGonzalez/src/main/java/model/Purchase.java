/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Laura
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Purchase {

    private int idPurchase;
    private int idCustomer;
    private String item;
    private String date;

    public Purchase() {
    }


    @Override
    public String toString() {
        return "ID: " + idPurchase + "  Item: " + item + "  Date: " + date + "\n";
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
