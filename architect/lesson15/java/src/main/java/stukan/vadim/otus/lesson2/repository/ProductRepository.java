package stukan.vadim.otus.lesson2.repository;

import org.springframework.data.repository.CrudRepository;
import stukan.vadim.otus.lesson2.domain.Product;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {
}
