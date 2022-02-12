package model;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @BsonProperty
    private String phone;
    @BsonProperty
    private String address;
}
