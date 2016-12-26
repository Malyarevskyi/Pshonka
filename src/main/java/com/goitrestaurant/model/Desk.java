package com.goitrestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "desks")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Desk implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "deskTitle")
    private String deskTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusOfDesk")
    private DeskStatus deskStatus;

    public Desk() {
    }

    public Desk(String deskTitle) {
        this.deskTitle = deskTitle;
        this.deskStatus = DeskStatus.FREE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeskTitle() {
        return deskTitle;
    }

    public void setDeskTitle(String deskTitle) {
        this.deskTitle = deskTitle;
    }

    public DeskStatus getDeskStatus() {
        return deskStatus;
    }

    public void setDeskStatus(DeskStatus deskStatus) {
        this.deskStatus = deskStatus;
    }

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "Desk{" +
                "id=" + id +
                ", deskTitle='" + deskTitle + '\'' +
                ", deskStatus=" + deskStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Desk desk = (Desk) o;

        if (deskTitle != null ? !deskTitle.equals(desk.deskTitle) : desk.deskTitle != null) return false;
        return deskStatus == desk.deskStatus;

    }

    @Override
    public int hashCode() {
        int result = deskTitle != null ? deskTitle.hashCode() : 0;
        result = 31 * result + (deskStatus != null ? deskStatus.hashCode() : 0);
        return result;
    }
}
