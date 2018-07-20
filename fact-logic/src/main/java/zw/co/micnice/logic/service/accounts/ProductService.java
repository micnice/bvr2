package zw.co.micnice.logic.service.accounts;

import java.util.List;
import zw.co.micnice.logic.domain.TypeOfService;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.domain.accounts.ProductGroup;
import zw.co.micnice.logic.service.IGenericService;

/**
 *
 * @author tidza
 */
public interface ProductService extends IGenericService<Product> {
    public List<Product> findProducts(Product product);
    public List<Product> findProducts(TypeOfService typeOfService);
    public List<Product> findProducts(ProductGroup productGroup);
    public List<Product> examProducts();
    public List<Product> findNoneExamProducts();
}
