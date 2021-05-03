package com.Eragoo.reversoContextBot;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.util.Map;
import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public class Message {
    private ObjectId _id;
    private String telegramId;
    private String text;
    private User user;

    public DBObject toMongoObject() {
        if (_id == null) {
            _id = ObjectId.get();
        }
        return  new BasicDBObject("_id", _id)
                .append("telegramId", telegramId)
                .append("text", text)
                .append("user", user);
    }

    public static Message valueOf(DBObject dbObject) {
        Map keyValue = dbObject.toMap();

        ObjectId id = (ObjectId) keyValue.getOrDefault("_id", null);
        String telegramId = (String) keyValue.getOrDefault("telegramId", null);
        String text = (String) keyValue.getOrDefault("text", null);
        User user = (User) keyValue.getOrDefault("user", null);

        return new Message(id, telegramId, text, user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return _id.equals(message._id) && Objects.equals(telegramId, message.telegramId) && Objects.equals(text, message.text) && Objects.equals(user, message.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, telegramId, text, user);
    }
}
