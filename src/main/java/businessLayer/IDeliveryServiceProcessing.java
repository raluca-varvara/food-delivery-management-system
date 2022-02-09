package businessLayer;

import java.time.LocalDate;
import java.util.List;

public interface IDeliveryServiceProcessing {

    void importProducts();
    /**
     * @pre newM!= null
     * @param newM modified product
     */
    void modifyProduct(MenuItem newM);
    /**
     * @pre newM!= null
     * @param newM added prod
     */
    void addProduct(MenuItem newM);
    /**
     * @pre title != null
     * @param title deleted product
     */
    void deleteProduct(String title);
    void timeIntervalReport(int hStart, int hStop);
    void popularProductsReport(int nbOfTimes);
    void loyalClientsReport(int nbOfTimes, int value);
    void todaysProducts(LocalDate date);
    List<MenuItem> searchByName(String name);
    List<MenuItem> searchByRating(double rating);
    List<MenuItem> searchByCalories(double calories);
    List<MenuItem> searchByProtein(double protein);
    List<MenuItem> searchByFat(double fat);
    List<MenuItem> searchBySodium(double sodium);
    List<MenuItem> searchByPrice(double price);

}
