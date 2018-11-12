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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

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

        tempResult = 0;
        calc = new TemperatureTransform();

        convertBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick();
            }
        });

        fromGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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

        toGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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

    private void handleClick() {
        if (inputText.getText().equals("")) {
            Toast.makeText(CalculatorActivity.this, "You need to input a value to convert.",
                    Toast.LENGTH_SHORT).show();
        }

        try {
            double tempValue = Double.parseDouble(String.valueOf(inputText.getText()));
            convertAndSend(tempValue);
        } catch (Exception e) {
            Toast.makeText(CalculatorActivity.this, "You need to input a numeric value to convert.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void convertAndSend(double tempValue) {
        String unit = "";
        int selectedFromId = fromGroup.getCheckedRadioButtonId();
        int selectedToId = toGroup.getCheckedRadioButtonId();
        if (selectedFromId == fromCelsius.getId() && selectedToId == toFarenheit.getId()) {
            tempResult = calc.celsiusToFarehnheit(tempValue);
            unit = calc.FARENHEIT_UNIT;
        } else if (selectedFromId == fromCelsius.getId() && selectedToId == toKelvin.getId()) {
            tempResult = calc.celsiusToKelvin(tempValue);
            unit = calc.KELVIN_UNIT;
        } else if (selectedFromId == fromKelvin.getId() && selectedToId == toFarenheit.getId()) {
            tempResult = calc.kelvinToFarenheit(tempValue);
            unit =  calc.FARENHEIT_UNIT;
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
            Toast.makeText(CalculatorActivity.this, "Unable to calculate the conversion.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra("result", tempResult);
        i.putExtra("unit", unit);
        startActivity(i);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // TODO
    }
}
