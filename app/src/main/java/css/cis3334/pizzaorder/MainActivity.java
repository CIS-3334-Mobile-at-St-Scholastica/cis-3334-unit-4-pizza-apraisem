package css.cis3334.pizzaorder;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements updateViewInterface {

    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    TextView txtPizzasOrdered;
    Spinner spinnerToppings;
    PizzaOrderInterface pizzaOrderSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create an pizza order system to use in the class for ordering pizzas.
        pizzaOrderSystem = new PizzaOrder(this);
        // Set up our radio buttons
        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);
        // review the lines below during the particpation activy and uncomment them
        rbSmall.append(" -- Price: $" + pizzaOrderSystem.getPrice(Pizza.pizzaSize.SMALL));
        rbMedium.append(" -- Price: $" + pizzaOrderSystem.getPrice(Pizza.pizzaSize.MEDIUM));
        rbLarge.append(" -- Price: $" + pizzaOrderSystem.getPrice(Pizza.pizzaSize.LARGE));

        // Set up the Check Boxes
        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        // Set up the TextViews
        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);
        txtPizzasOrdered = (TextView) findViewById(R.id.textViewPizzasOrdered);
        // Set up the Spinner
        spinnerToppings = (Spinner) findViewById(R.id.spinnerToppings);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateOrderStatusInView(String orderStatus) {

        txtStatus.setText("Order Status: " + orderStatus);
    }

    public void onClickOrder(View view) {
        // ****** Students need to add code here to get information from the UI widgets...

        if (chkbxDelivery.isChecked()) {
            chkbxDelivery = pizzaOrderSystem.setDelivery(); //I'm unsure what I'm supposed to do with the error here.
        }
        String pizzaSize = ""; //initiallizing string pizzasize to be set to the radiobutton that is selected.
        if (rbSmall.isChecked()){
            pizzaSize = "SMALL"; //sets to size when selected
        }
        else if (rbMedium.isChecked()){
            pizzaSize = "MEDIUM";
        }
        else if (rbLarge.isChecked()){
            pizzaSize = "LARGE";
        }

        // ****** Students need to modify the call to OrderPizza to order the type of pizza the user selects using the UI widgets
        String orderDescription = pizzaOrderSystem.OrderPizza(spinnerToppings.getSelectedItem().toString(), pizzaSize ,chkbxCheese.isChecked()); // appears to pull in toppings, pizza size, and whether extra cheese is wanted


        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+orderDescription , Toast.LENGTH_LONG).show();
        // get the order total from the order system
        txtTotal.setText("Total Due: " + pizzaOrderSystem.getTotalBill().toString());
        // add this pizza to the textview the lists the pizzas
        txtPizzasOrdered.append(orderDescription+"\n");

    }
}
