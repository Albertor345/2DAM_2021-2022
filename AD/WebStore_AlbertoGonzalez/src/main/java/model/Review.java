/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.*;
import services.impl.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Review {

    private int idReview;
    private int rating;
    private String title;
    private String description;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate date;
    private Purchase purchase;


    @Override
    public String toString() {
        return "No. " + idReview + "  Item: " + purchase.getItem().getName() + "  Rating: " + rating + "\nTitle: " + title + "\nComment: " + description + "\nDate: " + date + "\n____________________________________________________________\n";
    }

}
