package model.Local;

public class Product {
    private static int products_id=0;
    private int id;
    private String name;
    private double price;

    public Product(String name, int price) {
        products_id+=1;
        this.id = products_id;
        this.name = name;
        this.price = price;
    }
    @Override
    public boolean equals(Object o){
        if ( o == this){
            return true;
        }
        if (!(o instanceof Product)){
            return false;
        }
        Product p = (Product) o;
        return (this.id == p.id && this.name==p.name && this.price==p.price);
    }

    @Override
    public String toString(){
        return ("Product ID: " + this.id + " - " +this.name+" - "+this.price+"$");
    }

    public static int getProducts_id() {
        return products_id;
    }

    public static void setProducts_id(int products_id) {
        Product.products_id = products_id;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
