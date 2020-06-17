package stukan.vadim.otus.lesson2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stukan.vadim.otus.lesson2.domain.Product;
import stukan.vadim.otus.lesson2.exception.ProductNotFoundedException;
import stukan.vadim.otus.lesson2.repository.ProductRepository;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable UUID id) throws InterruptedException {

        Thread.sleep(1000);
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundedException(id.toString()));
    }
}
