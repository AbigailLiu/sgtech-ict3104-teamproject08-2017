package com.lifestyle.stps.services;

import java.util.List;

/**
 * Created by User 1 on 26/9/2017.
 */
public interface CRUDService<T> {
    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
