/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.*;
import org.bson.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Purchase {
    private String _id;
    private String customerID;
    private String itemID;
    private String itemName;
    private LocalDate date;
    private Review review;

    public static Document purchaseToDocument(Purchase purchase) {
        Document d = new Document()
                .append("id_purchase", purchase.get_id())
                .append("dni_customer", purchase.getCustomerID())
                .append("id_item", purchase.getItemID())
                .append("name_item", purchase.getItemName())
                .append("date", purchase.getDate());
        if (purchase.getReview() != null) {
            d.append("review", Review.reviewToDocument(purchase.getReview()));
        }
        return d;
    }

    public static Purchase documentToPurchase(Document d) {
        Purchase p = Purchase.builder()
                ._id(d.getString("id_purchase"))
                .customerID(d.getString("dni_customer"))
                .itemID(d.getString("id_item"))
                .itemName(d.getString("name_item"))
                .date(Instant.ofEpochMilli(d.getDate("date").getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate())
                .build();

        if (d.containsKey("review")) {
            p.setReview(d.get("review", Document.class) != null ? Review.documentToReview(d.get("review", Document.class)) : null);
        }
        return p;
    }

    @Override
    public String toString() {
        return "Customer with DNI: " + customerID + " has bought: " + itemName + " in: " + date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(_id, purchase._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }
}
