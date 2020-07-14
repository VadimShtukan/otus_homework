package stukan.vadim.otus.architect.lesson22.MongoShardServer;

import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;

interface ShardedServer {
    MongoOperations getOperations ();


}
