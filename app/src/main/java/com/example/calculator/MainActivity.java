package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
//Comment for second commit
//Import this library for calculating expression
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    int[] numberButtons = {R.id.buttonZero, R.id.buttonOne, R.id.buttonTwo, R.id.buttonThree, R.id.buttonFour, R.id.buttonFive, R.id.buttonSix, R.id.buttonSeven, R.id.buttonEight, R.id.buttonNine};
    int[] operatorButtons = {R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMutip, R.id.buttonDivide};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MyActivity","OnCreate");
        setContentView(R.layout.activity_main);

        tvResult = (TextView)findViewById(R.id.tvResult);
        tvResult.setText("");
        //Using these function to start recording the button press and put it on screen
        setNumberClick();
        setOperatorClick();
    }


    @Override
    public void onClick(View view) {
        String myButtonTag = (String)view.getTag();
        Log.d("MainActivity","Button" + myButtonTag +" Clicked");
        double operand1 = 0.0;
        double operand2 =0.0;
        double result = 0.0;
        switch (myButtonTag){
            case "opPlus":
                tvResult.setText(String.valueOf("0"));
                setNumberClick();
                operand2 = Double.parseDouble(tvResult.getText().toString());
                result = operand1 + operand2;
                break;
            case "opMinus":
                result = 0 ;
                break;
            default:
                Log.e("Main Activity","Unknown Button Click");
        }
        tvResult.setText(String.valueOf(result));
    }
    //Record on screen when number button are clicked
    private void setNumberClick(){

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button btn =(Button) v;
                tvResult.append(btn.getText());
            }
        };
        for(int id : numberButtons){
            findViewById(id).setOnClickListener(listener);
        }
    }
    // When operators button pressed, Record it to the text view, except result button.
    public void setOperatorClick(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                tvResult.append(btn.getText());

            }

        };
        for(int id : operatorButtons){
            findViewById(id).setOnClickListener(listener);
        }
        // When decimal button pressed, add "."
        findViewById(R.id.buttonDecimal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    tvResult.append(".");

                }

        });
        // When Delete button pressed, delete the last character from the text on screen using String method
        findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = tvResult.getText().toString();
                if(str == "")
                    tvResult.setText("");
                else{
                    String newTextView= str.substring(0, str.length()-1);
                    tvResult.setText(newTextView);
                }
            }
        });
        // When Negative button pressed, add -
        findViewById(R.id.buttonNegative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.append("-");
            }
        });
        // When Clear button pressed, set the text view to null
        findViewById(R.id.buttonC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("");
            }
        });
        // When Result button pressed, calculating result by pass on the string on screen to a function
        //that evaluate expression from a math library.
        findViewById(R.id.buttonResult).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = tvResult.getText().toString();
                double result=0.0;
                if(str == "")
                    tvResult.setText("");
                else {
                    Expression exp;
                    exp = new ExpressionBuilder(str).build();
                    result = exp.evaluate();
                    double newresult = rounding(result,5);
                    tvResult.setText(Double.toString(newresult));
                }
            }
        });
    }
    private double rounding(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MyActivity","OnStart");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MyActivity","OnResume");
    }

    public void onButtonClick(View v){
        Log.d( "MainActivity", "Button Clicked");
    }



}