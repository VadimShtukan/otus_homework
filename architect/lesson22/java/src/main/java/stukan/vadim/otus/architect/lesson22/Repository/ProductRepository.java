package stukan.vadim.otus.architect.lesson22.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import stukan.vadim.otus.architect.lesson22.MongoShardServer.ShardController;
import stukan.vadim.otus.architect.lesson22.domaim.Product;

import java.util.UUID;

@Controller
public class ProductRepository {
    @Autowired
    ShardController shardController;

    public Product findById(UUID id) {
        Query findProduct = new Query();
        findProduct.addCriteria(Criteria.where("uuid").is(id.toString()));
        return shardController.getMongoOperations(id).findOne(findProduct, Product.class);
    }


    public Product save(Product product) {
        product.setUuid(UUID.randomUUID().toString());

        return shardController.getMongoOperations(product.getUuid()).insert(product);
    }
}
