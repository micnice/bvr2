package zw.co.micnice.logic.domain;

import zw.co.micnice.logic.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author michael
 */
@Entity
@Table(name="vouchergroup")
public class VoucherGroup extends  BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
     
}
