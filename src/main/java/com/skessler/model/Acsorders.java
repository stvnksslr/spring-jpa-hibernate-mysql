package com.skessler.model;

import java.sql.Timestamp;

/**
 * Created by skessler on 3/12/15.
 */
public class Acsorders {
    private int id;
    private String orderdata;
    private byte processed;
    private Timestamp dateProcessed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderdata() {
        return orderdata;
    }

    public void setOrderdata(String orderdata) {
        this.orderdata = orderdata;
    }

    public byte getProcessed() {
        return processed;
    }

    public void setProcessed(byte processed) {
        this.processed = processed;
    }

    public Timestamp getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(Timestamp dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Acsorders acsorders = (Acsorders) o;

        if (id != acsorders.id) return false;
        if (processed != acsorders.processed) return false;
        if (dateProcessed != null ? !dateProcessed.equals(acsorders.dateProcessed) : acsorders.dateProcessed != null)
            return false;
        if (orderdata != null ? !orderdata.equals(acsorders.orderdata) : acsorders.orderdata != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderdata != null ? orderdata.hashCode() : 0);
        result = 31 * result + (int) processed;
        result = 31 * result + (dateProcessed != null ? dateProcessed.hashCode() : 0);
        return result;
    }
}
