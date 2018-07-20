package zw.co.micnice.logic.dao.accounts.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.ProductGroupDAO;
import zw.co.micnice.logic.dao.repo.ProductGroupRepository;
import zw.co.micnice.logic.domain.accounts.ProductGroup;

/**
 *
 * @author Tatenda Chiwandire
 * @author Michael Matiashe
 * @author Charles Chigoriwa
 */
@Repository
public class ProductGroupDAOImpl implements ProductGroupDAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductGroupRepository productGroupRepository;

    public ProductGroup save(ProductGroup t) {
        return productGroupRepository.save(t);
    }

    public List<ProductGroup> findAll() {
        return productGroupRepository.findAll();
    }

    public ProductGroup get(Long id) {
        return productGroupRepository.findOne(id);
    }

    public ProductGroupRepository getProductRepository() {
        return productGroupRepository;
    }

    public void setProductRepository(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

}
