package zw.co.micnice.logic.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.DistrictDAO;
import zw.co.micnice.logic.dao.repo.DistrictRepository;
import zw.co.micnice.logic.domain.District;

/**
 *
 * @author Constance Mabaso
 */
@Repository
public class DistrictDAOImpl implements DistrictDAO {

    @Autowired
    private DistrictRepository districtRepository;

    public District save(District district) {
        return districtRepository.save(district);
    }

    public List<District> findAll() {
        return districtRepository.findAll();
    }

    public District get(Long id) {
        return districtRepository.findOne(id);
    }
public void setDistrictRepository(DistrictRepository districtRepository) {
         this.districtRepository = districtRepository;
   
}
}