package Handyman;

public class Posts {
    private int post_id,h_id;
    private double price;
    private String category,name,post_date,h_name,image_url,description;

    public Posts(int post_id, int h_id, double price, String category, String name, String post_date, String h_name) {
        this.post_id = post_id;
        this.h_id = h_id;
        this.price = price;
        this.category = category;
        this.name = name;
        this.post_date = post_date;
        this.h_name = h_name;
    }

    public Posts(int post_id, int h_id, double price, String category, String name, String post_date, String h_name, String image_url,String description) {
        this.post_id = post_id;
        this.h_id = h_id;
        this.price = price;
        this.category = category;
        this.name = name;
        this.post_date = post_date;
        this.h_name = h_name;
        this.image_url = image_url;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getH_id() {
        return h_id;
    }

    public void setH_id(int h_id) {
        this.h_id = h_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }
}
