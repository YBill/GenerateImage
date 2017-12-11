package com.example.bill.generateimage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void handleGenerate(View view) {
        View myView = View.inflate(this, R.layout.layout_first, null);
        LinearLayout contentView = myView.findViewById(R.id.ll_content);
        Bitmap bitmap = Utils.generateBitmapByLinearLayout(this, contentView);
        Utils.saveBitmapToSD(bitmap, "/sdcard/generate/first.png");
    }
}
