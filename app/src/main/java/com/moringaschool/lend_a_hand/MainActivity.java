package com.moringaschool.lend_a_hand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moringaschool.lend_a_hand.ui.activities.LoginActivity;
import com.moringaschool.lend_a_hand.ui.activities.OrgActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.mainTextView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrgActivity.class));
            }
        });


    }
}