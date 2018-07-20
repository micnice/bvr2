/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.RegistrantAddressDAO;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.RegistrantAddress;
import zw.co.micnice.logic.service.RegistrantAddressService;

/**
 *
 * @author kelvin
 */
@Service
@Transactional(readOnly = true)
public class RegistrantAddressServiceImpl implements RegistrantAddressService {

    @Autowired
    private RegistrantAddressDAO registrantAddressDAO;

    @Transactional
    public RegistrantAddress save(RegistrantAddress registrantAddress) {
        return registrantAddressDAO.save(registrantAddress);
    }

    public List<RegistrantAddress> findAll() {
        return registrantAddressDAO.findAll();
    }

    public RegistrantAddress get(Long id) {
        return registrantAddressDAO.get(id);
    }

    public void setRegistrantAddressDAO(RegistrantAddressDAO registrantAddressDAO) {
        this.registrantAddressDAO = registrantAddressDAO;
    }


    public RegistrantAddress getActiveAddress(Registrant registrant) {
        return registrantAddressDAO.getActiveAddress(registrant);
    }
}
