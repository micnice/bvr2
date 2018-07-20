package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.RegistrantDAO;
import zw.co.micnice.logic.domain.Citizenship;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.service.RegistrantService;

/**
 *
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 */
@Service
@Transactional(readOnly = true)
public class RegistrantServiceImpl implements RegistrantService {

    @Autowired
    private RegistrantDAO registrantDAO;

    @Transactional
    public Registrant save(Registrant registrant) {
        return registrantDAO.save(registrant);
    }

    public List<Registrant> findAll() {
        return registrantDAO.findAll();
    }

    public Registrant get(Long id) {
        return registrantDAO.get(id);
    }

    public void setRegistrantDAO(RegistrantDAO registrantDAO) {
        this.registrantDAO = registrantDAO;
    }

    public List<Registrant> getDeRegistered() {
        return registrantDAO.getDeRegistrants();
    }

    public List<Registrant> getRegistrants(String query, String gender, Citizenship citizenship, Boolean dead) {
        return registrantDAO.getRegistrants(query, gender, citizenship, dead);
    }

    public List<Registrant> getRegistrantAgeByParameter(Integer lowerLimit, Integer upperLimit) {
       return registrantDAO.getRegistrantAgeByParameter(lowerLimit, upperLimit);
    }

    public Registrant getRegistrant(Customer customer) {
        return registrantDAO.getRegistrant(customer);
    }

    public Registrant getRegistrantByAccount(Customer customer) {
        return registrantDAO.getRegistrantByAccount(customer);
    }
    
     public Boolean getUniqueRegistrationNumber(String registrationNumber) {
        return registrantDAO.getUniqueRegistrationNumber(registrationNumber);
    }

    public Boolean getRegistrantIdentificationNumber(String idNumber) {
       return registrantDAO.getRegistrantIdentificationNumber(idNumber);
    }
    
    
    public Registrant getRegistrantByRegistrationNumber(String registrationNumber){
    return registrantDAO.getRegistrantByRegistrationNumber(registrationNumber);
    }

    public Registrant getRegistrantByIdentificationNumber(String idNumber){
    return registrantDAO.getRegistrantByIdentificationNumber(idNumber);
    }

    public List<Registrant> getApprovingRegistrants() {
        return registrantDAO.getApprovingRegistrants();
    }

    public Registrant createUids(Registrant registrant) {
        return registrantDAO.createUids(registrant);
    }

    public List<Long> getIds() {
        return registrantDAO.getIds();
    }
    
    
}
