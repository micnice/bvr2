/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Table;
import zw.co.micnice.logic.utils.Gender;

/**
 *
 * @author tidza
 */
@Entity
@Table(name = "title")
public class Title extends BaseEntity implements Serializable {

    private Set<Gender> genders = new HashSet<Gender>();

    public Title() {
    }

    @ElementCollection(targetClass = Gender.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING) 
    @CollectionTable(name = "gendertitle")
    @Column(name = "gender") 
    public Set<Gender> getGenders() {
        return genders;
    }

    public void setGenders(Set<Gender> genders) {
        this.genders = genders;
    }
}

