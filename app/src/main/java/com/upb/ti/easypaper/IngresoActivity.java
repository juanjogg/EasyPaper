package com.upb.ti.easypaper;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
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


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class IngresoActivity extends AppCompatActivity{
    //final FirebaseDatabase fireBase = FirebaseDatabase.getInstance();

    private DatabaseReference dataBase = FirebaseDatabase.getInstance().getReference();
    private ImageView imgEng;
    private TextView txtPapelerias;
//    private Thread t1 = new Thread(this,"Thread Consulta");

 //   private Thread t2 = new Thread(this,"Thread Adapter");
    private RecyclerView rvPapelerias;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvManager;
    private ArrayList<Papeleria> papelerias;
    int i = 0;
    //private Semaphore semaphore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        //semaphore = new Semaphore(0);
        //fireBase.getInstance().setLogLevel(Logger.Level.DEBUG);

        //t1.start();
        //t2.start();
        papelerias = new ArrayList<>();

        consultarPapelerias(new Callback() {
            @Override
            public void onComplete(Papeleria papeleria) {
                i++;
                papelerias.add(papeleria);
                Log.i("Papelerias",String.valueOf(papelerias.size()));
                Log.d("Callback","Ingresé " + i + " Veces");
            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imgEng = findViewById(R.id.imgEngranaje);
        rvPapelerias = findViewById(R.id.rvPapelerias);
        rvPapelerias.setHasFixedSize(true);
        rvManager = new LinearLayoutManager(this);
        rvPapelerias.setLayoutManager(rvManager);
        txtPapelerias = findViewById(R.id.txtPapelerias);
        rvAdapter = new DataAdapter(this.papelerias);
        rvPapelerias.setAdapter(rvAdapter);
        Log.d("Fin","Ya termine el onCreate");
        //consularPapelerias(fireBase);
        //consultarPapelerias(fireBase);


        this.imgEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent conf = new Intent(IngresoActivity.this,ConfigActivity.class);
                startActivity(conf);
            }
        });

    }

/*    public void consularPapelerias(final FirebaseDatabase fireBase) {

        final DatabaseReference db = fireBase.getReference().child("Papelerias");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Papeleria papeleria = new Papeleria("","");
                //papelerias.add(papeleria);
                txtPapelerias.setText(dataSnapshot.getValue().toString());
                System.out.println(dataSnapshot.getValue().toString());
                Log.d("Info","Si entro normal");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error",databaseError.getDetails());
                Log.d("Info","Si entro en error");

            }
        });

    }

*/
    public void consultarPapelerias(final Callback callback){
        DatabaseReference db = this.dataBase.child("Papelerias");

        db.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                int b = 0;
                String nombre = "",ubicacion = "", disponibilidad = "";
                //Papeleria papeleria = new Papeleria(dataSnapshot.getKey().toString(),dataSnapshot.getValue().toString());
                String idPapeleria = dataSnapshot.getKey();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(b == 0){
                        disponibilidad = ds.getValue().toString();
                        b++;
                    }
                    else if(b == 1){
                        nombre = ds.getValue().toString();
                        b++;

                    }
                    else {
                        b = 0;
                        Log.i("Dispo",ds.getValue().toString());
                        ubicacion = ds.getValue().toString();
                    }


                }
                Papeleria papeleria = new Papeleria(nombre,ubicacion,idPapeleria,disponibilidad);
                callback.onComplete(papeleria);


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


/*    @Override
    public void run() {
        Thread t = Thread.currentThread();
        if(t.getName().equals("Thread Consulta")){
            try {
                semaphore.acquire();
                consultarPapelerias();
                semaphore.release();
                t.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            setRecyclerView();
        }

   }

    public void setRecyclerView(){
        rvPapelerias.setHasFixedSize(true);
        rvManager = new LinearLayoutManager(this);
        rvPapelerias.setLayoutManager(rvManager);
        txtPapelerias = findViewById(R.id.txtPapelerias);
        rvAdapter = new DataAdapter(this.papelerias);
        rvPapelerias.setAdapter(rvAdapter);
    }
    */


}
