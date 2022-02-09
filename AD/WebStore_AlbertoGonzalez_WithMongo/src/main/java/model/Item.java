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

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(
                name = "getAllItems",
                query = "from Item"
        ),
        @NamedQuery(
                name = "getItem",
                query = "from Item where id = :id"
        ),
        @NamedQuery(
                name = "select_Price_AvgRating_NSales_FromItem_LastMonth",
                query = "select item.price, count(sales.id) - 1, avg(reviews.rating) " +
                        "from Item item " +
                        "inner join item.sales sales " +
                        "inner join sales.reviews reviews " +
                        "where item.id = :id and month(sales.date) like month(now())"
        )
})

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Item {
    private int id;
    private String name;
    private String company;
    private double price;
    private List<Purchase> purchases;

    public Item() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + " " + company + " " + price;
    }

}
