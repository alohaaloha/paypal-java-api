package travelsafe.service;

import java.util.List;

/**
 * Created by aloha on 24-Nov-16.
 */
public interface GenericService<T> {

    T findOne(Long id);

    List<T> findAll();

    T save(T t);

    void remove(Long id) throws IllegalArgumentException;

}
