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
@Entity
@Table(name = "users", schema = "alberto_WebStore", catalog = "")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    @OneToOne
    @PrimaryKeyJoinColumn
    @NotFound(action = NotFoundAction.IGNORE)
    private Customer customer;
}
