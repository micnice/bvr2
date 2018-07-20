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
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.dao.repo.UserRepository;
import zw.co.micnice.logic.service.UserService;

@Service

public class UserServiceImpl implements UserService{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User get(Long id) {
        return userRepository.findOne(id);
    }
    

    @Transactional(readOnly = true)
    public User get(String username, String password) {
       List<User> users=entityManager.createQuery("select u from User u where u.username=:username and u.password=:password")
                .setParameter("username", username)
                .setParameter("password", password).getResultList();
        if(users.isEmpty()){
            return null;
        }else{
            return users.get(0);
        }
    }

}
