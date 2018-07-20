package zw.co.micnice.logic.dao.accounts.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.accounts.ProductDAO;
import zw.co.micnice.logic.dao.repo.ProductRepository;
import zw.co.micnice.logic.domain.TypeOfService;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.AccountType;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.domain.accounts.ProductGroup;

/**
 *
 * @author Tatenda Chiwandire
 * @author Michael Matiashe
 * @author Charles Chigoriwa
 */
@Repository
public class ProductDAOImpl implements ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;

    public Product save(Product t) {
        if (t.getSalesAccount() == null || t.getSalesAccount().getId() == null) {
            t.setSalesAccount(new Account());
            t.getSalesAccount().setBalance(BigDecimal.ZERO);
            t.getSalesAccount().setAccountType(AccountType.ACCOUNTS_RECEIVABLE);
            t.getSalesAccount().setCode(UUID.randomUUID().toString());
            t.getSalesAccount().setName(t.getName());
            t.getSalesAccount().setPersonalAccount(Boolean.FALSE);
        }
        return productRepository.save(t);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product get(Long id) {
        return productRepository.findOne(id);
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findProducts(TypeOfService typeOfService) {
        return entityManager.createQuery("select p from Product p where p.typeOfService=:typeOfService")
                .setParameter("typeOfService", typeOfService).getResultList();
    }

    public List<Product> findProducts(ProductGroup productGroup) {
        return entityManager.createQuery("select p from Product p where p.productGroup=:productGroup")
                .setParameter("productGroup", productGroup).getResultList();
    }

    public List<Product> findProducts(Product product) {
        StringBuilder sb = new StringBuilder("");
        sb.append("select p from Product p where ");
       

        if (product.getTypeOfService() == null) {
            sb.append("p.typeOfService IS NULL ");
        } else {
            sb.append("and p.typeOfService=:typeOfService");
        }

        Query query = entityManager.createQuery(sb.toString());
        
        return query.getResultList();

    }

    public List<Product> examProducts() {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("examProduct", Boolean.TRUE));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();

    }

    public List<Product> findNoneExamProducts() {
        Boolean examProduct = Boolean.FALSE;
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.examProduct=:examProduct").setParameter("examProduct", examProduct).getResultList();
    }
}
