
package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.AllocationDAO;
import zw.co.micnice.logic.domain.accounts.Allocation;
import zw.co.micnice.logic.service.accounts.AllocationService;

/**
 *
 * @author Constance Mabaso
 */
@Service
@Transactional(readOnly = true)
public class AllocationServiceImpl implements AllocationService {

    @Autowired
    private AllocationDAO allocationDao;

    public Allocation save(Allocation t) {
        return allocationDao.save(t);
    }

    public List<Allocation> findAll() {
        return allocationDao.findAll();
    }

    public Allocation get(Long id) {
        return allocationDao.get(id);
    }

    public void setAllocationDAO(AllocationDAO allocationDAO) {
        this.allocationDao = allocationDAO;
    }

    public List<Allocation> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
