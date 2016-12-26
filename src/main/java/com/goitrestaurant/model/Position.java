package com.goitrestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "positions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Position implements Serializable{

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "positionTitle")
    private String positionTitle;

    public Position() {
    }

    public Position(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", positionTitle='" + positionTitle + '\'' +
                '}';
    }
}

