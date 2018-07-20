package zw.co.micnice.logic.domain.accounts;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import zw.co.micnice.logic.domain.BaseIdEntity;

/**
 *
 * @author tdhlakama
 */
@Entity
@Table(name="productprice")
public class ProductPrice extends BaseIdEntity implements Serializable{
    
   
    private Product product;    
    private BigDecimal price;

    @OneToOne
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
    
   
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
}
