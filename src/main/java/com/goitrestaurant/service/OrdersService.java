package com.goitrestaurant.service;

import com.goitrestaurant.dao.OrderDao;
import com.goitrestaurant.model.Desk;
import com.goitrestaurant.model.Employee;
import com.goitrestaurant.model.Orders;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public class OrdersService {

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    public void create(Orders orders) {
        orderDao.create(orders);
    }

    @Transactional
    public Orders findById(int id) {
        return orderDao.findById(id);
    }

    @Transactional
    public List<Orders> getAll() {
        return orderDao.getAll();
    }

    @Transactional
    public void delete(int id) {
        orderDao.delete(id);
    }

    @Transactional
    public void updateOrderWaiter(int id, Employee newWaiter) {
        orderDao.updateOrderWaiter(id, newWaiter);
    }

    @Transactional
    public void updateOrderDesk(int id, Desk newDesk) {
        orderDao.updateOrderDesk(id, newDesk);
    }

    @Transactional
    public void updateOrderDate(int id, Date newOrderDate) {
        orderDao.updateOrderDate(id, newOrderDate);
    }

    /*@Transactional
    public void updateOrderDishes(int id, List<Dish> newDishes) {
        orderDao.updateOrderDishes(id, newDishes);
    }*/
}
