package com.moringaschool.lend_a_hand.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.lend_a_hand.Adapter.RecyclerViewAdapter;
import com.moringaschool.lend_a_hand.Adapter.RecyclerViewInterface;
import com.moringaschool.lend_a_hand.Model.Organizations;
import com.moringaschool.lend_a_hand.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Organizations> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        recyclerView = findViewById(R.id.organizationList);
        databaseReference = FirebaseDatabase.getInstance().getReference("Organizations");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(this, HomeActivity.this, list);
        recyclerView.setAdapter(recyclerViewAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Organizations organizations = dataSnapshot.getValue(Organizations.class);
                    list.add(organizations);
                }
                recyclerViewAdapter.notifyItemInserted(list.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("details",list.get(position));
        startActivity(intent);

    }
}