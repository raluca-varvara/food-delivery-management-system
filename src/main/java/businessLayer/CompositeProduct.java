package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {

    private List<MenuItem> products = new ArrayList<MenuItem>();

    public CompositeProduct(String title, double rating, double calories, double protein, double fat, double sodium, double price,List<MenuItem> products) {
        super(title, rating, calories, protein, fat, sodium, price);
        this.products = products;
    }

    public CompositeProduct(String title,double price, List<MenuItem> products) {
        this.setTitle(title);
        this.setPrice(price);
        this.products = products;
        double rating=0;
        double calories=0;
        double protein=0;
        double fat=0;
        for(MenuItem p: products){
            rating+=p.getRating();
            calories+=p.getCalories();
            protein+=p.getProtein();
            fat+=p.getFat();
        }
        rating/=products.size();
        this.setCalories(calories);
        this.setProtein(protein);
        this.setFat(fat);
        this.setRating(rating);
    }

    public List<MenuItem> getProducts() {
        return products;
    }

    @Override
    public double computePrice() {
        double price = 0;
        for(MenuItem p: products){
            price+=p.computePrice();
        }
        return price;
    }
}
