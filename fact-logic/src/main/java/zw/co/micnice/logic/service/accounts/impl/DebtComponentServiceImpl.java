package zw.co.micnice.logic.service.accounts.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.DebtComponentDAO;
import zw.co.micnice.logic.dao.accounts.impl.DocumentDAOImpl;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.Customer;
import zw.co.micnice.logic.domain.accounts.DebtComponent;
import zw.co.micnice.logic.domain.accounts.Document;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.service.accounts.CustomerService;
import zw.co.micnice.logic.service.accounts.DebtComponentService;
import zw.co.micnice.logic.service.accounts.ProductService;

/**
 *
 * @author Michael Matiashe
 */
@Service
@Transactional(readOnly = true)
public class DebtComponentServiceImpl implements DebtComponentService {

    @Autowired
    private DebtComponentDAO debtComponentDAO;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ProductService productService;
    
    @Transactional
    public DebtComponent save(DebtComponent t) {
        return debtComponentDAO.save(t);
    }

    public List<DebtComponent> findAll() {
        return debtComponentDAO.findAll();
    }

    public DebtComponent get(Long id) {
        return debtComponentDAO.get(id);
    }

    public void setDebtComponentDao(DebtComponentDAO debtComponentDAO) {
        this.debtComponentDAO = debtComponentDAO;
    }

    public List<DebtComponent> getDebtComponents(Account account) {
        return this.debtComponentDAO.getDebtComponents(account);
    }

    public List<DebtComponent> getDebtComponents(Customer customerAccount) {
        return this.debtComponentDAO.getDebtComponents(customerAccount);
    }
    

    public List<DebtComponent> getDebtComponents(Product product, Date startDate, Date endDate) {
        return debtComponentDAO.getDebtComponents(product, startDate, endDate);
    }

    public List<DebtComponent> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<DebtComponent> getDebtComponents(Beneficiary beneficiary) {
        return  debtComponentDAO.getDebtComponents(beneficiary);
    }

    public List<DebtComponent> getCurrentDebtComponents(Beneficiary beneficiary) {
        return debtComponentDAO.getCurrentDebtComponents(new Account());
    }

    public List<DebtComponent> getOneMonthDebtComponents(Account account) {
        return debtComponentDAO.getOneMonthDebtComponents(account);
    }

    public List<DebtComponent> getTwoMonthDebtComponents(Account account) {
        return debtComponentDAO.getTwoMonthsDebtComponents(account);
    }

    public List<DebtComponent> getThreeMonthsOrMoreDebtComponents(Account account) {
        return debtComponentDAO.getThreeMonthsOrMoreDebtComponents(account);
    }

    public int getTotalDues(Account account, Boolean threeOrMore, Boolean twoMonths, Boolean oneMonth, Boolean current) {
        return debtComponentDAO.getTotalDues(account, threeOrMore, twoMonths, oneMonth, current);
    }

    public List<DebtComponent> getDeptComponents(Document document) {
        return debtComponentDAO.getDeptComponents(document);
    }
}
