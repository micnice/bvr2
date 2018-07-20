
package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.TransactionHeaderDAO;
import zw.co.micnice.logic.domain.accounts.TransactionHeader;
import zw.co.micnice.logic.service.accounts.TransactionHeaderService;

/**
 *
 * @author Edward Zengeni
 */
@Service
@Transactional(readOnly = true)
public class TransactionHeaderServiceImpl  implements TransactionHeaderService {
    @Autowired
    private TransactionHeaderDAO transactionHeaderDAO;

    @Transactional
    public TransactionHeader save(TransactionHeader transactionHeader) {
        return transactionHeaderDAO.save(transactionHeader);
    }

    public List<TransactionHeader> findAll() {
        return transactionHeaderDAO.findAll();
    }

    public TransactionHeader get(Long id) {
        return transactionHeaderDAO.get(id);
    }

    /**
     * A DAO setter method to facilitate mocking
     * @param transactionHeaderDAO 
     */
    public void setTransactionHeaderDAO(TransactionHeaderDAO transactionHeaderDAO) {
        this.transactionHeaderDAO = transactionHeaderDAO;
    }

    public List<TransactionHeader> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
}
