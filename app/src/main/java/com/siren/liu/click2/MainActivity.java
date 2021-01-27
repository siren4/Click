package com.siren.liu.click2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.siren.liu.click.onclick.MultipleClickListener;
import com.siren.liu.click.repeat.RepeatClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_repeat).setOnClickListener(new View.OnClickListener() {
            @RepeatClick(delay = 1000)
            @Override
            public void onClick(View v) {
                Log.d("siren4", "+++repeatClick+++");
            }
        });

        findViewById(R.id.bt_multiple).setOnClickListener(new MultipleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Log.d("siren4", "+++onSingleClick+++");
            }

            @Override
            public void onDoubleClick(View view) {
                Log.d("siren4", "+++onDoubleClick+++");
            }

            @Override
            public int doubleMark() {
                return 300;
            }
        });
    }
}