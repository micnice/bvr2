/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.ProductPriceDAO;
import zw.co.micnice.logic.domain.accounts.ProductPrice;
import zw.co.micnice.logic.service.accounts.ProductPriceService;

/**
 *
 * @author tidza
 */
@Service
@Transactional(readOnly = true)
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private ProductPriceDAO productPriceDAO;

    public ProductPrice save(ProductPrice t) {
        return productPriceDAO.save(t);
    }

    public List<ProductPrice> findAll() {
        return productPriceDAO.findAll();
    }

    public ProductPrice get(Long id) {
        return productPriceDAO.get(id);
    }

    public void setProductPriceDao(ProductPriceDAO productPriceDAO) {
        this.productPriceDAO = productPriceDAO;
    }

    public List<ProductPrice> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
