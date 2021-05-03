package com.Eragoo.reversoContextBot;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private ObjectId _id;
    private String name;
    private String surname;
    private String username;
    private String chatId;

    public User(ObjectId _id, String name, String surname, String username, String chatId) {
        if (_id == null) {
            this._id = _id;
        }
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return _id.equals(user._id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(username, user.username) && Objects.equals(chatId, user.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, name, surname, username, chatId);
    }
}
