package stukan.vadim.otus.lesson2.exception;

public class UserNotFoundedException extends RuntimeException{
    public UserNotFoundedException(String s) {
        super("User with id:" + s + " not founded");
    }
}
