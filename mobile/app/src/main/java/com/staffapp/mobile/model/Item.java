package com.staffapp.mobile.model;

public class Item {
    private Long id;
    private String itemName;
    private String barcode;

    public Item(Long id, String itemName, String barcode) {
        this.id = id;
        this.itemName = itemName;
        this.barcode = barcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
