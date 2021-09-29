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

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author dam2
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Customer {

    private int idCustomer;
    private String name;
    private String phone;
    private String address;
    private ArrayList<Review> reviews;

   /* public String toStringReviews() {
        ArrayList<String> rev = new ArrayList();

        if(reviews != null){
            for (int i = 0; i < reviews.size(); i++) {
                rev.add(reviews.get(i).toStringVisual());
            }
        }


        return "ID: " + idCustomer + "  Name: " + name
                + "\nPhone: " + phone + "  Address: " + address
                + "\n\n======       Reviews done by this client:      ======\n\n" + rev;
    }*/

    @Override
    public String toString() {
        return "ID: " + idCustomer + "  Name: " + name + "  Phone: " + phone + "  Address: " + address;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Customer other = (Customer) obj;
        if (this.idCustomer != other.idCustomer) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    

}
