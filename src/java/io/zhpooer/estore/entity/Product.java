package io.zhpooer.estore.entity;

public class Product {

    private int id;
    private String name;
    private String category;
    private int pnum;
    private double marketprice;
    private double estoreprice;
    private String imgurl;
    private String description;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public double getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(double marketprice) {
        this.marketprice = marketprice;
    }

    public double getEstoreprice() {
        return estoreprice;
    }

    public void setEstoreprice(double estoreprice) {
        this.estoreprice = estoreprice;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
