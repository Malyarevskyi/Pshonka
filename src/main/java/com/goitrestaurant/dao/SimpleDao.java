package com.goitrestaurant.dao;

import java.util.List;

public interface SimpleDao<T> extends Dao<T> {

    List<T> findByTitle(String title);

    void updateTitle(int id, String newTitle);

}
