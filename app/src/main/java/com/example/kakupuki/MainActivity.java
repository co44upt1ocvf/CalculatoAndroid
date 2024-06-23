package com.example.kakupuki;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView formula, finalResult;
    private Button one, two, three, four, five, six, seven, eight, nine, zero,
            plus, dif, divide, multiply, result,
            root, square, percent, clear;
    private char Action;
    private double first = Double.NaN;
    private double second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                formula.setText(formula.getText().toString() + button.getText().toString());
            }
        };
        one.setOnClickListener(numberClickListener);
        two.setOnClickListener(numberClickListener);
        three.setOnClickListener(numberClickListener);
        four.setOnClickListener(numberClickListener);
        five.setOnClickListener(numberClickListener);
        six.setOnClickListener(numberClickListener);
        seven.setOnClickListener(numberClickListener);
        eight.setOnClickListener(numberClickListener);
        nine.setOnClickListener(numberClickListener);
        zero.setOnClickListener(numberClickListener);

        View.OnClickListener actionClickListener = view -> {
            Button button = (Button) view;
            calc();
            Action = button.getText().charAt(0);
            formula.setText(String.valueOf(first) + Action);
            finalResult.setText(null);
        };
        plus.setOnClickListener(actionClickListener);
        divide.setOnClickListener(actionClickListener);
        dif.setOnClickListener(actionClickListener);
        multiply.setOnClickListener(actionClickListener);

        result.setOnClickListener(view -> {
            calc();
            Action = '=';
            finalResult.setText(String.valueOf(first));
            formula.setText(null);
        });
        root.setOnClickListener(view -> {
            if (!formula.getText().toString().isEmpty()) {
                double number = Double.parseDouble(formula.getText().toString());
                first = Math.sqrt(number);
                finalResult.setText(String.valueOf(first));
                formula.setText("");
            }
        });

        square.setOnClickListener(view -> {
            if (!formula.getText().toString().isEmpty()) {
                double number = Double.parseDouble(formula.getText().toString());
                first = Math.pow(number, 2);
                finalResult.setText(String.valueOf(first));
                formula.setText("");
            }
        });

        percent.setOnClickListener(view -> {
            if (!formula.getText().toString().isEmpty()) {
                double number = Double.parseDouble(formula.getText().toString());
                first = number / 100;
                finalResult.setText(String.valueOf(first));
                formula.setText("");
            }
        });

        clear.setOnClickListener(view -> {
            formula.setText("");
            finalResult.setText("0");
            first = Double.NaN;
        });
    }
    private void init() {
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);

        dif = (Button) findViewById(R.id.dif);
        divide = (Button) findViewById(R.id.div);
        plus = (Button) findViewById(R.id.plus);
        multiply = (Button) findViewById(R.id.multiply);
        result = (Button) findViewById(R.id.result);

        root = (Button) findViewById(R.id.root);
        square = (Button) findViewById(R.id.square);
        percent = (Button) findViewById(R.id.percent);
        clear = (Button) findViewById(R.id.clear);

        formula = (TextView) findViewById(R.id.formula);
        finalResult = (TextView) findViewById(R.id.finalResult);
    }

    private void calc() {
        if (!Double.isNaN(first)) {

            String textFormula = formula.getText().toString();
            int idx = textFormula.indexOf(Action);

            if (idx != -1) {
                String numberValue = textFormula.substring(idx + 1);
                second = Double.parseDouble(numberValue);

                switch (Action) {
                    case '/':
                        if (second == 0){
                            first = 0.0;
                        }
                        else {
                            first /= second;
                        }
                        break;

                    case '*':
                        first *= second;
                        break;

                    case '+':
                        first += second;
                        break;

                    case '-':
                        first -= second;
                        break;

                    case '=':
                        first = second;
                        break;
                }
            }
        }
        else {
            first = Double.parseDouble(formula.getText().toString());
        }
        finalResult.setText(String.valueOf(first));
        formula.setText("");
    }
}