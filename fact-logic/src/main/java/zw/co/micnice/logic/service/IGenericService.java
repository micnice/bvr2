package zw.co.micnice.logic.service;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<T extends Serializable> extends Serializable {

    public T save(T t);

    public List<T> findAll();

    public T get(Long id);

}
