package com.goitrestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "dishes")
public class Dish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dishTitle")
    private String dishTitle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryID")
    private Category category;

    @Column(name = "price")
    private float price;

    @Column(name = "weight")
    private float weight;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "dishes_to_ingredients",
            joinColumns = @JoinColumn(name = "dishId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId", referencedColumnName = "id")
    )
    private List<Ingredient> ingredients;

    @JsonIgnore
    @ManyToMany(mappedBy = "dishesInMenu", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    /*@JsonIgnore
    @ManyToMany(mappedBy = "dishesInOrder", cascade = CascadeType.ALL)
    private List<Orders> orderses = new ArrayList<>();*/

    @Column(name = "picture")
    @JsonIgnore
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] picture;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDishTitle() {
        return dishTitle;
    }

    public void setDishTitle(String dishTitle) {
        this.dishTitle = dishTitle;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    /*public List<Orders> getOrderses() {
        return orderses;
    }

    public void setOrderses(List<Orders> orderses) {
        this.orderses = orderses;
    }*/

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", dishTitle='" + dishTitle + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", weight=" + weight +
                ", ingredients=" + ingredients +
                ", picture=" + Arrays.toString(picture) +
                ", description=" + description +
                '}';
    }

}
