
package zw.co.micnice.logic.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.ProvinceDAO;
import zw.co.micnice.logic.dao.repo.ProvinceRepository;
import zw.co.micnice.logic.domain.Province;

/**
 *
 * @author Kelvin Goredema
 */
@Repository
public class ProvinceDAOImpl implements ProvinceDAO{
   @Autowired
   private ProvinceRepository provinceRepository;
    
   public Province save(Province province){
       return provinceRepository.save(province);
   }
   
   public List<Province> findAll(){
       return provinceRepository.findAll();
   }
   
   public Province get(Long id){
       return provinceRepository.findOne(id);
   }
   
   /**
     * A setter method that will make mocking repo object easier
     * @param provinceRepository 
     */
    public void setProvinceRepository(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }
}

