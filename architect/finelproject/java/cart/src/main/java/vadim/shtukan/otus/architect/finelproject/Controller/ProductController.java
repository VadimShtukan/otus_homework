package vadim.shtukan.otus.architect.finelproject.Controller;

import org.springframework.stereotype.Controller;
import vadim.shtukan.otus.architect.finelproject.Domain.ProductPrice;

import java.math.BigDecimal;
import java.util.UUID;

@Controller
public class ProductController {

    public ProductPrice getProductPrice(UUID countryId, UUID shopId, UUID userId, UUID productId, String size){
        ProductPrice result = new ProductPrice();

        if(size == null || size.isEmpty()){
            result.setPrice(BigDecimal.valueOf(0));
            result.setDiscount(BigDecimal.valueOf(0));
            result.setTotal(BigDecimal.valueOf(0));
            return result;
        };
        //TODO

        result.setPrice(BigDecimal.valueOf(1015.3));
        result.setDiscount(BigDecimal.valueOf(10.1));
        result.setTotal(BigDecimal.valueOf(1005.2));

        return result;
    }
}
