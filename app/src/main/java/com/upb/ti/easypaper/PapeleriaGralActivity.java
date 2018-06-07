package com.upb.ti.easypaper;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
    private String uid, pid;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    final Papeleria papeleria = DataAdapter.papeleriaStac;
    FirebaseAuth mAuth;
    TextView txtCrearpapeleria;
    Button edDocumentos, edServicios, servicios, documentos, dispAlta, dispMedia, dispBaja;
    RecyclerView rvDetalles;
    RecyclerView.Adapter rvAdapter;
    RecyclerView.LayoutManager rvManager;
    ConstraintLayout clEditar;
    //String datos[] = {"Ubicacion: "+papeleria.getUbicacion(),"Disponibilidad: "+papeleria.getDisponibilidad(),"Horario: "+papeleria.getHorario()};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papeleria_gral);
        nombrePap = findViewById(R.id.nombrePap);
        mAuth = FirebaseAuth.getInstance();
        txtCrearpapeleria = findViewById(R.id.txtCrearPapeleria);
        txtCrearpapeleria.setVisibility(View.INVISIBLE);
        edDocumentos = findViewById(R.id.btnEDocumentos);
        edServicios = findViewById(R.id.btnEditarServicios);
        edServicios.setVisibility(View.INVISIBLE);
        edDocumentos.setVisibility(View.INVISIBLE);
        servicios = findViewById(R.id.btnservicios);
        documentos = findViewById(R.id.btnDocumentos);
        rvDetalles = findViewById(R.id.rvDetalles);
        clEditar = findViewById(R.id.clEditar);
        clEditar.setVisibility(View.INVISIBLE);
        dispAlta = findViewById(R.id.dispAlta);
        dispBaja = findViewById(R.id.dispBaja);
        dispMedia = findViewById(R.id.dispMedia);

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
            String datos[] = {"Ubicacion: "+papeleria.getUbicacion(),"Disponibilidad: "+papeleria.getDisponibilidad(),"Horario: "+papeleria.getHorario()};

            nombrePap.setText(papeleria.getNombre());
            rvDetalles.setHasFixedSize(true);
            rvManager = new LinearLayoutManager(this);
            rvDetalles.setLayoutManager(rvManager);
            rvAdapter = new Adapter(datos);
            rvDetalles.setAdapter(rvAdapter);
        }
    }

    public void updateUI(FirebaseUser user){
        rvDetalles.setVisibility(View.INVISIBLE);
        clEditar.setVisibility(View.VISIBLE);
        obtenerPapeleria(user);
        this.txtCrearpapeleria.setVisibility(View.VISIBLE);
        edServicios.setVisibility(View.VISIBLE);
        edDocumentos.setVisibility(View.VISIBLE);
        servicios.setVisibility(View.INVISIBLE);
        documentos.setVisibility(View.INVISIBLE);
        this.txtCrearpapeleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intento);
            }
        });
        this.dispBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actDisponibilidad("Baja");
            }
        });
        this.dispMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actDisponibilidad("Media");
            }
        });
        this.dispAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actDisponibilidad("Alta");
            }
        });
    }

    public void obtenerPapeleria(FirebaseUser user){

        DatabaseReference reference = db.child("Papelerias");
        reference.orderByChild("uid").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pid = dataSnapshot.getKey();
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    switch (ds.getKey()){
                        case "nombre":
                            nombrePap.setText(ds.getValue().toString());
                            break;
                        /*case "disponibilidad":
                            disponibilidad = ds.getValue().toString();
                            break;
                        /*case "ubicacion":
                            ubicacion = ds.getValue().toString();
                            break;
                        case "uid":
                            uid = ds.getValue().toString();
                            break;
                        case "horario":
                            horario = ds.getValue().toString();
                            break;*/
                        case "uid":
                            uid = ds.getValue().toString();
                        break;
                    }
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

    public void actDisponibilidad(String value){
        DatabaseReference dataref = db.child("Papelerias").child(pid).child("disponibilidad");
        dataref.setValue(value);
    }
}
