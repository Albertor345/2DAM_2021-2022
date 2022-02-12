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
import org.bson.types.ObjectId;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private ObjectId _id;
    private int rate;
    private String title;
    private String review;
    private LocalDate date;
    private String purchaseID;
    private String customerDNI;

    public static Document reviewToDocument(Review r) {
        return new Document()
                .append("_id", r.get_id() != null ? r.get_id() : new ObjectId())
                .append("id_purchase", r.getPurchaseID())
                .append("dni_customer", r.getCustomerDNI())
                .append("title", r.getTitle())
                .append("description", r.getReview())
                .append("rate", r.getRate())
                .append("date", r.getDate());
    }

    public static Review documentToReview(Document d) {
        return Review.builder()
                ._id(d.getObjectId("_id"))
                .purchaseID(d.getString("id_purchase"))
                .customerDNI(d.getString("dni_customer"))
                .title(d.getString("title"))
                .review(d.getString("description"))
                .date(Instant.ofEpochMilli(d.getDate("date").getTime())
                        .atZone(ZoneId.systemDefault()).toLocalDate())
                .rate(Integer.parseInt(d.get("rate").toString()))
                .build();
    }

    @Override
    public String toString() {
        return "No. " + _id + " Rating: " + rate + "\nTitle: " + title + "\nComment: " + review + "\nDate: " + date + "\n____________________________________________________________\n";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return _id == review._id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }
}
