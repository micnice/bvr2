/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.ProductPriceDAO;
import zw.co.micnice.logic.dao.repo.ProductPriceRepository;
import zw.co.micnice.logic.domain.accounts.ProductPrice;

/**
 *
 * @author Tatenda Chiwandire
 * @author Michael Matiashe
 */
@Repository
public class ProductPriceDAOImpl implements ProductPriceDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductPriceRepository accountRepository;

    public ProductPrice save(ProductPrice t) {
        return accountRepository.save(t);
    }

    public List<ProductPrice> findAll() {
        return accountRepository.findAll();
    }

    public ProductPrice get(Long id) {
        return accountRepository.findOne(id);
    }

    public ProductPriceRepository getProductPriceRepository() {
        return accountRepository;
    }

    public void setProductPriceRepository(ProductPriceRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
   
}
