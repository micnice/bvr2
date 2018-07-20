
package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.TransactionHeaderDAO;
import zw.co.micnice.logic.dao.repo.TransactionHeaderRepository;
import zw.co.micnice.logic.domain.accounts.TransactionHeader;

/**
 *
 * @author Edward Zengeni
 */
@Repository
public class TransactionHeaderDAOImpl implements TransactionHeaderDAO {
     @Autowired
    private TransactionHeaderRepository transactionHeaderRepository;

    public TransactionHeader save(TransactionHeader transactionHeader) {
        return transactionHeaderRepository.save(transactionHeader);
    }

    public List<TransactionHeader> findAll() {
        return transactionHeaderRepository.findAll();
    }

    public TransactionHeader get(Long id) {
        return transactionHeaderRepository.findOne(id);
    }

    /**
     * A setter method that will make mocking repo object easier
     * @param transactionHeaderRepository 
     */
    public void setTransactionHeaderRepository(TransactionHeaderRepository transactionHeaderRepository) {
        this.transactionHeaderRepository = transactionHeaderRepository;
    }

    
    
}
