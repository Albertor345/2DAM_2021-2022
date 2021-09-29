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

import java.time.LocalDate;

/**
 *
 * @author dam2
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Review {

    private int idReview;
    private int rating;
    private String title;
    private String description;
    private LocalDate date;
    private int customerId;
    private int itemId;
    private int purchaseId;


    @Override
    public String toString() {
        return "No. " + idReview + "  Item: " + itemId + "  Rating: " + rating + "\nTitle: " + title + "\nComment: " + description + "\nDate: " + date + "\n____________________________________________________________\n";
    }

}
