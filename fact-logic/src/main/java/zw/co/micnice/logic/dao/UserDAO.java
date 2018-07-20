/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zw.co.micnice.logic.dao;

import java.util.List;
import zw.co.micnice.logic.domain.User;


/**
 *
 * @author Clive Gurure
 * @author Michael Matiashe
 * 
 */

public interface UserDAO extends IGenericDAO<User> {
    public User get(String username,String password);
    public List<User> getAuthorisers ();
    public List<User> getApplicationpProcessors();
}
