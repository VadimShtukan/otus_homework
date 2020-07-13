package vadim.shtukan.otus.architect.finelproject.ControllerRest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import vadim.shtukan.otus.architect.finelproject.Controller.CartController;
import vadim.shtukan.otus.architect.finelproject.Controller.RequestIdempotencyController;
import vadim.shtukan.otus.architect.finelproject.Domain.Cart;
import vadim.shtukan.otus.architect.finelproject.Domain.ProductCart;
import vadim.shtukan.otus.architect.finelproject.Repository.CartRepository;
import vadim.shtukan.otus.architect.finelproject.Repository.RequestIdempotencyRepository;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest
@RunWith(SpringRunner.class)
class CartRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private RequestIdempotencyRepository  requestIdempotencyRepository;

    @Test
    public void addProductToCart() throws Exception{
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        ProductCart productCart = new ProductCart();
        productCart.setProductId(UUID.randomUUID());
        productCart.setSize("32");
        productCart.setQuantity(2);

        this.mockMvc.perform(post("/cart")
                .header("X-UserId", UUID.randomUUID())
                .header("X-RequestId", UUID.randomUUID())
                .header("X-CountryId", UUID.randomUUID())
                .header("X-ShopId", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(productCart)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}