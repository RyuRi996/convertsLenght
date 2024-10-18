package com.example.convertslenght;

import static com.example.convertslenght.LengthConverter.convertLength;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {

    EditText inputNumber, outputNumber;
    MaterialButton convert, reverse;
    AutoCompleteTextView inputUnit ,outputUnit;

    String fromUnit, toUnit, inputText, outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropList();
        getDataValue();

        convert = findViewById(R.id.convert);
        reverse = findViewById(R.id.reverse);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertValue();
            }
        });

        reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               reverseResult();
            }
        });
    }

    private void dropList() {
        String[] lengthUnits = getResources().getStringArray(R.array.length_units);
        inputUnit = findViewById(R.id.inputType);
        outputUnit = findViewById(R.id.outputType);
        ArrayAdapter<String> selectLength = new ArrayAdapter<>(this ,R.layout.dropdown_list, lengthUnits);
        inputUnit.setAdapter(selectLength);
        outputUnit.setAdapter(selectLength);
    }

    private void getDataValue() {
        inputNumber = findViewById(R.id.inputValue);
        outputNumber = findViewById(R.id.outputValue);

        fromUnit = inputUnit.getText().toString();
        toUnit = outputUnit.getText().toString();

        inputText = inputNumber.getText().toString();
        inputNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    // Perform your action here
                    convert.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    private void convertValue(){
        getDataValue();
        if (fromUnit.isEmpty()){
            inputUnit.showDropDown();
        } else if (toUnit.isEmpty()) {
            outputUnit.showDropDown();
        } else if (inputText.isEmpty()){
            inputNumber.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(inputNumber, InputMethodManager.SHOW_IMPLICIT);
        }else {
            double value = Double.parseDouble(inputText);
            double convertLength = convertLength(value,fromUnit, toUnit);
            outputText = String.format("%.9f", convertLength).replaceAll("0*$", "");

            if (outputText.endsWith(".")){
                outputText = outputText.replace(".", "");
            }

            outputNumber.setText(outputText);
        }

    }

    private void reverseResult(){
        getDataValue();
        if (fromUnit.isEmpty()){
            inputUnit.showDropDown();
        } else if (toUnit.isEmpty()) {
            outputUnit.showDropDown();
        } else {
            inputUnit.setText(toUnit);
            outputUnit.setText(fromUnit);
            dropList();
        }
    }
}