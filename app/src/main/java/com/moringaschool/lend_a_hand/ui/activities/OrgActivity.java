package com.moringaschool.lend_a_hand.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moringaschool.lend_a_hand.DAO.DAOOrganization;
import com.moringaschool.lend_a_hand.Model.Organizations;
import com.moringaschool.lend_a_hand.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrgActivity extends AppCompatActivity {

    @BindView(R.id.organizationNameEditTextView) EditText mOrganizationName;
    @BindView(R.id.organizationContactsEditTextView) EditText mOrganizationContacts;
    @BindView(R.id.organizationOpenHoursEditTextView) EditText mOrganizationOpenHours;
    @BindView(R.id.organizationDescriptionEditTextView) EditText mOrganizationDescription;
    @BindView(R.id.organizationJoinButton) Button mOrganizationJoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org);
        ButterKnife.bind(this);

        DAOOrganization dao = new DAOOrganization();

        mOrganizationJoin.setOnClickListener(v -> {
            Organizations org = new Organizations(mOrganizationName.getText().toString(),mOrganizationContacts.getText().toString(),mOrganizationOpenHours.getText().toString(),
                    mOrganizationDescription.getText().toString());
            dao.add(org).addOnSuccessListener(suc->
            {
                Toast.makeText(this, "Registration was successful", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });

        });

    }
}