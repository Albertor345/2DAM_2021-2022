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
import org.bson.Document;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private String _id;
    private String name;
    private Address address;
    private List<Purchase> purchases;

    public static Document customerToDocument(Customer c) {
        Document d = new Document().append("_id", c.get_id())
                .append("name", c.getName())
                .append("contact_info", new Document()
                        .append("address", c.getAddress().getAddress())
                        .append("phone", c.getAddress().getPhone()));
        if (c.getPurchases() != null) {
            d.append("purchases", c.getPurchases().stream()
                    .map(Purchase::purchaseToDocument));
        }
        return d;
    }

    public static Customer documentToCustomer(Document d) {
        Customer c = Customer.builder()
                ._id(d.getString("_id"))
                .name(d.getString("name"))
                .address(Address.builder()
                        .phone(d.get("contact_info", Document.class).getString("phone"))
                        .address(d.get("contact_info", Document.class).getString("address"))
                        .build())
                .build();

        if (d.containsKey("purchases")) {
            c.setPurchases(d.getList("purchases", Document.class).stream().map(
                            Purchase::documentToPurchase)
                    .collect(Collectors.toList()));

        }
        return c;
    }

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
        if (this._id != other._id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }


}
