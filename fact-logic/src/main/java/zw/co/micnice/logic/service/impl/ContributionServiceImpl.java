/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.micnice.logic.dao.ContributionDAO;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.Contribution;
import zw.co.micnice.logic.service.ContributionService;

@Service
public class ContributionServiceImpl implements ContributionService{

    @Autowired
    private ContributionDAO contributionDAO;
     @PersistenceContext
     private EntityManager entityManager;
    
    public Contribution save(Contribution contribution) {
        if(contribution.getId() == null){
            contribution.setAmount(contribution.getStart());
        contribution.setBalance(contribution.getMax()-contribution.getStart());
        }
        return contributionDAO.save(contribution);
    }

    public List<Contribution> findAll() {
        return contributionDAO.findAll();
    }

    public Contribution get(Long id) {
        return contributionDAO.get(id);
    }

    public List<Contribution> getContributions(Beneficiary beneficiary) {
        return entityManager.createQuery("select c from Contribution c where c.remove=FALSE and c.beneficiary=:beneficiary").setParameter("beneficiary", beneficiary).getResultList();
    }

    public Contribution getTopBid(Beneficiary beneficiary) {
        List<Contribution> contributions =entityManager.createQuery("select c from Contribution c where c.beneficiary=:beneficiary order by c.amount DESC").setParameter("beneficiary", beneficiary).getResultList();
        if(contributions.isEmpty()){
            return null;
        } else {
            return  contributions.get(0);
        }
    }
    
}
