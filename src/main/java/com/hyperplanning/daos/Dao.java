package com.hyperplanning.daos;

import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

interface Dao<E> extends AutoCloseable{

    Optional<E> find(int id) throws DataAccessException;

    List<E> findAll() throws DataAccessException;

    E persist(E e) throws NotFoundException, DataAccessException;

    default List<E> persist(List<E> list) throws NotFoundException, DataAccessException {
        List<E> resultList = new ArrayList<>();
        for (E e : list)
            resultList.add(persist(e));
        return resultList;
    }

    void update(E e) throws DataAccessException;

    void remove(int id) throws DataAccessException;

    default void remove(E e) throws DataAccessException {
    }

}
