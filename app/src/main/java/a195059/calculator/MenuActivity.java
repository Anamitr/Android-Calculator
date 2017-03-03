package a195059.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void simpleClick(View v) {
        Intent intent = new Intent(this, SimpleActivity.class);
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
