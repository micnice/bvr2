package zw.co.micnice.logic.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author M2
 */
@Entity
@Table(name="district")
public class District extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Province province;

    @ManyToOne
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
