/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zw.co.micnice.logic.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.UserDAO;
import zw.co.micnice.logic.dao.repo.UserRepository;
import zw.co.micnice.logic.domain.User;


/**
 *
 * @author Clive Gurure
 * @author Michael Matiashe
 */
@Repository
public class UserDAOImpl implements UserDAO {
    
    @Autowired
    private UserRepository userRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public User save(User t) {
        return userRepository.save(t);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User get(Long id) {
        return userRepository.findOne(id);
    }
    
    public User get(String username,String password){
        List<User> users=entityManager.createQuery("select u from User u where u.username=:username and u.password=:password")
                .setParameter("username", username)
                .setParameter("password", password).getResultList();
        if(users.isEmpty()){
            return null;
        }else{
            return users.get(0);
        }
    }
    
    public List<User> getAuthorisers (){
        Boolean authoriser = Boolean.TRUE;
        return entityManager.createQuery("SELECT u FROM User u WHERE u.authoriser=:authoriser").setParameter("authoriser", authoriser).getResultList();
    }
    
    public List<User> getApplicationpProcessors(){
        return entityManager.createQuery("select u from User u where u.applicationProcessor=:applicationProcessor").setParameter("applicationProcessor", Boolean.TRUE).getResultList();
    }
    
    
    public void setuserRepository(UserRepository userRepository) {
         this.userRepository = userRepository;
    }
    
}
