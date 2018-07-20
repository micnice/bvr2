/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.domain.accounts;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import zw.co.micnice.logic.domain.BaseEntity;

/**
 *
 * @author tdhlakama
 */
@Entity
@Table(name="productgroup")
public class ProductGroup extends BaseEntity implements Serializable {

}
