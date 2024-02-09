package edu.harrisburgu.jasonB75.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //num1
    //num2
    //has decimal

    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_plus;
    private Button button_minus;
    private Button button_multiply;
    private Button button_divide;
    private Button button_equals;
    private Button button_del;
    private Button button_dot;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_0 = (Button) findViewById(R.id.button_0);
        button_1 = (Button) findViewById(R.id.button_1);;
        button_2 = (Button) findViewById(R.id.button_2);;
        button_3 = (Button) findViewById(R.id.button_3);;
        button_4 = (Button) findViewById(R.id.button_4);;
        button_5 = (Button) findViewById(R.id.button_5);;
        button_6 = (Button) findViewById(R.id.button_6);;
        button_7 = (Button) findViewById(R.id.button_7);;
        button_8 = (Button) findViewById(R.id.button_8);;
        button_9 = (Button) findViewById(R.id.button_9);;
        button_dot = (Button) findViewById(R.id.button_dot);;
        button_plus = (Button) findViewById(R.id.button_plus);;
        button_minus = (Button) findViewById(R.id.button_minus);;
        button_multiply = (Button) findViewById(R.id.button_multiply);;
        button_divide = (Button) findViewById(R.id.button_divide);;
        button_equals = (Button) findViewById(R.id.button_equal);;
        button_del = (Button) findViewById(R.id.button_del);;


        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("0");
            }
        });
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("1");
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("2");
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("3");
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("4");
            }
        });
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("5");
            }
        });
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("6");
            }
        });
        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("7");
            }
        });
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("8");
            }
        });
        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("9");
            }
        });
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("0");
            }
        });
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("0");
            }
        });
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("0");
            }
        });
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("0");
            }
        });
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("0");
            }
        });
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("0");
            }
        });
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("0");
            }
        });



    }

    private void  proccess_number_button_click(String input){

    }
    private void proccess_op_button_click(String input){


    }
}