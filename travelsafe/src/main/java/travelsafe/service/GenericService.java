package travelsafe.service;

import java.util.List;

/**
 * Created by aloha on 24-Nov-16.
 */
public interface GenericService<T> {

    /*get*/
    List<T> getAll();

    /*save*/
    T save(T entity);

    /*add*/

    /*delete*/

}
