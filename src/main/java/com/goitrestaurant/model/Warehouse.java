package com.goitrestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "warehouse")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Warehouse implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredientId")
    private Ingredient ingredient;

    @Column(name = "amount")
    private float amount;

    public Warehouse() {
    }

    public Warehouse(Ingredient ingredient, float amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", ingredient=" + ingredient +
                ", amount=" + amount +
                '}';
    }

}
