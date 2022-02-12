/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import lombok.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private ObjectId _id;
    private String name;
    private String company;
    private double price;
    private List<Purchase> purchases;
    private List<Review> reviews;

    public static Document ItemToDocument(Item i) {
        if (i.get_id() == null) {
            i.set_id(new ObjectId());
        }
        Document d = new Document()
                .append("_id", i.get_id())
                .append("name", i.getName())
                .append("company", i.getCompany())
                .append("price", i.getPrice());
        if (i.getPurchases() != null) {
            d.append("purchases", i.getPurchases().stream().map(Purchase::purchaseToDocument)
                    .collect(Collectors.toList()));
        }
        if (i.getReviews() != null) {
            d.append("reviews", i.getReviews().stream().map(Review::reviewToDocument)
                    .collect(Collectors.toList()));
        }
        return d;


    }

    public static Item documentToItem(Document d) {
        Item item = Item.builder()
                ._id(d.getObjectId("_id"))
                .name(d.getString("name"))
                .company(d.getString("company"))
                .price(Double.parseDouble(d.get("price").toString()))
                .build();

        if (d.containsKey("purchases")) {
            item.setPurchases(d.getList("purchases", Document.class).stream().map(
                            Purchase::documentToPurchase)
                    .collect(Collectors.toList()));
        }
        if (d.containsKey("reviews")) {
            item.setReviews(d.getList("reviews", Document.class).stream().map(
                            Review::documentToReview)
                    .collect(Collectors.toList()));
        }
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return _id == item._id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }

    @Override
    public String toString() {
        return name + " " + company + " " + price;
    }

}
