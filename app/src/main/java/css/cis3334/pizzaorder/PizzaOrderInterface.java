package css.cis3334.pizzaorder;

import android.widget.CheckBox;

/**
 * Created by tgibbons on 2/10/2017.
 */
public interface PizzaOrderInterface {

    String OrderPizza(String topping, String strSize, boolean extraCheese);
    Double getTotalBill();
    Double getPrice(Pizza.pizzaSize size);
    Double getExtraCheesePrice();
    void setDelivery(boolean deliver);
    boolean getDelivery();


}
