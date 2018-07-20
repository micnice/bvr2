
package zw.co.micnice.logic.service;

import zw.co.micnice.logic.domain.User;


public interface UserService extends IGenericService<User> {
    public User get(String username,String password);
    
}
