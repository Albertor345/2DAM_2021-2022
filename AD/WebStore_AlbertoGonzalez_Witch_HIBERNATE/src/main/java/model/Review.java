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

@NamedQueries({
        @NamedQuery(
                name = "getAllReviewsByItem",
                query = "from Review where sale.item.id = :id"
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
@Entity
@Table(name = "reviews", schema = "alberto_WebStore", catalog = "")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rating;
    private String title;
    private String review;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sale", referencedColumnName = "id")
    private Sale sale;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer customer;


    @Override
    public String toString() {
        return "No. " + id + "  Item: " + sale.getItem().getName() + "  Rating: " + rating + "\nTitle: " + title + "\nComment: " + review + "\nDate: " + date + "\n____________________________________________________________\n";
    }

}
