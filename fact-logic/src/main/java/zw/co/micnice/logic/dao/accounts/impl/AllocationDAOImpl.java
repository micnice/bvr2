package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.AllocationDAO;
import zw.co.micnice.logic.dao.repo.AllocationRepository;
import zw.co.micnice.logic.domain.accounts.Allocation;

/**
 *
 * @author Constance Mabaso
 */
@Repository
public class AllocationDAOImpl implements AllocationDAO {

    @Autowired
    private AllocationRepository allocationRepository;

    public Allocation save(Allocation t) {
        return allocationRepository.save(t);
    }

    public List<Allocation> findAll() {
        return allocationRepository.findAll();
    }

    public Allocation get(Long id) {
        return allocationRepository.findOne(id);
    }

    public void setAllocationRepository(AllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    public List<Allocation> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
