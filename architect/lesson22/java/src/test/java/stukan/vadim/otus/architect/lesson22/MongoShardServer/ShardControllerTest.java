package stukan.vadim.otus.architect.lesson22.MongoShardServer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShardControllerTest {

    @Autowired
    ShardController shardController;

    @Test
    void getMongoOperations() {
        UUID id = UUID.randomUUID();

        MongoOperations mongoOperations = shardController.getMongoOperations(id);

        assertNotNull(mongoOperations);
    }
}