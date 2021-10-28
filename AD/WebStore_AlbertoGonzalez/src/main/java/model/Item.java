/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Item {
    private int id;
    private String name;
    private String company;
    private double price;

    public Item() {

    }

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
        return "ID: " + id + "  Name: " + name + "  Company: " + company + " Price: " + price;
    }

}
