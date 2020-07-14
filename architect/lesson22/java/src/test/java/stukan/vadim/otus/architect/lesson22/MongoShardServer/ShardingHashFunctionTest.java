package stukan.vadim.otus.architect.lesson22.MongoShardServer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShardingHashFunctionTest {

    @Autowired
    ShardingHashFunction shardingHashFunction;

    @Test
    void getShardedServer() {
//        UUID id = UUID.fromString("c365b484-792a-4bec-a515-53fe6f48eab2");//18
        UUID id = UUID.randomUUID();

        ShardedServer shardedServer = shardingHashFunction.getShardedServer(id);

        String sharedServerString = shardedServer.toString();

        System.out.printf("UUID: " + id + " toString:" + sharedServerString);
    }
}