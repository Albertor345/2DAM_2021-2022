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
import java.time.LocalDate;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(
                name = "getAllReviews",
                query = "from Review"

        ),
        @NamedQuery(
                name = "getAllReviewsByItem",
                query = "from Review where sale.item.id = :id"

        ),
        @NamedQuery(
                name = "getAllReviewsByCustomer",
                query = "from Review where customer.id = :id"

        ),
        @NamedQuery(
                name = "orderReviewsAscRating",
                query = "from Review where sale.item.id = :id order by rating asc"
        ),
        @NamedQuery(
                name = "orderReviewsAscDate",
                query = "from Review where sale.item.id = :id order by date asc"
        ),
        @NamedQuery(
                name = "orderReviewsDescRating",
                query = "from Review where sale.item.id = :id order by rating desc"
        ),
        @NamedQuery(
                name = "orderReviewsDescDate",
                query = "from Review where sale.item.id = :id order by date desc"
        )
})

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int id;
    private int rating;
    private String title;
    private String review;
    private LocalDate date;

    private Purchase purchase;

    private Customer customer;


    @Override
    public String toString() {
        return "No. " + id + "  Item: " + purchase.getItem().getName() + "  Rating: " + rating + "\nTitle: " + title + "\nComment: " + review + "\nDate: " + date + "\n____________________________________________________________\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
