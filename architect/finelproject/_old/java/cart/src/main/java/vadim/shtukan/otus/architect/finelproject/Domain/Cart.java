package vadim.shtukan.otus.architect.finelproject.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Document(collection = "Cart")
public class Cart {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    @Indexed(unique = true)
    private UUID cartId;

    @JsonIgnore
    private UUID lastRequestId;

    private List<ProductCart> productList;

    private BigDecimal subTotal;

    private BigDecimal discount;

    private BigDecimal personalDiscount;

    private BigDecimal total;

    private Currency cartCurrency;

    public BigDecimal getPersonalDiscount() {
        return personalDiscount;
    }

    public void setPersonalDiscount(BigDecimal personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public Currency getCartCurrency() {
        return cartCurrency;
    }

    public void setCartCurrency(Currency cartCurrency) {
        this.cartCurrency = cartCurrency;
    }

    public List<ProductCart> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductCart> productList) {
        this.productList = productList;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public UUID getLastRequestId() {
        return lastRequestId;
    }

    public void setLastRequestId(UUID lastRequestId) {
        this.lastRequestId = lastRequestId;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }
}
