/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.*;
import services.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Review {

    private int id;
    private int rating;
    private String title;
    private String review;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate date;
    private Purchase purchase;


    @Override
    public String toString() {
        return "No. " + id + "  Item: " + purchase.getItem().getName() + "  Rating: " + rating + "\nTitle: " + title + "\nComment: " + review + "\nDate: " + date + "\n____________________________________________________________\n";
    }

}
