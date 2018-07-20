/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Michael Matiashe
 */
@Entity
@Table(name="province")
public class Province extends BaseEntity{
   private static final long serialVersionUID = 1L;
   
    private Set<District> districts = new HashSet<District>();
    private String shortName;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
      
   @OneToMany(mappedBy = "province")
    public Set<District> getDistricts() {
        return  districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }
}
    

    
    
    
    
    
    

