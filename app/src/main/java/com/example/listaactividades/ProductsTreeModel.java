package com.example.listaactividades;

class ProductsTreeModel {
    private int id;
    private String category;
    private String name;
    private String descr;
    private double price;
    private int father;
    private int image_drawable;

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getDescr() {
        return descr;
    }
    public String getCategory() {
        return category;
    }
    public int getId() {
        return id;
    }
    public int getFather() {
        return father;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setId(int id) {
        this.id = id   ;
    }
    public void setFather(int   father) {
        this.father = father;
    }

    public int getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(int image_drawable) {
        this.image_drawable = image_drawable;
    }
}
