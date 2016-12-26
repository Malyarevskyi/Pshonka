package com.goitrestaurant.dao;

import com.goitrestaurant.model.Desk;
import com.goitrestaurant.model.DeskStatus;

import java.util.List;

public interface DeskDao extends SimpleDao<Desk> {

    List<Desk> getAllFreeDesk();

    void updateStatus(int id, DeskStatus deskStatus);

}
