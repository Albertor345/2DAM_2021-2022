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

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.LocalDate;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "getAllSales",
                query = "from Sale "
        ),
        @NamedQuery(
                name = "getAllSalesOrderByItem",
                query = "from Sale order by item.name"
        ),
        @NamedQuery(
                name = "getAllSalesOrderByCustomer",
                query = "from Sale order by customer.name asc "
        ),
        @NamedQuery(
                name = "getAllSalesOrderByDate",
                query = "from Sale where date between :initDate and :finalDate order by date asc"
        ),
        @NamedQuery(
                name = "deleteAllSalesFromAnItem",
                query = "delete from Sale where item.id = :id"
        )
})

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Purchase {
    private int id;
    private Customer customer;
    private String item;
    private LocalDate date;
    private List<Review> reviews;

    public Purchase() {}

    @Override
    public String toString() {
        return "Purchase ID: " + id + " Customer[" + customer.getName() + "]" + item + " Date: " + date + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


}
