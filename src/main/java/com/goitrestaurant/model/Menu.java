package com.goitrestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "menuTitle")
    private String menuTitle;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "menus_to_dishes",
            joinColumns = @JoinColumn(name = "menuId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "dishId", referencedColumnName = "id")
    )
    private List<Dish> dishesInMenu = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public List<Dish> getDishesInMenu() {
        return dishesInMenu;
    }

    public void setDishesInMenu(List<Dish> dishesInMenu) {
        this.dishesInMenu = dishesInMenu;
    }

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuTitle='" + menuTitle + '\'' +
                ", dishesInMenu=" + dishesInMenu +
                '}';
    }
}
