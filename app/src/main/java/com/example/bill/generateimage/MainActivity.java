package com.example.bill.generateimage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleClick(View view) {
        Class cls = null;
        if (view.getId() == R.id.btn_1) {
            cls = FirstActivity.class;
        }
        goActivity(cls);
    }

    private void goActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

}
