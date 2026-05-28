package org.seleLv2.data;

public class ProductInfo {
    private String productName;
    private String price;
    private String quantity;

    public ProductInfo(String productName, String price, String quantity) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity(){return quantity;}

    public void setProductName() {
        this.productName = productName;
    }

    public void setPrice() {
        this.price = price;
    }

    public void setQuantity(){this.quantity = quantity;}

}

