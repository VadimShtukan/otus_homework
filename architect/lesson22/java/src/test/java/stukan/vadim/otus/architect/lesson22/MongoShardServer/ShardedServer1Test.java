package stukan.vadim.otus.architect.lesson22.MongoShardServer;

import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import stukan.vadim.otus.architect.lesson22.domaim.Cart;

import java.math.BigDecimal;

class ShardedServer1Test {

    @Test
    void getOperations() {
        ShardedServer shardedServer1 = new ShardedServer1();

        MongoOperations mongoOperations = shardedServer1.getOperations();

        Cart cart = new Cart();
        cart.setTotal(BigDecimal.valueOf(1000));

        mongoOperations.insert(cart);
    }
}