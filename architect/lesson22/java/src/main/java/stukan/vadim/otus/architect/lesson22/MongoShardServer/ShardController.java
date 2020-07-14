package stukan.vadim.otus.architect.lesson22.MongoShardServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.util.UUID;

@Controller
public class ShardController {

    @Autowired
    private ShardingHashFunction shardingHashFunction;

//    public ShardController() {
//        this.shardingHashFunction = new ShardingHashFunction();
//    }

    public MongoOperations getMongoOperations(UUID id){
        //if (this.shardingHashFunction == null) this.shardingHashFunction = new ShardingHashFunction();
        return shardingHashFunction.getShardedServer(id).getOperations();
    }

    public MongoOperations getMongoOperations(String uuid) {
        //if (this.shardingHashFunction == null) this.shardingHashFunction = new ShardingHashFunction();
        return this.getMongoOperations(UUID.fromString(uuid));
    }
}
