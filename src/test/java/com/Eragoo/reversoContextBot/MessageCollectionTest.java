package com.Eragoo.reversoContextBot;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.util.Optional;

class MessageCollectionTest {
    private static MessageCollection messageCollection;

    @SneakyThrows
    @BeforeAll
    static void init() {
        messageCollection = new MessageCollection("bot", "testMessage");
    }

    @AfterEach
    void cleanUp() {
        messageCollection.deleteAll();
    }

    @Test
    void save() {
        Message excepted = Message.builder()
                .text("Test")
                .build();
        messageCollection.save(excepted);
        Optional<Message> actual = messageCollection.findById(excepted.get_id());

        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(excepted, actual.get());
    }

    @Test
    void saveWithEmbedded() {
        Message excepted = Message.builder()
                .text("Test")
                .user(new User(null, "Test", "Test", "Test", "Test"))
                .build();
        messageCollection.save(excepted);
        Optional<Message> actual = messageCollection.findById(excepted.get_id());

        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(excepted, actual.get());
    }

    @Test
    void delete() {
    }
}