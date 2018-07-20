/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.accounts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.accounts.ProductGroupDAO;
import zw.co.micnice.logic.domain.accounts.ProductGroup;
import zw.co.micnice.logic.service.accounts.ProductGroupService;

/**
 *
 * @author tidza
 */
@Service
@Transactional(readOnly = true)
public class ProductGroupServiceImpl implements ProductGroupService {

    @Autowired
    private ProductGroupDAO productPriceDAO;

    public ProductGroup save(ProductGroup t) {
        return productPriceDAO.save(t);
    }

    public List<ProductGroup> findAll() {
        return productPriceDAO.findAll();
    }

    public ProductGroup get(Long id) {
        return productPriceDAO.get(id);
    }

    public void setProductGroupDao(ProductGroupDAO productPriceDAO) {
        this.productPriceDAO = productPriceDAO;
    }

    public List<ProductGroup> findAll(Boolean retired) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
