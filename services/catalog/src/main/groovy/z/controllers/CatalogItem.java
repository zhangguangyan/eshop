package z.controllers;

import java.math.BigDecimal;

public class CatalogItem {
    private int id;
    private String description;
    private String name;
    private String pictureUrl;
    private BigDecimal price;
    private int available_stock;
    private int max_stock_threshold;
    private boolean on_reorder;
    private int restock_threshold;
    private int brand_id;
    private int type_id;

    public CatalogItem(int id, String description, String name, String pictureUrl, BigDecimal price, int available_stock, int max_stock_threshold, boolean on_reorder, int restock_threshold, int brand_id, int type_id) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.price = price;
        this.available_stock = available_stock;
        this.max_stock_threshold = max_stock_threshold;
        this.on_reorder = on_reorder;
        this.restock_threshold = restock_threshold;
        this.brand_id = brand_id;
        this.type_id = type_id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAvailable_stock() {
        return available_stock;
    }

    public int getMax_stock_threshold() {
        return max_stock_threshold;
    }

    public boolean isOn_reorder() {
        return on_reorder;
    }

    public int getRestock_threshold() {
        return restock_threshold;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public int getType_id() {
        return type_id;
    }
}
