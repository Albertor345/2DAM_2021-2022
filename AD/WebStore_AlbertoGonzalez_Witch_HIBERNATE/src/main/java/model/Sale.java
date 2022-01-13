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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@NamedQueries({
        @NamedQuery(
                name = "getAllSales",
                query = "from Sale "
        ),
        @NamedQuery(
                name = "getAllSalesOrderByItem",
                query = "from Sale order by item.id"
        ),
        @NamedQuery(
                name = "getAllSalesOrderByCustomer",
                query = "from Sale order by customer.id asc "
        ),
        @NamedQuery(
                name = "getAllSalesOrderByDate",
                query = "from Sale where date >= :date order by date asc"
        )
})

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sales", schema = "alberto_WebStore", catalog = "")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_item", referencedColumnName = "id")
    private Item item;

    private LocalDate date;

    @OneToMany(mappedBy = "sale")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Review> reviews;

    public Sale() {
    }

    @Override
    public String toString() {
        return "Purchase ID: " + id + " Customer[ ID: " + customer.getId() + " Name: " + customer.getName() + "] Item[ ID: " + item.getId() + " Name: " + item.getName() + "] Date: " + date + "\n";
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
        final Sale other = (Sale) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


}
