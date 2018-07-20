/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.SupportDocumentDAO;
import zw.co.micnice.logic.dao.repo.SupportDocumentRepository;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.SupportDocument;

/**
 *
 * @author kelvin
 */
@Repository
public class SupportDocumentDAOImpl implements SupportDocumentDAO {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private SupportDocumentRepository supportDocumentRepository;

    public SupportDocument save(SupportDocument supportDocument) {
        return supportDocumentRepository.save(supportDocument);
    }

    public List<SupportDocument> findAll() {
        return supportDocumentRepository.findAll();
    }

    public SupportDocument get(Long id) {
        return supportDocumentRepository.findOne(id);
    }

    /**
     * A setter method that will make mocking repo object easier
     *
     * @param supportDocumentRepository
     */
    public void setSupportDocumentRepository(SupportDocumentRepository supportDocumentRepository) {
        this.supportDocumentRepository = supportDocumentRepository;
    }

}
