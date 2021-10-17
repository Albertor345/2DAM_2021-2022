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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@Builder
@XmlRootElement
public class Customer {

    @XmlAttribute
    private int idCustomer;
    @XmlElement
    private String name;
    @XmlElement
    private String phone;
    @XmlElement
    private String address;
    @XmlElement(name = "review")
    private ArrayList<Review> reviews;

    @Override
    public String toString() {
        return "ID: " + idCustomer + "  Name: " + name + "  Phone: " + phone + "  Address: " + address;
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
        if (this.idCustomer != other.idCustomer) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }


}
