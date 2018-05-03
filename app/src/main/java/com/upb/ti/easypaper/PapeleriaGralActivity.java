package com.upb.ti.easypaper;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PapeleriaGralActivity extends AppCompatActivity {
    private TextView nombrePap;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    final Papeleria papeleria = DataAdapter.papeleriaStac;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papeleria_gral);
        nombrePap = findViewById(R.id.nombrePap);
        mAuth = FirebaseAuth.getInstance();

    }

    public void obtenerServicios(){
        DatabaseReference sdb = this.db.child("Papelerias").child(papeleria.getIdPapeleria()).child("Servicios");
        sdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            updateUI(user);
        }
        else{
            nombrePap.setText(papeleria.getNombre());
        }
    }

    public void updateUI(FirebaseUser user){
        
    }
}
