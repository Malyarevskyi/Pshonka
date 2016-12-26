package com.goitrestaurant.dao;

import java.util.List;

public interface Dao<T> {

    void create(T item);

    T findById(int id);

    List<T> getAll();

    void delete(int id);

}
