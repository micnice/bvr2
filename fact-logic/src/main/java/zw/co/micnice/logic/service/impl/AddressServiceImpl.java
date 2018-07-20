/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import zw.co.micnice.logic.domain.Address;
import zw.co.micnice.logic.dao.repo.AddressRepository;
import zw.co.micnice.logic.service.AddressService;

public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;
            
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Address get(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
