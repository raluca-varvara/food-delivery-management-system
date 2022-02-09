package businessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {

    public BaseProduct(String title, double rating, double calories, double protein, double fat, double sodium, double price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    public BaseProduct(String[] s){
        super(s[0], Double.parseDouble(s[1]), Double.parseDouble(s[2]), Double.parseDouble(s[3]), Double.parseDouble(s[4]), Double.parseDouble(s[5]), Double.parseDouble(s[6]));
    }

    @Override
    public double computePrice() {
        return getPrice();
    }
}
