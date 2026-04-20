package org.seleLv2.data;

public class ProductInfo {
    private String productName;
    private String price;


    public ProductInfo(String productName, String price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public void setProductName() {
        this.productName = productName;
    }

    public void setPrice() {
        this.price = price;
    }

}

