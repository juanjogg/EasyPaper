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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class IngresoActivity extends AppCompatActivity{
    //final FirebaseDatabase fireBase = FirebaseDatabase.getInstance();

    private DatabaseReference dataBase = FirebaseDatabase.getInstance().getReference();
    private ImageView imgEng;
    private ImageView imgActualizar;
    private TextView txtPapelerias;
    private FirebaseAuth mAuth;

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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imgEng = findViewById(R.id.imgLogin);
        imgActualizar = findViewById(R.id.imgReload);
        rvPapelerias = findViewById(R.id.rvPapelerias);
        rvPapelerias.setHasFixedSize(true);
        rvManager = new LinearLayoutManager(this);
        rvPapelerias.setLayoutManager(rvManager);
        txtPapelerias = findViewById(R.id.txtPapelerias);
        rvAdapter = new DataAdapter(this.papelerias);
        rvPapelerias.setAdapter(rvAdapter);
        Log.d("Fin","Ya termine el onCreate");
        mAuth = FirebaseAuth.getInstance();
        this.imgActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });
        



        this.imgEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent conf = new Intent(IngresoActivity.this,LoginActivity.class);
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

*/
    public void consultarPapelerias(final Callback callback){
        DatabaseReference db = this.dataBase.child("Papelerias");

        db.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                int b = 0;
                String nombre = "",ubicacion = "", disponibilidad = "", uid = "", horario = "";
                //Papeleria papeleria = new Papeleria(dataSnapshot.getKey().toString(),dataSnapshot.getValue().toString());
                String idPapeleria = dataSnapshot.getKey();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    switch (ds.getKey()){
                        case "nombre":
                            nombre = ds.getValue().toString();
                        break;
                        case "disponibilidad":
                            disponibilidad = ds.getValue().toString();
                        break;
                        case "ubicacion":
                            ubicacion = ds.getValue().toString();
                        break;
                        case "uid":
                            uid = ds.getValue().toString();
                        break;
                        case "horario":
                            horario = ds.getValue().toString();
                        break;
                    }
                }
                Papeleria papeleria = new Papeleria(nombre,ubicacion,horario,idPapeleria,disponibilidad,uid);
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

    @Override
    public void onStart(){
        super.onStart();


        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            updateUI(user);
        }


    }

    public void updateUI(FirebaseUser user){
        this.imgEng.setImageResource(R.drawable.logout);
        this.imgEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(getIntent());
            }
        });
    }

    





}
                                                                                       