/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.ContactTypeDAO;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.ContactType;
import zw.co.micnice.logic.service.ContactTypeService;

@Service
public class ContactTypeServiceImpl implements ContactTypeService{

     @Autowired
    private ContactTypeDAO contactTypeDAO;
     @PersistenceContext
     private EntityManager entityManager;
    

    @Transactional
    public ContactType save(ContactType contactType) {
       return contactTypeDAO.save(contactType);
    }

    public List<ContactType> findAll() {
        return contactTypeDAO.findAll();
    }

    public ContactType get(Long id) {
       return contactTypeDAO.get(id);
    }

    public void setContactTypeDAO(ContactTypeDAO contactTypeDAO) {
        this.contactTypeDAO = contactTypeDAO;
    }

    public List<ContactType> getTopBid(Beneficiary beneficiary) {
        return entityManager.createQuery("select c from ContactType c WHERE c.beneficiary=:beneficiary").setParameter("beneficiary", beneficiary).getResultList();
    }
    
}
