package zw.co.micnice.logic.dao;


import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Matiashe Michael
 */
public interface IGenericDAO<T extends Serializable> extends Serializable {
    
    public T save(T t);
    public List<T> findAll();
    public T get(Long id);
}
