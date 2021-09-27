/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Item {
    private int idItem;
    private String name;
    private String company;
    private double price;

    public Item() {

    }

    @Override
    public String toString() {
        return "ID: " + idItem + "  Name: " + name + "  Company: " + company + " Price: " + price;
    }

}
