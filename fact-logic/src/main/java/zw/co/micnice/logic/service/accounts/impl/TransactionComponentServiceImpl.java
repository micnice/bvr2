package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.TransactionComponentDAO;
import zw.co.micnice.logic.domain.accounts.TransactionComponent;
import zw.co.micnice.logic.service.accounts.TransactionComponentService;

/**
 *
 * @author Judge Muzinda
 * @author Constance Mabaso
 */
@Service
@Transactional(readOnly=true)
public class TransactionComponentServiceImpl implements TransactionComponentService {

    @Autowired
    private TransactionComponentDAO transactionDao;
    
    public TransactionComponent save(TransactionComponent t) {
        return transactionDao.save(t);
    }

    public List<TransactionComponent> findAll() {
        return transactionDao.findAll();
    }

    public TransactionComponent get(Long id) {
        return transactionDao.get(id);
    }    

    public void setTransactionDao(TransactionComponentDAO transactionDao) {
        this.transactionDao = transactionDao;
    }

    public List<TransactionComponent> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}