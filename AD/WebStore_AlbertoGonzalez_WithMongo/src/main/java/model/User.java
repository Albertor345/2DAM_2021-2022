package model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "login",
                query = "from User where name = :name and password = :password"
        )
})

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String dni;
    private String name;
    private String password;
}
