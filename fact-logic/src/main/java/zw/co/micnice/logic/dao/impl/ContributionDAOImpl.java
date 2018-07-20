/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.ContributionDAO;
import zw.co.micnice.logic.dao.repo.ContributionRepository;
import zw.co.micnice.logic.domain.Contribution;

@Repository
public class ContributionDAOImpl implements ContributionDAO {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private ContributionRepository contributionRepository;

    public Contribution save(Contribution contribution) {
        return contributionRepository.save(contribution);
    }
    public Contribution get(Long id) {
        return contributionRepository.findOne(id);
    }

    public List<Contribution> findAll() {
       return contributionRepository.findAll();
    }
}
