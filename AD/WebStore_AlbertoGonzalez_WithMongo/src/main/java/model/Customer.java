/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(
                name = "getAllCustomers",
                query = "from Customer"
        ),
        @NamedQuery(
                name = "getCustomer",
                query = "from Customer c where c.id = :id"
        )
})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private String dni;
    private String name;
    private Address address;
    private List<Purchase> purchases;

    @Override
    public String toString() {
        return name + " " + address;
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
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }


}
