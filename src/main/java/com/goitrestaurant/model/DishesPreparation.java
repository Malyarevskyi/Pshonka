package com.goitrestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "dishes_preparation")
public class DishesPreparation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "dishId")
    private Dish dish;

    @OneToOne
    @JoinColumn(name ="cookId")
    private Employee cook;

    @Column(name = "datePreparation")
    private Date datePreparation;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Employee getCook() {
        return cook;
    }

    public void setCook(Employee cook) {
        this.cook = cook;
    }

    public Date getDatePreparation() {
        return datePreparation;
    }

    public void setDatePreparation(Date datePreparation) {
        this.datePreparation = datePreparation;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "DishesPreparation{" +
                "id=" + id +
                ", dish=" + dish +
                ", cook=" + cook +
                ", datePreparation=" + datePreparation +
                ", order=" + order +
                '}';
    }
}
