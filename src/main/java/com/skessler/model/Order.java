package com.skessler.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by skessler on 3/2/15.
 */
@Entity
public class Order {
    private int id;
    private String orderdata;
    private Byte orderprocessed;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "orderdata")
    public String getOrderdata() {
        return orderdata;
    }

    public void setOrderdata(String orderdata) {
        this.orderdata = orderdata;
    }

    @Basic
    @Column(name = "orderprocessed")
    public Byte getOrderprocessed() {
        return orderprocessed;
    }

    public void setOrderprocessed(Byte orderprocessed) {
        this.orderprocessed = orderprocessed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (orderdata != null ? !orderdata.equals(order.orderdata) : order.orderdata != null) return false;
        if (orderprocessed != null ? !orderprocessed.equals(order.orderprocessed) : order.orderprocessed != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderdata != null ? orderdata.hashCode() : 0);
        result = 31 * result + (orderprocessed != null ? orderprocessed.hashCode() : 0);
        return result;
    }
}
