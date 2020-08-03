package vadim.shtukan.otus.architect.finelproject.ControllerRest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vadim.shtukan.otus.architect.finelproject.Controller.CartController;
import vadim.shtukan.otus.architect.finelproject.Controller.ProductController;
import vadim.shtukan.otus.architect.finelproject.Controller.RequestIdempotencyController;
import vadim.shtukan.otus.architect.finelproject.Domain.Cart;
import vadim.shtukan.otus.architect.finelproject.Domain.ProductCart;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartRestController {
    final Logger logger = LogManager.getLogger();

    @Autowired
    RequestIdempotencyController requestIdempotencyController;

    @Autowired
    CartController cartController;

    @Autowired
    ProductController productController;

    @PostMapping("")
    public Cart addProductToCart(@RequestParam(value = "cartId", defaultValue = "") UUID cartId,
                                 @RequestBody ProductCart productCart,
                                 @RequestHeader(value = "X-UserId", defaultValue = "") UUID userID,
                                 @RequestHeader(value = "X-RequestId")UUID requestId,
                                 @RequestHeader(value = "X-CountryId")UUID countryId,
                                 @RequestHeader(value = "X-ShopId")UUID shopId,
                                 HttpServletResponse response) throws IOException {
        Cart result;

        if(productCart.getProductId() == null){
            throw new NullPointerException("ProductId can bot be null");
        }

        if(!requestIdempotencyController.insertRequestId(requestId)){
            logger.warn("Idempotency in add product to cart RequestId: " + requestId);

            return cartController.getCartByRequestId(requestId);
        }

        if(cartId != null){
            result = cartController.getCartById(cartId);
        }
        else {
            result = new Cart();
        }

        result.setLastRequestId(requestId);

        result = cartController.addProductToCart(result, countryId, shopId, userID, productCart);

        if(cartId != null) {
            result = cartController.updateCart(cartId, result);
        }
        else{
            result.setCartId(UUID.randomUUID());
            result = cartController.insertNewCart(result);
        }

        return result;
    }
}
