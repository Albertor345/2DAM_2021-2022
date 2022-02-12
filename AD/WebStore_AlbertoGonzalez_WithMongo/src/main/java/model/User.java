package model;

import lombok.*;
import org.bson.Document;



@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String _id;
    private String name;
    private String password;
    private String user_type;

    public static Document userToDocument(User user) {
        return new Document()
                .append("_id", user.get_id())
                .append("name", user.getName())
                .append("password", user.getPassword())
                .append("user_type", user.getUser_type());
    }

    public static User documentToUser(Document d) {
        return User.builder()
                ._id(d.getString("_id"))
                .name(d.getString("name"))
                .password(d.getString("password"))
                .user_type(d.getString("user_type"))
                .build();
    }


}
