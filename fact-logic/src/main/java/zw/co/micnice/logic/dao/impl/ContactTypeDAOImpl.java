package zw.co.micnice.logic.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.ContactTypeDAO;
import zw.co.micnice.logic.dao.repo.ContactTypeRepository;
import zw.co.micnice.logic.domain.ContactType;


/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class ContactTypeDAOImpl implements ContactTypeDAO{
   @Autowired
   private ContactTypeRepository contactTypeRepository;
    
   public ContactType save(ContactType contactType){
       return contactTypeRepository.save(contactType);
   }
   
   public List<ContactType> findAll(){
       return contactTypeRepository.findAll();
   }
   
   public ContactType get(Long id){
       return contactTypeRepository.findOne(id);
   }
   
   /**
     * A setter method that will make mocking repo object easier
     * @param contactTypeRepository 
     */
    public void setContactTypeRepository(ContactTypeRepository contactTypeRepository) {
        this.contactTypeRepository = contactTypeRepository;
    }
}
