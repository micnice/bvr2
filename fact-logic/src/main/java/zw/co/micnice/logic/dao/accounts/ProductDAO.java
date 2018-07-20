package zw.co.micnice.logic.dao.accounts;

import java.util.List;
import zw.co.micnice.logic.dao.IGenericDAO;
import zw.co.micnice.logic.domain.TypeOfService;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.domain.accounts.ProductGroup;

/**
 *
 * @author Tatenda Chiwandire
 * @author Michael Matiashe
 */
public interface ProductDAO extends IGenericDAO<Product> {

    public List<Product> findProducts(Product product);

    public List<Product> findProducts(TypeOfService typeOfService);

    public List<Product> examProducts();
    
    public List<Product> findNoneExamProducts();

    public List<Product> findProducts(ProductGroup productGroup);
}
