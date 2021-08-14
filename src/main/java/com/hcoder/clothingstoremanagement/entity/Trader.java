package com.hcoder.clothingstoremanagement.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trader")
public class Trader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "payed")
    int payed;

    @Column(name = "remaining")
    int remaining;

    @OneToMany(mappedBy = "trader")
    List<TraderPay> traderPayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPayed() {
        return payed;
    }

    public void setPayed(int payed) {
        this.payed = payed;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public List<TraderPay> getTraderPayList() {
        return traderPayList;
    }

    public void setTraderPayList(List<TraderPay> traderPayList) {
        this.traderPayList = traderPayList;
    }
}
