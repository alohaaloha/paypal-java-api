package travelsafe.service;

import java.util.List;

/**
 * Created by aloha on 24-Nov-16.
 */
public interface GenericService<T> {

    /*Add methods here*/

    List<T> getAll();

    T save(T entity);


}
