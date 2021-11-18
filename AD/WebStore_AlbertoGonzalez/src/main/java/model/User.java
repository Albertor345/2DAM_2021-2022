package model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private int id;
    private String name;
    private String password;
}
