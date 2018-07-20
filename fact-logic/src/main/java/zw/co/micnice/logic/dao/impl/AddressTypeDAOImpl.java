package zw.co.micnice.logic.dao.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.AddressTypeDAO;
import zw.co.micnice.logic.dao.repo.AddressTypeRepository;
import zw.co.micnice.logic.domain.AddressType;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class AddressTypeDAOImpl implements AddressTypeDAO{
   @Autowired
   private AddressTypeRepository addressTypeRepository;
    
   public AddressType save(AddressType addressType){
       return addressTypeRepository.save(addressType);
   }
   
   public List<AddressType> findAll(){
       return addressTypeRepository.findAll();
   }
   
   public AddressType get(Long id){
       return addressTypeRepository.findOne(id);
   }
 /**
     * A setter method that will make mocking repo object easier
     * @param addressTypeRepository 
     */
   
   public void setAddressTypeRepository(AddressTypeRepository addressTypeRepository) {
         this.addressTypeRepository = addressTypeRepository;
    }
}
