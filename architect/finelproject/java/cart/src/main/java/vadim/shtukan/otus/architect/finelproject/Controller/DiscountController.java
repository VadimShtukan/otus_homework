package vadim.shtukan.otus.architect.finelproject.Controller;

import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.UUID;

@Controller
public class DiscountController {

    public BigDecimal getPersonalDiscount(UUID userId, BigDecimal cartSubTotal, BigDecimal cartTotal){
        BigDecimal personalDiscount = BigDecimal.valueOf(10);

        //Защита от минуса
        if(personalDiscount.compareTo(cartTotal) >= 0) return BigDecimal.valueOf(0);

        //TODO
        return personalDiscount;
    }
}
