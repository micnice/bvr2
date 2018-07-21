package zw.co.micnice.logic.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author charlesc
 */
@Entity
@Table(name="vouchertype")
public class VoucherType extends  BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private VoucherGroup voucherGroup;
    private Double price;

    @ManyToOne
    public VoucherGroup getVoucherGroup() {
        return voucherGroup;
    }

    public void setVoucherGroup(VoucherGroup voucherGroup) {
        this.voucherGroup = voucherGroup;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
