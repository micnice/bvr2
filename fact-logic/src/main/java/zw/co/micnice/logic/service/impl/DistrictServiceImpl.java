package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.DistrictDAO;
import zw.co.micnice.logic.domain.District;
import zw.co.micnice.logic.service.DistrictService;

/**
 *
 * @author Constance Mabaso
 */
@Service
@Transactional(readOnly = true)
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictDAO districtDAO;
    

    @Transactional
    public District save(District district) {
        return districtDAO.save(district);
    }

    public List<District> findAll() {
        return districtDAO.findAll();
    }
    
    public District get(Long id) {
        return districtDAO.get(id);
    }

    public void setDistrictDAO(DistrictDAO districtDAO) {
        this.districtDAO = districtDAO;
    }

   
}
