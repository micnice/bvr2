/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.domain.City;
import zw.co.micnice.logic.dao.repo.CityRepository;
import zw.co.micnice.logic.service.CityService;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public City save(City city) {
        return cityRepository.save(city);  
    }

    @Transactional(readOnly = true)
    public List<City> findAll() {
        return entityManager.createQuery("select c from City c").getResultList();
    }

    @Transactional(readOnly = true)
    public City get(Long id) {
        return cityRepository.findOne(id);
    }
    
}
