package com.goitrestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="employeeId")
    private Employee waiter;

    @ManyToOne
    @JoinColumn(name = "deskId")
    private Desk desk;

    @Column(name = "orderDate")
    private Date orderDate;

    /*@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "orders_to_dishes",
            joinColumns = @JoinColumn(name = "orderId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "dishId", referencedColumnName = "id")
    )
    private List<Dish> dishesInOrder;*/

    public Orders() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getWaiter() {
        return waiter;
    }

    public void setWaiter(Employee waiter) {
        this.waiter = waiter;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /*public List<Dish> getDishesInOrder() {
        return dishesInOrder;
    }

    public void setDishesInOrder(List<Dish> dishesInOrder) {
        this.dishesInOrder = dishesInOrder;
    }*/

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", waiter=" + waiter +
                ", desk=" + desk +
                ", orderDate=" + orderDate +
                //", dishesInOrder=" + dishesInOrder +
                '}';
    }
}
