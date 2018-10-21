package z.controllers;

public class CatalogItem {
    private String name;

    public CatalogItem() {
    }

    public CatalogItem(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
