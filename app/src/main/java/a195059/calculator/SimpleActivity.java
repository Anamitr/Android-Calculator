package a195059.calculator;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;

import bsh.Interpreter;

//import android.widget.GridLayout.LayoutParams;
//import static a195059.calculator.R.id.simpleGrid;

public class SimpleActivity extends AppCompatActivity {
    public static final int TEXT_VIEW_SIZE = 200;
    public static final int FONT_SIZE = 60;
    public static final int BUTTON_ROWS = 5;
    public static final int BUTTON_COLUMNS = 4;
    public static final int NUM_OF_BUTTONS = BUTTON_ROWS * BUTTON_COLUMNS;
    public static final int MAX_TEXTVIEW_CHARS = 10;
    public static final String digitRegex = "\\d+";

    private TextView calTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getTableWithAllRowsStretchedView());
        if (savedInstanceState != null){
            updateTextView(savedInstanceState.getString("value"));
        }
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

        setButtonsAppearance(buttons);
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
//        Interpreter ip = new Interpreter();
//        try {
//            ip.eval("result = " + "10.0%4.0");
//            double res = (double)ip.get("result");
//            Log.d("Result:", String.valueOf(res));
//            textView.setText(ip.get("result").toString());
//        } catch (Exception e) {
//            Log.e("Calculator", "Exception: " + e.getMessage());
//        }
        return textView;
    }

    private void setButtonsAppearance(Button[] buttons) {
        String[] labels = {"CE", "C", "<-", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "+-", "0", ",", "=",};
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.parseColor("#2061c9")); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, Color.parseColor("#0099cc"));
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
            buttons[i].setText(labels[i]);
            buttons[i].setTextColor(Color.parseColor("#FFFFFF"));
            buttons[i].setBackgroundDrawable(gd);
        }
    }

    private void setButtonsListeners(Button[] buttons) {
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTextView(StringUtils.deleteLastOp(calTextView.getText().toString()));
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTextView("0");
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String equation = calTextView.getText().toString();
                updateTextView(equation.substring(0, equation.length() - 1));
            }
        });
        final String[] labels = {"/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "+-", "0", ".", "=",};
        for (int i = 3; i < 19; i++) {
            if (i == 16) continue;
            final String label = labels[i - 3];
            if (label == "/" || label == "*" || label == "-" || label == "+")
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("TextView: ", "(" + calTextView.getText().toString() + ")");
//                        Log.d("Equals 0?: ", Boolean.toString(calTextView.getText().toString().trim().equals("0")));
                        String current = calTextView.getText().toString().trim();
                        String lastChar = current.substring(current.length() - 1);
                        if (lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("/") || lastChar.equals("*") ) Log.d("SameChar: ", "Nic nie robimy");
                        else if (calTextView.getText().toString().trim().equals("0"))
                            updateTextView(label);
                        else updateTextView(calTextView.getText() + label);
                    }
                });
            else if (label == ".")
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("TextView: ", "(" + calTextView.getText().toString() + ")");
                        String current = calTextView.getText().toString().trim();
                        String lastChar = current.substring(current.length() - 1);
                        if(lastChar.matches(digitRegex) && !StringUtils.getLastNumber(current).contains(label)) updateTextView(calTextView.getText() + label);
                    }
                });
            else
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("TextView: ", "(" + calTextView.getText().toString() + ")");
                        Log.d("Equals 0?: ", Boolean.toString(calTextView.getText().toString().trim().equals("0")));
                        if (calTextView.getText().toString().trim().equals("0"))
                            updateTextView(label);
                        else updateTextView(calTextView.getText() + label);
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
            Log.d("Equation: ", StringUtils.insertDotsZeros(calTextView.getText().toString()));
            ip.eval("result = " + StringUtils.insertDotsZeros(calTextView.getText().toString()));
            updateTextView(new DecimalFormat("#.##").format((double) ip.get("result")).replace(",", "."));
        } catch (Exception e) {
            Log.e("Calculator", "Exception: " + e.getMessage());
        }
    }

    private void negateResult() {
        String result = calTextView.getText().toString();
        if (result.charAt(0) != '-')
            updateTextView("-" + calTextView.getText());
        else updateTextView(result.substring(1, result.length()));
    }

    private void updateTextView(String s) {
        calTextView.setText(StringUtils.getSafeSubstring(s, MAX_TEXTVIEW_CHARS));
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("Obrot: ", calTextView.getText().toString().trim());
        savedInstanceState.putString("value", calTextView.getText().toString().trim());
    }
}