package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.AddressTypeDAO;
import zw.co.micnice.logic.service.AddressTypeService;
import zw.co.micnice.logic.domain.AddressType;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
@Transactional(readOnly=true)
public class AddressTypeServiceImpl implements AddressTypeService{
    
    @Autowired
    private AddressTypeDAO addressTypeDAO;
    

    @Transactional
    public AddressType save(AddressType addressType) {
       return addressTypeDAO.save(addressType);
    }

    public List<AddressType> findAll() {
        return addressTypeDAO.findAll();
    }

    public AddressType get(Long id) {
       return addressTypeDAO.get(id);
    }

    public void setAddressTypeDAO(AddressTypeDAO addressTypeDAO) {
        this.addressTypeDAO = addressTypeDAO;
    }
    
}
