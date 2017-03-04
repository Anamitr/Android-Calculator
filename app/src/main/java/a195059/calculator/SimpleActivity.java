package a195059.calculator;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
//import android.widget.GridLayout.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

//import static a195059.calculator.R.id.simpleGrid;
import bsh.Interpreter;

import static android.R.attr.data;
import static android.R.attr.text;
import static android.R.attr.x;

public class SimpleActivity extends AppCompatActivity {
    public static final int TEXT_VIEW_SIZE = 200;
    public static final int FONT_SIZE = 60;
    public static final int BUTTON_ROWS = 5;
    public static final int BUTTON_COLUMNS = 4;
    public static final int NUM_OF_BUTTONS = BUTTON_ROWS * BUTTON_COLUMNS;

    private TextView calTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getTableWithAllRowsStretchedView());
    }

    public LinearLayout getTableWithAllRowsStretchedView() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.FILL_PARENT));
        tableLayout.setWeightSum(BUTTON_ROWS);
        Button[] buttons = new Button[NUM_OF_BUTTONS];

        for (int i = 0; i < BUTTON_ROWS; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setGravity(Gravity.CENTER);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT, 1.0f));
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.FILL_PARENT, 1.0f));

            for (int j = 0; j < BUTTON_COLUMNS; j++) {
                final int buttonNumber = (j + i * BUTTON_COLUMNS);
                buttons[buttonNumber] = new Button(this);
                buttons[buttonNumber].setText("" + buttonNumber);
                buttons[buttonNumber].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));

                tableRow.addView(buttons[buttonNumber]);
            }
            tableLayout.addView(tableRow);
        }

        setButtonsText(buttons);
        setButtonsListeners(buttons);
        linearLayout.addView(calTextView = getCalculatorTextView());
        linearLayout.addView(tableLayout);
        linearLayout.setBackgroundColor(Color.parseColor("#0099cc"));
        return linearLayout;
    }

    private TextView getCalculatorTextView() {
        TextView textView = new TextView(getApplicationContext());
        textView.setText("0");
        textView.setHeight(TEXT_VIEW_SIZE);
        textView.setBackgroundColor(Color.parseColor("#0099cc"));
        textView.setTextSize(FONT_SIZE);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setGravity(Gravity.CENTER | Gravity.RIGHT);
        Interpreter ip = new Interpreter();
        try {
            ip.eval("result = " + "-(-2)");
            textView.setText(ip.get("result").toString());
        } catch (Exception e) {
            Log.e("Calculator", "Exception: " + e.getMessage());
        }
        return textView;
    }

    private void setButtonsText(Button[] buttons) {
        String [] labels = {"CE","C","<-","/","7","8","9","*","4","5","6","-","1","2","3","+","+-","0",",","=",};
        for(int i = 0; i < NUM_OF_BUTTONS; i++) {
            buttons[i].setText(labels[i]);
        }
    }

    private void setButtonsListeners (Button[] buttons) {
        for(int i = 0; i < 2; i++) {
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calTextView.setText("0");
                }
            });
        }

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String equation = calTextView.getText().toString();
                calTextView.setText(equation.substring(0,equation.length()-1));
            }
        });
        final String [] labels = {"/","7","8","9","*","4","5","6","-","1","2","3","+","+-","0",".","=",};
        for(int i = 3; i < 19; i++) {
            if(i == 16) continue;
            final String label = labels[i - 3];
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(calTextView.getText().equals("0")) calTextView.setText(label);
                    else calTextView.append(label);
                }
            });
        }
        buttons[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
        buttons[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                negateResult();
            }
        });
    }

    private void calculate() {
        Interpreter ip = new Interpreter();
        try {
            Log.d("Before inserting: ", calTextView.getText().toString());
            Log.d("Equation: ", insertDotsZeros(calTextView.getText().toString()));
            ip.eval("result = " + insertDotsZeros(calTextView.getText().toString()));
            calTextView.setText(ip.get("result").toString());
        } catch (Exception e) {
            Log.e("Calculator", "Exception: " + e.getMessage());
        }
    }

    private static String insertDotsZeros(String equation) {
        String[] numbers = equation.split("[-/+\\*]");
        String operators = equation.replaceAll("[0123456789.]", "");
        for(int i = 0; i < numbers.length; i++) {
            if(!numbers[i].contains(".")) numbers[i] += ".0";
            //System.out.println(numbers[i]);
        }
        equation = "";
        for(int i = 0; i < numbers.length-1; i++) {
            equation += numbers[i];
            equation += operators.charAt(i);
        }
        equation += numbers[numbers.length-1];
        return equation;
    }

    private void negateResult() {
        String result = calTextView.getText().toString();
        if(result.charAt(0)!='-')
            calTextView.setText("-" + calTextView.getText());
    }

}