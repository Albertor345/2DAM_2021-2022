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

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reviews")
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
    @PrimaryKeyJoinColumn
    private Sale sale;
    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Customer customer;


    @Override
    public String toString() {
        return "No. " + id + "  Item: " + sale.getItem().getName() + "  Rating: " + rating + "\nTitle: " + title + "\nComment: " + review + "\nDate: " + date + "\n____________________________________________________________\n";
    }

}
