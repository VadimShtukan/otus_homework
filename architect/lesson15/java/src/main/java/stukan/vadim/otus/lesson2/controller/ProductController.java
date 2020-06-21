package stukan.vadim.otus.lesson2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import stukan.vadim.otus.lesson2.domain.Product;
import stukan.vadim.otus.lesson2.exception.ProductNotFoundedException;
import stukan.vadim.otus.lesson2.exception.UserCreateException;
import stukan.vadim.otus.lesson2.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping("/{id}")
    @Cacheable(value = "products", key = "#id")
    public Product getProduct(@PathVariable UUID id) throws InterruptedException {
        System.out.println("Non cache!");
        Thread.sleep(1000);
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundedException(id.toString()));
    }

    @PostMapping
    public Product add(@RequestBody Product product){
        Product result = product;

        try {
            result = productRepository.save(product);
        }catch (Exception e){
            throw new UserCreateException("Can not create product: " + result.getId());
        }

        Product finalResult = result;
        return productRepository.findById(result.getId())
                .orElseThrow(() -> new ProductNotFoundedException(finalResult.getId().toString()));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "products", key = "#id")
    public Optional<Product> change(@RequestBody Product product, @PathVariable UUID id){
        product.setId(id);
        product = productRepository.save(product);

        return productRepository.findById(product.getId());
    }
}
