package stukan.vadim.otus.lesson2.exception;

public class ProductNotFoundedException extends RuntimeException{
    public ProductNotFoundedException(String message) {
        super("Product with id:" + message + " not founded");
    }
}
