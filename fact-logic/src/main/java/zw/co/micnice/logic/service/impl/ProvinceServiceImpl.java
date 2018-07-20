
package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.ProvinceDAO;
import zw.co.micnice.logic.domain.Province;
import zw.co.micnice.logic.service.ProvinceService;

/**
 *
 * @author Kelvin Goredema
 */

@Service
@Transactional(readOnly=true)
public class ProvinceServiceImpl implements ProvinceService {
    
    
     @Autowired
    private ProvinceDAO provinceDAO;
    

    @Transactional
    public Province save(Province province) {
       return provinceDAO.save(province);
    }

    public List<Province> findAll() {
        return provinceDAO.findAll();
    }

    public Province get(Long id) {
       return provinceDAO.get(id);
    }
    
    public void setProvinceDAO(ProvinceDAO provinceDAO) {
        this.provinceDAO = provinceDAO;
    }
    
    
}
