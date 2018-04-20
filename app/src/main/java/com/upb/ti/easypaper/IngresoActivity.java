package com.upb.ti.easypaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

public class IngresoActivity extends AppCompatActivity {
    private String datos[] = new String[3];
    private DatabaseReference dataBase;
    private ImageView imgEng;
    private TextView txtPapelerias;
    private RecyclerView rvPapelerias;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        final FirebaseDatabase fireBase = FirebaseDatabase.getInstance();
        fireBase.getInstance().setLogLevel(Logger.Level.DEBUG);
        dataBase = FirebaseDatabase.getInstance().getReference();

        imgEng = findViewById(R.id.imgEngranaje);
        rvPapelerias = findViewById(R.id.rvPapelerias);
        rvPapelerias.setHasFixedSize(true);
        rvManager = new LinearLayoutManager(this);
        rvPapelerias.setLayoutManager(rvManager);
        txtPapelerias = findViewById(R.id.txtPapelerias);
        rvAdapter = new DataAdapter(this.datos);
        rvPapelerias.setAdapter(rvAdapter);
        datos[0] = "Hola";
        datos[1] = "Como te encuentras?";
        consularPapelerias(fireBase);


        this.imgEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent conf = new Intent(IngresoActivity.this,ConfigActivity.class);
                startActivity(conf);
            }
        });

    }

    public void consularPapelerias(final FirebaseDatabase fireBase) {
        final DatabaseReference db = fireBase.getReference().child("Nombre");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                datos[2] = dataSnapshot.getValue().toString();
                //txtPapelerias.setText(dataSnapshot.getValue().toString());
                Log.d("Info","Si entro normal");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error",databaseError.getDetails());
                Log.d("Info","Si entro en error");

            }
        });

    }
}
