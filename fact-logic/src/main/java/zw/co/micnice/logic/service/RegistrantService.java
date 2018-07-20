package zw.co.micnice.logic.service;

import java.util.List;
import zw.co.micnice.logic.domain.Citizenship;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.accounts.Customer;

/**
 *
 * @author Takunda Dhlakama
 * @author Matiashe Michael
 */
public interface RegistrantService extends IGenericService<Registrant> {
    
    public List<Registrant> getApprovingRegistrants();
    public Registrant createUids(Registrant registrant);

    public List<Registrant> getRegistrants(String query, String gender, Citizenship citizenship, Boolean dead);

    public List<Registrant> getDeRegistered();

    public List<Registrant> getRegistrantAgeByParameter(Integer lowerLimit, Integer upperLimit);

    public Registrant getRegistrant(Customer customer);

    public Registrant getRegistrantByAccount(Customer customer);

    public Boolean getUniqueRegistrationNumber(String registrationNumber);

    public Boolean getRegistrantIdentificationNumber(String idNumber);

    public Registrant getRegistrantByRegistrationNumber(String registrationNumber);

    public Registrant getRegistrantByIdentificationNumber(String idNumber);
    
    public List<Long> getIds();
}
