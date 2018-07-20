package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.ProductDAO;
import zw.co.micnice.logic.domain.TypeOfService;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.domain.accounts.ProductGroup;
import zw.co.micnice.logic.service.accounts.ProductService;

/**
 *
 * @author tidza
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    public Product save(Product t) {
        return productDAO.save(t);
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product get(Long id) {
        return productDAO.get(id);
    }

    public void setProductSaleDao(ProductDAO productSaleDAO) {
        this.productDAO = productSaleDAO;
    }

    public List<Product> findProducts(Product product) {
        return this.productDAO.findProducts(product);
    }

    public List<Product> findProducts(TypeOfService typeOfService) {
        return this.productDAO.findProducts(typeOfService);
    }

    public List<Product> examProducts() {
        return productDAO.examProducts();
    }

    public List<Product> findProducts(ProductGroup productGroup) {
        return productDAO.findProducts(productGroup);
    }

    public List<Product> findNoneExamProducts() {
        return productDAO.findNoneExamProducts();
    }
}
