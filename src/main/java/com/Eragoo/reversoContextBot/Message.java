package com.Eragoo.reversoContextBot;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

@Builder
@Getter
public class Message {
    private ObjectId id;
    private Long telegramId;
    private String text;

    public DBObject toMongoObject() {
        if (id == null) {
            id = ObjectId.get();
        }
        return  new BasicDBObject("_id", id)
                .append("telegramId", telegramId)
                .append("text", text);
    }
}
