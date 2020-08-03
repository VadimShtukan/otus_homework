package vadim.shtukan.otus.architect.finelproject.Domain;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductCart {

    private UUID productId;
    private String size;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal total;

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}
