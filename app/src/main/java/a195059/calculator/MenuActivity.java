package a195059.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
//import android.view.ViewGroup.LayoutParams;


public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        Log.d("Height: ", Float.toString(dpHeight));

        Button bSimple = (Button) this.findViewById(R.id.bSimple);
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        Double marginTop = 0.078125*dpHeight;
        params.setMargins(0, marginTop.intValue(), 0, 0);
        bSimple.setLayoutParams(params);
    }

    public void simpleClick(View v) {
        Intent intent = new Intent(this, SimpleActivity.class);
        startActivity(intent);
    }

    public void advancedClick(View v) {
        Intent intent = new Intent(this, AdvancedActivity.class);
        startActivity(intent);
    }

    public void aboutClick(View v) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void exitClick(View v) {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
