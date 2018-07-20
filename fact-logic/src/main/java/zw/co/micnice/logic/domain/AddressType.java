package zw.co.micnice.logic.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author charlesc
 */
@Entity
@Table(name="addresstype")
public class AddressType extends  BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
     
}
