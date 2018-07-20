/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.RegistrantAddressDAO;
import zw.co.micnice.logic.dao.repo.RegistrantAddressRepository;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.RegistrantAddress;

/**
 *
 * @author kelvin
 */
@Repository
public class RegistrantAddressDAOImpl implements RegistrantAddressDAO {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private RegistrantAddressRepository registrantAddressRepository;

    public RegistrantAddress save(RegistrantAddress registrantAddress) {
        return registrantAddressRepository.save(registrantAddress);
    }

    public List<RegistrantAddress> findAll() {
        return registrantAddressRepository.findAll();
    }

    public RegistrantAddress get(Long id) {
        return registrantAddressRepository.findOne(id);
    }

    /**
     * A setter method that will make mocking repo object easier
     *
     * @param registrantAddressRepository
     */
    public void setRegistrantAddressRepository(RegistrantAddressRepository registrantAddressRepository) {
        this.registrantAddressRepository = registrantAddressRepository;
    }

    public List<RegistrantAddress> getAddresses(Registrant registrant) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(RegistrantAddress.class);
        if (registrant == null) {
            return new ArrayList<RegistrantAddress>();
        }
        if (registrant != null) {
            criteria.add(Restrictions.eq("registrant", registrant));
        }
        
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public RegistrantAddress getActiveAddress(Registrant registrant) {
        List<RegistrantAddress> registrantAddresses = new ArrayList<RegistrantAddress>();
        registrantAddresses.addAll(getAddresses(registrant));
        if (registrantAddresses.isEmpty()) {
            return null;
        }
        List<RegistrantAddress> registrantActiveList = new ArrayList<RegistrantAddress>();
        for (RegistrantAddress r : registrantAddresses) {
            if (r.getStatus() != null && r.getStatus()) {
                registrantActiveList.add(r);
            }
        }
        if (registrantActiveList.isEmpty()) {
            return null;
        } else {
            return registrantActiveList.get(0);
        }
    }
}
