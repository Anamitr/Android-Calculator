package a195059.calculator;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import static android.R.attr.data;
import static android.R.attr.text;

public class SimpleActivity extends AppCompatActivity {
    public static final int TEXT_VIEW_SIZE = 200;
    public static final int FONT_SIZE = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_simple);
        setContentView(getTableWithAllRowsStretchedView());

        //TextView simpleView = (TextView) this.findViewById(R.id.SimpleView);
//        GridLayout simpleGrid = (GridLayout) this.findViewById(R.id.simpleGrid);
//        simpleView.setText(Integer.toString(simpleGrid.getWidth()));
//        Toast.makeText(this,Integer.toString(simpleGrid.getWidth()),
//                Toast.LENGTH_SHORT).show();
//        simpleGrid.
        //RelativeLayout simpleLayout = (RelativeLayout) this.findViewById(R.id.activity_simple);
//        GridLayout simpleGrid = new GridLayout(this);
//        simpleGrid.setColumnCount(4);
//        simpleGrid.setRowCount(5);
//        final LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//        simpleGrid.setLayoutParams(lp);
//        Toast.makeText(this,Integer.toString(simpleGrid.getWidth()), Toast.LENGTH_SHORT).show();
//
//
//        Button[] calculatorButtons = new Button[20];
//        for(Button button : calculatorButtons) {
//            button = new Button(this);
//            button.setTextSize(FONT_SIZE);
//            button.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT/4, LayoutParams.MATCH_PARENT/4));
//            //button.setHeight(simpleGrid.getHeight()/4);
//            button.setText("20");
//
//            simpleGrid.addView(button);
//        }
//
        //simpleLayout.addView(getTableWithAllRowsStretchedView());
        //LinearLayout lL = getTableWithAllRowsStretchedView();

    }

    public LinearLayout getTableWithAllRowsStretchedView() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.FILL_PARENT));
        tableLayout.setWeightSum(4);

        for (int i = 0; i < 4; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setGravity(Gravity.CENTER);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT, 1.0f));
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.FILL_PARENT, 1.0f));

            for (int j = 0; j < 4; j++) {
                Button button = new Button(this);
                final int buttonNumber = (j + i * 4);
                button.setText("" + buttonNumber);
                button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));

                tableRow.addView(button);
            }
            tableLayout.addView(tableRow);
        }

        TextView textView = new TextView(getApplicationContext());
        textView.setText("3454576");
        textView.setHeight(TEXT_VIEW_SIZE);
        textView.setBackgroundColor(Color.parseColor("#0099cc"));
        textView.setTextSize(FONT_SIZE);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setGravity(Gravity.CENTER | Gravity.RIGHT);

        linearLayout.addView(textView);
        linearLayout.addView(tableLayout);
        return linearLayout;
    }
}
