package zw.co.micnice.logic.domain.accounts;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import zw.co.micnice.logic.domain.BaseEntity;
import zw.co.micnice.logic.domain.TypeOfService;

/**
 *
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 */
@Entity
@Table(name="product")
public class Product extends BaseEntity implements Serializable {

    private ProductPrice productPrice;
    private Long code;
    private Account salesAccount;
    private Boolean directlyInvoicable;
    private ProductGroup productGroup; //Specifically for combining two or more papers
    private Boolean examProduct;
    private TypeOfService typeOfService;

    public Boolean getDirectlyInvoicable() {
        return directlyInvoicable;
    }

    public void setDirectlyInvoicable(Boolean directlyInvoicable) {
        this.directlyInvoicable = directlyInvoicable;
    }

    @Enumerated(EnumType.STRING)
    public TypeOfService getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(TypeOfService typeOfService) {
        this.typeOfService = typeOfService;
    }

    @Column(unique = true)
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @ManyToOne
    public Account getSalesAccount() {
        return salesAccount;
    }

    public void setSalesAccount(Account salesAccount) {
        this.salesAccount = salesAccount;
    }

    @OneToOne(mappedBy = "product")
    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(ProductPrice productPrice) {
        this.productPrice = productPrice;
    }

    public Boolean getExamProduct() {
        if (examProduct == null) {
            return Boolean.FALSE;
        }
        return examProduct;
    }

    public void setExamProduct(Boolean examProduct) {
        this.examProduct = examProduct;
    }

    @ManyToOne
    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }
}
