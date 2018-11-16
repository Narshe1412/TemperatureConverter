package mcolorado.temperatureconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

/**
 * Activity to gather the inputs from the user
 */
public class CalculatorActivity extends AppCompatActivity {

    private Button convertBtn;
    private RadioGroup toGroup;
    private RadioGroup fromGroup;
    private EditText inputText;
    private double tempResult;
    private TemperatureTransform calc;
    private RadioButton fromFarenheit;
    private RadioButton fromCelsius;
    private RadioButton fromKelvin;
    private RadioButton toFarenheit;
    private RadioButton toCelsius;
    private RadioButton toKelvin;

    /**
     * Activity lyfecicle onCreate method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Get the UI elements
        inputText = findViewById(R.id.txtTempInput);
        convertBtn = findViewById(R.id.convertBtn);
        fromGroup = findViewById(R.id.grpOptionsFrom);
        fromFarenheit = findViewById(R.id.radioFarenheitFrom);
        fromCelsius = findViewById(R.id.radioCelsiusFrom);
        fromKelvin = findViewById(R.id.radioKelvinFrom);
        toGroup = findViewById(R.id.grpOptionsTo);
        toFarenheit = findViewById(R.id.radioFarenheitTo);
        toCelsius = findViewById(R.id.radioCelsiusTo);
        toKelvin = findViewById(R.id.radioKelvinTo);

        // Initializes calculation result
        tempResult = 0;
        // Load calculator class
        calc = new TemperatureTransform();

        // Add onClick listener to the Convert button
        convertBtn.setOnClickListener(new OnClickListener() {
            /**
             * Calls the handler method to perform the conversion
             * @param v
             */
            @Override
            public void onClick(View v) {
                handleClick();
            }
        });

        // Add onCheckedChange listener to the radio button to disable mirror elements so a
        // conversion from unit X to unit X cannot happen
        fromGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            /**
             * Disable mirror element when one is clicked. Enable the rest.
             * @param group The group of radio buttons that is affected
             * @param checkedId The id of the element that was clicked
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                toFarenheit.setEnabled(true);
                toCelsius.setEnabled(true);
                toKelvin.setEnabled(true);

                if (checkedId == fromFarenheit.getId()) {
                    toFarenheit.setEnabled(false);
                }
                if (checkedId == fromCelsius.getId()) {
                    toCelsius.setEnabled(false);
                }
                if (checkedId == fromKelvin.getId()) {
                    toKelvin.setEnabled(false);
                }
            }
        });

        // Add onCheckedChange listener to the radio button to disable mirror elements so a
        // conversion from unit X to unit X cannot happen
        toGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            /**
             * Disable mirror element when one is clicked. Enable the rest.
             * @param group The group of radio buttons that is affected
             * @param checkedId The id of the element that was clicked
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fromFarenheit.setEnabled(true);
                fromCelsius.setEnabled(true);
                fromKelvin.setEnabled(true);

                if (checkedId == toFarenheit.getId()) {
                    fromFarenheit.setEnabled(false);
                }
                if (checkedId == toCelsius.getId()) {
                    fromCelsius.setEnabled(false);
                }
                if (checkedId == toKelvin.getId()) {
                    fromKelvin.setEnabled(false);
                }
            }
        });
    }

    /**
     * Handles Convert click button event
     */
    private void handleClick() {
        // Verify the input box is not empty
        if (inputText.getText().equals("")) {
            Toast.makeText(CalculatorActivity.this, "You need to input a value to convert.",
                    Toast.LENGTH_SHORT).show();
        }

        // Verifies the input box is numeric
        try {
            double tempValue = Double.parseDouble(String.valueOf(inputText.getText()));
            convertAndSend(tempValue);
        } catch (Exception e) {
            Toast.makeText(CalculatorActivity.this, "You need to input a numeric value to convert.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Attempts to perform a temperature conversion and load the next activity
     *
     * @param tempValue A Double that stores the temperature value to convert
     */
    private void convertAndSend(double tempValue) {
        String unit = "";
        int selectedFromId = fromGroup.getCheckedRadioButtonId();
        int selectedToId = toGroup.getCheckedRadioButtonId();

        // If--then cases to make sure we call the appropriate method on each conversion
        if (selectedFromId == fromCelsius.getId() && selectedToId == toFarenheit.getId()) {
            tempResult = calc.celsiusToFarehnheit(tempValue);
            unit = calc.FARENHEIT_UNIT;
        } else if (selectedFromId == fromCelsius.getId() && selectedToId == toKelvin.getId()) {
            tempResult = calc.celsiusToKelvin(tempValue);
            unit = calc.KELVIN_UNIT;
        } else if (selectedFromId == fromKelvin.getId() && selectedToId == toFarenheit.getId()) {
            tempResult = calc.kelvinToFarenheit(tempValue);
            unit = calc.FARENHEIT_UNIT;
        } else if (selectedFromId == fromKelvin.getId() && selectedToId == toCelsius.getId()) {
            tempResult = calc.kelvinToCelsius(tempValue);
            unit = calc.CELSIUS_UNIT;
        } else if (selectedFromId == fromFarenheit.getId() && selectedToId == toKelvin.getId()) {
            tempResult = calc.farenheitToKelvin(tempValue);
            unit = calc.KELVIN_UNIT;
        } else if (selectedFromId == fromFarenheit.getId() && selectedToId == toCelsius.getId()) {
            tempResult = calc.farenheitToCelsius(tempValue);
            unit = calc.CELSIUS_UNIT;
        } else {
            // Fallback in case of unknown error when performing the conversion
            Toast.makeText(CalculatorActivity.this, "Unable to calculate the conversion.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Call the new activity to display the result
        Intent i = new Intent(this, ResultActivity.class);
        // Pass the parameters we need in the second activity
        i.putExtra("result", tempResult);
        i.putExtra("unit", unit);
        startActivity(i);
    }

    /**
     * Activity lifeclycle onSaveInstanceState method
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("temperatureInput", inputText.getText()+"");
    }

    /**
     * Activity lifeclycle onRestoreInstanceState method
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        inputText.setText(savedInstanceState.getString("temperatureInput"));
    }
}
