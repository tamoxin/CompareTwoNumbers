package me.marcocarrizales.comparetwonumbers;

import android.content.Context;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText number1ET, number2ET;
    Button clear, calc;
    TextView error, comp, add, sub, product, division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set view content to activity
        number1ET = (EditText) findViewById(R.id.number1);
        number2ET = (EditText) findViewById(R.id.number2);
        clear = (Button) findViewById(R.id.clear);
        calc = (Button) findViewById(R.id.calculate);
        error = (TextView) findViewById(R.id.error);
        comp = (TextView) findViewById(R.id.comparison);
        add = (TextView) findViewById(R.id.addition);
        sub = (TextView) findViewById(R.id.subtraction);
        product = (TextView) findViewById(R.id.product);
        division = (TextView) findViewById(R.id.division);

        //Hide stuff
        clear.setVisibility(View.INVISIBLE);
        error.setVisibility(View.INVISIBLE);
    }

    protected void onClickCalc(View view) {
        double num1, num2;

        // Hide the keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(number1ET.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(number2ET.getWindowToken(), 0);

        // Lel
        error.setVisibility(View.INVISIBLE);

        // If the EditText is empty, it will not continue (look at the return)
        if(number1ET.getText().toString().matches("") || number2ET.getText().toString().matches("")) {
            error.setVisibility(View.VISIBLE);
            return;
        }

        // Parse the text from EditText to double
        num1 = Double.parseDouble(number1ET.getText().toString());
        num2 = Double.parseDouble(number2ET.getText().toString());

        // If the second number is zero, it will not continue
        if (num2 == 0) {
            error.setVisibility(View.VISIBLE);
            return;
        }

        setBigger(num1, num2);
        doCalculations(num1, num2);
        disableCalculations();

    }

    protected void setBigger(double a, double b) {
        String greatest = getString(R.string.greatest);
        String equivalent = getString(R.string.equivalent);
        double bigger;

        // If a equals b throws the equivalent message and returns
        // otherwise get the biggest and throws the message.
        if (a == b) {
            comp.setText(equivalent);
            return;
        }
        else if (a > b)
            bigger = a;
        else bigger = b;

        comp.setText(greatest + bigger);
    }

    protected void doCalculations(double a, double b) {
        // Addition
        add.setText(getString(R.string.addition) + (a + b));
        // Subtraction
        sub.setText(getString(R.string.subtraction) + (a - b));
        // Product
        product.setText(getString(R.string.product) + (a * b));
        // Division
        division.setText(getString(R.string.division) + (a / b));
    }

    protected void disableCalculations() {
        number1ET.setFocusable(false);
        number2ET.setFocusable(false);
        calc.setEnabled(false);
        clear.setVisibility(View.VISIBLE);
    }

    protected void enableCalculations() {
        number1ET.setFocusableInTouchMode(true);
        number2ET.setFocusableInTouchMode(true);
        calc.setEnabled(true);
        clear.setVisibility(View.INVISIBLE);
    }

    protected void onClickClear(View view) {
        enableCalculations();
        number1ET.setText("");
        number2ET.setText("");
        comp.setText(getString(R.string.comparison));
        add.setText(getString(R.string.addition));
        sub.setText(getString(R.string.subtraction));
        product.setText(getString(R.string.product));
        division.setText(getString(R.string.division));
    }
}
