package com.goitrestaurant.dao;

import com.goitrestaurant.model.Desk;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Orders;

import java.sql.Date;

public interface OrderDao extends Dao<Orders> {

    void updateOrderWaiter(int id, Employee newWaiter);

    void updateOrderDesk(int id, Desk newDesk);

    void updateOrderDate(int id, Date newOrderDate);

    //void updateOrderDishes(int id, List<Dish> newDishes);

}
