package vadim.shtukan.otus.architect.finelproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import vadim.shtukan.otus.architect.finelproject.Domain.Cart;
import vadim.shtukan.otus.architect.finelproject.Domain.Currency;
import vadim.shtukan.otus.architect.finelproject.Domain.ProductCart;
import vadim.shtukan.otus.architect.finelproject.Domain.ProductPrice;
import vadim.shtukan.otus.architect.finelproject.Repository.CartRepository;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@Controller
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductController productController;

    @Autowired
    DiscountController discountController;

    public Cart insertNewCart(Cart cart){
        return cartRepository.insert(cart);
    }

    public Cart getCartById(UUID cartId){
        return cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not founded. Id: " + cartId));
    }

    public Cart addProductToCart(Cart cart, UUID countryId, UUID shopId, UUID userId, ProductCart productCart) {
        ProductPrice productPrice = productController.getProductPrice(countryId, shopId, userId, productCart.getProductId(), productCart.getSize());
        cart.setCartCurrency(getCartCurrency(countryId, shopId));

        if(cart.getProductList() == null) {
            cart.setProductList(new ArrayList<>());
        }

        for (ProductCart productCartItem: cart.getProductList()) {
            if (productCartItem.getProductId().equals(productCart.getProductId()) && productCartItem.getSize().equals(productCart.getSize())) {
                productCartItem.setQuantity(productCartItem.getQuantity() + productCart.getQuantity());
                productCartItem.setTotal(productPrice.getTotal().multiply(BigDecimal.valueOf(productCartItem.getQuantity())));
                productCartItem.setPrice(productPrice.getPrice());
                productCartItem.setDiscount(productPrice.getDiscount().multiply(BigDecimal.valueOf(productCartItem.getQuantity())));
                return calcCartTotal(cart, userId);
            }
        }

        if(productCart.getQuantity() == null) productCart.setQuantity(0);
        productCart.setTotal(productPrice.getTotal().multiply(BigDecimal.valueOf(productCart.getQuantity())));
        productCart.setPrice(productPrice.getPrice());
        productCart.setDiscount(productPrice.getDiscount().multiply(BigDecimal.valueOf(productCart.getQuantity())));
        cart.getProductList().add(productCart);

        return calcCartTotal(cart, userId);
    }

    private Cart calcCartTotal(Cart cart, UUID userId) {
        cart.setSubTotal(BigDecimal.valueOf(0));
        cart.setTotal(BigDecimal.valueOf(0));
        cart.setDiscount(BigDecimal.valueOf(0));

        for (ProductCart productCartItem: cart.getProductList()) {
            cart.setSubTotal(cart.getSubTotal().add(productCartItem.getPrice().multiply(BigDecimal.valueOf(productCartItem.getQuantity()))));
            cart.setTotal(cart.getTotal().add(productCartItem.getTotal()));
            cart.setDiscount(cart.getDiscount().add(productCartItem.getDiscount()));
        }

        cart.setPersonalDiscount(discountController.getPersonalDiscount(userId, cart.getSubTotal(), cart.getTotal()));
        cart.setTotal(cart.getTotal().subtract(cart.getPersonalDiscount()));

        return cart;
    }

    private Currency getCartCurrency(UUID countryId, UUID shopId) {
        Currency result = new Currency();

        //TODO
        result.setCode("UAH");
        result.setName("грн");
        result.setCurrencyId(UUID.randomUUID());

        return result;
    }

    public Cart updateCart(UUID cartId, Cart cart) {
        cart.setCartId(cartId);
        return cartRepository.save(cart);
    }

    public Cart getCartByRequestId(UUID requestId) {
        return cartRepository.findByLastRequestIdQuery(requestId);
    }
}
