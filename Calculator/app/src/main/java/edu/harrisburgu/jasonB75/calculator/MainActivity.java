package edu.harrisburgu.jasonB75.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //num1 both nums hold the string version of the user inputted numbers
    private String num1 = " ";
    //num2
    private String num2 = " ";
    //has decimal
    private boolean hasDecimal;

    // is op selected
    private boolean isOpSelected; // The boolean that checks to see if an operator is selected.

    private String operationSelected; //the String that holds the operation selected for the experesion


    //button variables
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

    private TextView display_text; // The calculator display text view

    private Button[] op_buttons; // Holds the operator button variables for ease of use in function proccessing






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

        op_buttons = new Button[] {button_plus, button_minus, button_multiply, button_divide}; // Holds the operator button variables for ease of use in function proccessing

        display_text = (TextView) findViewById(R.id.display_text_view); // The calculator display text view

        /// All of the button on click listeners, most if not all direct to applicable functions
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
        button_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click(".");
            }
        });

        //Operator buttons
        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_op_button_click("+");
            }
        });
        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_op_button_click("-");
            }
        });
        button_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_op_button_click("*");
            }
        });
        button_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_op_button_click("/");
            }
        });
        button_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_op_button_click("Equals");
            }
        });

        //On a long click of the equals button clear the calculator
        button_equals.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clear_calc();
                return true;
            }
        });

        ////deleting
        button_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                proccess_number_button_click("del");
            }
        });



    }

    private void clear_calc(){ // called when the calculator is being cleared
        num1 = " "; // Sets both num strings to emtpy states
        num2 = " ";
        isOpSelected = false; // Makes it so an operation is not selected
        display_text.setText("0"); // and resets the display text and operator button color
        change_op_button_color("not an operation so they'll all turn off lol");
    }
    private void display_num(int num){ //Displays the selected number to the text view, num indicates which number to display
        if (num == 1){
            display_text.setText(num1);
        } else {
            display_text.setText(num2);
        }

    }
    private void  proccess_number_button_click(String input){ // Name is explanatory, includes del operation because of existing differentiation between num1 and 2 (and not repeating code)
        if (isOpSelected){ // If an operation has been selected process num2, otherwise num1
            if (input.equals("del")) { // Deletes the last entered char if one exists
                if (num2.length() >0){
                    String temp = num2; // uses temp variable to negate bug
                    num2 = temp.substring(0, temp.length() - 1);}// makes a substring of the num excluding the last char
            } else {
                num2 += input; // concats the input to the end of the string
                if (input.equals(".")){ hasDecimal = true;}
            }
            display_num(2); // Display the new number
        } else {
            if (input.equals("del")) {// Deletes the last entered char if one exists
                if (num1.length() > 0){
                    String temp = num1;// uses temp variable to negate bug
                    num1 = temp.substring(0, temp.length() - 1);} // makes a substring of the num excluding the last char
            } else {
            num1 += input; // concats the input to the end of the string
            if (input.equals(".")){ hasDecimal = true;}
            }
            display_num(1);
        }
    }
    // name is self explanatory, saves operation to variable,
    // if its the equals button it calls that function
    private void proccess_op_button_click(String input){
        if ((input.equals("Equals")) || (isOpSelected && !num2.equals(" "))){
            process_operation();
        } else {
            operationSelected = input;
            isOpSelected = true;
            change_op_button_color(input);
            if (num1 == null){
                num1 = "0";
            }
        }
    }

    //Takes the string input from an operation button and sets it to selected(so the color changes) and unselects the rest
    //op_buttons = {button_plus, button_minus, button_multiply, button_divide}
    private void change_op_button_color(String input){
        String[] operations =  {"+","-","*","/"};

        for (int i = 0; i<4; i++){
            if (input.equals(operations[i])){
                op_buttons[i].setSelected(true);
            } else {
                op_buttons[i].setSelected(false);
            }        }


    }

    // Turns the string numbers to doubles,
    //reads the operator selected and preforms the equation on the converted numbers
    //Then sets the new number to num1 so that the calculations can continue
    private void process_operation(){

        if (!num1.equals(" ") && !num2.equals(" ") && isOpSelected) {
            double final_Number1 = Double.parseDouble(num1);
            double final_Number2 = Double.parseDouble(num2);
            double output = 0;

            if (operationSelected.equals("+")) {
                output = final_Number1 + final_Number2;
            } else if (operationSelected.equals("-")) {
                output = final_Number1 - final_Number2;
            } else if (operationSelected.equals("*")) {
                output = final_Number1 * final_Number2;
            } else if (operationSelected.equals("/")) {
                output = final_Number1 / final_Number2;
            }
            num1 = Double.toString(output);
            num2 = " ";
            display_num(1);
            change_op_button_color("not an operation so they'll all turn off lol");
            }

        }


    }
