package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.TransactionComponentDAO;
import zw.co.micnice.logic.dao.repo.TransactionComponentReposiroty;
import zw.co.micnice.logic.domain.accounts.TransactionComponent;

/**
 *
 * @author Judge Muzinda
 * @author Constance Mabaso
 */
@Repository
public class TransactionComponentDAOImpl implements TransactionComponentDAO{

    @Autowired
    private TransactionComponentReposiroty transactionReposiroty;
    
    public TransactionComponent save(TransactionComponent t) {
        return transactionReposiroty.save(t);
    }

    public List<TransactionComponent> findAll() {
        return transactionReposiroty.findAll();
    }

    public TransactionComponent get(Long id) {
        return transactionReposiroty.findOne(id);
    }

    public void setTransactionReposiroty(TransactionComponentReposiroty transactionReposiroty) {
        this.transactionReposiroty = transactionReposiroty;
    }
    
}
