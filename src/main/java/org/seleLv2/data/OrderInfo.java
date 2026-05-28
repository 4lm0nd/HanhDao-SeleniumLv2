package org.seleLv2.data;

public class OrderInfo {
    private String orderNumber;
    private String date;
    private String total;

    public OrderInfo (String orderNumber, String date, String total){
        this.orderNumber = orderNumber;
        this.date = date;
        this.total = total;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;

    }
    public void setTotal(String total) {
        this.total = total;
    }
}
