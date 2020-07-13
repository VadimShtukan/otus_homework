package vadim.shtukan.otus.architect.finelproject.Domain;

import java.math.BigDecimal;

public class ProductPrice {
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal total;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
