package stukan.vadim.otus.architect.lesson22.MongoShardServer;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;

@Controller
class ShardedServer2 implements ShardedServer {
    //@Value("${app.mongo.server2.connection}")
//    private String connectionString = "mongodb://user:p123456@localhost:27018/product?authSource=product&readPreference=primary&ssl=false";
    private String connectionString = "mongodb://user:p123456@mongo02-mongodb:27017/product?authSource=product&readPreference=primary&ssl=false";

    //@Value("${app.mongo.server1.database}")
    private String dbName = "product";

    @Override
    public MongoOperations getOperations() {
        return new MongoTemplate(
                MongoClients.create(
                        new ConnectionString(connectionString)),
                this.dbName);
    }

    @Override
    public String toString() {

        return connectionString;
    }
}
