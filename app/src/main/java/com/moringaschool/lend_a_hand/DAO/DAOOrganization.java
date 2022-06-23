package com.moringaschool.lend_a_hand.DAO;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.lend_a_hand.Model.Organizations;

public class DAOOrganization {
    private DatabaseReference databaseReference;
    public DAOOrganization() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Organizations.class.getSimpleName());
    }

    // Insert New Vakue/Entry into database
    public Task<Void> add(Organizations org) {
        return databaseReference.push().setValue(org);
    }
}
