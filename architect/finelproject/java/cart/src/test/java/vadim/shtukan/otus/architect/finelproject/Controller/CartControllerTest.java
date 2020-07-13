package vadim.shtukan.otus.architect.finelproject.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vadim.shtukan.otus.architect.finelproject.Domain.Cart;
import vadim.shtukan.otus.architect.finelproject.Domain.ProductCart;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartControllerTest {
    @Autowired
    CartController cartController;


    @Test
    void addProductToCart_Currency() {
        UUID shopId = UUID.randomUUID();
        UUID countryId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Cart cart = new Cart();
        ProductCart productCart = new ProductCart();
        productCart.setProductId(UUID.randomUUID());

        cartController.addProductToCart(cart, countryId, shopId, userId, productCart);

        assertNotNull(cart.getCartCurrency());
        assertEquals(cart.getCartCurrency().getCode(), "UAH");
        assertEquals(cart.getCartCurrency().getName(), "грн");

    }

    @Test
    void addProductToCart_SizeNullAndQuantityNull() {
        UUID shopId = UUID.randomUUID();
        UUID countryId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Cart cart = new Cart();
        ProductCart productCart = new ProductCart();
        productCart.setProductId(UUID.randomUUID());

        cartController.addProductToCart(cart, countryId, shopId, userId, productCart);

        assertNotNull(cart);
        assertNull(cart.getProductList().get(0).getSize());
        assertEquals(cart.getProductList().get(0).getTotal().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getProductList().get(0).getPrice().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getProductList().get(0).getDiscount().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getProductList().get(0).getQuantity(), 0);
        assertEquals(cart.getTotal().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getSubTotal().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getDiscount().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getPersonalDiscount().compareTo(BigDecimal.valueOf(0)), 0);
    }

    @Test
    void addProductToCart_SizeNull() {
        UUID shopId = UUID.randomUUID();
        UUID countryId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Cart cart = new Cart();
        ProductCart productCart = new ProductCart();
        productCart.setProductId(UUID.randomUUID());
        productCart.setQuantity(1);

        cartController.addProductToCart(cart, countryId, shopId, userId, productCart);

        assertNotNull(cart);
        assertNull(cart.getProductList().get(0).getSize());
        assertEquals(cart.getProductList().get(0).getTotal().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getProductList().get(0).getPrice().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getProductList().get(0).getDiscount().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getProductList().get(0).getQuantity(), 1);
        assertEquals(cart.getTotal().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getSubTotal().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getDiscount().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getPersonalDiscount().compareTo(BigDecimal.valueOf(0)), 0);
    }

    @Test
    void addProductToCartAdd_OneProduct() {
        UUID shopId = UUID.randomUUID();
        UUID countryId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Cart cart = new Cart();
        ProductCart productCart = new ProductCart();
        productCart.setProductId(UUID.randomUUID());
        productCart.setQuantity(1);
        productCart.setSize("31");

        cartController.addProductToCart(cart, countryId, shopId, userId, productCart);

        assertNotNull(cart);
        assertEquals(cart.getProductList().get(0).getTotal().compareTo(BigDecimal.valueOf(1005.2)), 0);
        assertEquals(cart.getProductList().get(0).getPrice().compareTo(BigDecimal.valueOf(1015.3)), 0);
        assertEquals(cart.getProductList().get(0).getDiscount().compareTo(BigDecimal.valueOf(10.1)), 0);
        assertEquals(cart.getProductList().get(0).getQuantity(), 1);
        assertEquals(cart.getSubTotal().compareTo(BigDecimal.valueOf(1015.3)), 0);
        assertEquals(cart.getDiscount().compareTo(BigDecimal.valueOf(10.1)), 0);
        assertEquals(cart.getPersonalDiscount().compareTo(BigDecimal.valueOf(10)), 0);
        assertEquals(cart.getTotal().compareTo(BigDecimal.valueOf(995.2)), 0);
    }

    @Test
    void addProductToCartAdd_TwoProduct() {
        UUID shopId = UUID.randomUUID();
        UUID countryId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Cart cart = new Cart();
        ProductCart productCart = new ProductCart();
        productCart.setProductId(UUID.randomUUID());
        productCart.setQuantity(2);
        productCart.setSize("31");

        cartController.addProductToCart(cart, countryId, shopId, userId, productCart);

        assertNotNull(cart);
        assertEquals(cart.getProductList().get(0).getTotal().compareTo(BigDecimal.valueOf(1005.2 * 2)), 0);
        assertEquals(cart.getProductList().get(0).getPrice().compareTo(BigDecimal.valueOf(1015.3)), 0);
        assertEquals(cart.getProductList().get(0).getQuantity(), 2);
        assertEquals(cart.getProductList().get(0).getDiscount().compareTo(BigDecimal.valueOf(10.1 * 2)), 0);
        assertEquals(cart.getSubTotal().compareTo(BigDecimal.valueOf(1015.3 * 2)), 0);
        assertEquals(cart.getDiscount().compareTo(BigDecimal.valueOf(10.1 * 2)), 0);
        assertEquals(cart.getPersonalDiscount().compareTo(BigDecimal.valueOf(10)), 0);
        assertEquals(cart.getTotal().compareTo(BigDecimal.valueOf(1005.2 * 2 - 10)), 0);
    }

    @Test
    void addProductToCartAdd_OneProductTwice() {
        UUID shopId = UUID.randomUUID();
        UUID countryId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Cart cart = new Cart();
        ProductCart productCart = new ProductCart();
        productCart.setProductId(UUID.randomUUID());
        productCart.setQuantity(1);
        productCart.setSize("31");

        cartController.addProductToCart(cart, countryId, shopId, userId, productCart);
        cartController.addProductToCart(cart, countryId, shopId, userId, productCart);

        assertNotNull(cart);
        assertEquals(cart.getProductList().get(0).getTotal().compareTo(BigDecimal.valueOf(1005.2 * 2)), 0);
        assertEquals(cart.getProductList().get(0).getPrice().compareTo(BigDecimal.valueOf(1015.3)), 0);
        assertEquals(cart.getProductList().get(0).getQuantity(), 2);
        assertEquals(cart.getProductList().get(0).getDiscount().compareTo(BigDecimal.valueOf(10.1 * 2)), 0);
        assertEquals(cart.getSubTotal().compareTo(BigDecimal.valueOf(1015.3 * 2)), 0);
        assertEquals(cart.getDiscount().compareTo(BigDecimal.valueOf(10.1 * 2)), 0);
        assertEquals(cart.getPersonalDiscount().compareTo(BigDecimal.valueOf(10)), 0);
        assertEquals(cart.getTotal().compareTo(BigDecimal.valueOf(1005.2 * 2 - 10)), 0);
    }

    @Test
    void addProductToCartAdd_TwoDiffProduct() {
        UUID shopId = UUID.randomUUID();
        UUID countryId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Cart cart = new Cart();
        ProductCart productCart1 = new ProductCart();
        productCart1.setProductId(UUID.randomUUID());
        productCart1.setQuantity(1);
        productCart1.setSize("31");

        ProductCart productCart2 = new ProductCart();
        productCart2.setProductId(UUID.randomUUID());
        productCart2.setQuantity(2);
        productCart2.setSize("32");

        cartController.addProductToCart(cart, countryId, shopId, userId, productCart1);
        cartController.addProductToCart(cart, countryId, shopId, userId, productCart2);
        cartController.addProductToCart(cart, countryId, shopId, userId, productCart2);

        assertNotNull(cart);
        assertEquals(cart.getProductList().get(0).getTotal().compareTo(BigDecimal.valueOf(1005.2)), 0);
        assertEquals(cart.getProductList().get(0).getPrice().compareTo(BigDecimal.valueOf(1015.3)), 0);
        assertEquals(cart.getProductList().get(0).getQuantity(), 1);
        assertEquals(cart.getProductList().get(0).getSize(), "31");
        assertEquals(cart.getProductList().get(0).getDiscount().compareTo(BigDecimal.valueOf(10.1)), 0);

        assertEquals(cart.getProductList().get(1).getTotal().compareTo(BigDecimal.valueOf(1005.2 * 4)), 0);
        assertEquals(cart.getProductList().get(1).getPrice().compareTo(BigDecimal.valueOf(1015.3)), 0);
        assertEquals(cart.getProductList().get(1).getQuantity(), 4);
        assertEquals(cart.getProductList().get(1).getSize(), "32");
        assertEquals(cart.getProductList().get(1).getDiscount().compareTo(BigDecimal.valueOf(10.1 * 4)), 0);

        assertEquals(cart.getSubTotal().compareTo(BigDecimal.valueOf(1015.3 * 5)), 0);
        assertEquals(cart.getDiscount().compareTo(BigDecimal.valueOf(10.1 * 5)), 0);
        assertEquals(cart.getPersonalDiscount().compareTo(BigDecimal.valueOf(10)), 0);
        assertEquals(cart.getTotal().compareTo(BigDecimal.valueOf(1005.2 * 5 - 10)), 0);
    }

    @Test
    void addProductToCartAdd_TwoDiffProductAndEmptyProduct() {
        UUID shopId = UUID.randomUUID();
        UUID countryId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Cart cart = new Cart();
        ProductCart productCart1 = new ProductCart();
        productCart1.setProductId(UUID.randomUUID());
        productCart1.setQuantity(1);
        productCart1.setSize("31");

        ProductCart productCart2 = new ProductCart();
        productCart2.setProductId(UUID.randomUUID());
        productCart2.setQuantity(2);
        productCart2.setSize("32");

        ProductCart productCart3 = new ProductCart();
        productCart2.setProductId(UUID.randomUUID());

        cartController.addProductToCart(cart, countryId, shopId, userId, productCart1);
        cartController.addProductToCart(cart, countryId, shopId, userId, productCart2);
        cartController.addProductToCart(cart, countryId, shopId, userId, productCart2);
        cartController.addProductToCart(cart, countryId, shopId, userId, productCart3);

        assertNotNull(cart);
        assertEquals(cart.getProductList().get(0).getTotal().compareTo(BigDecimal.valueOf(1005.2)), 0);
        assertEquals(cart.getProductList().get(0).getPrice().compareTo(BigDecimal.valueOf(1015.3)), 0);
        assertEquals(cart.getProductList().get(0).getQuantity(), 1);
        assertEquals(cart.getProductList().get(0).getSize(), "31");
        assertEquals(cart.getProductList().get(0).getDiscount().compareTo(BigDecimal.valueOf(10.1)), 0);

        assertEquals(cart.getProductList().get(1).getTotal().compareTo(BigDecimal.valueOf(1005.2 * 4)), 0);
        assertEquals(cart.getProductList().get(1).getPrice().compareTo(BigDecimal.valueOf(1015.3)), 0);
        assertEquals(cart.getProductList().get(1).getQuantity(), 4);
        assertEquals(cart.getProductList().get(1).getSize(), "32");
        assertEquals(cart.getProductList().get(1).getDiscount().compareTo(BigDecimal.valueOf(10.1 * 4)), 0);

        assertEquals(cart.getProductList().get(2).getTotal().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getProductList().get(2).getPrice().compareTo(BigDecimal.valueOf(0)), 0);
        assertEquals(cart.getProductList().get(2).getQuantity(), 0);
        assertNull(cart.getProductList().get(2).getSize());
        assertEquals(cart.getProductList().get(2).getDiscount().compareTo(BigDecimal.valueOf(0)), 0);


        assertEquals(cart.getSubTotal().compareTo(BigDecimal.valueOf(1015.3 * 5)), 0);
        assertEquals(cart.getDiscount().compareTo(BigDecimal.valueOf(10.1 * 5)), 0);
        assertEquals(cart.getPersonalDiscount().compareTo(BigDecimal.valueOf(10)), 0);
        assertEquals(cart.getTotal().compareTo(BigDecimal.valueOf(1005.2 * 5 - 10)), 0);
    }
}