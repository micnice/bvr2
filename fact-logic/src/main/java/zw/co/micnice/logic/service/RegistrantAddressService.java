/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service;

import java.util.List;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.RegistrantAddress;

/**
 *
 * @author kelvin
 */
public interface RegistrantAddressService extends IGenericService<RegistrantAddress> {
    
    public RegistrantAddress getActiveAddress(Registrant registrant);
}
