package stukan.vadim.otus.architect.lesson22.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import stukan.vadim.otus.architect.lesson22.Repository.ProductRepository;
import stukan.vadim.otus.architect.lesson22.domaim.Product;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable UUID id) throws InterruptedException {
        return productRepository.findById(id);
    }

    @PostMapping
    public Product add(@RequestBody Product product){
        return productRepository.save(product);
    }
}
