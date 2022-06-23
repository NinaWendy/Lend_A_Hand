package com.moringaschool.lend_a_hand.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.lend_a_hand.Model.Organizations;
import com.moringaschool.lend_a_hand.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.organizationNameTextView)
    TextView orgName;
    @BindView(R.id.organizationDescriptionTextView)
    TextView orgdesc;
    @BindView(R.id.orgPhone)
    TextView orgContact;
    @BindView(R.id.hours)
    TextView orgHours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Organizations organization = (Organizations) intent.getSerializableExtra("details");


        ButterKnife.bind(this);
        orgName.setText(organization.getName());
        orgdesc.setText(organization.getDescription());
        orgContact.setText(organization.getContact());
        orgHours.setText(organization.getOpenHours());
    }
}