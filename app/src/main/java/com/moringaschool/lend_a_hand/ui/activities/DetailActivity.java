package com.moringaschool.lend_a_hand.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.lend_a_hand.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.organizationNameTextView)
    TextView orgName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String organization = intent.getStringExtra("organizationName");

        ButterKnife.bind(this);
        orgName.setText(organization);
    }
}